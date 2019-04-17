package com.krisztianszabo.blog;

import java.time.LocalDateTime;
import java.util.List;

public class BlogEntry {
  private int id;
  private String author;
  private LocalDateTime created;
  private LocalDateTime modified;
  private String title;
  private String content;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
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

  public String getContent() {
    return content;
  }

  public String[] getContentAsArray() {
    return this.content.split("\n");
  }

  public void setContent(String content) {
    this.content = content;
  }
}
