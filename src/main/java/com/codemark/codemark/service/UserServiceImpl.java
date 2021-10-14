package com.codemark.codemark.service;

//Имплементация сервиса работы с пользователями

import com.codemark.codemark.exceptionhandler.UserSaveException;
import com.codemark.codemark.model.User;
import com.codemark.codemark.repositories.UserRepository;
import com.codemark.codemark.utils.StringUtils;
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

    @Override
    public void validateUserData(User user, Boolean isANewUser){
        if (user.getUserLogin()==null || StringUtils.isEmpty(user.getUserLogin())){
            throw new UserSaveException("Required field LOGIN not filled");
        }
        if (isANewUser && getUser(user.getUserLogin())!=null){
            throw new UserSaveException("User ("+user.getUserLogin()+") already exists");
        }
        if (StringUtils.isEmpty(user.getPassword())){
            throw new UserSaveException("Required field PASSWORD not filled");
        }
        if (StringUtils.passwordNotValid(user.getPassword())){
            throw new UserSaveException("Required field PASSWORD not valid");
        }
    }

}
