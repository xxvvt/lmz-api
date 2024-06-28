package com.bing.admin.model;


public class ZsEmphasis {

  private long id;
  private long yjId;
  private String carNo;
  private String carMan;
  private String carComplay;
  private String addtime;

  private String userName;
  private String userId;

  private String userDepartment;
  private String departmentId;

  private long carStatus;

  private long companyStatus;

  private long manStatus;

  public String getUserDepartment() {
    return userDepartment;
  }

  public void setUserDepartment(String userDepartment) {
    this.userDepartment = userDepartment;
  }

  public String getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(String departmentId) {
    this.departmentId = departmentId;
  }

  private long type;
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getYjId() {
    return yjId;
  }

  public void setYjId(long yjId) {
    this.yjId = yjId;
  }


  public String getCarNo() {
    return carNo;
  }

  public void setCarNo(String carNo) {
    this.carNo = carNo;
  }


  public String getCarMan() {
    return carMan;
  }

  public void setCarMan(String carMan) {
    this.carMan = carMan;
  }


  public String getCarComplay() {
    return carComplay;
  }

  public void setCarComplay(String carComplay) {
    this.carComplay = carComplay;
  }


  public String getAddtime() {
    return addtime;
  }

  public void setAddtime(String addtime) {
    this.addtime = addtime;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }

  public long getCarStatus() {
    return carStatus;
  }

  public void setCarStatus(long carStatus) {
    this.carStatus = carStatus;
  }

  public long getCompanyStatus() {
    return companyStatus;
  }

  public void setCompanyStatus(long companyStatus) {
    this.companyStatus = companyStatus;
  }

  public long getManStatus() {
    return manStatus;
  }

  public void setManStatus(long manStatus) {
    this.manStatus = manStatus;
  }
}
