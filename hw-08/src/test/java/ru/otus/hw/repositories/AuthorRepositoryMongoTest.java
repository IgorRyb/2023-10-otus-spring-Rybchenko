package ru.otus.hw.repositories;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.hw.models.Author;


import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class AuthorRepositoryMongoTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    @DisplayName(" Должен находить всех авторов")
    void shouldFindAllAuthors() {
        assertThat(authorRepository.findAll()).hasSize(3);
    }

    @Test
    @DisplayName(" Должен сохранять нового автора")
    void shouldSaveAuthor() {
        Author expectedAuthor = new Author("1", "Bulgakov");
        authorRepository.save(expectedAuthor);
        assertThat(mongoOperations.findById("1", Author.class).getFullName())
                .isEqualTo("Bulgakov");
    }
}