package repository;

import model.Task;

import java.util.List;

public interface UserFun {
    void logowanie(String login, String haslo);
    void wylogowywanie();
    List<Task> getZadania();
}
