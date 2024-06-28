package com.bing.admin.lmz.model;

import java.util.List;

public class FlowDataModel {
    String flowId;
    String nodeId;
    String nodeType;
    String oId;
    String text;
    //提交状态
    String submitType;
    //提交人
    String submitUser;

    List<String> bhList;
    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
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

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    public String getSubmitUser() {
        return submitUser;
    }

    public void setSubmitUser(String submitUser) {
        this.submitUser = submitUser;
    }

    public List<String> getBhList() {
        return bhList;
    }

    public void setBhList(List<String> bhList) {
        this.bhList = bhList;
    }
}
