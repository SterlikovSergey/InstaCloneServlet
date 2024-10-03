package by.tms.instaclone66.dao;

import by.tms.instaclone66.entity.User;

import java.util.List;

public interface SubscriptionsDao {
    List<User> selectAllUnSubscribers(int id);

    void saveSubscription(int idFollowing, int idFollower);

    List<User> collectAllSubscriptions(int idFollowing);

    List<User> collectAllSubscribers(int idFollowing);

    void deleteSubscription(int idFollowing, int idFollower);

}

