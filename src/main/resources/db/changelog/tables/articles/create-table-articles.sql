--liquibase formatted sql

--changeset egor:2021-07-22-create-table-articles

CREATE TABLE articles(
	id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	author BIGINT NOT NULL,
	content TEXT NOT NULL,
	publication_date VARCHAR(30) NOT NULL
)

-- rollback DROP TABLE articles;