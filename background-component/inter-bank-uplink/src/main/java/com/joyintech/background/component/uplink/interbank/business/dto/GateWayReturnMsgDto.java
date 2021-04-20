/*
 * 文件名：GateWayReturnMsg.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoguo
 * 修改时间：2020年5月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.uplink.interbank.business.dto;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author caoguo
 * @version 1.0 2020年5月14日
 * @see GateWayReturnMsgDto
 * @since 1.0
 */
public class GateWayReturnMsgDto {
    /**
     * 处理状态 ，true正常，false异常
     */
    private boolean status = true;
    
    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 内部报价编号
     */
    private String quoteId;
    
    /**
     * 版本号
     */
    private Long version;
    
    /**
     * 外部报价编号
     */
    private String pQuoteId;
    
    /**
     * 请求报价明细编号信息
     */
    private List<GateWayReqMsgDTO> reqMsgDto;

    
    /**
     * 获取 status
     * @return status.
     */
    public boolean isStatus() {
        return status;
    }

    
    /**
     * 获取 errCode
     * @return errCode.
     */
    public String getErrCode() {
        return errCode;
    }

    
    /**
     * 获取 errMsg
     * @return errMsg.
     */
    public String getErrMsg() {
        return errMsg;
    }

    
    /**
     * 获取 version
     * @return version.
     */
    public Long getVersion() {
        return version;
    }

    
    /**
     * 获取 pQuoteId
     * @return pQuoteId.
     */
    public String getpQuoteId() {
        return pQuoteId;
    }

    
    /**
     * 获取 reqMsgDto
     * @return reqMsgDto.
     */
    public List<GateWayReqMsgDTO> getReqMsgDto() {
        return reqMsgDto;
    }

    
    /**
     * 设置 status
     * @param status status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    
    /**
     * 设置 errCode
     * @param errCode errCode
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    
    /**
     * 设置 errMsg
     * @param errMsg errMsg
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    
    /**
     * 设置 version
     * @param version version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    
    /**
     * 设置 pQuoteId
     * @param pQuoteId pQuoteId
     */
    public void setpQuoteId(String pQuoteId) {
        this.pQuoteId = pQuoteId;
    }

    /**
     * 设置 reqMsgDto
     * @param reqMsgDto reqMsgDto
     */
    public void setReqMsgDto(List<GateWayReqMsgDTO> reqMsgDto) {
        this.reqMsgDto = reqMsgDto;
    }

    /**
     * 获取 quoteId
     * @return quoteId.
     */
    public String getQuoteId() {
        return quoteId;
    }

    /**
     * 设置 quoteId
     * @param quoteId quoteId
     */
    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

}
