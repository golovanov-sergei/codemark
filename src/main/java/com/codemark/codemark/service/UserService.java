package com.codemark.codemark.service;

//Контракт для реализации сервиса работы с пользователями

import com.codemark.codemark.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public void saveUser(User user);
    public User getUser(String login);
    public void deleteUser(String login);
}
