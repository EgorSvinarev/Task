--liquibase formatted sql

--changeset egor:2021-07-22-add_role-fk_constraint

ALTER TABLE users ADD CONSTRAINT role_fk FOREIGN KEY(role) REFERENCES roles(id); 

-- rollback ALTER TABLE users DROP CONSTRAINT role_fk;