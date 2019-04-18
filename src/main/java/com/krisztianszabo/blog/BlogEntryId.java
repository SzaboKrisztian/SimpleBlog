package com.krisztianszabo.blog;

public class BlogEntryId {
  private int entryId;

  public int getEntryId() {
    return entryId;
  }

  public void setEntryId(int entryId) {
    this.entryId = entryId;
  }

  @Override
  public String toString() {
    return "BlogEntryId{" +
        "entryId=" + entryId +
        '}';
  }
}
