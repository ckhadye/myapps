package com.ck.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ck.entities.User;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer>{

//	TODO: Use Optional wrapper
	User findByUserName(String userName);
}
