USE myblog;

SELECT BlogEntry.id,
       Users.username,
       BlogEntry.created,
       BlogEntry.modified,
       BlogEntry.title,
       BlogEntryContent.content
FROM BlogEntry
    INNER JOIN BlogEntryContent ON BlogEntry.contentId = BlogEntryContent.id
    INNER JOIN Users ON BlogEntry.authorId = Users.id;

ALTER TABLE blogentry ADD title VARCHAR(255) NOT NULL;