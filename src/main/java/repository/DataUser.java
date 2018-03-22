package repository;

import model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DataUser {
    User logIn(String login, String password);
    User logout();
    List<User> listOfuser();
}
