package ru.otus.hw.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.hw.exceptions.EntityNotFoundException;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BookRepositoryJdbc implements BookRepository {

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Optional<Book> findById(long id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject(
                    "select b.id as id, b.title as title, a.id as a_id, " +
                            "a.full_name, g.id as g_id, g.name " +
                            "from books as b " +
                            "inner join authors as a on b.author_id = a.id " +
                            "inner join genres as g on b.genre_id = g.id " +
                            "where b.id = :id",
                    Map.of("id", id), new BookRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Book with id " + id + " not found");
        }

    }

    @Override
    public List<Book> findAll() {
        return jdbc.query("select b.id as id, b.title as title, a.id as a_id, " +
                        "a.full_name, g.id as g_id, g.name " +
                        "from books as b " +
                        "inner join authors as a on b.author_id = a.id " +
                        "inner join genres as g on b.genre_id = g.id ",
                new BookRowMapper());
    }

    @Override
    public Book save(Book book) {
        if (book.getId() != 0) {
            return update(book);
        } else {
            return insert(book);
        }
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from books where id = :id", Map.of("id", id));
    }

    private Book insert(Book book) {
        var keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());
        jdbc.update("insert into books (title, author_id, genre_id) " +
                "values (:title, :author_id, :genre_id)", params, keyHolder, new String[]{"id"});

        book.setId(keyHolder.getKeyAs(Long.class));
        return book;
    }

    private Book update(Book book) {
        jdbc.update("update books set title = :title, author_id = :author_id, genre_id = :genre_id where id = :id",
                Map.of("id", book.getId(), "title", book.getTitle(),
                        "author_id", book.getAuthor().getId(), "genre_id", book.getGenre().getId()));
        return book;
    }

    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            return new Book(id, title,
                    new Author(rs.getLong("a_id"), rs.getString("full_name")),
                    new Genre(rs.getLong("g_id"), rs.getString("name")));
        }
    }
}
