--liquibase formatted sql

--changeset egor:2021-07-22-create-table-users

CREATE TABLE users (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(30) NOT NULL,
	password CHAR(60) NOT NULL,
	role INT UNSIGNED NOT NULL
);

-- rollback DROP TABLE users;