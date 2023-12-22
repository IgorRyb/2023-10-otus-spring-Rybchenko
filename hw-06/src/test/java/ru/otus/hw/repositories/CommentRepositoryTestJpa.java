package ru.otus.hw.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw.models.Book;
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
        var actualComment = em.find(Comment.class, 1L);
        assertThat(actualComment)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedComment);
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
    @DisplayName(" Должен обновить комментарий")
    void shouldUpdateComment() {
        Book book = new Book(1, "title", null, null);
        Comment expectedComment = new Comment(1, "new Text", book);
        commentRepository.save(expectedComment);
        var actualComment = em.find(Comment.class, 1L);
        assertThat(actualComment).hasFieldOrPropertyWithValue("comment", "new Text");
    }
}
