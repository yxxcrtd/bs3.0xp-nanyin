/*
 * 文件名：FtpConnectDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年4月19日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.dto;

import org.apache.commons.net.ftp.FTPClient;

/**
 * ftp 连接Dto
 * 
 * @author lihuadong
 * @version 1.0 2017年4月19日
 * @see FtpConnectDto
 * @since 1.0
 */
public class FtpConnectDto {

    /**
     * IP地址
     */
    private String ip;

    /**
     * 端口号
     */
    private int port;

    /**
     * 编码方式
     */
    private String encoding;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * ftpClient
     */
    private FTPClient ftpClient;

    /**
     * default
     */
    public FtpConnectDto() {

    }

    /**
     * 初始化
     */
    public FtpConnectDto(String ip, int port, String encoding, String username, String password) {
        this.ip = ip;
        this.port = port;
        this.encoding = encoding;
        this.username = username;
        this.password = password;
    }

    /**
     * 获取 ip
     * 
     * @return ip.
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置 ip
     * 
     * @param ip ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取 port
     * 
     * @return port.
     */
    public int getPort() {
        return port;
    }

    /**
     * 设置 port
     * 
     * @param port port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * 获取 encoding
     * 
     * @return encoding.
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * 设置 encoding
     * 
     * @param encoding encoding
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * 获取 username
     * 
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置 username
     * 
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取 password
     * 
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置 password
     * 
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取 ftpClient
     * 
     * @return ftpClient.
     */
    public FTPClient getFtpClient() {
        return ftpClient;
    }

    /**
     * 设置 ftpClient
     * 
     * @param ftpClient ftpClient
     */
    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

}
