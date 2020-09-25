/*
 * 文件名：AccountCurrentRatePKDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.accountcurrentrate.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年11月22日
 * @see AccountCurrentRatePKDto
 * @since 1.0
 */
@PageId("AccountCurrentRate002")
public class AccountCurrentRateDto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @PrimaryKey(value = Field.PK_1)
    @NotEmpty(fieldName = "AccountCurrentRate.accountId")
    private String acctNo;

    /**
     * 账户名
     */
    private String acctName;

    /**
     * 账户类型
     */
    @NotEmpty(fieldName = "AccountCurrentRate.accountType")
    private String acctType;

    /**
     * 账户类型Code
     */
    private String acctTypeCode;

    /**
     * 利率(%)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * listAccountCurrentRate001Dto
     */
    private List<AccountCurrentRate001Dto> listAccountCurrentRate001Dto;

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 利率类型
     */
    private String calcType;

    /**
     * 账户名
     */
    private String accountName;

    /**
     * 计息基础
     */
    @NotEmpty(fieldName = "AccountCurrentRate.basis")
    private String basis;

    /**
     * 利率结构
     */
    // @NotEmpty(fieldName = "AccountCurrentRate.intRateStructure")
    private String intRateStructure;

    /**
     * 利率类型
     */
    @NotEmpty(fieldName = "AccountCurrentRate.intType")
    private String intType;

    /**
     * 利率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 基准利率
     */
    private String basicRate;

    /**
     * 生效日
     */
    @PrimaryKey(value = Field.PK_2)
    @NotEmpty(fieldName = "AccountCurrentRate.effDate")
    private String effDate;

    /**
     * 修改后的生效日
     */
    private String effDate1;

    /**
     * 行情代码
     */
    private String intRateId;

    /**
     * 行情名称
     */
    private String rateName;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 intType
     * 
     * @return intType.
     */
    public String getCalcType() {
        return calcType;
    }

    /**
     * calcType
     * 
     * @param calcType calcType
     * @see
     * @since 1.0
     */
    public void setCalcType(String calcType) {
        this.calcType = calcType;
    }

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
     * 
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
     * 获取 basis
     * 
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     * 
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 yield
     * 
     * @return yield.
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 yield
     * 
     * @param yield yield
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 获取 effDate
     * 
     * @return effDate.
     */
    public String getEffDate() {
        return effDate;
    }

    /**
     * 设置 effDate
     * 
     * @param effDate effDate
     */
    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    /**
     * 获取 acctTypeCode
     * 
     * @return acctTypeCode.
     */
    public String getAcctTypeCode() {
        return this.acctTypeCode;
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
     * 获取 cashId
     * 
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 cashId
     * 
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    /**
     * 获取 listAccountCurrentRate001Dto
     * 
     * @return listAccountCurrentRate001Dto.
     */
    public List<AccountCurrentRate001Dto> getListAccountCurrentRate001Dto() {
        return listAccountCurrentRate001Dto;
    }

    /**
     * 设置 listAccountCurrentRate001Dto
     * 
     * @param listAccountCurrentRate001Dto listAccountCurrentRate001Dto
     */
    public void setListAccountCurrentRate001Dto(List<AccountCurrentRate001Dto> listAccountCurrentRate001Dto) {
        this.listAccountCurrentRate001Dto = listAccountCurrentRate001Dto;
    }

    /**
     * 取得 账户名
     * 
     * @return 账户名
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 设置 账户名
     * 
     * @param accountName 账户名
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * 取得 利率结构
     * 
     * @return 利率结构
     */
    public String getIntRateStructure() {
        return intRateStructure;
    }

    /**
     * 设置 利率结构
     * 
     * @param intRateStructure 利率结构
     */
    public void setIntRateStructure(String intRateStructure) {
        this.intRateStructure = intRateStructure;
    }

    /**
     * 取得 利率类型
     * 
     * @return 利率类型
     */
    public String getIntType() {
        return intType;
    }

    /**
     * 设置 利率类型
     * 
     * @param intType 利率类型
     */
    public void setIntType(String intType) {
        this.intType = intType;
    }

    /**
     * 取得 利率
     * 
     * @return 利率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 利率
     * 
     * @param rate 利率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 取得 基准利率
     * 
     * @return 基准利率
     */
    public String getBasicRate() {
        return basicRate;
    }

    /**
     * 设置 基准利率
     * 
     * @param basicRate 基准利率
     */
    public void setBasicRate(String basicRate) {
        this.basicRate = basicRate;
    }

    /**
     * 获取 intRateId
     * 
     * @return intRateId.
     */
    public String getIntRateId() {
        return intRateId;
    }

    /**
     * 设置 intRateId
     * 
     * @param intRateId intRateId
     */
    public void setIntRateId(String intRateId) {
        this.intRateId = intRateId;
    }

    /**
     * 获取 rateName
     * 
     * @return rateName.
     */
    public String getRateName() {
        return rateName;
    }

    /**
     * 设置 rateName
     * 
     * @param rateName rateName
     */
    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    /**
     * 获取 effDate1
     * 
     * @return effDate1.
     */
    public String getEffDate1() {
        return effDate1;
    }

    /**
     * 设置 effDate1
     * 
     * @param effDate1 effDate1
     */
    public void setEffDate1(String effDate1) {
        this.effDate1 = effDate1;
    }

}
