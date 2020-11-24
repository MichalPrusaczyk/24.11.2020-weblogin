package root.database;

import root.model.User;

public interface IUserRepository {
    User authenticate(User user);
}
