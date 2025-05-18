--liquibase formatted sql

--changeset you:006-create-temporary-vote-table
CREATE TABLE temporary_vote (
                                id UUID,
                                candidate_id BIGINT NOT NULL,
                                generated_number BIGINT,
                                ciphertext TEXT NOT NULL
);

CREATE INDEX idx_temporary_vote_id
    ON temporary_vote(id);