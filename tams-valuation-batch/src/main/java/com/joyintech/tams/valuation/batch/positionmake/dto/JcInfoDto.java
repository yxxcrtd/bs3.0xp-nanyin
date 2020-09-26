package com.joyintech.tams.valuation.batch.positionmake.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 价差信息dto
 */
public class JcInfoDto {

    /**
     * 价差收入_原币
     */
    private BigDecimal jcForO;

    /**
     *价差收入_本币
     */
    private BigDecimal jcForB;

    /**
     *账套代码
     */
    private String booksetId;

    /**
     *入账日期
     */
    private Date bookDate;

    /**
     *发生日
     */
    private Date happenDate;

    /**
     *账务明细代码
     */
    private String bokDetailId;

    /**
     *业务明细代码
     */
    private String busiId;

    /**
     *投资目的
     */
    private String invAim;

    /**
     *证券账户
     */
    private String secManageAcctId;


    public BigDecimal getJcForO() {
        return jcForO;
    }

    public void setJcForO(BigDecimal jcForO) {
        this.jcForO = jcForO;
    }

    public BigDecimal getJcForB() {
        return jcForB;
    }

    public void setJcForB(BigDecimal jcForB) {
        this.jcForB = jcForB;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public Date getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    public String getBokDetailId() {
        return bokDetailId;
    }

    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    public String getBusiId() {
        return busiId;
    }

    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    public String getInvAim() {
        return invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }
}
