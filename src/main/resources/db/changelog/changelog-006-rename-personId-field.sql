--liquibase formatted sql

--changeset you:006-rename-personId-field
ALTER TABLE vote RENAME COLUMN person_id TO candidate_id;
