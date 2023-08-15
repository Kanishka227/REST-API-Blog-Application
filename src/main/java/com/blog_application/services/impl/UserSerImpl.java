package com.blog_application.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog_application.exceptions.*;
import com.blog_application.entities.User;
import com.blog_application.payloads.UserDto;
import com.blog_application.repositories.UserRepository;
import com.blog_application.services.UserService;

@Service
public class UserSerImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto)
	{
		User u = this.DtoToUser(userDto);
		User savedUser = userRepo.save(u);
		return this.UserToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int user_id) {
		
		User user = this.userRepo.findById(user_id).orElseThrow(()-> new ResourceNotFound("User","id",user_id));
		user.setAbout(userDto.getAbout());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		
		User updatedUser = this.userRepo.save(user);
		return this.UserToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(int user_id) {
		
		User user = userRepo.findById(user_id).orElseThrow(()-> new ResourceNotFound("User","id",user_id));
		
		return this.UserToDto(user);
	}

	@Override
	public void deleteUser(int user_id) {
		User user = this.userRepo.findById(user_id).orElseThrow(()-> new ResourceNotFound("User","id",user_id));
		this.userRepo.delete(user);

	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user-> this.UserToDto(user)).collect(Collectors.toList());
		return userDtos;
	}
	
	
	private User DtoToUser(UserDto userDto) {
		User user = modelMapper.map(userDto,User.class);
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setName(userDto.getName());
		
		return user;
	}
	
	private UserDto UserToDto(User u) {
		UserDto udto = modelMapper.map(u, UserDto.class);
//		udto.setAbout(u.getAbout());
//		udto.setEmail(u.getEmail());
//		udto.setId(u.getId());
//		udto.setName(u.getName());
//		udto.setPassword(u.getPassword());
		
		return udto;
	}
	
	

}
