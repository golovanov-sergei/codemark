package com.codemark.codemark.model;

import java.util.Set;

public class Roles {
    private String roleName;
    private Set<Users> users;

    public Roles(String roleName, Set<Users> users) {
        this.roleName = roleName;
    }

    public Roles() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }
}
