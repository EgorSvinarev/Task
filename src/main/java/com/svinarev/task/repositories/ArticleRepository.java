package com.svinarev.task.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;
import java.util.Date;

import com.svinarev.task.entities.Article;
import com.svinarev.task.entities.User;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	List<Article> findAllByAuthor(User author);
	
	@Query("select a from Article a where a.date >= :date")
    List<Article> findAllWithDateAfter(@Param("date") Date date);
	
}
