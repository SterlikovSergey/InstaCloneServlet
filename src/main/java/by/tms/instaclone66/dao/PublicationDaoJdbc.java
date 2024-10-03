package by.tms.instaclone66.dao;


import by.tms.instaclone66.entity.Comment;
import by.tms.instaclone66.entity.Like;
import by.tms.instaclone66.entity.Publication;
import by.tms.instaclone66.entity.User;
import by.tms.instaclone66.utils.JdbcUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublicationDaoJdbc implements PublicationDao {
    private static final String INSERT_POST = "INSERT INTO Post(account_id,content,description,post_date) VALUES (?,?,?,?)";
    private static final String SELECT_POST_LIKE_COMMENT = "SELECT P.id,P.content,P.description,P.post_date FROM Post P INNER JOIN Account A ON P.account_id = A.id WHERE A.id = ?";
    private static final String DELETE_POST_QUERY = "DELETE FROM Post WHERE id = ?";
    private static PublicationDaoJdbc instance;

    private PublicationDaoJdbc() {

    }

    public static PublicationDaoJdbc getInstance() {
        if (instance == null) {
            instance = new PublicationDaoJdbc();
        }
        return instance;
    }

    @Override
    public void save(Publication publication) throws IOException {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_POST);
            preparedStatement.setInt(1, publication.getUser().getId());
            preparedStatement.setBytes(2, publication.getContent());
            preparedStatement.setString(3, publication.getDescription());
            preparedStatement.setDate(4, java.sql.Date.valueOf(publication.getPostOfDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePost(int id) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_POST_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Publication> selectAllPostByAuthorId(User user) {
        List<Publication> publications = new ArrayList<>();
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POST_LIKE_COMMENT);
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Publication publication = new Publication(
                        resultSet.getInt(1),
                        resultSet.getBytes(2),
                        resultSet.getString(3),
                        resultSet.getDate(4).toLocalDate());
                publications.add(publication);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return publications;
    }

    @Override
    public List<Comment> selectAllCommentByPostId(User authorDto) {
        return null;
    }

    @Override
    public List<Like> selectAllLikesByPostId(User authorDto) {
        return null;
    }
}
