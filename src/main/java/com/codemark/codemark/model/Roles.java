package com.codemark.codemark.model;

import javax.persistence.*;
import java.util.Set;
@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String roleName;
    @ManyToMany
    @JoinTable( name = "user_role",
                joinColumns = @JoinColumn(name = "user_login"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Users> users;

    public Roles(String roleName, Set<Users> users) {
        this.roleName = roleName;
    }

    public Roles() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
