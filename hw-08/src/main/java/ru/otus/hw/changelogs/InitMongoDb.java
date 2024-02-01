package ru.otus.hw.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Comment;
import ru.otus.hw.models.Genre;
import ru.otus.hw.repositories.AuthorRepository;
import ru.otus.hw.repositories.BookRepository;
import ru.otus.hw.repositories.CommentRepository;
import ru.otus.hw.repositories.GenreRepository;

@ChangeLog(order = "001")
public class InitMongoDb {

    private Author firstAuthor;

    private Author secondAuthor;

    private Author thirdAuthor;

    private Genre firstGenre;

    private Genre secondGenre;

    private Genre thirdGenre;

    private Book firstBook;

    private Book secondBook;

    private Book thirdBook;

    @ChangeSet(order = "000", id = "dropDb", author = "igorRyb", runAlways = true)
    public void dropDB(MongoDatabase mongo) {
        mongo.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "igorRyb", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        firstAuthor = repository.save(new Author(null, "Dostoevsky"));
        secondAuthor = repository.save(new Author(null, "Pelevin"));
        thirdAuthor = repository.save(new Author(null, "Sorokin"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "igorRyb", runAlways = true)
    public void initGenres(GenreRepository repository) {
        firstGenre = repository.save(new Genre(null, "Novel"));
        secondGenre = repository.save(new Genre(null, "Fiction"));
        thirdGenre = repository.save(new Genre(null, "Fantasy"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "igorRyb", runAlways = true)
    public void initBooks(BookRepository repository) {
        firstBook = repository.save(new Book(null, "Demons", firstAuthor, firstGenre));
        secondBook = repository.save(new Book(null, "Generation P", secondAuthor, secondGenre));
        thirdBook = repository.save(new Book(null, "Blizzard", thirdAuthor, thirdGenre));
    }

    @ChangeSet(order = "004", id = "initComments", author = "igorRybs", runAlways = true)
    public void initComment(CommentRepository repository) {
        repository.save(new Comment(null, "Comment_1", firstBook));
        repository.save(new Comment(null, "Comment_2", secondBook));
        repository.save(new Comment(null, "Comment_3", thirdBook));
    }


}
