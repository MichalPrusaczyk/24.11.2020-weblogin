package root.database.impl;

import org.springframework.stereotype.Component;
import root.database.IUserRepository;
import root.model.User;

import java.util.ArrayList;
import java.util.List;

import static root.model.User.Role.ADMIN;
import static root.model.User.Role.USER;


public class ListUserRepositoryImpl implements IUserRepository {
    private final List<User> userList = new ArrayList<>();

    public ListUserRepositoryImpl() {
//        this.userList.add(new User("Michal","Prusaczyk","admin", "admin",ADMIN));
//        this.userList.add(new User("Adam","Prusaczyk","admin", "admin",USER));
    }

    @Override
    public User authenticate(User user) {
        for (User userFromDatabase : this.userList) {
            if (userFromDatabase.getLogin().equals(user.getLogin())) {
                if (userFromDatabase.getPass().equals(user.getPass())) {
                    return userFromDatabase;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public User updateUserData(User user) {
        for(User userFromDB : this.userList){
            if(userFromDB.getLogin().equals(user.getLogin())){
                userFromDB.setName(user.getName());
                userFromDB.setSurname(user.getSurname());
                return userFromDB;
            }
        }

        return null;
    }

    @Override
    public User updateUserPass(User user) {
        for(User userFromDB : this.userList){
            if(userFromDB.getLogin().equals(user.getLogin())){
                userFromDB.setPass(user.getPass());
                return userFromDB;
            }
        }


        return null;
    }
    @Override
    public boolean checkIfLoginExist(String login) {
        for(User userFromDB : this.userList) {
            if(userFromDB.getLogin().equals(login)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void addUser(User user) {
        this.userList.add(user);
    }
}
