package by.tms.instaclone66.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountService {
    private static final String PATH = "jdbc:mysql://localhost:3306/HOSPITAL";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final String DELETE_ACCOUNT_QUERY = "DELETE * FROM Account WHERE Id = ?";
    private static AccountService instance;

    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }

    public void deleteAccount(int id) {
        try (Connection connection = DriverManager.getConnection(PATH, LOGIN, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT_QUERY)) {
            preparedStatement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
