package ru.itis.repositories.impl;

import ru.itis.models.User;
import ru.itis.repositories.interfaces.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImpl implements UserRepository {

    private final DataSource dataSource;


    private static final String SQL_SAVE_USER = "INSERT INTO users (nickname, email, password) VALUES (?,?,?)";
    private static final String SQL_GET_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_UPDATE_USER = "UPDATE users SET nickname = ?, email = ? WHERE id = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE user_id = ?";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SQL_SELECT_PASSWORD_BY_EMAIL = "SELECT password FROM users WHERE email = ?";
    private static final String SQL_SELECT_ID_BY_EMAIL = "SELECT user_id FROM users WHERE email = ?";
    private static final String SQL_SELECT_ROLE_BY_EMAIL = "SELECT role FROM users WHERE email = ?";

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll() {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_USERS);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = User.builder()
                        .userId(resultSet.getInt(1))
                        .nickname(resultSet.getString(2))
                        .email(resultSet.getString(3))
                        .role(resultSet.getString(4))
                        .build();
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void save(User user) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_USER);
            ps.setString(1, user.getNickname());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(User user) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_USER);
            ps.setString(1, user.getNickname());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void remove(User user) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_USER);
            ps.setInt(1, user.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean findByEmail(String email) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return false;
    }

    @Override
    public String checkPassword(String email) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_PASSWORD_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public Integer getIdByEmail(String email) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ID_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return -1;
    }

    @Override
    public String getRoleByEmail(String email) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ROLE_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return "";
    }
}
