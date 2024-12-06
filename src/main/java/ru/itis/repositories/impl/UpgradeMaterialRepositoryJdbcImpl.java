package ru.itis.repositories.impl;

import ru.itis.models.UpgradeMaterial;
import ru.itis.repositories.interfaces.UpgradeMaterialRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UpgradeMaterialRepositoryJdbcImpl implements UpgradeMaterialRepository {

    private final Connection connection;


    private static final String SQL_SAVE_UPGRADEMATERIAL = "INSERT INTO upgradematerial VALUES (?,?,?)";
    private static final String SQL_GET_ALL_UPGRADEMATERIAL = "SELECT * FROM upgradematerial";
    private static final String SQL_UPDATE_UPGRADEMATERIAL = "UPDATE upgradematerial SET enemy_id = ?, info = ?, name = ? WHERE id = ?";
    private static final String SQL_DELETE_UPGRADEMATERIAL = "DELETE FROM upgradematerial WHERE upgradematerial_id = ?";

    public UpgradeMaterialRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<UpgradeMaterial> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_UPGRADEMATERIAL);
            List<UpgradeMaterial> upgradeMaterials = new ArrayList<>();
            while (resultSet.next()) {
                UpgradeMaterial upgradeMaterial = UpgradeMaterial.builder()
                        .upgradeMaterialId(resultSet.getInt(1))
                        .enemyId(resultSet.getInt(2))
                        .upgradeMaterialDescription(resultSet.getString(3))
                        .upgradeMaterialName(resultSet.getString(4))
                        .build();
                upgradeMaterials.add(upgradeMaterial);
            }
            return upgradeMaterials;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void save(UpgradeMaterial upgradeMaterial) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_UPGRADEMATERIAL);
            ps.setInt(1, upgradeMaterial.getEnemyId());
            ps.setString(2, upgradeMaterial.getUpgradeMaterialDescription());
            ps.setString(3, upgradeMaterial.getUpgradeMaterialName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(UpgradeMaterial upgradeMaterial) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_UPGRADEMATERIAL);
            ps.setInt(1, upgradeMaterial.getEnemyId());
            ps.setString(2, upgradeMaterial.getUpgradeMaterialDescription());
            ps.setString(3, upgradeMaterial.getUpgradeMaterialName());
            ps.setInt(4,upgradeMaterial.getUpgradeMaterialId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void remove(UpgradeMaterial upgradeMaterial) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_UPGRADEMATERIAL);
            ps.setInt(1, upgradeMaterial.getUpgradeMaterialId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
