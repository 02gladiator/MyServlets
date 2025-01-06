package ru.itis.repositories.interfaces;

import ru.itis.models.User;

public interface UserRepository extends CrudRepository<User> {
    boolean findByEmail(String email);
    String checkPassword(String email);
    Integer getIdByEmail(String email);
    String getRoleByEmail(String email);
}
