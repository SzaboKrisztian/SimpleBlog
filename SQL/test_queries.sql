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

UPDATE BlogEntry INNER JOIN BlogEntryContent ON BlogEntry.contentId = BlogEntryContent.id SET BlogEntry.title='crap', BlogEntry.modified = CURRENT_TIMESTAMP, BlogEntryContent.content='told you there was crap here' WHERE blogentry.id=3;