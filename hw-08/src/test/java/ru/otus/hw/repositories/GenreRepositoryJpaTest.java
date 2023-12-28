package ru.otus.hw.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName(" Должен вывести все жанры")
    void shouldFindAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        assertThat(genres).hasSize(3);
    }

    @Test
    @DisplayName(" Должен обновить жанр")
    void shouldSaveGenre() {
        Genre genre = genreRepository.findAll().get(0);
        assertThat(genre).isNotNull();
        genre.setName("story");
        genreRepository.save(genre);
        assertThat(genreRepository.findAll().get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("name","story");
    }

    @Test
    @DisplayName(" Должен удалить жанр по id")
    void shouldDeleteGenre() {
        Genre genre = genreRepository.findAll().get(0);
        assertThat(genre).isNotNull();
        genreRepository.deleteById(genre.getId());
        assertThat(genreRepository.findById(genre.getId())).isEmpty();
    }
}
