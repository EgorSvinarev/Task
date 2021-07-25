package com.svinarev.task.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "articles")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@JoinColumn(name = "author",
				nullable = false,
				foreignKey = @ForeignKey(name = "author_fk"))
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private User author;
	
	@Column(name = "title", nullable = false)
	@NotNull
	@Size(min = 1, max = 100)
	private String title;
	
	@Column(name = "content", nullable = false)
	@NotNull
	private String content;
	
	@Column(name = "publication_date", nullable = false)
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date date;
}
