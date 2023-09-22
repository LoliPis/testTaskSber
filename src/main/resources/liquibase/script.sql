-- liquibase formatted sql

-- changeset lolipis:1
CREATE TABLE books
(
    ID     INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title  VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    year   INT,
    genre  VARCHAR(255)
);
