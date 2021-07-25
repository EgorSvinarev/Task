package com.svinarev.task.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDTO {
	
	private Integer id;
	@NotNull
	private String name;

	@Override
	public String toString() {
		return "{"
				+ "id: " + this.id + ", "
				+ "name: " + this.name
				+ "}";
	}
	
}
