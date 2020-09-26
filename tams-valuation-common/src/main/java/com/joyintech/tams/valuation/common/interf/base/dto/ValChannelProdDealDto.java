package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.Date;

import com.joyintech.tams.common.base.entity.TrdProductDeal;


/**
 * 理财产品借通道投资交易
 */
public class ValChannelProdDealDto extends TrdProductDeal {

    private static final long serialVersionUID = 1L;

    /**
     * 子账套代码
     */
    private String booksetIdSon;

    /**
     * 子账套名称
     */
    private String booksetNameSon;

    /**
     * 账套当前日期
     */
    private Date booksetDate;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 账套全称
     */
    private String booksetFullname;

    /**
     * 账套类别
     */
    private String booksetType;

    /**
     * 核算方式
     */
    private String acctMode;

    /**
     * 特殊核算模型
     */
    private String bokSpecMode;

    /**
     * 核算主体代码
     */
    private String acctSubjectId;

    /**
     * 核算主体类别
     */
    private String acctSubjectType;

    /**
     * 本位币
     */
    private String standardCcy;

    /**
     * 产品代码
     */
    private String prodId;

    public String getBooksetIdSon() {
        return booksetIdSon;
    }

    public void setBooksetIdSon(String booksetIdSon) {
        this.booksetIdSon = booksetIdSon;
    }

    public String getBooksetNameSon() {
        return booksetNameSon;
    }

    public void setBooksetNameSon(String booksetNameSon) {
        this.booksetNameSon = booksetNameSon;
    }

    public Date getBooksetDate() {
        return booksetDate;
    }

    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getBooksetName() {
        return booksetName;
    }

    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    public String getBooksetFullname() {
        return booksetFullname;
    }

    public void setBooksetFullname(String booksetFullname) {
        this.booksetFullname = booksetFullname;
    }

    public String getBooksetType() {
        return booksetType;
    }

    public void setBooksetType(String booksetType) {
        this.booksetType = booksetType;
    }

    public String getAcctMode() {
        return acctMode;
    }

    public void setAcctMode(String acctMode) {
        this.acctMode = acctMode;
    }

    public String getBokSpecMode() {
        return bokSpecMode;
    }

    public void setBokSpecMode(String bokSpecMode) {
        this.bokSpecMode = bokSpecMode;
    }

    public String getAcctSubjectId() {
        return acctSubjectId;
    }

    public void setAcctSubjectId(String acctSubjectId) {
        this.acctSubjectId = acctSubjectId;
    }

    public String getAcctSubjectType() {
        return acctSubjectType;
    }

    public void setAcctSubjectType(String acctSubjectType) {
        this.acctSubjectType = acctSubjectType;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getStandardCcy() {
        return standardCcy;
    }

    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

}
