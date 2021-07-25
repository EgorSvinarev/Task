--liquibase formatted sql

--changeset egor:2021-07-22-create-table-roles

CREATE TABLE roles(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(30) NOT NULL
);

-- rollback DROP TABLE roles;