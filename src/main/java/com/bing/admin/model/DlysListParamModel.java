package com.bing.admin.model;

import java.util.List;
import java.util.Map;

public class DlysListParamModel {
    private Integer offset;
    private Integer limit;
    private List<Map<String,String>> where;

    private String order;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    private List<DlysListWhereModel> wheres;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<Map<String,String>> getWhere() {
        return where;
    }

    public void setWhere(List<Map<String,String>> where) {
        this.where = where;
    }

    public List<DlysListWhereModel> getWheres() {
        return wheres;
    }

    public void setWheres(List<DlysListWhereModel> wheres) {
        this.wheres = wheres;
    }
}
