package com.svinarev.task.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.ActiveProfiles;

import com.svinarev.task.dto.ArticleDTO;
import com.svinarev.task.dto.UserDTO;
import com.svinarev.task.entities.Article;
import com.svinarev.task.entities.User;


public class ArticleControllerTest extends BaseControllerTest {

	private static String URI = "/article";
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getById() throws Exception {
		Article article = getTestArticle();
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
					URI + "/getById/{id}", article.getId()
				)).andReturn();
		
		int responseStatus = result.getResponse().getStatus();
		assertEquals(responseStatus, 200);
		
		String content = result.getResponse().getContentAsString();
		ArticleDTO dto = super.mapFromJson(content, ArticleDTO.class);
		
		assertEquals(article.getTitle(), dto.getTitle());
	}
	
	@Test
	public void getByAuthor() throws Exception {
		Article article = getTestArticle();
		User user = article.getAuthor();
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
					URI + "/getByAuthor/{authorId}", user.getId()
				)).andReturn();
		
		int responseStatus = result.getResponse().getStatus();
		assertEquals(responseStatus, 200);
		
		String content = result.getResponse().getContentAsString();
		ArticleDTO[] dto = super.mapFromJson(content, ArticleDTO[].class);
		
		assertTrue(dto.length > 0);
	}
	
	@Test
	public void save() throws Exception {
		ArticleDTO articleDTO = getTestArticleDto();
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(
				URI + "/save"
			).contentType(MediaType.APPLICATION_JSON_VALUE).content(super.mapToJson(articleDTO))
			).andReturn();
		
		int responseStatus = result.getResponse().getStatus();
		assertEquals(responseStatus, 200);
		
		String content = result.getResponse().getContentAsString();
		ArticleDTO resultDto = super.mapFromJson(content, ArticleDTO.class);
		
		assertEquals(articleDTO.getTitle(), resultDto.getTitle());
	}
	
}
