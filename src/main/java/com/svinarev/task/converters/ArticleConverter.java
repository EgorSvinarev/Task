package com.svinarev.task.converters;

import com.svinarev.task.entities.Article;
import com.svinarev.task.dto.ArticleDTO;
import com.svinarev.task.converters.UserConverter;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class ArticleConverter {

	public static ArticleDTO toDTO(Article article) {
		if (article == null) {
			return ArticleDTO.builder().build();
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		
		return ArticleDTO.builder()
					.id(article.getId())
					.author(UserConverter.toDTO(article.getAuthor()))
					.title(article.getTitle())
					.pages(ArticleDTO.separateContentOnPages(article.getContent()))
					.content(article.getContent())
					.date(format.format(article.getDate()))
				.build();
	}
	
	public static Article fromDTO(ArticleDTO article) {
		if (article == null) {
			return Article.builder().build();
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		try {
			return Article.builder()
						.id(article.getId())
						.author(UserConverter.fromDTO(article.getAuthor()))
						.title(article.getTitle())
						.content(article.getContent())
						.date(format.parse(article.getDate()))
					.build();
		}
		catch(ParseException | NullPointerException e) {
			return Article.builder()
					.id(article.getId())
					.author(UserConverter.fromDTO(article.getAuthor()))
					.title(article.getTitle())
					.content(article.getContent())
					.date(new Date(System.currentTimeMillis()))
				.build();
		}
	}
	
	public static List<ArticleDTO> toListDTO(List<Article> articles) {
		List<ArticleDTO> articlesDTO = new ArrayList<>();
		
		for (Article a: articles) {
			articlesDTO.add(ArticleConverter.toDTO(a));
		}
		
		return articlesDTO;
	}
}
