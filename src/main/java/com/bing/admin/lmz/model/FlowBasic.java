package com.bing.admin.lmz.model;


import java.util.List;

public class FlowBasic {

  private String id;
  private String createBy;
  private java.sql.Timestamp createTime;
  private String updateBy;
  private java.sql.Timestamp updateTime;
  private String title;
  private long status;
  private String remark;
  private String titleFormula;
  private String database;
  private String type;
  private String module;
  private long flowType;
  private List<FlowModel> modelList;
  private List<FlowModelCorrelation> modelCoreList;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public String getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getTitleFormula() {
    return titleFormula;
  }

  public void setTitleFormula(String titleFormula) {
    this.titleFormula = titleFormula;
  }


  public String getDatabase() {
    return database;
  }

  public void setDatabase(String database) {
    this.database = database;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getModule() {
    return module;
  }

  public void setModule(String module) {
    this.module = module;
  }


  public long getFlowType() {
    return flowType;
  }

  public void setFlowType(long flowType) {
    this.flowType = flowType;
  }

  public List<FlowModel> getModelList() {
    return modelList;
  }

  public void setModelList(List<FlowModel> modelList) {
    this.modelList = modelList;
  }

  public List<FlowModelCorrelation> getModelCoreList() {
    return modelCoreList;
  }

  public void setModelCoreList(List<FlowModelCorrelation> modelCoreList) {
    this.modelCoreList = modelCoreList;
  }
}
