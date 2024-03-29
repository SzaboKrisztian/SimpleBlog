package com.krisztianszabo.blog;

import com.krisztianszabo.blog.security.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BlogEntry {
  private int id;
  private User author;
  private LocalDateTime created;
  private LocalDateTime modified;
  private String title;
  private String content;
  private final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("dd MMMM yyyy', at 'kk:mm");

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public LocalDateTime getModified() {
    return modified;
  }

  public void setModified(LocalDateTime modified) {
    this.modified = modified;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent(int charLimit) {
    if (charLimit < this.content.length()) {
      return this.content.substring(0, charLimit).concat("...");
    } else {
      return getContent();
    }
  }

  public String[] getContentAsArray(int charLimit) {
    return getContent(charLimit).split("\n");
  }

  public String getContent() {
    return this.content;
  }

  public String[] getContentAsArray() {
    return getContent().split("\n");
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getFormattedCreated() {
    return this.created.format(DT_FORMAT);
  }

  public String getFormattedModified() {
    return this.modified.format(DT_FORMAT);
  }

  @Override
  public String toString() {
    return "BlogEntry{" +
        "id=" + id +
        ", author='" + author + '\'' +
        ", created=" + created +
        ", modified=" + modified +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        '}';
  }
}