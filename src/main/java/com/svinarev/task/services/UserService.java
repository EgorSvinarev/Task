package com.svinarev.task.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.svinarev.task.repositories.UserRepository;
import com.svinarev.task.repositories.RoleRepository;
import com.svinarev.task.entities.User;
import com.svinarev.task.entities.Role;
import com.svinarev.task.exceptions.UserNotFoundException;
import com.svinarev.task.exceptions.UserAlreadyExistsException;
import com.svinarev.task.exceptions.RoleNotFoundException;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> u = userRepository.findByUsername(username);
		
		if (u.isEmpty()) {
			throw new UsernameNotFoundException("Incorrect credentials.");
		}
		
		return u.get();
		
	}
	
	public User findUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("User with this id wasn't found.");
		}
		
		return user.get();
		
	}
	
	public User findUserByUsername(String username) throws UserNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("User with this username wasn't found.");
		}
		
		return user.get();
		
	}
	
	public void deleteUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("User with this id wasn't found");
		}
		
		userRepository.delete(user.get());
	}
	
	public User save(User user, String roleName) throws UserAlreadyExistsException, RoleNotFoundException {
		
		Optional<User> storedUser = userRepository.findByUsername(user.getUsername());
		Optional<Role> role = roleRepository.findByName(roleName);
		
		if (storedUser.isPresent()) {
			throw new UserAlreadyExistsException("User with this username already exists");
		}
		
		if (role.isEmpty()) {
			throw new RoleNotFoundException("Role with this name wasn't found.");
		}
		
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(role.get());
		
		return userRepository.save(user);
	}
	
}
