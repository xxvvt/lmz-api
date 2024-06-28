package com.bing.admin.model;


public class ZsRolePermiss {

  private long id;
  private long roleId;
  private String PermissionId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }

  public String getPermissionId() {
    return PermissionId;
  }

  public void setPermissionId(String permissionId) {
    PermissionId = permissionId;
  }
}
