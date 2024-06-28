package com.bing.admin.model;


public class GClassicsLevel {

  private long id;
  private long classicsId;
  private String name;
  private String level;
  private String exp;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getClassicsId() {
    return classicsId;
  }

  public void setClassicsId(long classicsId) {
    this.classicsId = classicsId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }


  public String getExp() {
    return exp;
  }

  public void setExp(String exp) {
    this.exp = exp;
  }

}
