package com.bing.admin.lmz.model;


import java.util.List;
import java.util.Map;

public class FlowOperation {

  private String id;
  private String flowId;
  private String createBy;
  private String createTime;
  private String updateBy;
  private String updateTime;
  private String title;
  private long status;
  private String remark;
  private String node;
  private String nodeId;
  private String nodeType;
  private long type;
  private String basicId;
  private String department;
  private String departmentId;
  private String userName;
  private String nodeTitle;
  private String upModel;

  private List<Map<String,String>> params;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getFlowId() {
    return flowId;
  }

  public void setFlowId(String flowId) {
    this.flowId = flowId;
  }


  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }


  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }


  public String getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }


  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
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


  public String getNode() {
    return node;
  }

  public void setNode(String node) {
    this.node = node;
  }


  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }


  public String getNodeType() {
    return nodeType;
  }

  public void setNodeType(String nodeType) {
    this.nodeType = nodeType;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public String getBasicId() {
    return basicId;
  }

  public void setBasicId(String basicId) {
    this.basicId = basicId;
  }


  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }


  public String getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(String departmentId) {
    this.departmentId = departmentId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getNodeTitle() {
    return nodeTitle;
  }

  public void setNodeTitle(String nodeTitle) {
    this.nodeTitle = nodeTitle;
  }

  public String getUpModel() {
    return upModel;
  }

  public void setUpModel(String upModel) {
    this.upModel = upModel;
  }

    public List<Map<String, String>> getParams() {
        return params;
    }

    public void setParams(List<Map<String, String>> params) {
        this.params = params;
    }
}
