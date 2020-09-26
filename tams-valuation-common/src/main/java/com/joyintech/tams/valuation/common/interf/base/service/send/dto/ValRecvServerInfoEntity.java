/*
 * 文件名：ValRecvServerInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.send.dto;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;

/**
 * 服务器信息
 * @author 王波
 * @version 1.0 2018年6月20日
 * @see ValRecvServerInfoEntity
 * @since 1.0
 */
public class ValRecvServerInfoEntity extends InterfNeedOperateEntity{

    /**
     * 服务器IP地址
     */
    private String serverIp;
    /**
     * 服务器端口号
     */
    private int serverPort;
    
    /**
     * 服务名（数据库方式下为服务名）
     */
    private String serverName;
    /**
     * 服务器登录用户名
     */
    private String userName;
    /**
     * 服务器登录密码
     */
    private String password;
    /**
     * 服务器登录类型
     */
    private String serverType;
    
    /**
     * 数据状态
     */
    private String status;
    
    
    /**
     * 系统ID
     */
    private String systemID;
    
    /**
     * 远程路径
     */
    private String remotePath;
    
    /**
     * 本地路径
     */
    private String localPath;
    
    
    /**
     * 执行任务的ID 取自行为日志的seqno
     */
    private String execInfoId;
    
    
    /**
     * 获取 execInfoId
     * @return execInfoId.
     */
    public String getExecInfoId() {
        return execInfoId;
    }

    /**
     * 设置 execInfoId
     * @param execInfoId execInfoId
     */
    public void setExecInfoId(String execInfoId) {
        this.execInfoId = execInfoId;
    }



    /**
     * 获取 远程路径
     * @return 远程路径.
     */
    public String getRemotePath() {
        return remotePath;
    }


    
    /**
     * 设置 远程路径
     * @param remotePath 远程路径
     */
    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }


    
    /**
     * 获取 本地路径
     * @return 本地路径.
     */
    public String getLocalPath() {
        return localPath;
    }


    
    /**
     * 设置 本地路径
     * @param localPath 本地路径
     */
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }


    /**
     * 获取 数据状态
     * @return 数据状态.
     */
    public String getStatus() {
        return status;
    }

    
    /**
     * 设置 数据状态
     * @param status 数据状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    
    /**
     * 获取 系统ID
     * @return 系统ID.
     */
    public String getSystemID() {
        return systemID;
    }

    
    /**
     * 设置 系统ID
     * @param systemID 系统ID
     */
    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    /**
     * 获取 服务器IP地址
     * @return 服务器IP地址.
     */
    public String getServerIp() {
        return serverIp;
    }
    
    /**
     * 设置 服务器IP地址
     * @param serverIp 服务器IP地址
     */
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
    
    /**
     * 获取 服务器端口号
     * @return 服务器端口号.
     */
    public int getServerPort() {
        return serverPort;
    }
    
    /**
     * 设置 服务器端口号
     * @param serverPort 服务器端口号
     */
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
    
    /**
     * 获取 服务器登录用户名
     * @return 服务器登录用户名.
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * 设置 服务器登录用户名
     * @param userName 服务器登录用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * 获取 服务器登录密码
     * @return 服务器登录密码.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * 设置 服务器登录密码
     * @param password 服务器登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * 获取 服务器登录类型
     * @return 服务器登录类型.
     */
    public String getServerType() {
        return serverType;
    }
    
    /**
     * 设置 服务器登录类型
     * @param serverType 服务器登录类型
     */
    public void setServerType(String serverType) {
        this.serverType = serverType;
    }



	public String getServerName() {
		return serverName;
	}



	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
}
