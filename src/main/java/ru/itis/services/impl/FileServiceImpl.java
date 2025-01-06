package ru.itis.services.impl;

import ru.itis.models.FileInfo;
import ru.itis.repositories.interfaces.FilesRepository;
import ru.itis.services.interfaces.FileService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class FileServiceImpl implements FileService {

    private final FilesRepository filesRepository;

    public FileServiceImpl(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @Override
    public void saveCharIcon(InputStream file, String originalFileName, String contentType, Long size, int charId) {
        FileInfo fileInfo = FileInfo.builder()
                .originalFileName(originalFileName)
                .storageFileName(UUID.randomUUID().toString())
                .size(size)
                .type(contentType)
                .build();
        try{
            Files.copy(file, Paths.get("D://games/MyProjects/MyServlets/src/main/resources/charIcon/" + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
            filesRepository.saveCharIcon(fileInfo, charId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveUserIcon(InputStream file, String originalFileName, String contentType, Long size) {

    }

    @Override
    public void saveWeaponIcon(InputStream file, String originalFileName, String contentType, Long size) {

    }

    @Override
    public void saveArtefactIcon(InputStream file, String originalFileName, String contentType, Long size) {

    }

    @Override
    public void saveEnemyIcon(InputStream file, String originalFileName, String contentType, Long size) {

    }

    @Override
    public void writeCharIconFromStorage(int charIconId, OutputStream outputStream) {

        FileInfo fileInfo = filesRepository.findCharIconById(charIconId);

        File file = new File("D://games/MyProjects/MyServlets/src/main/resources/charIcon/" + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]);

        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public FileInfo getFileInfo(int charIconId) {
        return filesRepository.findCharIconById(charIconId);
    }

}
