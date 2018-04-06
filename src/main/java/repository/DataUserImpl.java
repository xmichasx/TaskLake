package repository;

import model.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@Repository
public class DataUserImpl implements DataUser {
    private List<User> userList = new ArrayList<>();
    public DataUserImpl() {
        User user1= new User(1,"user1","haslo1".toCharArray(),false);
        userList.add(user1);
        User user2= new User(2,"user2","haslo2".toCharArray(),false);
        userList.add(user2);
        User admin= new User(3,"admin1","haslo1".toCharArray(),true);
        userList.add(admin);
    }
    @Override
    public User logIn(String login, char[] password) {
        for(User i : userList){
            if(i.getLogin().equals(login)){

                if( Arrays.equals(i.getPassword(),password)){
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
    @Override
    public boolean doAdmin(User user) {
        if(null==user || !user.isLoggedIn()||!user.isAdmin()){
            return true;
        }
        return false;
    }
}
