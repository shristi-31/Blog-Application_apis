package com.blog.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;

@Service
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
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," id ",userId));
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		
		User updatedUser = this.userRepo.save(user);
		return UserToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," id ",userId));
		return UserToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> listUsers = this.userRepo.findAll();
		List<UserDto> list = new ArrayList<>();
		for(User user:listUsers) {
			UserDto uDto = UserToDto(user);
			list.add(uDto);
		}
		return list;
	}

	@Override
	public void deleteUser(Integer id) {
		this.userRepo.deleteById(id);
		
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
