package ru.otus.hw.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class CommentRepositoryTestJpa {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName(" Должен удалить комментарий по id")
    void shouldDeleteById() {
        Comment comment = commentRepository.findAll().get(0);
        assertThat(comment).isNotNull();
        commentRepository.deleteById(comment.getId());
        assertThat(commentRepository.findById(comment.getId())).isEmpty();
    }

    @Test
    @DisplayName(" Должен обновить комментарий")
    void shouldUpdateComment() {
        Comment comment = commentRepository.findAll().get(0);
        assertThat(comment).isNotNull();
        comment.setComment("Отлично!");
        commentRepository.save(comment);
        assertThat(commentRepository.findAll().get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("comment", "Отлично!");
    }
}
