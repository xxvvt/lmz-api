package com.bing.admin.model;

import java.util.List;
import java.util.Map;

public class listResultModel {
    private List<Map<String, Object>> list;
    private Integer total;
    private Integer offset;
    private Integer limit;

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }
}
