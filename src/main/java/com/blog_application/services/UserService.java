package com.blog_application.services;

import java.util.List;

import com.blog_application.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, int user_id);
	UserDto getUserById(int user_id);
	void deleteUser(int user_id);
	List<UserDto> getAllUsers();
}
