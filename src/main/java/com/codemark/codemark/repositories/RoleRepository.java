package com.codemark.codemark.repositories;

//Репозиторий для работы с ролями. Для поставленной задачи достаточно CrudRepository

import com.codemark.codemark.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Integer> {
}
