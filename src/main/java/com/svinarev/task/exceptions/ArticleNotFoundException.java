package com.svinarev.task.exceptions;

public class ArticleNotFoundException extends Exception {

	public ArticleNotFoundException() {
		super();
	}
	
	public ArticleNotFoundException(String message) {
		super(message);
	}
	
}
