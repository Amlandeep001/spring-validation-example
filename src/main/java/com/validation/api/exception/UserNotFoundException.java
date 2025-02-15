package com.validation.api.exception;

public class UserNotFoundException extends Exception
{
	private static final long serialVersionUID = -2886201976347514212L;

	public UserNotFoundException(String message)
	{
		super(message);
	}
}
