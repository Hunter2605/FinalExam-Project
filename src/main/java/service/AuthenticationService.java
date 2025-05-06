package service;

import dao.UserDB;
import model.User;
import java.sql.SQLException;

public class AuthenticationService {
    private UserDB userDB;

    public AuthenticationService() {
        this.userDB = new UserDB();
    }

    public User authenticate (String username, String password) throws ClassNotFoundException {
        try {
            return userDB.authenticateUser(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getUserId(String username) {
        try {
            return userDB.getUserId(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}