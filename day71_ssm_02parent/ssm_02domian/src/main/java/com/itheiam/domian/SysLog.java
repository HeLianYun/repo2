package com.itheiam.domian;

import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable {

    private String id;
//    访问的时间
    private Date visitTime;
//    用户名称
    private String username;
//    ip地址
    private String ip;
//    执行的方法名称
    private String method;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
