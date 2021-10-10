package com.codemark.codemark.repositories;

import com.codemark.codemark.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
}
