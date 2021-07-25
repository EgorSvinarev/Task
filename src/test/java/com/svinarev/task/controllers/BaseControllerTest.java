package com.svinarev.task.controllers;

import com.svinarev.task.AbstractTest;

import org.springframework.beans.factory.annotation.Autowired;

import com.svinarev.task.repositories.*;
import com.svinarev.task.entities.*;
import com.svinarev.task.dto.*;
import com.svinarev.task.converters.*;
import com.svinarev.task.utils.RandomString;

import java.util.Date;

public class BaseControllerTest extends AbstractTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public User getTestUser() {
		User user = User.builder()
						.username(RandomString.getRandomStringChar(8))
						.password(RandomString.getRandomStringCharAndNum(10))
						.role(roleRepository.findByName("USER").get())
					.build();
		
		return userRepository.save(user);
	}
	
	public User getTestAdmin() {
		User user = User.builder()
						.username(RandomString.getRandomStringChar(8))
						.password(RandomString.getRandomStringCharAndNum(10))
						.role(roleRepository.findByName("ADMIN").get())
					.build();
		
		return userRepository.save(user);
	}
	
	public UserDTO getTestUserDto() {
		UserDTO userDto = UserDTO.builder()
							.username(RandomString.getRandomStringChar(8))
							.password(RandomString.getRandomStringCharAndNum(10))
							.role(RoleConverter.toDTO(roleRepository.findByName("ADMIN").get()))
						  .build();
		return userDto;
	}
	
	public Article getTestArticle() {
		User user = getTestUser();
		Article article = Article.builder()
						.author(user)
						.title(RandomString.getRandomStringCharAndNum(10))
						.content(RandomString.getRandomStringCharAndNum(10))
						.date(new Date(System.currentTimeMillis()))
					.build();
		
		return articleRepository.save(article);
	}
	
	public ArticleDTO getTestArticleDto() {
		User user = getTestUser();
		ArticleDTO articleDto = ArticleDTO.builder()
							.author(UserConverter.toDTO(user))
							.title(RandomString.getRandomStringCharAndNum(10))
							.content(RandomString.getRandomStringCharAndNum(10))
						  .build();
		return articleDto;
	}
}
