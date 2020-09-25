/*
 * 文件名：CashDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年4月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.interf.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

/**
 * 账户余额实体类入口
 * 
 * @author fanshuanghu
 * @version 1.0 2019年4月11日
 * @see CashDto
 * @since 1.0
 */
public class CashDto {

    /**
     * 验证标志
     */
    private String validationFlag;

    /**
     * 账号
     */
    private String acctNo;

    /**
     * 机构(分行)号
     */
    private String branchId;

    /**
     * 币种
     */
    private String ccy;
    /**
     * 止息日
     */
    private String valueEndDate;

    /**
     * 业务类型
     */
    private String bussType;

    /**
     * 分账户序号
     */
    private String branchAcctNoSeq;

    /**
     * 子账户序号
     */
    private String subAcctSerialNo;

    /**
     * 起始日期
     */
    private String startDate;

    /**
     * 终止日期
     */
    private String endDate;

    /**
     * 金额
     */
    private BigDecimal prinamt;

    /**
     * 余额
     */
    private BigDecimal reprinamt;

    /**
     * 日期
     */
    private Date cdate;

    /**
     * 实体类集合
     */
    private List<CashDto> cashList;

    /**
     * validationFlag
     * 
     * @return validationFlag
     */
    public String getValidationFlag() {
        return validationFlag;
    }

    /**
     * validationFlag
     * 
     * @param validationFlag
     */
    public void setValidationFlag(String validationFlag) {
        this.validationFlag = validationFlag;
    }

    /**
     * acctNo
     * 
     * @return acctNo
     */
    @Required
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * acctNo
     * 
     * @param acctNo
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    /**
     * branchId
     * 
     * @return branchId
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * branchId
     * 
     * @param branchId
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * ccy
     * 
     * @return ccy
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * ccy
     * 
     * @param ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * bussType
     * 
     * @return bussType
     */
    public String getBussType() {
        return bussType;
    }

    /**
     * bussType
     * 
     * @param bussType
     */
    public void setBussType(String bussType) {
        this.bussType = bussType;
    }

    /**
     * branchAcctNoSeq
     * 
     * @return branchAcctNoSeq
     */
    public String getBranchAcctNoSeq() {
        return branchAcctNoSeq;
    }

    /**
     * branchAcctNoSeq
     * 
     * @param branchAcctNoSeq
     */
    public void setBranchAcctNoSeq(String branchAcctNoSeq) {
        this.branchAcctNoSeq = branchAcctNoSeq;
    }

    /**
     * subAcctSerialNo
     * 
     * @return subAcctSerialNo
     */
    public String getSubAcctSerialNo() {
        return subAcctSerialNo;
    }

    /**
     * subAcctSerialNo
     * 
     * @param subAcctSerialNo
     */
    public void setSubAcctSerialNo(String subAcctSerialNo) {
        this.subAcctSerialNo = subAcctSerialNo;
    }

    /**
     * startDate
     * 
     * @return startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * startDate
     * 
     * @param startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * endDate
     * 
     * @return endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * endDate
     * 
     * @param endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * cashList
     * 
     * @param cashList
     */
    public List<CashDto> getCashList() {
        return cashList;
    }

    /**
     * cashList
     * 
     * @param cashList
     */
    public void setCashList(List<CashDto> cashList) {
        this.cashList = cashList;
    }

    /**
     * prinamt
     * 
     * @return prinamt
     */
    public BigDecimal getPrinamt() {
        return prinamt;
    }

    /**
     * prinamt
     * 
     * @param prinamt prinamt
     */
    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
    }

    /**
     * reprinamt
     * 
     * @return reprinamt
     */
    public BigDecimal getReprinamt() {
        return reprinamt;
    }

    /**
     * reprinamt
     * 
     * @param reprinamt reprinamt
     */
    public void setReprinamt(BigDecimal reprinamt) {
        this.reprinamt = reprinamt;
    }

    /**
     * cdate
     * 
     * @return cdate
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * cdate
     * 
     * @param cdate
     */
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    
    /**
     * 获取 valueEndDate
     * @return valueEndDate.
     */
    public String getValueEndDate() {
        return valueEndDate;
    }

    
    /**
     * 设置 valueEndDate
     * @param valueEndDate valueEndDate
     */
    public void setValueEndDate(String valueEndDate) {
        this.valueEndDate = valueEndDate;
    }

}
