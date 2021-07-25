package com.svinarev.task.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.MediaType;

import com.svinarev.task.entities.User;
import com.svinarev.task.dto.UserDTO;
import com.svinarev.task.converters.UserConverter;

public class UserContollerTest extends BaseControllerTest {
	
	private static String URI = "/user";
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getUserById() throws Exception {
		User user = getTestUser();
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
					URI + "/getById/{id}", user.getId()
				)).andReturn();
		
		int responseStatus = result.getResponse().getStatus();
		assertEquals(responseStatus, 200);
		
		String content = result.getResponse().getContentAsString();
		UserDTO dto = super.mapFromJson(content, UserDTO.class);
		
		assertEquals(user.getUsername(), dto.getUsername());
		
	}
	
	@Test
	public void getUserByUsername() throws Exception {
		User user = getTestUser();
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
					URI + "/getByUsername/{username}", user.getUsername()
				)).andReturn();
		
		int responseStatus = result.getResponse().getStatus();
		assertEquals(responseStatus, 200);
		
		String content = result.getResponse().getContentAsString();
		UserDTO dto = super.mapFromJson(content, UserDTO.class);
		
		assertEquals(user.getUsername(), dto.getUsername());
		
	}
	
	@Test
	public void save() throws Exception {
		UserDTO userDto = getTestUserDto();
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(
					URI + "/save"
				).contentType(MediaType.APPLICATION_JSON_VALUE).content(super.mapToJson(userDto))
				).andReturn();
		
		int responseStatus = result.getResponse().getStatus();
		assertEquals(responseStatus, 200);
		
		String content = result.getResponse().getContentAsString();
		UserDTO resultDto = super.mapFromJson(content, UserDTO.class);
		
		assertEquals(userDto.getUsername(), resultDto.getUsername());
		
	}
	
	@Test
	public void delete() throws Exception {
		User user = getTestUser();
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(
				URI + "/deleteById/{id}", user.getId()
			)).andReturn();
		
		int responseStatus = result.getResponse().getStatus();
		assertEquals(responseStatus, 200);
		
		String content = result.getResponse().getContentAsString();
		
		assertEquals(content, "User was successfully deleted.");
		
	}
	
	
	
}
