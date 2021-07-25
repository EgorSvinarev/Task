package com.svinarev.task.converters;

import com.svinarev.task.dto.UserDTO;
import com.svinarev.task.entities.User;

public class UserConverter {

	public static UserDTO toDTO(User user) {
		return UserDTO.builder()
			.id(user.getId())
			.username(user.getUsername())
			.role(RoleConverter.toDTO(user.getRole()))
		.build();
	}
	
	public static User fromDTO(UserDTO user) {
		return User.builder()
			.id(user.getId())
			.username(user.getUsername())
			.password(user.getPassword())
			.role(RoleConverter.fromDTO(user.getRole()))
		.build();
	}
	
}
