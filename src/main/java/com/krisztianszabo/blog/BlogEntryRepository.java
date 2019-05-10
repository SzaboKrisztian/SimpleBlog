package com.krisztianszabo.blog;

import com.krisztianszabo.blog.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BlogEntryRepository {
  @Autowired
  private JdbcTemplate jdbc;

  public List<BlogEntry> getAllBlogEntries() {
    SqlRowSet rowSet = jdbc.queryForRowSet("SELECT BlogEntry.id, BlogEntry.authorId, " +
        "BlogEntry.created, BlogEntry.modified, BlogEntry.title, BlogEntryContent.content FROM BlogEntry " +
        "INNER JOIN BlogEntryContent ON BlogEntry.contentId = BlogEntryContent.id " +
        "ORDER BY BlogEntry.created DESC");
    List<BlogEntry> result = new ArrayList<>();

    while(rowSet.next()) {
      BlogEntry blogEntry = new BlogEntry();
      blogEntry.setId(rowSet.getInt("id"));
      blogEntry.setAuthor(findUserById(rowSet.getInt("authorId")));
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
    SqlRowSet rowSet = jdbc.queryForRowSet("SELECT BlogEntry.id, BlogEntry.authorId, " +
        "BlogEntry.created, BlogEntry.modified, BlogEntry.title, BlogEntryContent.content FROM BlogEntry " +
        "INNER JOIN BlogEntryContent ON BlogEntry.contentId = BlogEntryContent.id " +
        "WHERE BlogEntry.id = " + id);
    BlogEntry result = null;
    
    if (rowSet.first()) {
      result = new BlogEntry();
      result.setId(rowSet.getInt("id"));
      result.setAuthor(findUserById(rowSet.getInt("authorId")));
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

  public BlogEntry createBlogEntry(BlogEntry data) {
    PreparedStatementCreator psc = new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO BlogEntryContent(content) VALUES (?)", new String[]{"id"});
        ps.setString(1, data.getContent());
        return ps;
      }
    };
    KeyHolder id = new GeneratedKeyHolder();
    jdbc.update(psc, id);
    int contentId = id.getKey().intValue();

    User currentUser = findUserByUsername((String)SecurityContextHolder
        .getContext().getAuthentication().getPrincipal());

    psc = new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO BlogEntry(authorId, " +
            "title, contentId) VALUES(?, ?, ?);", new String[]{"id"});
        ps.setInt(1, currentUser.getId());
        ps.setString(2, data.getTitle());
        ps.setInt(3, contentId);
        return ps;
      }
    };
    id = new GeneratedKeyHolder();
    jdbc.update(psc, id);
    int entryId = id.getKey().intValue();

    return getBlogEntry(entryId);
  }

  public User findUserById(int id) {
    String query = "SELECT id, emailAddress, username, displayName FROM Users WHERE id = ?;";
    SqlRowSet rs = jdbc.queryForRowSet(query, id);
    User result = null;

    if (rs.first()) {
      result = new User();
      result.setId(rs.getInt("id"));
      result.setEmail(rs.getString("emailAddress"));
      result.setUserName(rs.getString("username"));
      result.setDisplayName(rs.getString("displayName"));
    }

    return result;
  }

  public User findUserByUsername(String username) {
    String query = "SELECT id, emailAddress, username, displayName FROM Users WHERE username = ?;";
    SqlRowSet rs = jdbc.queryForRowSet(query, username);
    User result = null;

    if (rs.first()) {
      result = new User();
      result.setId(rs.getInt("id"));
      result.setEmail(rs.getString("emailAddress"));
      result.setUserName(rs.getString("username"));
      result.setDisplayName(rs.getString("displayName"));
    }

    return result;
  }
}