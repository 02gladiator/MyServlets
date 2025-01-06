package ru.itis.services.impl;

import ru.itis.dto.LogInForm;
import ru.itis.models.User;
import ru.itis.repositories.interfaces.UserRepository;
import ru.itis.services.interfaces.UsersService;

import java.util.List;

public class UsersServiceIml implements UsersService {

    private final UserRepository userRepository;

    public UsersServiceIml(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> viewAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String checkUserRole(LogInForm user) {
        return userRepository.getRoleByEmail(user.getEmail());
    }
}
