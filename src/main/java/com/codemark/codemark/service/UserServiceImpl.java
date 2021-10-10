package com.codemark.codemark.service;

import com.codemark.codemark.model.User;
import com.codemark.codemark.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(String login) {
        User user = null;
        Optional<User> optionalUser = userRepository.findById(login);
        if (optionalUser.isPresent()){
            user = optionalUser.get();
        }
        return user;  //possible null return
    }

    @Override
    public void deleteUser(String login) {
        userRepository.deleteById(login);
    }

}
