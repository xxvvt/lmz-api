package com.bing.admin.model;


import java.util.List;

public class ZsMapCode {

  private String code;
  private String name;
  private List<ZsMapCode> children;

  private String childrens;


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public List<ZsMapCode> getChildren() {
    return children;
  }

  public void setChildren(List<ZsMapCode> children) {
    this.children = children;
  }

  public String getChildrens() {
    return childrens;
  }

  public void setChildrens(String childrens) {
    this.childrens = childrens;
  }
}
