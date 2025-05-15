package com.validation.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.validation.api.constant.Gender;
import com.validation.api.dto.UserRequest;
import com.validation.api.entity.User;
import com.validation.api.exception.UserNotFoundException;
import com.validation.api.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest
{
	@Mock
	private UserService userService;
	@InjectMocks
	private UserController userController;

	private User user;
	private UserRequest userRequest;

	@BeforeEach
	void setUp()
	{
		user = User.build(1, "John Doe", "john.doe@example.com", "1234567890", Gender.Male, 25, "American");
		userRequest = UserRequest.builder()
				.id(1)
				.name("John Doe")
				.email("john.doe@example.com")
				.mobile("1234567890")
				.gender(Gender.Male)
				.age(25)
				.nationality("American")
				.build();
	}

	@Test
	@DisplayName("saveUser should return 201 Created when user is valid")
	void saveUserShouldReturnCreatedWhenUserIsValid()
	{
		when(userService.saveUser(any(UserRequest.class))).thenReturn(user);

		ResponseEntity<User> response = userController.saveUser(userRequest);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(user.getName(), response.getBody().getName());
		verify(userService, times(1)).saveUser(any(UserRequest.class));
	}

	@Test
	@DisplayName("getAllUsers should return 200 OK with list of users")
	void getAllUsersShouldReturnOkWithListOfUsers()
	{
		List<User> users = Arrays.asList(user, User.build(2, "Ahaan Pandey", "ahaan.pandey@example.com", "0123456789", Gender.Male, 25, "Indian"));
		when(userService.getAllUsers()).thenReturn(users);

		ResponseEntity<List<User>> response = userController.getAllUsers();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(2, response.getBody().size());
		verify(userService, times(1)).getAllUsers();
	}

	@Test
	@DisplayName("getUser should return 200 OK when user is found")
	void getUserShouldReturnOkWhenUserIsFound() throws UserNotFoundException
	{
		final int userId = 1;
		when(userService.getUser(userId)).thenReturn(user);

		ResponseEntity<User> response = userController.getUser(userId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(user.getUserId(), response.getBody().getUserId());
		verify(userService, times(1)).getUser(userId);
	}

	@Test
	@DisplayName("getUser should throw UserNotFoundException when user is not found")
	void getUserShouldReturnNotFoundWhenUserIsNotFound() throws UserNotFoundException
	{
		final int userId = 99;
		when(userService.getUser(userId)).thenThrow(new UserNotFoundException("User not found"));

		assertThrows(UserNotFoundException.class, () -> userController.getUser(userId));
		verify(userService, times(1)).getUser(userId);
	}
}