package root.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import root.dao.IUserDAO;
import root.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAOImpl implements IUserDAO {

    @Autowired
    Connection connection;

    @Override
    public User getUserByLogin(String login) {
        try {
            String sql = "SELECT * FROM tuser WHERE login = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return this.mapResultSetToUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateUser(User user) {
        try {
            String sql = "UPDATE tuser SET name = ?, surname = ?, login = ?, pass = ?, role = ? WHERE id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPass());
            preparedStatement.setString(5, user.getRole().toString());
            preparedStatement.setInt(6, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void persistUser(User user) {
        try {
            String sql = "INSERT INTO tuser (name, surname, login, pass, role) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
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

    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setLogin(resultSet.getString("login"));
        user.setPass(resultSet.getString("pass"));
        user.setRole(User.Role.valueOf(resultSet.getString("role")));

        return user;
    }
}
