USE myblog;

SELECT BlogEntry.entryId,
       Users.username,
       BlogEntry.created,
       BlogEntry.modified,
       BlogEntry.title,
       BlogEntryContent.content
FROM BlogEntry
    INNER JOIN BlogEntryContent ON BlogEntry.contentId = BlogEntryContent.entryId
    INNER JOIN Users ON BlogEntry.authorId = Users.entryId;

ALTER TABLE blogentry ADD title VARCHAR(255) NOT NULL;

SELECT BlogEntry.entryId,
       Users.displayName,
       BlogEntry.created,
       BlogEntry.modified,
       BlogEntry.title,
       BlogEntryContent.content
FROM BlogEntry
    INNER JOIN BlogEntryContent ON BlogEntry.contentId = BlogEntryContent.entryId
    INNER JOIN Users ON BlogEntry.authorId = Users.entryId WHERE BlogEntry.entryId = 2;