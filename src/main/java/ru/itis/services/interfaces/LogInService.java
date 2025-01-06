package ru.itis.services.interfaces;

import ru.itis.dto.LogInForm;

import java.sql.SQLException;

public interface LogInService {
    boolean logIn(LogInForm form) throws SQLException;
    int userId(LogInForm form) throws SQLException;
}
