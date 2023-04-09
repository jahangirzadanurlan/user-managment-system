package com.example.usermanagmentsystem.service;

import com.example.usermanagmentsystem.model.User;

import java.util.List;

public interface UserService {
    void registerUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    List<User> showUser();
    User showUserById(Long id);
}
