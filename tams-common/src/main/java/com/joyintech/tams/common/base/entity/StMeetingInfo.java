package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class StMeetingInfo extends BaseEntity {
    /**
     * 股东大会编号
     */
    private String meetingseq;

    /**
     * 股东大会名称
     */
    private String meetingdesc;

    /**
     * 公司代码，同资产代码
     */
    private String companycode;

    /**
     * 公司名称，同资产名称
     */
    private String companyinfo;

    /**
     * 股东大会开始日期
     */
    private Date meetingdatebegin;

    /**
     * 股东大会结束日期
     */
    private Date meetingdateend;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 取得 股东大会编号
     * @return 股东大会编号
     */
    public String getMeetingseq() {
        return meetingseq;
    }

    /**
     * 设置 股东大会编号
     * @param meetingseq 股东大会编号
     */
    public void setMeetingseq(String meetingseq) {
        this.meetingseq = meetingseq == null ? null : meetingseq.trim();
    }

    /**
     * 取得 股东大会名称
     * @return 股东大会名称
     */
    public String getMeetingdesc() {
        return meetingdesc;
    }

    /**
     * 设置 股东大会名称
     * @param meetingdesc 股东大会名称
     */
    public void setMeetingdesc(String meetingdesc) {
        this.meetingdesc = meetingdesc == null ? null : meetingdesc.trim();
    }

    /**
     * 取得 公司代码，同资产代码
     * @return 公司代码，同资产代码
     */
    public String getCompanycode() {
        return companycode;
    }

    /**
     * 设置 公司代码，同资产代码
     * @param companycode 公司代码，同资产代码
     */
    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }

    /**
     * 取得 公司名称，同资产名称
     * @return 公司名称，同资产名称
     */
    public String getCompanyinfo() {
        return companyinfo;
    }

    /**
     * 设置 公司名称，同资产名称
     * @param companyinfo 公司名称，同资产名称
     */
    public void setCompanyinfo(String companyinfo) {
        this.companyinfo = companyinfo == null ? null : companyinfo.trim();
    }

    /**
     * 取得 股东大会开始日期
     * @return 股东大会开始日期
     */
    public Date getMeetingdatebegin() {
        return meetingdatebegin;
    }

    /**
     * 设置 股东大会开始日期
     * @param meetingdatebegin 股东大会开始日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMeetingdatebegin(Date meetingdatebegin) {
        this.meetingdatebegin = meetingdatebegin;
    }

    /**
     * 取得 股东大会结束日期
     * @return 股东大会结束日期
     */
    public Date getMeetingdateend() {
        return meetingdateend;
    }

    /**
     * 设置 股东大会结束日期
     * @param meetingdateend 股东大会结束日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMeetingdateend(Date meetingdateend) {
        this.meetingdateend = meetingdateend;
    }

    /**
     * 取得 交易日期
     * @return 交易日期
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 交易日期
     * @param tradeDate 交易日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }
}