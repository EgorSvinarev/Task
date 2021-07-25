--liquibase formatted sql

--changeset egor:2021-07-22-insert-data-roles-user
INSERT INTO roles(name) VALUES ("USER")
-- rollback DELETE FROM roles WHERE name = "USER";

--changeset egor:2021-07-22-insert-data-roles-admin
INSERT INTO roles(name) VALUES ("ADMIN")
-- rollback DELETE FROM roles WHERE name = "ADMIN";