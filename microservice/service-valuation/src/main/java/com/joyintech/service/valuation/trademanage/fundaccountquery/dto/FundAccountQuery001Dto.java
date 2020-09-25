/*
 * 文件名：FundAccountQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.fundaccountquery.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 资金账户查询实体类
 * 
 * @author niuqianyu
 * @version 1.0 2018年10月11日
 * @see FundAccountQuery001Dto
 * @since 1.0
 */
public class FundAccountQuery001Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String acctNo;

    /**
     * 户名
     */
    private String acctName;

    /**
     * 账户类型
     */
    private String acctType;

    /**
     * 账户类型Code
     */
    private String acctTypeCode;

    /**
     * 开户行
     */
    private String bankDep;

    /**
     * 行内行外
     */
    private String inOutBank;

    /**
     * 行内行外Code
     */
    private String inOutBankCode;

    /**
     * 联行行号
     */
    private String corrNo;

    /**
     * 大额支付系统号
     */
    private String largePayno;

    /**
     * 境内境外
     */
    private String blnArea;

    /**
     * 境内境外Code
     */
    private String blnAreaCode;

    /**
     * swiftCode
     */
    private String swiftCode;

    /**
     * 获取 acctNo
     * 
     * @return acctNo.
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * 设置 acctNo
     * 
     * @param acctNo acctNo
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    /**
     * 获取 acctName
     * 
     * @return acctName.
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * 设置 acctName
     * 
     * @param acctName acctName
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    /**
     * 获取 acctType
     * @return acctType.
     */
    public String getAcctType() {
        return acctType;
    }

    /**
     * 设置 acctType
     * 
     * @param acctType acctType
     */
    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    /**
     * 获取 acctTypeCode
     * 
     * @return acctTypeCode.
     */
    public String getAcctTypeCode() {
        return acctTypeCode;
    }

    /**
     * 设置 acctTypeCode
     * 
     * @param acctTypeCode acctTypeCode
     */
    public void setAcctTypeCode(String acctTypeCode) {
        this.acctTypeCode = acctTypeCode;
    }

    /**
     * 获取 bankDep
     * 
     * @return bankDep.
     */
    public String getBankDep() {
        return bankDep;
    }

    /**
     * 设置 bankDep
     * 
     * @param bankDep bankDep
     */
    public void setBankDep(String bankDep) {
        this.bankDep = bankDep;
    }

    /**
     * 获取 inOutBank
     * @return inOutBank.
     */
    public String getInOutBank() {
        return inOutBank;
    }

    /**
     * 设置 inOutBank
     * 
     * @param inOutBank inOutBank
     */
    public void setInOutBank(String inOutBank) {
        this.inOutBank = inOutBank;
    }

    /**
     * 获取 inOutBankCode
     * 
     * @return inOutBankCode.
     */
    public String getInOutBankCode() {
        return inOutBankCode;
    }

    /**
     * 设置 inOutBankCode
     * 
     * @param inOutBankCode inOutBankCode
     */
    public void setInOutBankCode(String inOutBankCode) {
        this.inOutBankCode = inOutBankCode;
    }

    /**
     * 获取 corrNo
     * 
     * @return corrNo.
     */
    public String getCorrNo() {
        return corrNo;
    }

    /**
     * 设置 corrNo
     * 
     * @param corrNo corrNo
     */
    public void setCorrNo(String corrNo) {
        this.corrNo = corrNo;
    }

    /**
     * 获取 largePayno
     * 
     * @return largePayno.
     */
    public String getLargePayno() {
        return largePayno;
    }

    /**
     * 设置 largePayno
     * 
     * @param largePayno largePayno
     */
    public void setLargePayno(String largePayno) {
        this.largePayno = largePayno;
    }

    /**
     * 获取 blnArea
     * @return blnArea.
     */
    public String getBlnArea() {
        return blnArea;
    }

    /**
     * 设置 blnArea
     * 
     * @param blnArea blnArea
     */
    public void setBlnArea(String blnArea) {
        this.blnArea = blnArea;
    }

    /**
     * 获取 blnAreaCode
     * 
     * @return blnAreaCode.
     */
    public String getBlnAreaCode() {
        return blnAreaCode;
    }

    /**
     * 设置 blnAreaCode
     * 
     * @param blnAreaCode blnAreaCode
     */
    public void setBlnAreaCode(String blnAreaCode) {
        this.blnAreaCode = blnAreaCode;
    }

    /**
     * 获取 swiftCode
     * 
     * @return swiftCode.
     */
    public String getSwiftCode() {
        return swiftCode;
    }

    /**
     * 设置 swiftCode
     * 
     * @param swiftCode swiftCode
     */
    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

}
