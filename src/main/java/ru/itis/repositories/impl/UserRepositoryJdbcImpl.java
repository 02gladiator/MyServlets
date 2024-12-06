package ru.itis.repositories.impl;

import ru.itis.models.User;
import ru.itis.repositories.interfaces.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImpl implements UserRepository {

    private final Connection connection;


    private static final String SQL_SAVE_USER = "INSERT INTO user VALUES (?,?)";
    private static final String SQL_GET_ALL_USERS = "SELECT * FROM user";
    private static final String SQL_UPDATE_USER = "UPDATE user SET nickname = ?, email = ? WHERE id = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE user_id = ?";

    public UserRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() {
        try {
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
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_USER);
            ps.setString(1, user.getNickname());
            ps.setString(2,user.getEmail());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_USER);
            ps.setString(1, user.getNickname());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getUserId());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void remove(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_USER);
            ps.setInt(1, user.getUserId());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new IllegalArgumentException(e);
        }
    }
}
