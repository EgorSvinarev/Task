package com.svinarev.task.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.MediaType;

import com.svinarev.task.dto.ArticleDTO;


public class AdminControllerTest extends BaseControllerTest {

	private static String URI = "/statistics";
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void findAllLastArticles() throws Exception{
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
				URI + "/getLastPerDays/2"
			)).andReturn();
		
		int responseStatus = result.getResponse().getStatus();
		assertEquals(responseStatus, 200);
		
		String content = result.getResponse().getContentAsString();
		ArticleDTO[] dto = super.mapFromJson(content, ArticleDTO[].class);
		
		assertTrue(dto.length > 0);
	}
	
}
