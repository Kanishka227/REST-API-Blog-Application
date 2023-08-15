package com.blog_application.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_application.payloads.ApiResponse;
import com.blog_application.payloads.UserDto;
import com.blog_application.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	//create user
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userdto){
		UserDto createdUsr = userService.createUser(userdto);
		return new ResponseEntity<>(createdUsr,HttpStatus.CREATED);
	}
	
	//update user
	
	@PutMapping("/{uid}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto udto, @PathVariable int uid){
		
		UserDto updatedUser = this.userService.updateUser(udto, uid);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	//delete user
	
	@DeleteMapping("/{uid}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int uid){
		
		this.userService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Successfull",true),HttpStatus.OK);
	
	}
	
	//get all users
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAll(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	//get user by id
	
	@GetMapping("/{uid}")
	public ResponseEntity<UserDto> getUser(@PathVariable int uid){
		
		UserDto user = this.userService.getUserById(uid);
		return ResponseEntity.ok(user);
	}
}
