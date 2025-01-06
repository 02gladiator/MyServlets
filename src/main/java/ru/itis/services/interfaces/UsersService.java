package ru.itis.services.interfaces;

import ru.itis.dto.LogInForm;
import ru.itis.models.User;

import java.util.List;

public interface UsersService {
    List<User> viewAllUsers();
    String checkUserRole(LogInForm form);
}
