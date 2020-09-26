/*
 * 文件名：ValBokBalanceDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.common.accounting.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.base.entity.BokBalTableData;
import com.joyintech.tams.common.base.entity.BokBalance;


public class ValBokBalanceDto extends BokBalance {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3743220650583563224L;

    /**
     * 科目名称
     */
    private String subjectName;
    
    /**
     * 原币
     * 期初借或贷
     */
    private String oOpeningCdFlag;
    /**
     * 原币
     * 期初余额
     */
    private BigDecimal oOpeningBalance;
    
    /**
     * 原币
     * 本期发生额-贷方
     */
    private BigDecimal oHappenAmtC;

    /**
     * 原币
     * 本期发生额-借方
     */
    private BigDecimal oHappenAmtD;
    
    /**
     * 原币
     * 期末借或贷
     */
    private String oEndingCdFlag;
    /**
     * 原币
     * 期末余额
     */
    private BigDecimal oEndingBalance;

    /**
     * 本位币
     * 期初借或贷
     */
    private String bOpeningCdFlag;
    /**
     * 本位币
     * 期初余额
     */
    private BigDecimal bOpeningBalance;
    
    /**
     * 本位币
     * 本期发生额-贷方
     */
    private BigDecimal bHappenAmtC;

    /**
     * 本位币
     * 本期发生额-借方
     */
    private BigDecimal bHappenAmtD;
    
    /**
     * 本位币
     * 期末借或贷
     */
    private String bEndingCdFlag;
    /**
     * 本位币
     * 期末余额
     */
    private BigDecimal bEndingBalance;

    
    public String getSubjectName() {
        return subjectName;
    }

    
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    
    public String getoOpeningCdFlag() {
        return oOpeningCdFlag;
    }

    
    public void setoOpeningCdFlag(String oCdFlag) {
        this.oOpeningCdFlag = oCdFlag;
    }

    
    public BigDecimal getoOpeningBalance() {
        return oOpeningBalance;
    }

    
    public void setoOpeningBalance(BigDecimal oOpeningBalance) {
        this.oOpeningBalance = oOpeningBalance;
    }

    
    public BigDecimal getoHappenAmtC() {
        return oHappenAmtC;
    }

    
    public void setoHappenAmtC(BigDecimal oHappenAmtC) {
        this.oHappenAmtC = oHappenAmtC;
    }

    
    public BigDecimal getoHappenAmtD() {
        return oHappenAmtD;
    }

    
    public void setoHappenAmtD(BigDecimal oHappenAmtD) {
        this.oHappenAmtD = oHappenAmtD;
    }

    
    public BigDecimal getoEndingBalance() {
        return oEndingBalance;
    }

    
    public void setoEndingBalance(BigDecimal oEndingBalance) {
        this.oEndingBalance = oEndingBalance;
    }

    
    public String getbOpeningCdFlag() {
        return bOpeningCdFlag;
    }

    
    public void setbOpeningCdFlag(String bCdFlag) {
        this.bOpeningCdFlag = bCdFlag;
    }

    
    public BigDecimal getbOpeningBalance() {
        return bOpeningBalance;
    }

    
    public void setbOpeningBalance(BigDecimal bOpeningBalance) {
        this.bOpeningBalance = bOpeningBalance;
    }

    
    public BigDecimal getbHappenAmtC() {
        return bHappenAmtC;
    }

    
    public void setbHappenAmtC(BigDecimal bHappenAmtC) {
        this.bHappenAmtC = bHappenAmtC;
    }

    
    public BigDecimal getbHappenAmtD() {
        return bHappenAmtD;
    }

    
    public void setbHappenAmtD(BigDecimal bHappenAmtD) {
        this.bHappenAmtD = bHappenAmtD;
    }

    
    public BigDecimal getbEndingBalance() {
        return bEndingBalance;
    }

    
    public void setbEndingBalance(BigDecimal bEndingBalance) {
        this.bEndingBalance = bEndingBalance;
    }


    
    public String getoEndingCdFlag() {
        return oEndingCdFlag;
    }


    
    public void setoEndingCdFlag(String oEndingCdFlag) {
        this.oEndingCdFlag = oEndingCdFlag;
    }


    
    public String getbEndingCdFlag() {
        return bEndingCdFlag;
    }

    
    public void setbEndingCdFlag(String bEndingCdFlag) {
        this.bEndingCdFlag = bEndingCdFlag;
    }
    
    public BokBalTableData toBokBalTableData(String seqNo, String booksetId,String booksetName,Date balDate) {
        BokBalTableData b = new BokBalTableData();
        BeanUtil.copyPropertiesIgnoreNull(b, this);
        b.setoOpenBalFlag(this.getoOpeningCdFlag());
        b.setoEndBalFlag(this.getoEndingCdFlag());
        b.setoOpenBalance(this.getoOpeningBalance());
        b.setoEndBalance(this.getoEndingBalance());
        b.setbOpenBalFlag(this.getbOpeningCdFlag());
        b.setbEndBalFlag(this.getbEndingCdFlag());
        b.setbOpenBalance(this.getbOpeningBalance());
        b.setbEndBalance(this.getbEndingBalance());
        b.setSeqNo(seqNo);
        b.setBooksetId(booksetId);
        b.setBooksetName(booksetName);
        b.setBalDate(balDate);
        return b;
    }
}
