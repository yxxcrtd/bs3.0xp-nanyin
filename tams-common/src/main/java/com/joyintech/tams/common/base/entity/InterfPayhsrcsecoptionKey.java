package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class InterfPayhsrcsecoptionKey extends BaseEntity {
    /**
     * 债券ID：xxxx.IB/xxxx.SZ
     */
    private String secid;

    /**
     * 实际行权日
     */
    private Date optionpayday;

    /**
     * 数据来源
     */
    private String source;

    /**
     * null
     */
    private Date theoryoptionpayday;

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
     * 取得 实际行权日
     * @return 实际行权日
     */
    public Date getOptionpayday() {
        return optionpayday;
    }

    /**
     * 设置 实际行权日
     * @param optionpayday 实际行权日
     */
    public void setOptionpayday(Date optionpayday) {
        this.optionpayday = optionpayday;
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

    /**
     * 取得 null
     * @return null
     */
    public Date getTheoryoptionpayday() {
        return theoryoptionpayday;
    }

    /**
     * 设置 null
     * @param theoryoptionpayday null
     */
    public void setTheoryoptionpayday(Date theoryoptionpayday) {
        this.theoryoptionpayday = theoryoptionpayday;
    }
}