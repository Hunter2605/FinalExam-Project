package model;

public class User extends Base {
    private String username;
    private String password;
    private String userType;

    public User() {}

    public User(int id, String username, String password, String userType) {
        super(id);
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }

    }
