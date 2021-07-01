/*
 * 文件名：BokBankPositionDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：qiangchengxi
 * 修改时间：2019年8月28日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.valuation.batch.psbc.bankcalbook.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行账余额dto
 * 
 * @author wangyang1
 * @version 1.0 2019年8月28日
 * @see BokBankPositionDto
 * @since 1.0
 */
public class BokBankPositionDto extends BaseEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /*金融产品代码*/  
    private String finprodId ;  
    /*余额日期*/  
    private Date balanceDate;  
    /*科目号*/
    private String subjectNo ; 
    /*账套号*/
    private String booksetId;  
    /*父科目号*/  
    private String fsubjectNo;  
    /*科目级别*/  
    private Long subjectLevel;  
    /*余额方向*/  
    private String balFlag;  
    /*原币*/  
    private String oCcy;  
    /*原币余额*/  
    private BigDecimal oAmt;  
    /*本位币*/
    private String bCcy;  
    /*本位币余额*/
    private BigDecimal bAmt;  
    /*是否叶子节点*/  
    private String isLeaf;  
    /*数量余额*/  
    private BigDecimal numAmt;
    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }
    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
    
    /**
     * 获取 balanceDate
     * @return balanceDate.
     */
    public Date getBalanceDate() {
        return balanceDate;
    }
    
    /**
     * 设置 balanceDate
     * @param balanceDate balanceDate
     */
    public void setBalanceDate(Date balanceDate) {
        this.balanceDate = balanceDate;
    }
    
    /**
     * 获取 subjectNo
     * @return subjectNo.
     */
    public String getSubjectNo() {
        return subjectNo;
    }
    
    /**
     * 设置 subjectNo
     * @param subjectNo subjectNo
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }
    
    /**
     * 获取 fsubjectNo
     * @return fsubjectNo.
     */
    public String getFsubjectNo() {
        return fsubjectNo;
    }
    
    /**
     * 设置 fsubjectNo
     * @param fsubjectNo fsubjectNo
     */
    public void setFsubjectNo(String fsubjectNo) {
        this.fsubjectNo = fsubjectNo;
    }
    
    /**
     * 获取 subjectLevel
     * @return subjectLevel.
     */
    public Long getSubjectLevel() {
        return subjectLevel;
    }
    
    /**
     * 设置 subjectLevel
     * @param subjectLevel subjectLevel
     */
    public void setSubjectLevel(Long subjectLevel) {
        this.subjectLevel = subjectLevel;
    }
    
    /**
     * 获取 balFlag
     * @return balFlag.
     */
    public String getBalFlag() {
        return balFlag;
    }
    
    /**
     * 设置 balFlag
     * @param balFlag balFlag
     */
    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag;
    }
    
    /**
     * 获取 oCcy
     * @return oCcy.
     */
    public String getoCcy() {
        return oCcy;
    }
    
    /**
     * 设置 oCcy
     * @param oCcy oCcy
     */
    public void setoCcy(String oCcy) {
        this.oCcy = oCcy;
    }
    
    /**
     * 获取 oAmt
     * @return oAmt.
     */
    public BigDecimal getoAmt() {
        return oAmt;
    }
    
    /**
     * 设置 oAmt
     * @param oAmt oAmt
     */
    public void setoAmt(BigDecimal oAmt) {
        this.oAmt = oAmt;
    }
    
    /**
     * 获取 bCcy
     * @return bCcy.
     */
    public String getbCcy() {
        return bCcy;
    }
    
    /**
     * 设置 bCcy
     * @param bCcy bCcy
     */
    public void setbCcy(String bCcy) {
        this.bCcy = bCcy;
    }
    
    /**
     * 获取 bAmt
     * @return bAmt.
     */
    public BigDecimal getbAmt() {
        return bAmt;
    }
    
    /**
     * 设置 bAmt
     * @param bAmt bAmt
     */
    public void setbAmt(BigDecimal bAmt) {
        this.bAmt = bAmt;
    }
    
    /**
     * 获取 isLeaf
     * @return isLeaf.
     */
    public String getIsLeaf() {
        return isLeaf;
    }
    
    /**
     * 设置 isLeaf
     * @param isLeaf isLeaf
     */
    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }
    
    /**
     * 获取 numAmt
     * @return numAmt.
     */
    public BigDecimal getNumAmt() {
        return numAmt;
    }
    
    /**
     * 设置 numAmt
     * @param numAmt numAmt
     */
    public void setNumAmt(BigDecimal numAmt) {
        this.numAmt = numAmt;
    }

    
    /**
     * 获取 booksetId
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    
    /**
     * 设置 booksetId
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    } 
    
    
}
