package com.bing.admin.lmz.model;

public class ApiLoginModel {
    private String startTime;
    private String statusCode;
    private String sessionid;
    private String kdsvcsessionid;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getKdsvcsessionid() {
        return kdsvcsessionid;
    }

    public void setKdsvcsessionid(String kdsvcsessionid) {
        this.kdsvcsessionid = kdsvcsessionid;
    }
}
