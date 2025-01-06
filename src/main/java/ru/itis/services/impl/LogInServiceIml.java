package ru.itis.services.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.LogInForm;
import ru.itis.repositories.interfaces.UserRepository;
import ru.itis.services.interfaces.LogInService;

import java.sql.SQLException;

public class LogInServiceIml implements LogInService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LogInServiceIml(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    @Override
    public boolean logIn(LogInForm form) throws SQLException {
        String email = form.getEmail();
        String password = form.getPassword();
        String passwordDB = userRepository.checkPassword(email);
        if(passwordEncoder.matches(password, passwordDB) && userRepository.findByEmail(email)) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int userId(LogInForm form) throws SQLException {
        return userRepository.getIdByEmail(form.getEmail());
    }
}
