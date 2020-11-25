package root.database.impl;

import org.springframework.stereotype.Component;
import root.database.IUserRepository;
import root.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListUserRepositoryImpl implements IUserRepository {
    private final List<User> userList = new ArrayList<>();

    public ListUserRepositoryImpl() {
        this.userList.add(new User("Michal","Prusaczyk","admin", "admin"));
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
}