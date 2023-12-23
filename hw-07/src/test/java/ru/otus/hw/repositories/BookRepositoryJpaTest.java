package ru.otus.hw.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName(" Должен найти книгу по id")
    @Test
    void shouldFindBookById() {
        var optionalActualBook = bookRepository.findById(1L);
        var expectedBook = em.find(Book.class, 1L);
        assertThat(optionalActualBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName(" Должен найти все книги")
    @Test
    void shouldFindAllBooks() {
        var firstBook = em.find(Book.class, 1L);
        var secondBook = em.find(Book.class, 2L);
        var thirdBook = em.find(Book.class, 3L);

        List<Book> expectedBooks = List.of(firstBook, secondBook, thirdBook);

        var books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(3).containsAll(expectedBooks);
    }

    @DisplayName(" Должен сохранить измененную книгу")
    @Test
    void shouldSaveBook() {
        var expectedBook = new Book(1L, "title", new Author(), new Genre());
        assertThat(em.find(Book.class, 1L))
                .isNotEqualTo(expectedBook);

        var savedBook = bookRepository.save(expectedBook);
        assertThat(em.find(Book.class, 1L))
                .isEqualTo(savedBook);
    }

    @DisplayName( "Должен удалить книгу по id")
    @Test
    void shouldDeleteBookById() {
        assertThat(em.find(Book.class, 1L)).isNotNull();

        bookRepository.deleteById(1L);
        var actualBook = em.find(Book.class, 1L);
        assertThat(actualBook).isNull();
    }
}
