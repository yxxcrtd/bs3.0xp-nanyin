/*
 * 文件名：ValueAddedTax001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年9月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.valueaddedtax.dto;

import java.util.Date;

public class BooksetInfoDto {

    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 账套结束日
     */
    private Date endDate;
    
    /**
     * 当前日期
     */
    private Date booksetDate;
    
    /**
     * 收益标识
     * @return
     */
    private String signFlag;
    
    /**
     * 金融产品类型_理财产品
     */
    private String finprodType;
    
    /**
     * 核算主体类别_理财产品
     */
    private String acctSubjectType;
    
    /**
     * 现金流二级类型_税额
     */
    private String cashType2;
    
    /**
     * 征期开始日
     */
    private Date declareStart;

    public String getBooksetId() {
        return booksetId;
    }
    
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(String signFlag) {
        this.signFlag = signFlag;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getBooksetDate() {
        return booksetDate;
    }

    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getAcctSubjectType() {
        return acctSubjectType;
    }

    public void setAcctSubjectType(String acctSubjectType) {
        this.acctSubjectType = acctSubjectType;
    }

    public String getCashType2() {
        return cashType2;
    }

    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2;
    }

    public Date getDeclareStart() {
        return declareStart;
    }

    public void setDeclareStart(Date declareStart) {
        this.declareStart = declareStart;
    }
    
}
