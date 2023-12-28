package ru.otus.hw.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class BookRepositoryMongoTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private CommentRepository commentRepository;

    private List<Author> authorsDb;

    private List<Genre> genreDb;

    private List<Book> bookDb;

    @BeforeEach
    void initDb() {
        authorsDb = getAuthorsDb();
        genreDb = getGenresDb();
        bookDb = getBooksDb();
    }


    @DisplayName(" Должен найти книгу по id")
    @Test
    void shouldFindBookById() {
        var expectedBook = bookDb.get(0);
        var actualBook = bookRepository.findById(expectedBook.getId());
        assertThat(actualBook)
                .isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName(" Должен сохранить новую книгу")
    @Test
    void shouldSaveBook() {
        var expectedBook = new Book(null, "title", authorsDb.get(0), genreDb.get(0));
        var savedBook = bookRepository.save(expectedBook);
        assertThat(savedBook).isNotNull()
                .usingRecursiveComparison().isEqualTo(expectedBook);
      }

    @DisplayName( "Должен удалить книгу по id")
    @Test
    void shouldDeleteBookById() {
        var expectedBook = bookDb.get(0);
        assertThat(expectedBook).isNotNull();
        bookRepository.deleteById(expectedBook.getId());
        assertThat(bookRepository.findById(expectedBook.getId())).isEmpty();
    }

    private List<Author> getAuthorsDb() {
        return authorRepository.findAll();
    }

    private List<Genre> getGenresDb() {
        return genreRepository.findAll();
    }

    private List<Book> getBooksDb() {
        return bookRepository.findAll();
    }
}
