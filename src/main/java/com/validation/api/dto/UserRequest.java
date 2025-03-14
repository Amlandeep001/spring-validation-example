package com.validation.api.dto;

import com.validation.api.constant.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class UserRequest
{
	@NotNull(message = "id shouldn't be null")
	int id;

	@NotNull(message = "username shouldn't be null")
	String name;

	@Email(message = "invalid email address")
	String email;

	@Pattern(regexp = "^\\d{10}$", message = "invalid mobile number entered ")
	String mobile;

	@Enumerated(EnumType.STRING)
	Gender gender;

	@Min(18)
	@Max(60)
	int age;

	@NotBlank
	String nationality;
}
