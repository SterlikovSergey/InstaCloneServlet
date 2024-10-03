package by.tms.instaclone66.dao;

import by.tms.instaclone66.entity.User;
import by.tms.instaclone66.utils.JdbcUtils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionsDaoJdbc implements SubscriptionsDao {

    private static final String SELECT_UNSUBSCRIBED = "SELECT A.* FROM Account A LEFT JOIN Follower F ON A.id = F.follower_id AND F.following_id = ? WHERE A.id <> ? AND F.follower_id IS NULL";
    private static final String SELECT_SUBSCRIBE = "SELECT A.* FROM Account A INNER JOIN Follower F on A.id = F.follower_id WHERE F.following_id = ?";
    private static final String SELECT_SUBSCRIBERS = "SELECT A.* FROM Account A LEFT JOIN Follower F ON A.id = F.following_id AND F.follower_id = ? WHERE A.id <> ? AND F.follower_id IS NOT NULL";
    private static final String INSERT_FOLLOWING = "INSERT INTO Follower(follower_id,following_id) VALUES (?,?)";
    private static final String DELETE_SUBSCRIPTION = "DELETE FROM Follower WHERE following_id = ? AND follower_id = ?";
    private static SubscriptionsDaoJdbc instance;

    private SubscriptionsDaoJdbc() {

    }

    public static SubscriptionsDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SubscriptionsDaoJdbc();
        }
        return instance;
    }

    @Override
    public List<User> selectAllUnSubscribers(int id) {
        List<User> allAuthors = new ArrayList<>();
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_UNSUBSCRIBED);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getBytes("avatar"), // Изменено на getBytes
                        resultSet.getString("bio"),
                        resultSet.getDate("registration_date").toLocalDate());
                allAuthors.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allAuthors;
    }

    @Override
    public void saveSubscription(int idFollower, int idFollowing) {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FOLLOWING);
            preparedStatement.setInt(1, idFollower);
            preparedStatement.setInt(2, idFollowing);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> collectAllSubscriptions(int idFollowing) {
        List<User> subscriptions = new ArrayList<>();
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBSCRIBE);
            preparedStatement.setInt(1, idFollowing);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getBytes("avatar"), // Изменено на getBytes
                        resultSet.getString("bio"),
                        resultSet.getDate("registration_date").toLocalDate());
                subscriptions.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subscriptions;
    }

    @Override
    public List<User> collectAllSubscribers(int idFollowing) {
        List<User> subscribers = new ArrayList<>();
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBSCRIBERS);
            preparedStatement.setInt(1, idFollowing);
            preparedStatement.setInt(2, idFollowing);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getBytes("avatar"), // Изменено на getBytes
                        resultSet.getString("bio"),
                        resultSet.getDate("registration_date").toLocalDate());
                subscribers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subscribers;
    }

    @Override
    public void deleteSubscription(int idFollowing, int idFollower) {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUBSCRIPTION);
            preparedStatement.setInt(1, idFollowing);
            preparedStatement.setInt(2, idFollower);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
