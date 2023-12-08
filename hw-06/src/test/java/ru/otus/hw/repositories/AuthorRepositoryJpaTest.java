package ru.otus.hw.repositories;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;
import ru.otus.hw.models.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AuthorRepositoryJpa.class)
public class AuthorRepositoryJpaTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName(" Должен находить автора по его id")
    void shouldFindAuthorById() {
        var optionalActualAuthor = authorRepository.findById(1L);
        var expectedAuthor = em.find(Author.class, 1L);
        assertThat(optionalActualAuthor).isPresent()
                .get().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName(" Должен находить всех авторов")
    void shouldFindAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        assertThat(authors).hasSize(3);
    }
}
