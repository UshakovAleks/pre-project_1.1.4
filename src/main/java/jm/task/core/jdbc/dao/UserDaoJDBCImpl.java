package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final String CREATE_TABLE = "CREATE TABLE if not exists users (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,name VARCHAR(80),lastName VARCHAR(80),age TINYINT);";
    private final String DROP_TABLE = "DROP TABLE if exists users;";
    private final String SAVE_USER = "INSERT INTO users(name, lastName, age) VALUES(?,?,?);";
    private final String REMOVE_USER = "DELETE FROM users WHERE id = ?;";
    private final String SELECT_USERS = "SELECT * FROM users;";
    private final String CLEAN_TABLE = "DELETE FROM users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.getUtil().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.getUtil().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DROP_TABLE);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getUtil().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        Connection connection = Util.getUtil().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = Util.getUtil().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                String lastName = rs.getString(3);
                byte age = rs.getByte(4);
                var user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getUtil().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CLEAN_TABLE);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
