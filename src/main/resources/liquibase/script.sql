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


CREATE TABLE users
(
    ID    INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name  VARCHAR(255)        NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);


CREATE TABLE book_issue
(
    ID          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    book_ID     INT REFERENCES books (ID),
    user_ID     INT REFERENCES users (ID),
    issue_date  DATE NOT NULL,
    return_date DATE
);
