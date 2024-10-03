package by.tms.instaclone66.dao;

import by.tms.instaclone66.entity.Comment;

import java.time.LocalDate;
import java.util.List;

public interface CommentDao {
    void save(int accountId, int postId, String textComment, LocalDate date);

    void delete(int accountId, int postId);

    List<Comment> selectAllCommentsByPostId(int postId);
}
