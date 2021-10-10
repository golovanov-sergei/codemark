package com.codemark.codemark.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonFilter("userFilter")
public class User {
    @Id
    @Column(name = "user_login")
    private String userLogin; //as a primary key???
    @Column(name = "user_name",nullable = false)
    private String userName;
    @Column(name = "password",nullable = false)
    private String password;
    @ManyToMany
    @JoinTable( name = "user_role",
                joinColumns = @JoinColumn(name = "user_login"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String login, String name, String password) {
        this.userLogin = login;
        this.userName = name;
        this.password = password;
    }

    @JsonProperty(value = "userName")
    public String geName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    @JsonProperty(value = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty(value = "roles")
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @JsonProperty(value = "userLogin")
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String login) {
        this.userLogin = login;
    }

    @Override
    public String toString() {
        return "Users{" +
                "login='" + userLogin + '\'' +
                ", name='" + userName + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User users = (User) o;

        return userLogin != null ? userLogin.equals(users.userLogin) : users.userLogin == null;
    }

    @Override
    public int hashCode() {
        return userLogin != null ? userLogin.hashCode() : 0;
    }
}
