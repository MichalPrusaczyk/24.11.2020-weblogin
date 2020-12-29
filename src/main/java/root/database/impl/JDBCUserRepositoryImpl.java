package root.database.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.database.IUserRepository;
import root.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBCUserRepositoryImpl implements IUserRepository {

    @Autowired
    private Connection connection;

    @Override
    public User authenticate(User user) {
        User userFromDb = getUser(user.getLogin());

        if(userFromDb != null && userFromDb.getPass().equals(user.getPass())) {
            return userFromDb;
        }
        return null;
    }

    @Override
    public User updateUserData(User user) {
        try {
            String SQL = "UPDATE tuser SET name=?, surname=? WHERE login=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.executeUpdate();

            return this.getUser(user.getLogin());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User updateUserPass(User user) {
        try {
            String SQL = "UPDATE tuser SET pass=? WHERE login=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getPass());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.executeUpdate();

            return this.getUser(user.getLogin());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean checkIfLoginExist(String login) {
        try {
            String SQL = "SELECT * FROM tuser WHERE login=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void addUser(User user) {
        try {
            String SQL = "INSERT INTO tuser (name, surname, login, pass, role) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPass());
            preparedStatement.setString(5, user.getRole().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUser(String login) {
        try {
            String SQL = "SELECT * FROM tuser WHERE login=?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);

            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()) {
                return null;
            }

            User userFromDb = new User();
            userFromDb.setId(resultSet.getInt("id"));
            userFromDb.setName(resultSet.getString("name"));
            userFromDb.setSurname(resultSet.getString("surname"));
            userFromDb.setLogin(resultSet.getString("login"));
            userFromDb.setPass(resultSet.getString("pass"));
            userFromDb.setRole(User.Role.valueOf(resultSet.getString("role")));

            return userFromDb;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
