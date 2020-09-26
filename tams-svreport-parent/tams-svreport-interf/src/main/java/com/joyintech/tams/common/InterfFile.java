/*
 * 文件名：InterfFile.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019年4月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common;

import java.io.File;

/**
 * 接口文件实体
 * @author chenxc
 * @version 1.0 2019年4月30日
 * @see InterfFile
 * @since 1.0
 */
public class InterfFile {
    // FTP_TYPE
    String ftpType;
    // IP
    String url;
    // PORT
    int port;
    // ftp连接用户名
    String username;
    // ftp密码
    String password;
    String localPath;
    String remotePath;
    File file;
    String filename;
    String moveRemovePath;//移动文件目录 remotePath+remoteDir
    String dataSource;// 数据来源
    String interfType;//接口类型
    String fileType;// 文件类型
    
    public String getFtpType() {
        return ftpType;
    }
    
    public void setFtpType(String ftpType) {
        this.ftpType = ftpType;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public int getPort() {
        return port;
    }
    
    public void setPort(int port) {
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
    
    public String getRemotePath() {
        return remotePath;
    }
    
    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }
    
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFilename() {
        return filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }

	public String getMoveRemovePath() {
		return moveRemovePath;
	}

	public void setMoveRemovePath(String moveRemovePath) {
		this.moveRemovePath = moveRemovePath;
	}

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getInterfType() {
        return interfType;
    }

    public void setInterfType(String interfType) {
        this.interfType = interfType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
}
