package com.codemark.codemark.repositories;

//Репозиторий для работы с пользователями. Для поставленной задачи достаточно CrudRepository

import com.codemark.codemark.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
}
