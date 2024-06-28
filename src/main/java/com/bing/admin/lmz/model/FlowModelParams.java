package com.bing.admin.lmz.model;


public class FlowModelParams {

  private String id;
  private String modelId;
  private String name;
  private String value;
  private String valueFormula;
  private long type;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getModelId() {
    return modelId;
  }

  public void setModelId(String modelId) {
    this.modelId = modelId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  public String getValueFormula() {
    return valueFormula;
  }

  public void setValueFormula(String valueFormula) {
    this.valueFormula = valueFormula;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }

}
