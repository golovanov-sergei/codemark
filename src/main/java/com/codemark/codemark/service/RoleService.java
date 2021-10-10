package com.codemark.codemark.service;

import com.codemark.codemark.model.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getAllRoles();
    public void saveRole(Role role);
    public Role getRole(Integer id);
    public void deleteRole(Integer id);
}
