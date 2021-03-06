package com.xmichasx.tasklake.repository;

import com.xmichasx.tasklake.model.User;
import java.util.List;

public interface DataUser {
    User logIn(String login, char[] password);
    User logout();
    List<User> listOfuser();
    boolean doAdmin(User user);
}
