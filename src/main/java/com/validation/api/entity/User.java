package com.validation.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	String gender;
	int age;
	String nationality;
}
