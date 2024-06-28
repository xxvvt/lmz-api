package com.bing.admin.lmz.model;

public class FlowOperationParams {

  private String id;
  private String modelId;
  private String name;
  private String value;
  private String valueFormula;
  private long type;
  private String operationId;


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


  public String getOperationId() {
    return operationId;
  }

  public void setOperationId(String operationId) {
    this.operationId = operationId;
  }

}
