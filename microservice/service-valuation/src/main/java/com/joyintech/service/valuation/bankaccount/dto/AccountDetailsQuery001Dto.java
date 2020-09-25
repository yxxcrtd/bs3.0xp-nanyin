/*
 * 文件名：AccountDetailsQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.bankaccount.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.tams.constant.DictCode;

/**
 * 账务明细查询-dto
 * 
 * @author niuqianyu
 * @version 1.0 2018年11月1日
 * @see AccountDetailsQuery001Dto
 * @since 1.0
 */
public class AccountDetailsQuery001Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 科目名称
     */
    @NotEmpty(fieldName = "AccountDetailsQuery001.subjectName")
    private String subjectName;

    /**
     * 业务类别
     */
    @NotEmpty(fieldName = "AccountDetailsQuery001.busiType")
    private String busiType;

    /**
     * 查询日期开始日
     */
    @NotEmpty(fieldName = "AccountDetailsQuery001.selectDateBegin")
    private Date selectDateBegin;

    /**
     * 查询日期结束日
     */
    private Date selectDateEnd;

    /**
     * 发生日
     */
    private String happenDate;

    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 币种Code
     */
    private String ccyCode;

    /**
     * 借贷方向
     */
    private String cdFlag;
    
    /**
     * 借贷方向Code
     */
    private String cdFlagCode;

    /**
     * 借方金额
     */
    private BigDecimal dAmt;

    /**
     * 贷方金额
     */
    private BigDecimal cAmt;

    /**
     * 余额方向
     */
    private String balFlag;

    /**
     * 原币余额
     */
    private BigDecimal oAmt;

    /**
     * 业务类别Code
     */
    private String busiTypeCode;

    /**
     * 科目名称Code
     */
    private String subjectNameCode;

    /**
     * 余额方向Code
     */
    private String balFlagCode;

    /**
     * 获取 subjectName
     * 
     * @return subjectName.
     */
    public String getSubjectName() {
        return this.subjectName;
    }

    /**
     * 设置 subjectName
     * 
     * @param subjectName subjectName
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * 获取 busiType
     * 
     * @return busiType.
     */
    public String getBusiType() {
        return this.busiType;
    }

    /**
     * 设置 busiType
     * 
     * @param busiType busiType
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    /**
     * 获取 selectDateBegin
     * 
     * @return selectDateBegin.
     */
    public Date getSelectDateBegin() {
        return selectDateBegin;
    }

    /**
     * 设置 selectDateBegin
     * 
     * @param selectDateBegin selectDateBegin
     */
    public void setSelectDateBegin(Date selectDateBegin) {
        this.selectDateBegin = selectDateBegin;
    }

    /**
     * 获取 selectDateEnd
     * 
     * @return selectDateEnd.
     */
    public Date getSelectDateEnd() {
        return selectDateEnd;
    }

    /**
     * 设置 selectDateEnd
     * 
     * @param selectDateEnd selectDateEnd
     */
    public void setSelectDateEnd(Date selectDateEnd) {
        this.selectDateEnd = selectDateEnd;
    }

    /**
     * 获取 happenDate
     * 
     * @return happenDate.
     */
    public String getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 happenDate
     * 
     * @param happenDate happenDate
     */
    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 获取 ccy
     * 
     * @return ccy.
     */
    public String getCcy() {
        return this.ccy;
    }

    /**
     * 设置 ccy
     * 
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }
    
    

    
    /**
     * 获取 ccyCode
     * @return ccyCode.
     */
    public String getCcyCode() {
        return ccyCode;
    }

    
    /**
     * 设置 ccyCode
     * @param ccyCode ccyCode
     */
    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }

    /**
     * 获取 cdFlag
     * 
     * @return cdFlag.
     */
    public String getCdFlag() {
    	return this.cdFlag;
    }

    /**
     * 设置 cdFlag
     * 
     * @param cdFlag cdFlag
     */
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }

    
    
    /**
     * 获取 cdFlagCode
     * @return cdFlagCode.
     */
    public String getCdFlagCode() {
        return cdFlagCode;
    }

    
    /**
     * 设置 cdFlagCode
     * @param cdFlagCode cdFlagCode
     */
    public void setCdFlagCode(String cdFlagCode) {
        this.cdFlagCode = cdFlagCode;
    }

    /**
     * 获取 dAmt
     * 
     * @return dAmt.
     */
    public BigDecimal getdAmt() {
        return dAmt;
    }

    /**
     * 设置 dAmt
     * 
     * @param dAmt dAmt
     */
    public void setdAmt(BigDecimal dAmt) {
        this.dAmt = dAmt;
    }

    /**
     * 获取 cAmt
     * 
     * @return cAmt.
     */
    public BigDecimal getcAmt() {
        return cAmt;
    }

    /**
     * 设置 cAmt
     * 
     * @param cAmt cAmt
     */
    public void setcAmt(BigDecimal cAmt) {
        this.cAmt = cAmt;
    }

    /**
     * 获取 balFlag
     * 
     * @return balFlag.
     */
    public String getBalFlag() {
    	return this.balFlag;
    }

    /**
     * 设置 balFlag
     * 
     * @param balFlag balFlag
     */
    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag;
    }

    /**
     * 获取 oAmt
     * 
     * @return oAmt.
     */
    public BigDecimal getoAmt() {
        return oAmt;
    }

    /**
     * 设置 oAmt
     * 
     * @param oAmt oAmt
     */
    public void setoAmt(BigDecimal oAmt) {
        this.oAmt = oAmt;
    }

    /**
     * 获取 busiTypeCode
     * 
     * @return busiTypeCode.
     */
    public String getBusiTypeCode() {
        return busiTypeCode;
    }

    /**
     * 设置 busiTypeCode
     * 
     * @param busiTypeCode busiTypeCode
     */
    public void setBusiTypeCode(String busiTypeCode) {
        this.busiTypeCode = busiTypeCode;
    }

    /**
     * 获取 subjectNameCode
     * 
     * @return subjectNameCode.
     */
    public String getSubjectNameCode() {
        return subjectNameCode;
    }

    /**
     * 设置 subjectNameCode
     * 
     * @param subjectNameCode subjectNameCode
     */
    public void setSubjectNameCode(String subjectNameCode) {
        this.subjectNameCode = subjectNameCode;
    }

    /**
     * 获取 balFlagCode
     * 
     * @return balFlagCode.
     */
    public String getBalFlagCode() {
        return balFlagCode;
    }

    /**
     * 设置 balFlagCode
     * 
     * @param balFlagCode balFlagCode
     */
    public void setBalFlagCode(String balFlagCode) {
        this.balFlagCode = balFlagCode;
    }

}
