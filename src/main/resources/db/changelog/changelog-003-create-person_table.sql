--liquibase formatted sql

--changeset you:003-create-person-table
CREATE TABLE PERSON (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
    party_code BIGINT,
    CONSTRAINT fk_person_party FOREIGN KEY (party_code) REFERENCES PARTY(id)
);
