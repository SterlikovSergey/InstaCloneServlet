package by.tms.instaclone66.dao;

import by.tms.instaclone66.entity.*;

import java.io.IOException;
import java.util.List;

public interface PublicationDao {
    void save(Publication publication) throws IOException;

    List<Publication> selectAllPostByAuthorId(User user);

    List<Comment> selectAllCommentByPostId(User user);

    List<Like> selectAllLikesByPostId(User user);

    void deletePost(int id);
}