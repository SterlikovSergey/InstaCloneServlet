package by.tms.instaclone66.service;

import by.tms.instaclone66.dao.CommentDaoJdbc;
import by.tms.instaclone66.entity.Comment;

import java.time.LocalDate;
import java.util.List;

public class CommentService {
    private static CommentService instance;
    public final CommentDaoJdbc commentDaoJdbc = CommentDaoJdbc.getInstance();

    private CommentService() {

    }

    public static CommentService getInstance() {
        if (instance == null) {
            instance = new CommentService();
        }
        return instance;
    }

    public void create(int accountId, int postId, String textComment, LocalDate date) {
        commentDaoJdbc.save(accountId, postId, textComment, date);
    }

    public List<Comment> getAllCommentsByPostId(int postId) {
        return commentDaoJdbc.selectAllCommentsByPostId(postId);
    }

    public void delete(int accountId, int postId) {
        commentDaoJdbc.delete(accountId, postId);
    }
}
