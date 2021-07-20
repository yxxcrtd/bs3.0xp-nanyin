package com.joyintech.tams.invest.batch.stockonlinevoting.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 股票网络投票实体
 */
public class StockOnlineVotingDto extends NeedOperateEntity {

    /**
     * FTP类型
     */
    private String ftpType;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * ftp url
     */
    private String host;

    /**
     *  ftp port
     */
    private String port;

    /**
     *  ftp username
     */
    private String username;

    /**
     *  ftp password
     */
    private String password;

    /**
     *  ftp localPath
     */
    private String localPath;

    public String getFtpType() {
        return ftpType;
    }

    public void setFtpType(String ftpType) {
        this.ftpType = ftpType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
