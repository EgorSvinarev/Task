--liquibase formatted sql

--changeset egor:2021-07-22-add_author-fk_constraint

ALTER TABLE articles ADD CONSTRAINT author_fk FOREIGN KEY(author) REFERENCES users(id); 

-- rollback ALTER TABLE articles DROP CONSTRAINT author_fk;