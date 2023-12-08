package ru.otus.hw.commands;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw.converters.CommentConverter;
import ru.otus.hw.services.CommentService;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {

    private final CommentService commentService;

    private final CommentConverter commentConverter;

    @ShellMethod(value = "Find comment by id", key = "cid")
    public String findCommentById(long id) {
        return commentService.findById(id)
                .map(commentConverter::commentToString)
                .toString();
    }

    @ShellMethod(value = "Find comment by book id", key = "cbi")
    public String findCommentsByBookId(long id) {
        return commentService.findById(id)
                .map(commentConverter::commentToString)
                .toString();
    }

    @ShellMethod(value = "Delete comment by id", key = "dci")
    public void updateComment(long id) {
        commentService.deleteById(id);
    }

    @ShellMethod(value = "Update comment by id", key = "uci")
    public void updateComment(long id, String text) {
        commentService.updateCommentById(id, text);
    }

}
