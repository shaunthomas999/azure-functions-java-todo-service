package com.shaunthomas999.model;

import org.bson.types.ObjectId;

public class Todo {
  private ObjectId id;
  private String username;
  private String description;
  private Boolean done;

  public Todo() {
    super();
  }

  public Todo(ObjectId id, String username, String description, Boolean done) {
    this.id = id;
    this.username = username;
    this.description = description;
    this.done = done;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getDone() {
    return done;
  }

  public void setDone(Boolean done) {
    this.done = done;
  }
}
