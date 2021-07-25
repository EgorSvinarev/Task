package com.svinarev.task.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.svinarev.task.services.ArticleService;
import com.svinarev.task.dto.ArticleDTO;
import com.svinarev.task.entities.Article;
import com.svinarev.task.converters.ArticleConverter;

@RestController
@RequestMapping(value = "/statistics")
public class StatisticsController {

	@Autowired
	private ArticleService articleService;
	
	@GetMapping(value = "/getLastPerDays/{days}")
	public ResponseEntity<List<ArticleDTO>> findAllLastArticles(@PathVariable int days) {
		List<Article> articles = articleService.findAllLastArticles(days);
		
		return ResponseEntity.ok(ArticleConverter.toListDTO(articles));
	}
	
}
