package ru.otus.hw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.models.Comment;
import ru.otus.hw.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Optional<Comment> findById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByBookId(String id) {
        return commentRepository.findByBookId(id);
    }
}
