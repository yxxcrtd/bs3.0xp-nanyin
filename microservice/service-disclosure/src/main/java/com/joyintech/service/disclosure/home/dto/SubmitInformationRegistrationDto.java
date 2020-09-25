/*
 * 文件名：SubmitInformationRegistrationDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月19日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.service.disclosure.home.dto;


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
    private String delaynum;
    /**
     * 即将延期统计
     */
    private String delayingnum;
    /**
     * 将报送统计
     */
    private String sendnum;
    /**
     * 合计统计
     */
    private String totalnum;
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
     * 获取 delaynum
     * @return delaynum.
     */
    public String getDelaynum() {
        return delaynum;
    }
    
    /**
     * 设置 delaynum
     * @param delaynum delaynum
     */
    public void setDelaynum(String delaynum) {
        this.delaynum = delaynum;
    }
    
    /**
     * 获取 delayingnum
     * @return delayingnum.
     */
    public String getDelayingnum() {
        return delayingnum;
    }
    
    /**
     * 设置 delayingnum
     * @param delayingnum delayingnum
     */
    public void setDelayingnum(String delayingnum) {
        this.delayingnum = delayingnum;
    }
    
    /**
     * 获取 sendnum
     * @return sendnum.
     */
    public String getSendnum() {
        return sendnum;
    }
    
    /**
     * 设置 sendnum
     * @param sendnum sendnum
     */
    public void setSendnum(String sendnum) {
        this.sendnum = sendnum;
    }
    
    /**
     * 获取 totalnum
     * @return totalnum.
     */
    public String getTotalnum() {
        return totalnum;
    }
    
    /**
     * 设置 totalnum
     * @param totalnum totalnum
     */
    public void setTotalnum(String totalnum) {
        this.totalnum = totalnum;
    }
    
    /**
     * 获取 reporttype
     * @return reporttype.
     */
    public String getReporttype() {
        return reporttype;
    }
    
    /**
     * 设置 reporttype
     * @param reporttype reporttype
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
    }

    
    /**
     * 获取 mindate
     * @return mindate.
     */
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
