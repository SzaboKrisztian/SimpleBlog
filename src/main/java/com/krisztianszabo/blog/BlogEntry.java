package com.krisztianszabo.blog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BlogEntry {
  private int id;
  private String author;
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
}