package ru.otus.hw.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.hw.models.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class CommentRepositoryMongoTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    @DisplayName(" Должен удалить комментарий по id")
    void shouldDeleteById() {
        Comment comment = mongoOperations.findAll(Comment.class).get(0);
        assertThat(comment).isNotNull();
        commentRepository.deleteById(comment.getId());
        assertThat(mongoOperations.findById(comment.getId(), Comment.class)).isNull();
    }

    @Test
    @DisplayName(" Должен обновить комментарий")
    void shouldUpdateComment() {
        Comment comment = mongoOperations.findAll(Comment.class).get(0);
        assertThat(comment).isNotNull();
        comment.setComment("Отлично!");
        commentRepository.save(comment);
        assertThat(mongoOperations.findAll(Comment.class).get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("comment", "Отлично!");
    }
}
