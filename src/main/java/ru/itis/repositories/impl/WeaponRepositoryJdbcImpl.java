package ru.itis.repositories.impl;

import ru.itis.models.Weapon;
import ru.itis.repositories.interfaces.WeaponRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeaponRepositoryJdbcImpl implements WeaponRepository {

    private final Connection connection;


    private static final String SQL_SAVE_WEAPON = "INSERT INTO weapon VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_GET_ALL_WEAPON = "SELECT * FROM weapon";
    private static final String SQL_UPDATE_WEAPON = "UPDATE weapon SET name = ? ,quality_id = ?, type_id = ?, baseatk = ?, 2ndStat = ?, passiveability = ?, upgradematerial_id = ? WHERE id = ?";
    private static final String SQL_DELETE_WEAPON = "DELETE FROM weapon WHERE weapon_id = ?";

    public WeaponRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Weapon> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_GET_ALL_WEAPON);
            List<Weapon> weapons = new ArrayList<>();
            while (rs.next()) {
                Weapon weapon = Weapon.builder()
                        .weaponId(rs.getInt(1))
                        .name(rs.getString(2))
                        .qualityId(rs.getInt(3))
                        .typeId(rs.getInt(4))
                        .baseATK(rs.getInt(5))
                        .secondStat(rs.getString(6))
                        .passiveAbility(rs.getString(7))
                        .upgradeMaterialId(rs.getInt(8))
                        .build();
                weapons.add(weapon);
            }

            return weapons;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void save(Weapon weapon) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_WEAPON);
            ps.setString(1,weapon.getName());
            ps.setInt(2, weapon.getQualityId());
            ps.setInt(3, weapon.getTypeId());
            ps.setInt(4,weapon.getBaseATK());
            ps.setString(5,weapon.getSecondStat());
            ps.setString(6,weapon.getPassiveAbility());
            ps.setInt(7,weapon.getUpgradeMaterialId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(Weapon weapon) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_WEAPON);
            ps.setString(1,weapon.getName());
            ps.setInt(2, weapon.getQualityId());
            ps.setInt(3, weapon.getTypeId());
            ps.setInt(4,weapon.getBaseATK());
            ps.setString(5,weapon.getSecondStat());
            ps.setString(6,weapon.getPassiveAbility());
            ps.setInt(7,weapon.getUpgradeMaterialId());
            ps.setInt(8,weapon.getWeaponId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void remove(Weapon weapon) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_WEAPON);
            ps.setInt(1,weapon.getWeaponId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
