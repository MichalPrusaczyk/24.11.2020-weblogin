package root.dao;

import root.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);
    void updateUser(User user);
    void persistUser(User user);
}
