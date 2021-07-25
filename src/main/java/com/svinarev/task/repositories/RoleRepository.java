package com.svinarev.task.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.svinarev.task.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByName(String name);
	
}
