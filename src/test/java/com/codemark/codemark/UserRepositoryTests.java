package com.codemark.codemark;

import com.codemark.codemark.model.Role;
import com.codemark.codemark.model.User;
import com.codemark.codemark.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    TestEntityManager entityManager;

    @Test
    public void test() {

    }

    @Test
    public void testCreateRoles(){
        Role roleAdmin = new Role("Administrator");
        Role roleManager = new Role("Manager");
        Role roleOperator = new Role("Operator");
        entityManager.persist(roleAdmin);
        entityManager.persist(roleManager);
        entityManager.persist(roleOperator);
    }

    @Test
    public void testCreateUserWithRole() {
        Set<Role> roles = new HashSet<>();
        roles.add(entityManager.find(Role.class,1));
        User user = new User("imp","Sergei","StrongPassword");
        user.setRoles(roles);
        userRepository.save(user);
    }
}
