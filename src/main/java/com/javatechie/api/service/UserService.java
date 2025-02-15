package com.javatechie.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javatechie.api.dto.UserRequest;
import com.javatechie.api.entity.User;
import com.javatechie.api.exception.UserNotFoundException;
import com.javatechie.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService
{
	private final UserRepository repository;

	public User saveUser(UserRequest userRequest)
	{
		User user = User.build(userRequest.getId(), userRequest.getName(), userRequest.getEmail(),
				userRequest.getMobile(), userRequest.getGender(), userRequest.getAge(), userRequest.getNationality());
		return repository.save(user);
	}

	public List<User> getALlUsers()
	{
		return repository.findAll();
	}

	public User getUser(int id) throws UserNotFoundException
	{
		User user = repository.findByUserId(id);
		if(user != null)
		{
			return user;
		}
		else
		{
			throw new UserNotFoundException("user not found with id : " + id);
		}
	}
}
