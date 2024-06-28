package com.bing.admin.lmz.model;


public class FlowDict {

  private long id;
  private String code;
  private String title;
  private String value;
  private long isEnable;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  public long getIsEnable() {
    return isEnable;
  }

  public void setIsEnable(long isEnable) {
    this.isEnable = isEnable;
  }

}
