CREATE TABLE genre
(
    id   BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE
);