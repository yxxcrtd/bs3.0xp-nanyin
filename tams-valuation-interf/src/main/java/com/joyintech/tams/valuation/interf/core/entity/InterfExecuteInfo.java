/*
 * 文件名：InterfExecuteInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.core.entity;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.ValInterfActionlog;

public class InterfExecuteInfo extends BaseEntity {


    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    private int taskId;

    /**
     * bean名称
     */
    private String beanName;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 批次序号
     */
    private String orderNo;

    /**
     * 数据有效状态
     */
    private String status;

    /**
     * 接收和发送状态
     */
    private String rs;

    /**
     * 系统ID
     */
    private String systemId;
    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private Date updateTime;

    /**
     * 接口处理日期
     */
    private Date interfDate;

    /**
     * 从服务器下载的路径的根路径
     */
    private String localRootPath;

    /**
     * 行为日志
     */
    private ValInterfActionlog actionLog;
    /**
     * 账套代码
     */
    private String bookSetId;

    /**
     * 接口模式
     */
    private String valModel;
    /**
     * 服务器登录类型
     */
    private String serverType;

    /**
     * 服务器远程路径
     */
    private String remotePath;

    /**
     * 服务器登录ip
     */
    private String ip;

    /**
     * 服务器登录端口号
     */
    private int port;

    /**
     * 服务器登录用户
     */
    private String userName;

    /**
     * 服务器登录密码
     */
    private String passWord;

    /**
     * 文件名
     */
    private String fileName;


    /**
     * 获取 remotePath
     * @return remotePath.
     */
    public String getRemotepath() {
        return remotePath;
    }



    /**
     * 设置 remotePath
     * @param remotepath remotePath
     */
    public void setRemotepath(String remotepath) {
        this.remotePath = remotepath;
    }



    /**
     * 获取 ip
     * @return ip.
     */
    public String getIp() {
        return ip;
    }



    /**
     * 设置 ip
     * @param ip ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }



    /**
     * 获取 port
     * @return port.
     */
    public int getPort() {
        return port;
    }



    /**
     * 设置 port
     * @param port port
     */
    public void setPort(int port) {
        this.port = port;
    }



    /**
     * 获取 userName
     * @return userName.
     */
    public String getUserName() {
        return userName;
    }



    /**
     * 设置 userName
     * @param username userName
     */
    public void setUserName(String username) {
        this.userName = username;
    }

    /**
     * 获取 行为日志
     * @return  行为日志.
     */
    public ValInterfActionlog getActionLog() {
        return actionLog;
    }


    /**
     * 设置 行为日志
     * @param actionLog 行为日志
     */
    public void setActionLog(ValInterfActionlog actionLog) {
        this.actionLog = actionLog;
    }

    /**
     * 获取 localRootPath
     * @return localRootPath.
     */
    public String getLocalRootPath() {
        return localRootPath;
    }

    /**
     * 设置 localRootPath
     * @param localRootPath localRootPath
     */
    public void setLocalRootPath(String localRootPath) {
        this.localRootPath = localRootPath;
    }

    /**
     * 获取 interfDate
     * @return interfDate.
     */
    public Date getInterfDate() {
        return interfDate;
    }

    /**
     * 设置 interfDate
     * @param interfDate interfDate
     */
    public void setInterfDate(Date interfDate) {
        this.interfDate = interfDate;
    }



    /**
     * 获取 rs
     * @return rs.
     */
    public String getRs() {
        return rs;
    }



    /**
     * 设置 rs
     * @param rs rs
     */
    public void setRs(String rs) {
        this.rs = rs;
    }



    /**
     * 获取 systemId
     * @return systemId.
     */
    public String getSystemId() {
        return systemId;
    }



    /**
     * 设置 systemId
     * @param systemId systemId
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }


    /**
     * 获取 taskId
     * @return taskId.
     */
    public int getTaskId() {
        return taskId;
    }


    /**
     * 设置 taskId
     * @param taskId taskId
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }


    /**
     * 获取 beanName
     * @return beanName.
     */
    public String getBeanName() {
        return beanName;
    }


    /**
     * 设置 beanName
     * @param beanName beanName
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }


    /**
     * 获取 taskName
     * @return taskName.
     */
    public String getTaskName() {
        return taskName;
    }


    /**
     * 设置 taskName
     * @param taskName taskName
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }


    /**
     * 获取 orderNo
     * @return orderNo.
     */
    public String getOrderNo() {
        return orderNo;
    }


    /**
     * 设置 orderNo
     * @param orderNo orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    /**
     * 获取 status
     * @return status.
     */
    public String getStatus() {
        return status;
    }


    /**
     * 设置 status
     * @param status status
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * 获取 createUser
     * @return createUser.
     */
    public String getCreateUser() {
        return createUser;
    }


    /**
     * 设置 createUser
     * @param createUser createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }


    /**
     * 获取 createTime
     * @return createTime.
     */
    public Date getCreateTime() {
        return createTime;
    }


    /**
     * 设置 createTime
     * @param createTime createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    /**
     * 获取 updateUser
     * @return updateUser.
     */
    public String getUpdateUser() {
        return updateUser;
    }


    /**
     * 设置 updateUser
     * @param updateUser updateUser
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }


    /**
     * 获取 updateTime
     * @return updateTime.
     */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
     * 设置 updateTime
     * @param updateTime updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBookSetId() {
        return bookSetId;
    }

    public void setBookSetId(String bookSetId) {
        this.bookSetId = bookSetId;
    }

    public String getValModel() {
        return valModel;
    }

    public void setValModel(String valModel) {
        this.valModel = valModel;
    }
    /**
     * 获取 serverType
     * @return serverType.
     */
    public String getServerType() {
        return serverType;
    }


    /**
     * 设置 serverType
     * @param serverType serverType
     */
    public void setServerType(String serverType) {
        this.serverType = serverType;
    }



    /**
     * 获取 passWord
     * @return passWord.
     */
    public String getPassWord() {
        return passWord;
    }



    /**
     * 设置 passWord
     * @param passWord passWord
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }



    /**
     * 获取 fileName
     * @return fileName.
     */
    public String getFileName() {
        return fileName;
    }



    /**
     * 设置 fileName
     * @param fileName fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
