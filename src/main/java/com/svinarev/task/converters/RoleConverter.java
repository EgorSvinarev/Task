package com.svinarev.task.converters;

import com.svinarev.task.dto.RoleDTO;
import com.svinarev.task.entities.Role;

public class RoleConverter {

	public static RoleDTO toDTO(Role role) {
		if (role == null) {
			return RoleDTO.builder().build();
		}
		
		return RoleDTO.builder()
					.id(role.getId())
					.name(role.getName())
				.build();
	}
	
	public static Role fromDTO(RoleDTO role) {
		if (role == null) {
			return Role.builder().build();
		}
		
		return Role.builder()
					.id(role.getId())
					.name(role.getName())
				.build();
	}
	
}
