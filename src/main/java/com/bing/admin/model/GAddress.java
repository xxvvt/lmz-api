package com.bing.admin.model;


public class GAddress {

  private long id;
  private String name;
  private long partenId;
  private String content;


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


  public long getPartenId() {
    return partenId;
  }

  public void setPartenId(long partenId) {
    this.partenId = partenId;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
