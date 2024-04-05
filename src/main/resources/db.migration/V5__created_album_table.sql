CREATE TABLE album
(
    id           BIGSERIAL NOT NULL,
    title        VARCHAR(255) NOT NULL,
    release_date TIMESTAMP(6) WITH TIME ZONE
);