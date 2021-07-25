package com.svinarev.task.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

	private Long id;
	@NotNull
	@Size(min = 5, max = 30)
	private String username;
	@NotNull
	private String password;
	@NotNull
	private RoleDTO role;
	
	@Override
	public String toString() {
		return "{"
				+ "id: " + this.id + ", "
				+ "username: " + this.username + ", "
				+ "role: " + this.role
				+ "}";
	}
	
}
