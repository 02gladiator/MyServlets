package ru.itis.services.interfaces;

import ru.itis.dto.SignUpForm;

import java.sql.SQLException;

public interface SignUpService {
    void signUp(SignUpForm form) throws SQLException;
    boolean validate(String email) throws SQLException;
}
