CREATE DATABASE IF NOT EXISTS MyBlog;
USE MyBlog;

CREATE TABLE Users(
    entryId INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL ,
    emailAddress VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY (entryId)
);

CREATE TABLE BlogEntryContent(
    entryId INT NOT NULL AUTO_INCREMENT,
    content LONGTEXT,
    PRIMARY KEY (entryId)
);

CREATE TABLE BlogEntry(
    entryId INT NOT NULL AUTO_INCREMENT,
    authorId INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified TIMESTAMP,
    contentId INT NOT NULL,
    PRIMARY KEY (entryId),
    FOREIGN KEY (authorId) REFERENCES Users(entryId),
    FOREIGN KEY (contentId) REFERENCES BlogEntryContent(entryId)
);