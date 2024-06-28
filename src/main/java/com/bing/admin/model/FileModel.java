package com.bing.admin.model;




public class FileModel {

    /**
     * 文件ID
     */
    private Long id;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件类型
     */
    private String suffix;

    /**
     * 文件相对路径
     */
    private String path;

    /**
     * 文件原名字
     */
    private String oldName;

    /**
     * 添加时间
     */
    private String addtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
