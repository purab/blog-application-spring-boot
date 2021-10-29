package in.purabtech.service;

import in.purabtech.model.Comment;
import in.purabtech.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;


    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
