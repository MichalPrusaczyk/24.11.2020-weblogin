package root.database;

import root.model.User;

public interface IUserRepository {
    User authenticate(User user);
    User updateUserData(User user);
    User updateUserPass(User user);
    boolean checkIfLoginExist(String login);
    void addUser(User user);
}
