/*
 * 文件名：OwnerEquityChange001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.ownerequitychangenew.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.common.base.entity.BokBalance;

import java.math.BigDecimal;

;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author huanghao
 * @version 1.0 2020年5月31日
 * @see OwnerEquityChangeNew001Dto
 * @since 1.0
 */
public class OwnerEquityChangeNew001Dto extends BokBalance implements ITableRow {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2016667813772620305L;

    /**
     * 项目
     */
    private String project;

    /**
     * 报表期限类型
     */
    @NotEmpty(fieldName = "OwnerEquityChange001.reportTermType")
    private String reportTermType;

    /**
     * 日期
     */
    @NotEmpty(fieldName = "OwnerEquityChange001.valDate")
    private String valDate;

    /**
     * 账套
     */
    @NotEmpty(fieldName = "OwnerEquityChange001.booksetId")
    private String booksetId;

    /**
     * 本期开始日
     */
    private String valueDateStart;

    /**
     * 本期结束日
     */
    private String valueDateEnd;

    /**
     * 期限明细
     */
    private String reportTerm;

    /**
     * 上期维度
     */
    @NotEmpty(fieldName = "ValProfitQuery001.lastDimen")
    private String lastDimen;

    /**
     * 排序号
     */
    private String sortNum;

    /**
     * 本期金额_实收基金
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tIncomeFund;

    /**
     * 本期金额_未分配利润
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tUnQuarterProfit;

    /**
     * 本期金额_其他综合收益
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tOtherCompIncome;

    public BigDecimal gettOtherCompIncome() {
        return tOtherCompIncome;
    }

    public void settOtherCompIncome(BigDecimal tOtherCompIncome) {
        this.tOtherCompIncome = tOtherCompIncome;
    }

    /**
     * 本期金额_所有者权益合计
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tOwnerEquityTot;

    /**
     * 上期金额_实收基金
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yIncomeFund;

    /**
     * 上期金额_未分配利润
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yUnQuarterProfit;

    /**
     * 上期金额_所有者权益
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yOwnerEquityTot;

    /**
     * 科目类型
     */
    private String subjectType;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 上级科目号
     */
    private String fsubjectId;

    /**
     * 是否核算数量
     */
    private String accQuaFlag;

    /**
     * 是否计息
     */
    private String accIntFlag;

    /**
     * 是否允许透支
     */
    private String overdrawnFlag;

    /**
     * 是否生成四级科目
     */
    private String genDetsubFlag;

    /**
     * 基础科目性质
     */
    private String baseSubjectNature;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 证券管理户代码
     */
    private String secManageAcctId;

    /**
     * 项目
     */
    public String getProject() {
        return project;
    }

    /**
     * 项目
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * 报表期限类型
     */
    public String getReportTermType() {
        return reportTermType;
    }

    /**
     * 报表期限类型
     */
    public void setReportTermType(String reportTermType) {
        this.reportTermType = reportTermType;
    }

    /**
     * 日期
     */
    public String getValDate() {
        return valDate;
    }

    /**
     * 日期
     */
    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    /**
     * 账套
     */
    @Override
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 账套
     */
    @Override
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 本期开始日
     */
    public String getValueDateStart() {
        return valueDateStart;
    }

    /**
     * 本期开始日
     */
    public void setValueDateStart(String valueDateStart) {
        this.valueDateStart = valueDateStart;
    }

    /**
     * 本期结束日
     */
    public String getValueDateEnd() {
        return valueDateEnd;
    }

    /**
     * 本期结束日
     */
    public void setValueDateEnd(String valueDateEnd) {
        this.valueDateEnd = valueDateEnd;
    }

    /**
     * 获取 期限明细
     * 
     * @return 期限明细.
     */
    public String getReportTerm() {
        return reportTerm;
    }

    /**
     * 设置 期限明细
     * 
     * @param reportTerm 期限明细
     */
    public void setReportTerm(String reportTerm) {
        this.reportTerm = reportTerm;
    }

    /**
     * 上期维度
     */
    public String getLastDimen() {
        return lastDimen;
    }

    /**
     * 上期维度
     */
    public void setLastDimen(String lastDimen) {
        this.lastDimen = lastDimen;
    }

    /**
     * 排序号
     */
    public String getSortNum() {
        return sortNum;
    }

    /**
     * 排序号
     */
    public void setSortNum(String sortNum) {
        this.sortNum = sortNum;
    }

    /**
     * 本期金额_实收基金
     */
    public BigDecimal gettIncomeFund() {
        return tIncomeFund;
    }

    /**
     * 本期金额_实收基金
     */
    public void settIncomeFund(BigDecimal tIncomeFund) {
        this.tIncomeFund = tIncomeFund;
    }

