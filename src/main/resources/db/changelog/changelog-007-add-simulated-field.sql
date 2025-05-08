--liquibase formatted sql

--changeset you:007-add-simulated-field
ALTER TABLE vote
    ADD COLUMN simulated BOOLEAN NOT NULL DEFAULT FALSE;


UPDATE vote
SET simulated = FALSE
WHERE simulated IS NULL;


CREATE INDEX idx_vote_simulated
    ON vote(simulated);
