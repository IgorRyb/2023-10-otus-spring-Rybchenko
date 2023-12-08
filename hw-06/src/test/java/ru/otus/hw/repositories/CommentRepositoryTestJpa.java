package ru.otus.hw.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw.models.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(CommentRepositoryJpa.class)
public class CommentRepositoryTestJpa {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName(" Должен найти комментарий по id")
    void shouldCommentById() {
        var expectedComment = em.find(Comment.class, 1L);
        assertThat(expectedComment).isNotNull();
        var actualComment = commentRepository.findById(1L);
        assertThat(actualComment).isPresent().get()
                .isNotNull().usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    @DisplayName(" Должен удалить комментарий по id")
    void shouldDeleteById() {
        var firestComment = em.find(Comment.class, 1L);
        assertThat(firestComment).isNotNull();
        em.detach(firestComment);

        commentRepository.deleteById(1L);
        var deletedComment = em.find(Comment.class, 1L);
        assertThat(deletedComment).isNull();
    }

    @Test
    @DisplayName(" Должен изменить текст комментария")
    void shouldUpdateComment() {
        commentRepository.updateCommentById(1l, "new Text");
        var actualComment = commentRepository.findById(1L);
        assertThat(actualComment).isPresent().get().hasFieldOrPropertyWithValue("comment", "new Text");
    }
}
