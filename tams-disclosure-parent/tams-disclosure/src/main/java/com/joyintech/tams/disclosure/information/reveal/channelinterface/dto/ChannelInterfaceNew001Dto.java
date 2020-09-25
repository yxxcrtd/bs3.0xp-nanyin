/*
 * 文件名：ChannelInterfaceNew001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2019年8月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.disclosure.information.reveal.channelinterface.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RepFtpDetailMsg;
import com.joyintech.tams.constant.DictCode;

@PageId("ChannelInterface001")
public class ChannelInterfaceNew001Dto extends RepFtpDetailMsg{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 披露编号
	 */
	private String revealId;
	
	/**
     * 主键
     */
    private String id;

    /**
     * 披露产品编号
     */
    private String prodId;

    /**
     * 披露报表名称
     */
    private String reportId;

    /**
     * 报告期开始日
     */
    private String statrDateStr;

    /**
     * 报告期结束日
     */
    private String endDateStr;

    /**
     * 报表类别
     */
    private String reportCategory;
    
    private String reportCategoryName;
    
    /**
     * 记录主键
     */
    private String detailId;

    /**
     * 发送时间
     */
    private String sendDateStr;

    /**
     * 发送状态
     */
    private String sendStatusStr;

    /**
     * 反馈时间
     */
    private String receiveDateStr;

    /**
     * 反馈状态
     */
    private String receiveStatusStr;

    /**
     * 披露文件名称
     */
    private String fileName;

    /**
     * 披露渠道
     */
    private String channelCodeStr;
    
    private String reportName;
    
    /**
     * 勾选的数据json字符串
     */
    private String data;
    

    /**
     * 备选字段1
     */
    private String att1;

    /**
     * 备选字段2
     */
    private String att2;

    /**
     * 备选字段3
     */
    private String att3;

    /**
     * 备选字段4
     */
    private String att4;
    
    
	public String getRevealId() {
		return revealId;
	}

	public void setRevealId(String revealId) {
		this.revealId = revealId;
	}

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getProdId() {
		return prodId;
	}



	public void setProdId(String prodId) {
		this.prodId = prodId;
	}



	public String getReportId() {
		return reportId;
	}



	public void setReportId(String reportId) {
		this.reportId = reportId;
	}



	public String getStatrDateStr() {
		return statrDateStr;
	}



	public void setStatrDateStr(String statrDateStr) {
		this.statrDateStr = statrDateStr;
	}



	public String getEndDateStr() {
		return endDateStr;
	}



	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}



	public String getReportCategory() {
		return reportCategory;
	}



	public void setReportCategory(String reportCategory) {
		this.reportCategory = reportCategory;
	}



	public String getDetailId() {
		return detailId;
	}



	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}



	public String getSendDateStr() {
		return sendDateStr;
	}



	public void setSendDateStr(String sendDateStr) {
		this.sendDateStr = sendDateStr;
	}


	public String getSendStatusStr() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.披露发送状态, this.getSendStatus()))?
    	        SysDataUtil.getNameFromDataDict(DictCode.披露发送状态, this.getSendStatus()):this.getSendStatus();
	}

	public void setSendStatusStr(String sendStatusStr) {
		this.sendStatusStr = sendStatusStr;
	}


	public String getReceiveDateStr() {
		return receiveDateStr;
	}



	public void setReceiveDateStr(String receiveDateStr) {
		this.receiveDateStr = receiveDateStr;
	}
	
	public String getReceiveStatusStr() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.披露反馈状态, this.getReceiveStatus()))?
    	        SysDataUtil.getNameFromDataDict(DictCode.披露反馈状态, this.getReceiveStatus()):this.getReceiveStatus();
	}

	public void setReceiveStatusStr(String receiveStatusStr) {
		this.receiveStatusStr = receiveStatusStr;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getChannelCodeStr() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.披露渠道, this.getChannelCode()))?
    	        SysDataUtil.getNameFromDataDict(DictCode.披露渠道, this.getChannelCode()):this.getChannelCode();
	}

	public void setChannelCodeStr(String channelCodeStr) {
		this.channelCodeStr = channelCodeStr;
	}



	public String getAtt1() {
		return att1;
	}



	public void setAtt1(String att1) {
		this.att1 = att1;
	}



	public String getAtt2() {
		return att2;
	}



	public void setAtt2(String att2) {
		this.att2 = att2;
	}



	public String getAtt3() {
		return att3;
	}



	public void setAtt3(String att3) {
		this.att3 = att3;
	}



	public String getAtt4() {
		return att4;
	}



	public void setAtt4(String att4) {
		this.att4 = att4;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getReportCategoryName() {
		return reportCategoryName;
	}

	public void setReportCategoryName(String reportCategoryName) {
		this.reportCategoryName = reportCategoryName;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
