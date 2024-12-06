package ru.itis.repositories.impl;

import ru.itis.models.Character;
import ru.itis.models.Weapon;
import ru.itis.repositories.interfaces.CharacterRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterRepositoryJdbcImpl implements CharacterRepository {
    private final Connection connection;


    private static final String SQL_SAVE_CHARACTER = "INSERT INTO character VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_GET_ALL_CHARACTER = "SELECT * FROM character";
    private static final String SQL_UPDATE_CHARACTER = "UPDATE character SET name = ? ,weapon_id = ?, atrifacts_id = ?, element_id = ?, region_id = ?, upgradematerial_id = ?, info = ?, baseatk = ?, basehp = ? WHERE id = ?";
    private static final String SQL_DELETE_CHARACTER = "DELETE FROM character WHERE character_id = ?";

    public CharacterRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Character> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_GET_ALL_CHARACTER);
            List<Character> characters = new ArrayList<>();
            while (rs.next()) {
                Character character = Character.builder()
                        .characterId(rs.getInt(1))
                        .characterName(rs.getString(2))
                        .weaponId(rs.getInt(3))
                        .artefactId(rs.getInt(4))
                        .elementId(rs.getInt(5))
                        .regionId(rs.getInt(6))
                        .upgradeMaterialId(rs.getInt(7))
                        .characterDescription(rs.getString(8))
                        .baseATK(rs.getInt(9))
                        .baseHP(rs.getInt(10))
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
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_CHARACTER);
            ps.setString(1, character.getCharacterName());
            ps.setInt(2, character.getWeaponId());
            ps.setInt(3, character.getArtefactId());
            ps.setInt(4, character.getElementId());
            ps.setInt(5, character.getRegionId());
            ps.setInt(6, character.getUpgradeMaterialId());
            ps.setString(7, character.getCharacterDescription());
            ps.setInt(8, character.getBaseATK());
            ps.setInt(9, character.getBaseHP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(Character character) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_CHARACTER);
            ps.setString(1, character.getCharacterName());
            ps.setInt(2, character.getWeaponId());
            ps.setInt(3, character.getArtefactId());
            ps.setInt(4, character.getElementId());
            ps.setInt(5, character.getRegionId());
            ps.setInt(6, character.getUpgradeMaterialId());
            ps.setString(7, character.getCharacterDescription());
            ps.setInt(8, character.getBaseATK());
            ps.setInt(9, character.getBaseHP());
            ps.setInt(10, character.getCharacterId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void remove(Character character) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_CHARACTER);
            ps.setInt(1, character.getCharacterId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
