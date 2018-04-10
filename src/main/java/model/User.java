package model;

public class User {
    private int userId;
    // TODO review: po co inicjalizacja 'login' w tym miejscu?
    private String login="";
    private char[] password;
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

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
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

    public User(int userId, String login, char[] password, boolean admin) {
        this.userId=userId;
        this.login = login;
        this.password = password;
        this.admin = admin;
    }
}
