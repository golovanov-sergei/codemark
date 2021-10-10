package com.codemark.codemark;

import com.codemark.codemark.model.Role;
import com.codemark.codemark.model.User;
import com.codemark.codemark.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = true)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    TestEntityManager entityManager;

    @Test
    public void test() {

    }
    @Test
    public void createRolesTest(){
        Role roleAdmin = new Role("Administrator");
        Role roleManager = new Role("Manager");
        Role roleOperator = new Role("Operator");
        entityManager.persist(roleAdmin);
        entityManager.persist(roleManager);
        entityManager.persist(roleOperator);
    }
    @Test
    public void createUserWithRoleTest() {
        Set<Role> roles = new HashSet<>();
        roles.add(entityManager.find(Role.class,1));
        User user = new User("imp","Sergei","StrongPassword");
        user.setRoles(roles);
        userRepository.save(user);
        Assertions.assertThat(user.getUserLogin()).isEqualTo("imp");
    }
    @Test
    public void getUserTest() {
        User user = new User("imp","Sergei","StrongPassword");
        userRepository.save(user);
        user = userRepository.findById("imp").get();
        Assertions.assertThat(user.getUserLogin()).isEqualTo("imp");
    }
    @Test
    public void getListOfUserTest() {
        List<User> users = (List<User>) userRepository.findAll();
        Assertions.assertThat(users.size()).isGreaterThan(0);
    }
    @Test
    public void updateUserTest() {
        User user = new User("imp","Sergei","StrongPassword");
        userRepository.save(user);
        user.setUserName("Ivan");
        User updatedUser = userRepository.save(user);
        Assertions.assertThat(updatedUser.getUserName()).isEqualTo("Ivan");
    }
    @Test
    public void deleteUserTest(){
        User newUser = new User("imp","Sergei","StrongPassword");
        userRepository.save(newUser);
        User deleteUser = userRepository.findById("imp").get();
        userRepository.delete(deleteUser);
        User searchUser = null;
        if (userRepository.findById("imp").isPresent()){
            searchUser = userRepository.findById("imp").get();
        }
        Assertions.assertThat(searchUser).isNull();
    }
}
