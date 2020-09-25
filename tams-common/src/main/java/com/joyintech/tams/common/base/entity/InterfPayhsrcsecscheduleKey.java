package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class InterfPayhsrcsecscheduleKey extends BaseEntity {
    /**
     * 债券ID：xxxx.IB/xxxx.SZ
     */
    private String secid;

    /**
     * 现金流类型 
     */
    private String schedutype;

    /**
     * 实际付息起始日
     */
    private Date intereststartday;

    /**
     * 实际付息结束日
     */
    private Date interestendday;

    /**
     * 数据来源
     */
    private String source;

    /**
     * 取得 债券ID：xxxx.IB/xxxx.SZ
     * @return 债券ID：xxxx.IB/xxxx.SZ
     */
    public String getSecid() {
        return secid;
    }

    /**
     * 设置 债券ID：xxxx.IB/xxxx.SZ
     * @param secid 债券ID：xxxx.IB/xxxx.SZ
     */
    public void setSecid(String secid) {
        this.secid = secid == null ? null : secid.trim();
    }

    /**
     * 取得 现金流类型 
     * @return 现金流类型 
     */
    public String getSchedutype() {
        return schedutype;
    }

    /**
     * 设置 现金流类型 
     * @param schedutype 现金流类型 
     */
    public void setSchedutype(String schedutype) {
        this.schedutype = schedutype == null ? null : schedutype.trim();
    }

    /**
     * 取得 实际付息起始日
     * @return 实际付息起始日
     */
    public Date getIntereststartday() {
        return intereststartday;
    }

    /**
     * 设置 实际付息起始日
     * @param intereststartday 实际付息起始日
     */
    public void setIntereststartday(Date intereststartday) {
        this.intereststartday = intereststartday;
    }

    /**
     * 取得 实际付息结束日
     * @return 实际付息结束日
     */
    public Date getInterestendday() {
        return interestendday;
    }

    /**
     * 设置 实际付息结束日
     * @param interestendday 实际付息结束日
     */
    public void setInterestendday(Date interestendday) {
        this.interestendday = interestendday;
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置 数据来源
     * @param source 数据来源
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
}