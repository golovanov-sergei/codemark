package com.codemark.codemark.controller;

//главный контроллер приложения

import com.codemark.codemark.exceptionhandler.Response;
import com.codemark.codemark.exceptionhandler.DatabaseException;
import com.codemark.codemark.exceptionhandler.UserSaveException;
import com.codemark.codemark.model.Role;
import com.codemark.codemark.model.User;
import com.codemark.codemark.service.RoleService;
import com.codemark.codemark.service.UserService;
import com.codemark.codemark.utils.StringUtils;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RESTController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    public MappingJacksonValue getAllUsers() {
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("roles");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", simpleBeanPropertyFilter);
        List<User> allUsers = userService.getAllUsers();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(allUsers);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @GetMapping("/users/{login}")
    public MappingJacksonValue getUser(@PathVariable String login) {
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAll();
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", simpleBeanPropertyFilter);
        User user = userService.getUser(login);
        if (user==null){
            throw new DatabaseException("User ("+login+") not found!");
        }
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @PostMapping("/users")
    public ResponseEntity<Response> saveUser(@RequestBody User user) {

        userService.validateUserData(user, true);
        userService.saveUser(user);
        Response response = new Response();
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<Response> updateUser(@RequestBody User user) {
        userService.validateUserData(user, false);
        String login=user.getUserLogin();
        if (userService.getUser(login)==null){
            throw new DatabaseException("User ("+login+") not found!");
        }

        for (Role roleIter:user.getRoles()) {
            if (roleService.getRole(roleIter.getId())==null){
                throw new DatabaseException("Role id=("+roleIter.getId()+") not found!");
            }
            
        }
        userService.saveUser(user);
        Response response = new Response();
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/users/{login}/roles")
    public User saveUserRole(@PathVariable String login, @RequestBody Set<Role> roles) {
        User user = userService.getUser(login);
        user.setRoles(roles);
        userService.saveUser(user);
        return user;
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        List<Role> allRoles = roleService.getAllRoles();
        return allRoles;
    }

    @GetMapping("/roles/{id}")
    public Role getRole(@PathVariable Integer id) {
        Role role = roleService.getRole(id);
        return role;
    }

    @PostMapping("/roles")
    public Role saveRole(@RequestBody Role role) {
        roleService.saveRole(role);
        return role;
    }

    @DeleteMapping("/users/{login}")
    public ResponseEntity<Response> deleteUser(@PathVariable String login){
        User user = userService.getUser(login);
        if (user==null){
            throw new DatabaseException("User ("+login+") not found!");
        }
        userService.deleteUser(login);
        Response response = new Response();
        response.setSuccess(true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Response> handleException(DatabaseException exception){
        Response response = new Response();
        response.setSuccess(false);
        response.setErrors(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<Response> handleException(UserSaveException exception){
        Response response = new Response();
        response.setSuccess(false);
        response.setErrors(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
