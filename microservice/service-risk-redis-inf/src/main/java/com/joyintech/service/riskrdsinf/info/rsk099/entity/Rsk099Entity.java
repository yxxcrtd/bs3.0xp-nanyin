/*
 * 文件名：Rsk099InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.info.rsk099.entity;

import com.joyintech.framework.base.BaseEntity;

/**
 * 接口RSK099-输入
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月11日
 * @see Rsk099Entity
 * @since 1.0
 */
public class Rsk099Entity extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -6787721609056605181L;

    /**
     * 通知类型
     */
    private String callType;

    /**
     * 处理状态
     */
    private String dealStatus;

    /**
     * 业务编号
     */
    private String interfId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件名前缀
     */
    private String fileCode;

    /**
     * 文件批次号
     */
    private String fileNo;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 消息列表
     */
    private String message;

    /**
     * 取得 通知类型
     * 
     * @return 通知类型
     */
    public String getCallType() {
        return this.callType;
    }

    /**
     * 设置 通知类型
     * 
     * @param callType 通知类型
     */
    public void setCallType(String callType) {
        this.callType = callType;
    }

    /**
     * 取得 处理状态
     * 
     * @return 处理状态
     */
    public String getDealStatus() {
        return this.dealStatus;
    }

    /**
     * 设置 处理状态
     * 
     * @param dealStatus 处理状态
     */
    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    /**
     * 取得 业务编号
     * 
     * @return 业务编号
     */
    public String getInterfId() {
        return this.interfId;
    }

    /**
     * 设置 业务编号
     * 
     * @param interfId 业务编号
     */
    public void setInterfId(String interfId) {
        this.interfId = interfId;
    }

    /**
     * 取得 文件名
     * 
     * @return 文件名
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * 设置 文件名
     * 
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 取得 文件名前缀
     * 
     * @return 文件名前缀
     */
    public String getFileCode() {
        return this.fileCode;
    }

    /**
     * 设置 文件名前缀
     * 
     * @param fileCode 文件名前缀
     */
    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    /**
     * 取得 文件批次号
     * 
     * @return 文件批次号
     */
    public String getFileNo() {
        return this.fileNo;
    }

    /**
     * 设置 文件批次号
     * 
     * @param fileNo 文件批次号
     */
    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    /**
     * 取得 文件路径
     * 
     * @return 文件路径
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * 设置 文件路径
     * 
     * @param filePath 文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 取得 消息列表
     * 
     * @return 消息列表
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * 设置 消息列表
     * 
     * @param message 消息列表
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
