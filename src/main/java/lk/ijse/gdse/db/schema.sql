CREATE DATABASE CMS;

USE CMS;

CREATE TABLE User (
                      id VARCHAR(40)  PRIMARY KEY,
                      name VARCHAR(30),
                      password VARCHAR(20),
                      role VARCHAR(10)
);

CREATE TABLE complaint (
                           id VARCHAR(36) PRIMARY KEY,
                           uid varchar(40),
                           username varchar(20),
                           title varchar(50),
                           complaint varchar(800),
                           date date,
                           status varchar(30),
                           remark varchar(1000),
                           foreign key(uid)references User(id) on update cascade on delete cascade

);