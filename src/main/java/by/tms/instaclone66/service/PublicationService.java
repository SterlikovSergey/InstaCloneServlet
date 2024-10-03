package by.tms.instaclone66.service;

import by.tms.instaclone66.dao.PublicationDaoJdbc;
import by.tms.instaclone66.entity.*;

import java.io.IOException;
import java.util.List;

public class PublicationService {
    private static PublicationService instance;
    private final PublicationDaoJdbc postDaoJdbc = PublicationDaoJdbc.getInstance();

    private PublicationService() {

    }

    public static PublicationService getInstance() {
        if (instance == null) {
            instance = new PublicationService();
        }
        return instance;
    }

    public void create(Publication publication) throws IOException {
        postDaoJdbc.save(publication);
    }

    public List<Publication> getPostsByAuthorId(User user) {
        return postDaoJdbc.selectAllPostByAuthorId(user);
    }
}
