package com.bing.admin.lmz.model;


import lombok.Data;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import java.io.Serializable;
import java.util.List;
@Data
public class FlowMain  {

  private String id;
  private String basicId;
  private String createTime;
  private String createBy;
  private String title;
  private String status;
  private long type;
  private String flowNo;
  private String parentId;
  private String coreId;
//  private String titleFormula;
//  private String titleType;

  private String bgtType;
  private String bgtYear;

  private String versionId;



}
