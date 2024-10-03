package by.tms.instaclone66.dao;

import by.tms.instaclone66.entity.Comment;
import by.tms.instaclone66.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CommentDaoJdbc implements CommentDao {
    private static final String INSERT_COMMENT = "INSERT INTO Comment(account_id,post_id,text,comment_date) VALUES (?,?,?,?)";
    private static final String DELETE_COMMENT = "DELETE FROM Comment WHERE id = ? AND account_id = ?";
    private static CommentDaoJdbc instance;

    private CommentDaoJdbc() {

    }

    public static CommentDaoJdbc getInstance() {
        if (instance == null) {
            instance = new CommentDaoJdbc();
        }
        return instance;
    }

    @Override
    public void save(int accountId, int postId, String textComment, LocalDate date) {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMENT);
            preparedStatement.setInt(1, accountId);
            preparedStatement.setInt(2, postId);
            preparedStatement.setString(3, textComment);
            preparedStatement.setDate(4, java.sql.Date.valueOf(date));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int accountId, int postId) {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMMENT);
            preparedStatement.setInt(1, accountId);
            preparedStatement.setInt(2, postId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Comment> selectAllCommentsByPostId(int postId) {
       /* try (Connection connection = JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }*/
        return null;
    }
}
