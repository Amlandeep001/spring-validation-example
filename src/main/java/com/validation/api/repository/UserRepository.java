package com.validation.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.validation.api.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
	User findByUserId(int id);
}
