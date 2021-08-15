package com.svinarev.task.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import com.svinarev.task.repositories.RoleRepository;
import com.svinarev.task.entities.Role;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Optional<Role> getRoleByName(String name) {
		return roleRepository.findByName(name);
	}
	
}