    /**
     * 本期金额_未分配利润
     */
    public BigDecimal gettUnQuarterProfit() {
        return tUnQuarterProfit;
    }

    /**
     * 本期金额_未分配利润
     */
    public void settUnQuarterProfit(BigDecimal tUnQuarterProfit) {
        this.tUnQuarterProfit = tUnQuarterProfit;
    }

    /**
     * 本期金额_所有者权益合计
     */
    public BigDecimal gettOwnerEquityTot() {
        return tOwnerEquityTot;
    }

    /**
     * 本期金额_所有者权益合计
     */
    public void settOwnerEquityTot(BigDecimal tOwnerEquityTot) {
        this.tOwnerEquityTot = tOwnerEquityTot;
    }

    /**
     * 上期金额_实收基金
     */
    public BigDecimal getyIncomeFund() {
        return yIncomeFund;
    }

    /**
     * 上期金额_实收基金
     */
    public void setyIncomeFund(BigDecimal yIncomeFund) {
        this.yIncomeFund = yIncomeFund;
    }

    /**
     * 上期金额_未分配利润
     */
    public BigDecimal getyUnQuarterProfit() {
        return yUnQuarterProfit;
    }

    /**
     * 上期金额_未分配利润
     */
    public void setyUnQuarterProfit(BigDecimal yUnQuarterProfit) {
        this.yUnQuarterProfit = yUnQuarterProfit;
    }

    /**
     * 上期金额_所有者权益
     */
    public BigDecimal getyOwnerEquityTot() {
        return yOwnerEquityTot;
    }

    /**
     * 上期金额_所有者权益
     */
    public void setyOwnerEquityTot(BigDecimal yOwnerEquityTot) {
        this.yOwnerEquityTot = yOwnerEquityTot;
    }

    /**
     * 获取 subjectType
     * 
     * @return subjectType.
     */
    public String getSubjectType() {
        return subjectType;
    }

    /**
     * 设置 subjectType
     * 
     * @param subjectType subjectType
     */
    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    /**
     * 获取 subjectName
     * 
     * @return subjectName.
     */
    public String getSubjectName() {
        return subjectName;
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
     * 获取 fsubjectId
     * 
     * @return fsubjectId.
     */
    public String getFsubjectId() {
        return fsubjectId;
    }

    /**
     * 设置 fsubjectId
     * 
     * @param fsubjectId fsubjectId
     */
    public void setFsubjectId(String fsubjectId) {
        this.fsubjectId = fsubjectId;
    }

    /**
     * 获取 accQuaFlag
     * 
     * @return accQuaFlag.
     */
    public String getAccQuaFlag() {
        return accQuaFlag;
    }

    /**
     * 设置 accQuaFlag
     * 
     * @param accQuaFlag accQuaFlag
     */
    public void setAccQuaFlag(String accQuaFlag) {
        this.accQuaFlag = accQuaFlag;
    }

    /**
     * 获取 accIntFlag
     * 
     * @return accIntFlag.
     */
    public String getAccIntFlag() {
        return accIntFlag;
    }

    /**
     * 设置 accIntFlag
     * 
     * @param accIntFlag accIntFlag
     */
    public void setAccIntFlag(String accIntFlag) {
        this.accIntFlag = accIntFlag;
    }

    /**
     * 获取 overdrawnFlag
     * 
     * @return overdrawnFlag.
     */
    public String getOverdrawnFlag() {
        return overdrawnFlag;
    }

    /**
     * 设置 overdrawnFlag
     * 
     * @param overdrawnFlag overdrawnFlag
     */
    public void setOverdrawnFlag(String overdrawnFlag) {
        this.overdrawnFlag = overdrawnFlag;
    }

    /**
     * 获取 genDetsubFlag
     * 
     * @return genDetsubFlag.
     */
    public String getGenDetsubFlag() {
        return genDetsubFlag;
    }

    /**
     * 设置 genDetsubFlag
     * 
     * @param genDetsubFlag genDetsubFlag
     */
    public void setGenDetsubFlag(String genDetsubFlag) {
        this.genDetsubFlag = genDetsubFlag;
    }

    /**
     * 获取 baseSubjectNature
     * 
     * @return baseSubjectNature.
     */
    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }

    /**
     * 设置 baseSubjectNature
     * 
     * @param baseSubjectNature baseSubjectNature
     */
    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature;
    }

    /**
     * 获取 invAim
     * 
     * @return invAim.
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 invAim
     * 
     * @param invAim invAim
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    /**
     * 获取 secManageAcctId
     * 
     * @return secManageAcctId.
     */
    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    /**
     * 设置 secManageAcctId
     * 
     * @param secManageAcctId secManageAcctId
     */
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

}
