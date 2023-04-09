package com.example.usermanagmentsystem.service.impl;

import com.example.usermanagmentsystem.dao.UserDao;
import com.example.usermanagmentsystem.model.User;
import com.example.usermanagmentsystem.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public void registerUser(User user) {
        userDao.registerUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> showUser() {
        return userDao.showUser();
    }

    @Override
    public User showUserById(Long id) {
        return userDao.selectUser(id);
    }
}
