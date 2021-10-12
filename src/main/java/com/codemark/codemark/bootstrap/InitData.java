package com.codemark.codemark.bootstrap;

//Начальное заполнение таблиц

import com.codemark.codemark.model.Role;
import com.codemark.codemark.model.User;
import com.codemark.codemark.repositories.RoleRepository;
import com.codemark.codemark.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitData implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public InitData(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> users = (List<User>) userRepository.findAll();
        for (User user: users) {
            user.setRoles(null);
            userRepository.save(user);
        }
        roleRepository.deleteAll();
        userRepository.deleteAll();
        Role roleAdmin = new Role("Admin");
        Role roleOperator = new Role("Operator");
        Role roleManager = new Role("Manager");
        Role roleViewer = new Role("Viewer");
        User user1 = new User("imp","Sergei Golovanov","Strongpassword1");
        User user2 = new User("vik","Viktor Petrov","Strongpassword2");
        User user3 = new User("sam","Samuel Jackson","Strongpassword3");
        user1.getRoles().add(roleAdmin);
        user2.getRoles().add(roleViewer);
        user3.getRoles().add(roleManager);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleManager);
        roleRepository.save(roleOperator);
        roleRepository.save(roleViewer);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
