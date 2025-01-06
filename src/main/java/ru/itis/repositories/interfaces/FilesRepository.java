package ru.itis.repositories.interfaces;

import ru.itis.models.FileInfo;

public interface FilesRepository extends CrudRepository<FileInfo> {
    void saveCharIcon(FileInfo file, int charId);
    FileInfo findCharIconById(int id);
    void saveUserIcon(FileInfo file);
    void saveWeaponIcon(FileInfo file);
    void saveArtefactIcon(FileInfo file);
    void saveEnemyIcon(FileInfo file);
}
