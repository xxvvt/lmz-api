package com.bing.admin.lmz.model;

import lombok.Data;

@Data
public class FlowError {
    private String id;
    private String createTime;
    private String createUser;
    private String type;
    private String content;
    private String remark;
}
