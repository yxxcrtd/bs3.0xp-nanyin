/*
 * 文件名：RskInterfDataDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年11月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.riskparamcalc.dto;

import java.util.Map;

import com.joyintech.framework.base.BaseEntity;


/**
 * 预占实占报文内容
 * 
 * @author zhangrui1
 * @version 1.0 2020年11月3日
 * @see RskInterfDataDto
 * @since 1.0
 */
public class RskInterfDataDto extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -4837331920242259366L;

    /**
     * 报文流水号
     */
    private String seqNo;

    /**
     * 序号
     */
    private String orderNo;

    /**
     * 事件编号
     */
    private String eventId;

    /**
     * 报文体
     */
    private String requestData;

    /**
     * 接口代码
     */
    private String infCode;

    /**
     * 实占对应的预占编号
     */
    private String preOccupyCode;

    /**
     * 获取 报文流水号
     * 
     * @return 报文流水号.
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 报文流水号
     * 
     * @param seqNo 报文流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * 获取 序号
     * 
     * @return 序号.
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置 序号
     * 
     * @param orderNo 序号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取 事件编号
     * 
     * @return 事件编号.
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * 设置 事件编号
     * 
     * @param eventId 事件编号
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * 获取 报文体
     * 
     * @return 报文体.
     */
    public String getRequestData() {
        return requestData;
    }

    /**
     * 设置 报文体
     * 
     * @param requestData 报文体
     */
    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    /**
     * 获取 接口代码
     * 
     * @return 接口代码.
     */
    public String getInfCode() {
        return infCode;
    }

    /**
     * 设置 接口代码
     * 
     * @param infCode 接口代码
     */
    public void setInfCode(String infCode) {
        this.infCode = infCode;
    }

    /**
     * 获取 实占对应的预占编号
     * 
     * @return 实占对应的预占编号.
     */
    public String getPreOccupyCode() {
        return preOccupyCode;
    }

    /**
     * 设置 实占对应的预占编号
     * 
     * @param preOccupyCode 实占对应的预占编号
     */
    public void setPreOccupyCode(String preOccupyCode) {
        this.preOccupyCode = preOccupyCode;
    }

    /**
     * 报文体
     */
    private Map<String, Object> dataMap;

    /**
     * 获取 报文体
     * 
     * @return 报文体.
     */
    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    /**
     * 设置 报文体
     * 
     * @param dataMap 报文体
     */
    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
