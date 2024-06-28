package com.bing.admin.lmz.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author BEJSON
 * @description flow_push_basic
 * @date 2023-12-13
 */
@Data
public class FlowPushBasic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * create_by
     */
    private String createBy;

    /**
     * create_time
     */
    private String createTime;

    /**
     * update_by
     */
    private String updateBy;

    /**
     * update_time
     */
    private String updateTime;

    /**
     * title
     */
    private String title;

    /**
     * status
     */
    private Integer status;

    /**
     * remark
     */
    private String remark;

    /**
     * title_formula
     */
    private String titleFormula;

    /**
     * database
     */
    private String database;

    /**
     * type
     */
    private String type;

    /**
     * module
     */
    private String module;

    private String bgtType;
    private String bgtYear;

    private String versionId;

    public FlowPushBasic() {
    }
}