package ru.itis.services.interfaces;

import ru.itis.models.FileInfo;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileService {
    void saveCharIcon(InputStream file, String originalFileName, String contentType, Long size, int charId);
    void saveUserIcon(InputStream file, String originalFileName, String contentType, Long size);
    void saveWeaponIcon(InputStream file, String originalFileName, String contentType, Long size);
    void saveArtefactIcon(InputStream file, String originalFileName, String contentType, Long size);
    void saveEnemyIcon(InputStream file, String originalFileName, String contentType, Long size);
    void writeCharIconFromStorage(int charIconId, OutputStream outputStream);
    FileInfo getFileInfo(int charIconId);

}
