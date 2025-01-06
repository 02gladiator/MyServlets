package ru.itis.services.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.SignUpForm;
import ru.itis.models.User;
import ru.itis.repositories.interfaces.UserRepository;
import ru.itis.services.interfaces.SignUpService;

import java.sql.SQLException;

public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void signUp(SignUpForm form) throws SQLException {
        User user = User.builder()
                .nickname(form.getNickname())
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .build();
        userRepository.save(user);
    }

    @Override
    public boolean validate(String email) throws SQLException {
        return userRepository.findByEmail(email);
    }
}
