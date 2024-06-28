package com.bing.admin.model;


public class GSkill {

  private long id;
  private String name;
  private String content;
  private String level;
  private long classicsId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }


  public long getClassicsId() {
    return classicsId;
  }

  public void setClassicsId(long classicsId) {
    this.classicsId = classicsId;
  }

}
