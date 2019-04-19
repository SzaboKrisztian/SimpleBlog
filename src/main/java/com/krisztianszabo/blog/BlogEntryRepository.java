package com.krisztianszabo.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BlogEntryRepository {
  @Autowired
  private JdbcTemplate jdbc;

  public List<BlogEntry> getAllBlogEntries() {
    SqlRowSet rowSet = jdbc.queryForRowSet("SELECT BlogEntry.id, Users.displayName, " +
        "BlogEntry.created, BlogEntry.modified, BlogEntry.title, BlogEntryContent.content FROM BlogEntry " +
        "INNER JOIN BlogEntryContent ON BlogEntry.contentId = BlogEntryContent.id " +
        "INNER JOIN Users ON BlogEntry.authorId = Users.id ORDER BY BlogEntry.created DESC");
    List<BlogEntry> result = new ArrayList<>();

    while(rowSet.next()) {
      BlogEntry blogEntry = new BlogEntry();
      blogEntry.setId(rowSet.getInt("id"));
      blogEntry.setAuthor(rowSet.getString("displayName"));
      blogEntry.setCreated(rowSet.getTimestamp("created").toLocalDateTime());
      Timestamp modified = rowSet.getTimestamp("modified");
      blogEntry.setModified(modified == null ? null : modified.toLocalDateTime());
      blogEntry.setTitle(rowSet.getString("title"));
      blogEntry.setContent(rowSet.getString("content"));
      result.add(blogEntry);
    }

    return result;
  }
  
  public BlogEntry getBlogEntry(int id) {
    SqlRowSet rowSet = jdbc.queryForRowSet("SELECT BlogEntry.id, Users.displayName, " +
        "BlogEntry.created, BlogEntry.modified, BlogEntry.title, BlogEntryContent.content FROM BlogEntry " +
        "INNER JOIN BlogEntryContent ON BlogEntry.contentId = BlogEntryContent.id " +
        "INNER JOIN Users ON BlogEntry.authorId = Users.id WHERE BlogEntry.id = " + id);
    BlogEntry result = null;
    
    if (rowSet.first()) {
      result = new BlogEntry();
      result.setId(rowSet.getInt("id"));
      result.setAuthor(rowSet.getString("displayName"));
      result.setCreated(rowSet.getTimestamp("created").toLocalDateTime());
      Timestamp modified = rowSet.getTimestamp("modified");
      result.setModified(modified == null ? null : modified.toLocalDateTime());
      result.setTitle(rowSet.getString("title"));
      result.setContent(rowSet.getString("content"));
    }

    return result;
  }

  public void editBlogEntry(BlogEntry data) {
    jdbc.update("UPDATE BlogEntry INNER JOIN BlogEntryContent ON BlogEntry.contentId =" +
        " BlogEntryContent.id SET BlogEntry.title='" + data.getTitle() +
        "', BlogEntry.modified = CURRENT_TIMESTAMP, BlogEntryContent.content='" + data.getContent() +
        "' WHERE BlogEntry.id=" + data.getId());
  }

  public void deleteBlogEntry(int id) {
    jdbc.update("DELETE FROM BlogEntry WHERE BlogEntry.id=" + id);
  }
}