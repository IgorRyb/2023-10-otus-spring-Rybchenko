package ru.otus.hw.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class BookRepositoryMongoTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoOperations mongoOperations;

    private List<Book> bookDb;

    @BeforeEach
    void initDb() {
        bookDb = getBooksDb();
    }


    @DisplayName(" Должен найти книгу по id")
    @Test
    void shouldFindBookById() {
        var expectedBook = bookDb.get(0);
        var actualBook = bookRepository.findById(expectedBook.getId());
        assertThat(actualBook)
                .isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName(" Должен сохранить новую книгу")
    @Test
    void shouldSaveBook() {
        var expectedBook = new Book(null, "title",
                new Author("0","New author"), new Genre("0", "New genre"));
        var savedBook = bookRepository.save(expectedBook);
        assertThat(savedBook).isNotNull()
                .usingRecursiveComparison().isEqualTo(expectedBook);
      }

    @DisplayName( "Должен удалить книгу по id")
    @Test
    void shouldDeleteBookById() {
        var expectedBook = bookDb.get(0);
        assertThat(expectedBook).isNotNull();
        bookRepository.deleteById(expectedBook.getId());
        assertThat(mongoOperations.findById(expectedBook.getId(), Book.class)).isNull();
    }

    private List<Book> getBooksDb() {
        return bookRepository.findAll();
    }
}
