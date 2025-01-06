package ru.itis.repositories.impl;

import ru.itis.models.Artifacts;
import ru.itis.repositories.interfaces.ArtifactsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtifactsRepositoryJdbcImpl implements ArtifactsRepository {

    private final Connection connection;

    private static final String SQL_SAVE_ARTIFACTS = "INSERT INTO artifacts VALUES (?,?,?)";
    private static final String SQL_GET_ALL_ARTIFACTS = "SELECT * FROM artifacts";
    private static final String SQL_UPDATE_ARTIFACTS = "UPDATE artifacts SET first_bonus = ?, second_bonus = ?, name = ? WHERE id = ?";
    private static final String SQL_DELETE_ARTIFACTS = "DELETE FROM artifacts WHERE artifacts_id = ?";

    public ArtifactsRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Artifacts> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_ARTIFACTS);
            List<Artifacts> artifacts = new ArrayList<>();
            while (resultSet.next()) {
                Artifacts artifact = Artifacts.builder()
                        .artifactId(resultSet.getInt(1))
                        .firstSetBonus(resultSet.getString(2))
                        .secondSetBonus(resultSet.getString(3))
                        .artifactName(resultSet.getString(4))
                        .build();
                artifacts.add(artifact);
            }
            return artifacts;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void save(Artifacts artifacts) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_ARTIFACTS);
            ps.setString(1, artifacts.getFirstSetBonus());
            ps.setString(2, artifacts.getSecondSetBonus());
            ps.setString(3, artifacts.getArtifactName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void update(Artifacts artifacts) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_ARTIFACTS);
            ps.setString(1, artifacts.getFirstSetBonus());
            ps.setString(2, artifacts.getSecondSetBonus());
            ps.setString(3, artifacts.getArtifactName());
            ps.setInt(4, artifacts.getArtifactId());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void remove(Artifacts artifacts) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_ARTIFACTS);
            ps.setInt(1, artifacts.getArtifactId());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new IllegalArgumentException(e);
        }

    }
}
