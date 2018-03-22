package model;

public class User {
    int userId;
    private String login="";
    private String password="";
    private boolean admin = false;
    private boolean loggedIn= false;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public User() {
    }

    public User(int userId, String login, String password, boolean admin) {
        this.userId=userId;
        this.login = login;
        this.password = password;
        this.admin = admin;
    }
}
