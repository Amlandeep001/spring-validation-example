package com.javatechie.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.api.dto.UserRequest;
import com.javatechie.api.entity.User;
import com.javatechie.api.exception.UserNotFoundException;
import com.javatechie.api.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController
{

	@Autowired
	private UserService service;

	@PostMapping("/signup")
	public ResponseEntity<User> saveUser(@RequestBody @Valid UserRequest userRequest)
	{
		return new ResponseEntity<>(service.saveUser(userRequest), HttpStatus.CREATED);
	}

	@GetMapping("/fetchAll")
	public ResponseEntity<List<User>> getAllUsers()
	{
		return ResponseEntity.ok(service.getALlUsers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable int id) throws UserNotFoundException
	{
		return ResponseEntity.ok(service.getUser(id));
	}
}
