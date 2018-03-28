package repository;

import model.Task;

import java.util.List;

// TODO review: jakie jest przeznaczenie tej klasy?
public interface UserFun {
    void logowanie(String login, String haslo);
    void wylogowywanie();
    List<Task> getZadania();
}
