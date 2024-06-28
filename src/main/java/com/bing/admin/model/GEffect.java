package com.bing.admin.model;


public class GEffect {

  private long id;
  private long toId;
  private String toType;
  private String type;
  private String timeType;
  private String target;
  private String targetType;
  private String value;
  private String valueType;
  private String valueTarget;
  private String sort;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getToId() {
    return toId;
  }

  public void setToId(long toId) {
    this.toId = toId;
  }


  public String getToType() {
    return toType;
  }

  public void setToType(String toType) {
    this.toType = toType;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getTimeType() {
    return timeType;
  }

  public void setTimeType(String timeType) {
    this.timeType = timeType;
  }


  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }


  public String getTargetType() {
    return targetType;
  }

  public void setTargetType(String targetType) {
    this.targetType = targetType;
  }


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  public String getValueType() {
    return valueType;
  }

  public void setValueType(String valueType) {
    this.valueType = valueType;
  }


  public String getValueTarget() {
    return valueTarget;
  }

  public void setValueTarget(String valueTarget) {
    this.valueTarget = valueTarget;
  }


  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

}
