package com.blog.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blog.entities.User;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.DtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return UserToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	private User DtoToUser(UserDto userDto) {
		User user=new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		user.setEmail(null);
		return user;
	}

	private UserDto UserToDto(User user) {
		UserDto dto = new UserDto();
		dto.setAbout(user.getAbout());
		dto.setEmail(user.getEmail());
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setPassword(user.getPassword());
		
		return dto;
	}
}
