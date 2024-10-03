package by.tms.instaclone66.service;

import by.tms.instaclone66.dao.UserDaoJdbc;
import by.tms.instaclone66.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AuthorService {
    private static final String EMAIL_ALREADY_REGISTERED_MSG = "Пользователь с таким email уже зарегистрирован !";
    private static final String REGISTRATION_COMPLETED_SUCCESSFULLY_MSG = "Регистрация прошла успешно ! ";
    private static AuthorService instance;
    UserDaoJdbc authorDaoJdbc = UserDaoJdbc.getInstance();

    private AuthorService() {

    }

    public static AuthorService getInstance() {
        if (instance == null) {
            instance = new AuthorService();
        }
        return instance;
    }


    public void create(User user) throws IOException {
        authorDaoJdbc.save(user);
    }

    public Optional<User> getAuthorByEmail(String email) throws IOException {
        return authorDaoJdbc.findAuthorByEmail(email);
    }

    public List<User> printingAuthorData() {
        return null;
    }
}
