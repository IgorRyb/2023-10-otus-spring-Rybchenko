package ru.otus.hw.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw.models.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
public class GenreRepositoryJpaTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName(" Должен вывести все жанры")
    void shouldFindAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        assertThat(genres).hasSize(3);
    }

    @Test
    @DisplayName(" Должен вернуть жанр по id")
    void shouldFindGenreById() {
        Optional<Genre> genre = genreRepository.findById(1L);
        assertThat(em.find(Genre.class, 1L)).isNotNull()
                .hasFieldOrPropertyWithValue("name", "Genre_1");
    }

    @Test
    @DisplayName(" Должен обновить жанр")
    void shouldSaveGenre() {
        var firstGenre = new Genre(1L, "new Name to Genre");
        assertThat(em.find(Genre.class, firstGenre.getId()))
                .isNotEqualTo(firstGenre);

        var actualGenre = genreRepository.save(firstGenre);
        assertThat(em.find(Genre.class, actualGenre.getId()))
                .isEqualTo(actualGenre);
    }

    @Test
    @DisplayName(" Должен удалить жанр по id")
    void shouldDeleteGenre() {
        assertThat(em.find(Genre.class, 1L)).isNotNull();
        genreRepository.deleteById(1L);
        var deleteGenre = em.find(Genre.class, 1L);
        assertThat(deleteGenre).isNull();
    }
}
