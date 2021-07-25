package com.svinarev.task.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

import java.util.List;

import com.svinarev.task.dto.ArticleDTO;
import com.svinarev.task.entities.Article;
import com.svinarev.task.entities.User;
import com.svinarev.task.services.ArticleService;
import com.svinarev.task.services.UserService;
import com.svinarev.task.converters.ArticleConverter;
import com.svinarev.task.exceptions.ArticleNotFoundException;
import com.svinarev.task.exceptions.UserNotFoundException;

@RestController
@RequestMapping(value = "/article")
public class ArticleContoller {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(ArticleConverter.toDTO(articleService.getById(id)));
		}
		catch (ArticleNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/getByAuthor/{authorId}")
	public ResponseEntity<?> getByAuthor(@PathVariable Long authorId) {
		try {
			User user = userService.findUserById(authorId);
			List<Article> articles = articleService.getAllByAuthor(user);
			
			return ResponseEntity.ok(ArticleConverter.toListDTO(articles));
		}
		catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<ArticleDTO> save(@Valid @RequestBody ArticleDTO article) {
		Article storedArticle = articleService.save(ArticleConverter.fromDTO(article));
		
		ArticleDTO storedArticleDTO = ArticleConverter.toDTO(storedArticle);
		
		storedArticleDTO.setPages(storedArticleDTO.separateContentOnPages(storedArticleDTO.getContent()));
		
		return ResponseEntity.ok(storedArticleDTO);
	}
	
}
