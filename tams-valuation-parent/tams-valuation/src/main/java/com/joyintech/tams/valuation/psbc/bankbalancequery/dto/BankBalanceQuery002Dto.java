/*
 * 文件名：ValVoucherQuery002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.psbc.bankbalancequery.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.controller.ValVoucherQuery001Controller;

/**
 * 记账凭证详情查看dto
 * 
 * @author wangyang1
 * @version 1.0 2019年8月29日
 * @see BankBalanceQuery002Dto
 * @since 1.0
 */
public class BankBalanceQuery002Dto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 借贷方向
     */
    private String cdFlag;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 金额
     */
    private BigDecimal happenAmt;

    /**
     * 数量
     */
    private BigDecimal happenNumber;

    /**
     * 记账币种
     */
    private String bCcy;

    /**
     * 记账币种
     */
    private String bCcyFmt;

    /**
     * 本币金额
     */
    private BigDecimal bHappenAmt;

    /**
     * 借方金额（本币）
     */
    private BigDecimal dBHappenAmt;

    /**
     * 贷方金额（本币）
     */
    private BigDecimal cBHappenAmt;

    /**
     * 借方金额（原币）
     */
    private BigDecimal dHappenAmt;

    /**
     * 贷方金额（原币）
     */
    private BigDecimal cHappenAmt;

    /**
     * 摘要
     */
    private String vouchRemark;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getdBHappenAmt() {
        return dBHappenAmt;
    }

    public void setdBHappenAmt(BigDecimal dBHappenAmt) {
        this.dBHappenAmt = dBHappenAmt;
    }

    public BigDecimal getcBHappenAmt() {
        return cBHappenAmt;
    }

    public void setcBHappenAmt(BigDecimal cBHappenAmt) {
        this.cBHappenAmt = cBHappenAmt;
    }

    public BigDecimal getdHappenAmt() {
        return dHappenAmt;
    }

    public void setdHappenAmt(BigDecimal dHappenAmt) {
        this.dHappenAmt = dHappenAmt;
    }

    public BigDecimal getcHappenAmt() {
        return cHappenAmt;
    }

    public void setcHappenAmt(BigDecimal cHappenAmt) {
        this.cHappenAmt = cHappenAmt;
    }

    public String getbCcy() {
        return bCcy;
    }

    public void setbCcy(String bCcy) {
        this.bCcy = bCcy;
    }

    public String getbCcyFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, bCcy))?
            SysDataUtil.getNameFromDataDict(DictCode.币种,bCcy):bCcy;
    }

    public void setbCcyFmt(String bCcyFmt) {
        this.bCcyFmt = bCcyFmt;
    }

    public BigDecimal getbHappenAmt() {
        return bHappenAmt;
    }

    public void setbHappenAmt(BigDecimal bHappenAmt) {
        this.bHappenAmt = bHappenAmt;
    }


    /**
     * 取得 科目号
     * 
     * @return 科目号
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 科目号
     * 
     * @param subjectNo 科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    /**
     * 取得 科目名称
     * 
     * @return 科目名称
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置 科目名称
     * 
     * @param subjectName 科目名称
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * 取得 借贷方向
     * 
     * @return 借贷方向
     */
    public String getCdFlag() {
    	 return cdFlag;
//        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag))?
//            SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag):cdFlag;
        
//        return SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag);
    }

    /**
     * 设置 借贷方向
     * 
     * @param cdFlag 借贷方向
     */
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }

    /**
     * 取得 币种
     * 
     * @return 币种
     */
    public String getCcy() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, ccy))?
            SysDataUtil.getNameFromDataDict(DictCode.币种, ccy):ccy;
        
//        return SysDataUtil.getNameFromDataDict(DictCode.币种, ccy);
    }

    /**
     * 设置 币种
     * 
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 取得 金额
     * 
     * @return 金额
     */
    public BigDecimal getHappenAmt() {
        return happenAmt;
    }

    /**
     * 设置 金额
     * 
     * @param happenAmt 金额
     */
    public void setHappenAmt(BigDecimal happenAmt) {
        this.happenAmt = happenAmt;
    }

    /**
     * 取得 数量
     * 
     * @return 数量
     */
    public BigDecimal getHappenNumber() {
        return happenNumber;
    }

    /**
     * 设置 数量
     * 
     * @param happenNumber 数量
     */
    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
    }

    public String getVouchRemark() {
        return vouchRemark;
    }

    public void setVouchRemark(String vouchRemark) {
        this.vouchRemark = vouchRemark;
    }
}
