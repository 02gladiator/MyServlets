package ru.itis.repositories.impl;

import ru.itis.models.Team;
import ru.itis.repositories.interfaces.TeamRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamRepositoryJdbcImpl implements TeamRepository {

    private final Connection connection;


    private static final String SQL_SAVE_TEAM = "INSERT INTO team VALUES (?,?,?,?,?)";
    private static final String SQL_GET_ALL_TEAM = "SELECT * FROM team";
    private static final String SQL_UPDATE_TEAM = "UPDATE team SET position_1 = ?, position_2 = ?, position_3= ?, position_4 = ?, user_id = ? WHERE team_id = ?";
    private static final String SQL_DELETE_TEAM = "DELETE FROM team WHERE team_id = ?";

    public TeamRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Team> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_TEAM);
            List<Team> teams = new ArrayList<>();
            while (resultSet.next()) {
                Team team = Team.builder()
                        .teamId(resultSet.getInt(1))
                        .firstPositionId(resultSet.getInt(2))
                        .secondPositionId(resultSet.getInt(3))
                        .thirdPositionId(resultSet.getInt(4))
                        .fourthPositionId(resultSet.getInt(5))
                        .userId(resultSet.getInt(6))
                        .build();
                teams.add(team);
            }
            return teams;
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void save(Team team) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_TEAM);
            ps.setInt(1, team.getFirstPositionId());
            ps.setInt(2, team.getSecondPositionId());
            ps.setInt(3, team.getThirdPositionId());
            ps.setInt(4, team.getFourthPositionId());
            ps.setInt(5, team.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void update(Team team) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_TEAM);
            ps.setInt(1, team.getFirstPositionId());
            ps.setInt(2, team.getSecondPositionId());
            ps.setInt(3, team.getThirdPositionId());
            ps.setInt(4, team.getFourthPositionId());
            ps.setInt(5, team.getUserId());
            ps.setInt(6, team.getTeamId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void remove(Team team) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_TEAM);
            ps.setInt(1, team.getTeamId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }

    }
}
