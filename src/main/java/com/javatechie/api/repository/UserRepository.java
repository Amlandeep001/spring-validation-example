package com.javatechie.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.api.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
	User findByUserId(int id);
}
