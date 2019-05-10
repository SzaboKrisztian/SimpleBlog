CREATE DATABASE IF NOT EXISTS MyBlog;
USE MyBlog;

CREATE TABLE Users(
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL ,
    emailAddress VARCHAR(255),
    password VARCHAR(255),
    displayName VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE BlogEntryContent(
    id INT NOT NULL AUTO_INCREMENT,
    content LONGTEXT,
    PRIMARY KEY (id)
);

CREATE TABLE BlogEntry(
    id INT NOT NULL AUTO_INCREMENT,
    authorId INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified TIMESTAMP,
    contentId INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (authorId) REFERENCES Users(id),
    FOREIGN KEY (contentId) REFERENCES BlogEntryContent(id)
);