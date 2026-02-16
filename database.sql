CREATE DATABASE bankdb;
USE bankdb;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50),
    balance INT
);

INSERT INTO users(username,password,balance)
VALUES ('admin','1234',10000);
