package ru.itis.repositories.impl;

import ru.itis.models.FileInfo;
import ru.itis.repositories.interfaces.FilesRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class FileRepositoryJdbcImpl implements FilesRepository {

    private final DataSource dataSource;

    private final static String SQL_INSERT_CHAR_ICON = "insert into charicon (storage_file_name, original_file_name, type, size, char_id) values (?, ?, ?, ?,?)";
    private final static String SQL_FIND_CHAR_ICON = "select * from charicon where char_id = ?";

    public FileRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveCharIcon(FileInfo file, int charId) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_INSERT_CHAR_ICON);
            ps.setString(1, file.getStorageFileName());
            ps.setString(2, file.getOriginalFileName());
            ps.setString(3, file.getType());
            ps.setLong(4, file.getSize());
            ps.setInt(5, charId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileInfo findCharIconById(int id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_FIND_CHAR_ICON);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FileInfo file = new FileInfo();
                file.setStorageFileName(rs.getString(2));
                file.setOriginalFileName(rs.getString(3));
                file.setType(rs.getString(4));
                file.setSize(rs.getLong(5));
                return file;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void saveUserIcon(FileInfo file) {

    }

    @Override
    public void saveWeaponIcon(FileInfo file) {

    }

    @Override
    public void saveArtefactIcon(FileInfo file) {

    }

    @Override
    public void saveEnemyIcon(FileInfo file) {

    }

    @Override
    public List<FileInfo> findAll() {
        return null;
    }

    @Override
    public void save(FileInfo entity) {

    }

    @Override
    public void update(FileInfo entity) {

    }

    @Override
    public void remove(FileInfo entity) {

    }
}
