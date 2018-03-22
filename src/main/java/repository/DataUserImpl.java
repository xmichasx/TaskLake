package repository;

import model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class DataUserImpl implements DataUser {

    List<User> userList = new ArrayList<>();
    public DataUserImpl() {
        User user1= new User(1,"user1","haslo1",false);
        userList.add(user1);
        User user2= new User(2,"user2","haslo2",false);
        userList.add(user2);
        User admin= new User(3,"admin1","haslo1",true);
        userList.add(admin);
    }




    @Override
    public User logIn(String login, String password) {

        for(User i : userList){
            if(i.getLogin().equals(login)){
                if(i.getPassword().equals(password)){
                    i.setLoggedIn(true);
                    return i;
                }
            }

        }
        return null;

    }

    @Override
    public User logout() {
        User user= new User();
        return user;
    }

    @Override
    public List<User> listOfuser() {
        return userList;
    }
}
