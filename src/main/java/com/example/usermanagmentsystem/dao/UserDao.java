package com.example.usermanagmentsystem.dao;

import com.example.usermanagmentsystem.model.User;

import java.util.List;

public interface UserDao {
    boolean registerUser(User user);
    User selectUser(Long id);
    boolean updateUser(User user);
    boolean deleteUser(Long id);
    List<User> showUser();

}
