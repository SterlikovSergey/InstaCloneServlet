package by.tms.instaclone66.dao;

import by.tms.instaclone66.entity.User;
import by.tms.instaclone66.utils.JdbcUtils;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoJdbc implements UserDao {

    private static final String INSERT_QUERY = "INSERT INTO Account(username,email,password,avatar,bio,registration_date)" +
            " VALUES (?,?,?,?,?,?)";
    private static final String SELECT_AUTHOR_BY_EMAIL_QUERY = "SELECT * FROM Account WHERE email = ?";
    private static UserDaoJdbc instance;


    private UserDaoJdbc() {

    }

    public static UserDaoJdbc getInstance() {
        if (instance == null) {
            instance = new UserDaoJdbc();
        }
        return instance;
    }

    @Override
    public void save(User user) throws IOException {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setBytes(4, user.getAvatar());
            preparedStatement.setString(5, user.getBio());
            preparedStatement.setDate(6, java.sql.Date.valueOf(user.getRegistrationOfDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JdbcUtils.printSQLException(e);
        }
    }

    @Override
    public Optional<User> findAuthorByEmail(String email) {
        User user = new User();
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUTHOR_BY_EMAIL_QUERY);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setAvatar(resultSet.getBytes(5));
                user.setBio(resultSet.getString(6));
                user.setRegistrationOfDate(resultSet.getDate(7).toLocalDate());
            }
        } catch (SQLException e) {
            JdbcUtils.printSQLException(e);
        }
        if (user.getEmail() != null) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
