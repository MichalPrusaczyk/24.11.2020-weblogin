package root.services;

import root.model.User;
import root.model.view.ChangePassData;
import root.model.view.UserRegistrationData;

public interface IUserService {
    User authenticate(User user);
    User updateUserData(User user);
    User updateUserPass(ChangePassData changePassData);
    boolean registerUser(UserRegistrationData userRegistrationData);
}
