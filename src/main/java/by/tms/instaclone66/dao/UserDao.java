package by.tms.instaclone66.dao;

import by.tms.instaclone66.entity.User;

import java.io.IOException;
import java.util.Optional;

public interface UserDao {
    void save(User user) throws IOException;

    Optional<User> findAuthorByEmail(String email);
}
