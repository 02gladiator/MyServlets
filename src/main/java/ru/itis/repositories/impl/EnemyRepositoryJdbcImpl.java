package ru.itis.repositories.impl;

import ru.itis.models.Enemy;
import ru.itis.models.User;
import ru.itis.repositories.interfaces.EnemyRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnemyRepositoryJdbcImpl implements EnemyRepository {

    private final Connection connection;


    private static final String SQL_SAVE_ENEMY = "INSERT INTO enemy VALUES (?,?)";
    private static final String SQL_GET_ALL_ENEMY = "SELECT * FROM enemy";
    private static final String SQL_UPDATE_ENEMY = "UPDATE enemy SET name = ?, info = ? WHERE id = ?";
    private static final String SQL_DELETE_ENEMY = "DELETE FROM enemy WHERE enemy_id = ?";

    public EnemyRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Enemy> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_ENEMY);
            List<Enemy> enemies = new ArrayList<>();
            while (resultSet.next()) {
                Enemy enemy = Enemy.builder()
                        .enemyId(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .enemyDescription(resultSet.getString(3))
                        .build();
                enemies.add(enemy);
            }
            return enemies;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void save(Enemy enemy) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_ENEMY);
            ps.setString(1, enemy.getName());
            ps.setString(2, enemy.getEnemyDescription());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(Enemy enemy) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_ENEMY);
            ps.setString(1, enemy.getName());
            ps.setString(2, enemy.getEnemyDescription());
            ps.setInt(3, enemy.getEnemyId());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void remove(Enemy enemy) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_ENEMY);
            ps.setInt(1, enemy.getEnemyId());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new IllegalArgumentException(e);
        }
    }
}
