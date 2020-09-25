package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepFtpPath extends BaseEntity {
    /**
     * 披露地址编号
     */
    private String id;

    /**
     * 披露地址名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 目录
     */
    private String path;

    /**
     * 端口号
     */
    private String prot;

    /**
     * 主机地址
     */
    private String ip;
    
    /**
     * 实现类名称
     */
    private String beanName;

    /**
     * 附属参数
     */
    private String beanVal;

    /**
     * 取得 披露地址编号
     * @return 披露地址编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 披露地址编号
     * @param id 披露地址编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 披露地址名称
     * @return 披露地址名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 披露地址名称
     * @param name 披露地址名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 取得 密码
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 取得 用户名
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置 用户名
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 取得 目录
     * @return 目录
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置 目录
     * @param path 目录
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 取得 端口号
     * @return 端口号
     */
    public String getProt() {
        return prot;
    }

    /**
     * 设置 端口号
     * @param prot 端口号
     */
    public void setProt(String prot) {
        this.prot = prot == null ? null : prot.trim();
    }

    /**
     * 取得 主机地址
     * @return 主机地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置 主机地址
     * @param ip 主机地址
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
    
    /**
     * 取得 实现类名称
     * @return 实现类名称
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * 设置 实现类名称
     * @param beanName 实现类名称
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName == null ? null : beanName.trim();
    }

    /**
     * 取得 附属参数
     * @return 附属参数
     */
    public String getBeanVal() {
        return beanVal;
    }

    /**
     * 设置 附属参数
     * @param beanVal 附属参数
     */
    public void setBeanVal(String beanVal) {
        this.beanVal = beanVal == null ? null : beanVal.trim();
    }
}