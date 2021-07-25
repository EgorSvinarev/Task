package com.svinarev.task.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

import java.util.Optional;

import com.svinarev.task.converters.RoleConverter;
import com.svinarev.task.converters.UserConverter;
import com.svinarev.task.dto.UserDTO;
import com.svinarev.task.entities.User;
import com.svinarev.task.services.UserService;
import com.svinarev.task.exceptions.UserNotFoundException;
import com.svinarev.task.exceptions.UserAlreadyExistsException;
import com.svinarev.task.exceptions.RoleNotFoundException;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		try {
			User user = userService.findUserById(id);	
			
			return ResponseEntity.ok(UserConverter.toDTO(user));
		}
		catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		
	}
	
	@GetMapping(value = "/getByUsername/{username}")
	public ResponseEntity<?> get(@PathVariable String username) {
		try {
			User user = userService.findUserByUsername(username);	
			
			return ResponseEntity.ok(UserConverter.toDTO(user));
		}
		catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@Valid @RequestBody UserDTO user) {
		try {
			User u = userService.save(UserConverter.fromDTO(user), RoleConverter.fromDTO(user.getRole()).getName());	
			return ResponseEntity.ok(UserConverter.toDTO(u));
		}
		catch (UserAlreadyExistsException | RoleNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			userService.deleteUserById(id);
			
			return ResponseEntity.status(HttpStatus.OK).body("User was successfully deleted.");
		}
		catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
}
