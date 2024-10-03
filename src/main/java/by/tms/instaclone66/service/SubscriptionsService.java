package by.tms.instaclone66.service;

import by.tms.instaclone66.dao.SubscriptionsDaoJdbc;
import by.tms.instaclone66.entity.User;

import java.util.List;

public class SubscriptionsService {
    private static SubscriptionsService instance;
    private final SubscriptionsDaoJdbc subscriptionsDaoJdbc = SubscriptionsDaoJdbc.getInstance();

    private SubscriptionsService() {

    }

    public static SubscriptionsService getInstance() {
        if (instance == null) {
            instance = new SubscriptionsService();
        }
        return instance;
    }

    public List<User> showAllAuthors(int id) {
        return subscriptionsDaoJdbc.selectAllUnSubscribers(id);
    }

    public void subscribe(int idFollower, int idFollowing) {
        subscriptionsDaoJdbc.saveSubscription(idFollower, idFollowing);
    }

    public void unsubscribe(int idFollower, int idFollowing) {
        subscriptionsDaoJdbc.deleteSubscription(idFollowing, idFollower);
    }

    public List<User> showAllSubscriptions(int idFollowing) {
        return subscriptionsDaoJdbc.collectAllSubscriptions(idFollowing);
    }

    public List<User> showAllSubscribers(int idFollowing) {
        return subscriptionsDaoJdbc.collectAllSubscribers(idFollowing);
    }
}
