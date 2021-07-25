package com.svinarev.task.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.svinarev.task.repositories.ArticleRepository;
import com.svinarev.task.entities.Article;
import com.svinarev.task.entities.User;
import com.svinarev.task.exceptions.ArticleNotFoundException;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	public Article save(Article article) {
		Date currentDate = new Date(System.currentTimeMillis());
		
		article.setDate(currentDate);
		return articleRepository.save(article);
	}
	
	public Article getById(Long id) throws ArticleNotFoundException {
		Optional<Article> article = articleRepository.findById(id);
		
		if (article.isEmpty()) {
			throw new ArticleNotFoundException("Article with this id wasn't found.");
		}
		
		return article.get();
	}
	
	public List<Article> getAllByAuthor(User author) {
		return articleRepository.findAllByAuthor(author);
	}
	
	public void deleteById(Long id) throws ArticleNotFoundException {
		Optional<Article> article = articleRepository.findById(id);
		
		if (article.isEmpty()) {
			throw new ArticleNotFoundException("Article with this id wasn't found.");
		}
		
		articleRepository.delete(article.get());
	}
	
	public List<Article> findAllLastArticles(int days) {
		LocalDateTime calculated = LocalDateTime.now().minusDays(days);
		Date dateCalculated = Date.from(calculated.atZone(ZoneId.systemDefault()).toInstant());
		
		return articleRepository.findAllWithDateAfter(dateCalculated);
	}
	
}
