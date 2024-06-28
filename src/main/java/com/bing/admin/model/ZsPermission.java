package com.bing.admin.model;


import java.util.List;

public class ZsPermission {

  private String id;
  private String key;
  private String title;
  private String parentId;
  private String name;
  private String perms;
  private String permsType;
  private String icon;
  private String component;
  private String url;
  private String redirect;
  private long sortNo;
  private Integer menuType;
  private String isLeaf;
  private boolean route;
  private String keepAlive;
  private String description;
  private String delFlag;
  private String createBy;
  private String createTime;
  private String updateBy;
  private String updateTime;
  private boolean alwaysShow;
  private boolean hidden;
  private Integer status;
  private boolean internalOrExternal;
  private List<ZsPermission> children;
  private String leaf;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPerms() {
    return perms;
  }

  public void setPerms(String perms) {
    this.perms = perms;
  }

  public String getPermsType() {
    return permsType;
  }

  public void setPermsType(String permsType) {
    this.permsType = permsType;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getComponent() {
    return component;
  }

  public void setComponent(String component) {
    this.component = component;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getRedirect() {
    return redirect;
  }

  public void setRedirect(String redirect) {
    this.redirect = redirect;
  }

  public long getSortNo() {
    return sortNo;
  }

  public void setSortNo(long sortNo) {
    this.sortNo = sortNo;
  }

  public Integer getMenuType() {
    return menuType;
  }

  public void setMenuType(Integer menuType) {
    this.menuType = menuType;
  }

  public String getIsLeaf() {
    return isLeaf;
  }

  public void setIsLeaf(String isLeaf) {
    this.isLeaf = isLeaf;
  }

  public boolean isRoute() {
    return route;
  }

  public void setRoute(boolean route) {
    this.route = route;
  }

  public String getKeepAlive() {
    return keepAlive;
  }

  public void setKeepAlive(String keepAlive) {
    this.keepAlive = keepAlive;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
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

  public boolean isAlwaysShow() {
    return alwaysShow;
  }

  public void setAlwaysShow(boolean alwaysShow) {
    this.alwaysShow = alwaysShow;
  }

  public boolean isHidden() {
    return hidden;
  }

  public void setHidden(boolean hidden) {
    this.hidden = hidden;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public boolean isInternalOrExternal() {
    return internalOrExternal;
  }

  public void setInternalOrExternal(boolean internalOrExternal) {
    this.internalOrExternal = internalOrExternal;
  }

  public List<ZsPermission> getChildren() {
    return children;
  }

  public void setChildren(List<ZsPermission> children) {
    this.children = children;
  }

  public String getLeaf() {
    return leaf;
  }

  public void setLeaf(String leaf) {
    this.leaf = leaf;
  }
}
