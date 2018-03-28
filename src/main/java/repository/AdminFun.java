package repository;

import model.User;
import model.Task;

// TODO review: jakie jest przeznaczenie tej klasy?
public interface AdminFun extends UserFun  {
    void dodajZadanie(String nazwa, String status, String opis, User user);
    Task wyswietlZadanie(int ID);
    void zmienStatusZadania(int ID);
}
