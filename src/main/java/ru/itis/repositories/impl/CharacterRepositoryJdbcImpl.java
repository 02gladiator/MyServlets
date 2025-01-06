package ru.itis.repositories.impl;

import ru.itis.models.Character;
import ru.itis.repositories.interfaces.CharacterRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterRepositoryJdbcImpl implements CharacterRepository {

    private final DataSource dataSource;


    private static final String SQL_SAVE_CHARACTER = "INSERT INTO character(name, weapon, element, region, info) VALUES (?,?,?,?,?)";
    private static final String SQL_GET_ALL_CHARACTER = "SELECT * FROM character";
    private static final String SQL_UPDATE_CHARACTER = "UPDATE character SET name = ? ,weapon = ?,element = ?, region = ?, info = ?, WHERE character_id = ?";
    private static final String SQL_DELETE_CHARACTER = "DELETE FROM character WHERE character_id = ?";
    private static final String SQL_GET_CHARACTER_ID = "SELECT character_id FROM character WHERE name = ?";

    public CharacterRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Character> findAll() {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_GET_ALL_CHARACTER);
            List<Character> characters = new ArrayList<>();
            while (rs.next()) {
                Character character = Character.builder()
                        .characterId(rs.getInt(1))
                        .characterName(rs.getString(2))
                        .weapon(rs.getString(3))
                        .element(rs.getString(4))
                        .region(rs.getString(5))
                        .characterDescription(rs.getString(6))
                        .build();
                characters.add(character);
            }
            return characters;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void save(Character character) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_CHARACTER);
            ps.setString(1, character.getCharacterName());
            ps.setString(2, character.getWeapon());
            ps.setString(3, character.getElement());
            ps.setString(4, character.getRegion());
            ps.setString(5, character.getCharacterDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(Character character) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_CHARACTER);
            ps.setString(1, character.getCharacterName());
            ps.setString(2, character.getWeapon());
            ps.setString(3, character.getElement());
            ps.setString(4, character.getRegion());
            ps.setString(5, character.getCharacterDescription());
            ps.setInt(6, character.getCharacterId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void remove(Character character) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_CHARACTER);
            ps.setInt(1, character.getCharacterId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public int getCharIdByName(String name) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_GET_CHARACTER_ID);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return -1;
    }
}
