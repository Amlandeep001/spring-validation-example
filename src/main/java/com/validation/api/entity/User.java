package com.validation.api.entity;

import com.validation.api.constant.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "USERS_TBL")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class User
{
	@Id
	// @GeneratedValue
	int userId;
	String name;
	String email;
	String mobile;

	@Enumerated(EnumType.STRING)
	Gender gender;

	int age;
	String nationality;
}
