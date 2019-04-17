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
    SqlRowSet rowSet = jdbc.queryForRowSet("SELECT BlogEntry.id, Users.username, " +
        "BlogEntry.created, BlogEntry.modified, BlogEntry.title, BlogEntryContent.content FROM BlogEntry " +
        "INNER JOIN BlogEntryContent ON BlogEntry.contentId = BlogEntryContent.id " +
        "INNER JOIN Users ON BlogEntry.authorId = Users.id");
    List<BlogEntry> result = new ArrayList<>();

    while(rowSet.next()) {
      BlogEntry blogEntry = new BlogEntry();
      blogEntry.setId(rowSet.getInt("id"));
      blogEntry.setAuthor(rowSet.getString("username"));
      blogEntry.setCreated(rowSet.getTimestamp("created").toLocalDateTime());
      Timestamp modified = rowSet.getTimestamp("modified");
      blogEntry.setModified(modified == null ? null : modified.toLocalDateTime());
      blogEntry.setTitle(rowSet.getString("title"));
      blogEntry.setContent(rowSet.getString("content"));
      result.add(blogEntry);
    }

    return result;
  }

}