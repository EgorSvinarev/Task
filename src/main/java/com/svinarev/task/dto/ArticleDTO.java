package com.svinarev.task.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDTO {
	
	private Long id;
	@NotNull
	private UserDTO author;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String title;
	
	private List<String> pages = new ArrayList<>();
	
	@NotNull
	private String content;
	
	private String date;

	public static List<String> separateContentOnPages(String content) {
		List<String> pages = new ArrayList<>();
		
		String lines = "";
		int lineBegin = 0;
		int lineCounter = 0;
		
		for (int i = 0; i < content.length(); i++) {
			if (content.charAt(i) == '\n') {
				lines += content.substring(lineBegin, i + 1);
				lineBegin = i + 1;
				lineCounter += 1;
			}
			
			if (lineCounter > 10) {
				pages.add(lines);
				
				lines = "";
				lineCounter = 0;
			}
		}
		
		lines += content.substring(lineBegin);
		pages.add(lines);
		
		return pages;
	}
	
	@Override
	public String toString() {
		String data = "{"
				+ "id: " + this.id + ", "
				+ "author: " + this.author + ", "
				+ "title: " + this.title + ", "
				+ "content: '" + this.content + "', "
				+ "pagination: {pages: ["; 
		for (int i = 0; i < this.pages.size(); i++) {
			data += "{number: " + (i + 1) + ", text: " + this.pages.get(i) + "}";
			if (i < this.pages.size() - 1) data += ", ";
		}
		
		data += "]}, ";
		data += "date: " + this.date;
		data += "}";
		
		return data;
	}
	
}
