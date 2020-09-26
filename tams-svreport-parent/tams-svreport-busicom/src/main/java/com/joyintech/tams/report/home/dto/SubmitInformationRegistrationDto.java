/*
 * 文件名：SubmitInformationRegistrationDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月19日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.home.dto;


/**
 * 主页报送信息登记查询所用Dto
 * @author dingzhipeng
 * @version 1.0 2017年9月19日
 * @see SubmitInformationRegistrationDto
 * @since 1.0
 */
public class SubmitInformationRegistrationDto {
    /**
     * 已延期统计
     */
    private int delaynum;
    /**
     * 即将延期统计
     */
    private int delayingnum;
    /**
     * 将报送统计
     */
    private int sendnum;
    /**
     * 合计统计
     */
    private int totalnum;
    /**
     * 报送类型
     */
    private String reporttype;
    /**
     * 最早的行内截止日
     */
    private String mindate;
    /**
     * 最迟的报送截止日
     */
    private String maxdate;
    
    /**
     * 不通过
     */
    private int failnum;
    /**
     * 预警
     */
    private int warningnum;
    /**
     * 手工处理
     */
    private int manualnum;
    /**
     * 接口通过
     */
    private int interfPassnum;
    /**
     * 接口退回
     */
    private int interfBacknum;
    /**
     * 报送提醒
     */
    private int submitRemindnum;
    /**
     * 报送检查
     */
    private int submitChecknum;
    /**
     * 当日报送完成
     */
    private int submitSuccessnum;
    
    /**
     * 删除模版
     */
    private int deletenum;
    
    private String countType;
    
    public String getCountType() {
		return countType;
	}

	public void setCountType(String countType) {
		this.countType = countType;
	}

    public int getDelaynum() {
		return delaynum;
	}

	public void setDelaynum(int delaynum) {
		this.delaynum = delaynum;
	}

	public int getDelayingnum() {
		return delayingnum;
	}

	public void setDelayingnum(int delayingnum) {
		this.delayingnum = delayingnum;
	}

	public int getSendnum() {
		return sendnum;
	}

	public void setSendnum(int sendnum) {
		this.sendnum = sendnum;
	}

	public int getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}

	public String getReporttype() {
		return reporttype;
	}

	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}

	public int getFailnum() {
		return failnum;
	}

	public void setFailnum(int failnum) {
		this.failnum = failnum;
	}

	public int getWarningnum() {
		return warningnum;
	}

	public void setWarningnum(int warningnum) {
		this.warningnum = warningnum;
	}

	public int getManualnum() {
		return manualnum;
	}

	public void setManualnum(int manualnum) {
		this.manualnum = manualnum;
	}

	public int getInterfPassnum() {
		return interfPassnum;
	}

	public void setInterfPassnum(int interfPassnum) {
		this.interfPassnum = interfPassnum;
	}

	public int getInterfBacknum() {
		return interfBacknum;
	}

	public void setInterfBacknum(int interfBacknum) {
		this.interfBacknum = interfBacknum;
	}

	public int getSubmitRemindnum() {
		return submitRemindnum;
	}

	public void setSubmitRemindnum(int submitRemindnum) {
		this.submitRemindnum = submitRemindnum;
	}

	public int getSubmitChecknum() {
		return submitChecknum;
	}

	public void setSubmitChecknum(int submitChecknum) {
		this.submitChecknum = submitChecknum;
	}

	public int getSubmitSuccessnum() {
		return submitSuccessnum;
	}

	public void setSubmitSuccessnum(int submitSuccessnum) {
		this.submitSuccessnum = submitSuccessnum;
	}

	public int getDeletenum() {
		return deletenum;
	}

	public void setDeletenum(int deletenum) {
		this.deletenum = deletenum;
	}

	public String getMindate() {
		return mindate;
	}

	/**
     * 设置 mindate
     * @param mindate mindate
     */
    public void setMindate(String mindate) {
        this.mindate = mindate;
    }

    
    /**
     * 获取 maxdate
     * @return maxdate.
     */
    public String getMaxdate() {
        return maxdate;
    }

    
    /**
     * 设置 maxdate
     * @param maxdate maxdate
     */
    public void setMaxdate(String maxdate) {
        this.maxdate = maxdate;
    }
}
