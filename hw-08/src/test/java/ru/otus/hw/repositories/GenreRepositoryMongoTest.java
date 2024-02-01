package ru.otus.hw.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.hw.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class GenreRepositoryMongoTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    @DisplayName(" Должен вывести все жанры")
    void shouldFindAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        assertThat(genres).hasSize(3);
    }

    @Test
    @DisplayName(" Должен обновить жанр")
    void shouldSaveGenre() {
        Genre genre = mongoOperations.findAll(Genre.class).get(0);
        assertThat(genre).isNotNull();
        genre.setName("story");
        genreRepository.save(genre);
        assertThat(mongoOperations.findAll(Genre.class).get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("name","story");
    }

    @Test
    @DisplayName(" Должен удалить жанр по id")
    void shouldDeleteGenre() {
        Genre genre = mongoOperations.findAll(Genre.class).get(0);
        assertThat(genre).isNotNull();
        genreRepository.deleteById(genre.getId());
        assertThat(mongoOperations.findById(genre.getId(), Genre.class)).isNull();
    }
}
