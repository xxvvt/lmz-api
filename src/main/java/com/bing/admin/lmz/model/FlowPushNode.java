package com.bing.admin.lmz.model;

/**
 * @description flow_push_node
 * @author BEJSON
 * @date 2023-12-13
 */

import lombok.Data;

import java.io.Serializable;

@Data
public class FlowPushNode implements Serializable {

private static final long serialVersionUID = 1L;

/**
 * id
 */
private String id;

/**
 * pid
 */
private String pid;

/**
 * title
 */
private String title;

/**
 * users
 */
private String users;

/**
 * sort
 */
private Integer sort;

/**
 * remark
 */
private String remark;

public FlowPushNode() {}
        }