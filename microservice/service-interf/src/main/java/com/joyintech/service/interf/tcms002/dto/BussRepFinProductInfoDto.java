/*
 * 文件名：BussRepFinProductInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.tcms002.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.common.base.entity.RepProdDeclare;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Email;
import com.joyintech.framework.validation.annotation.IDCard;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.MobilePhone;
import com.joyintech.framework.validation.annotation.OfficePhone;

/**
 * 产品中债报备信息
 * 
 * @author changwenbin
 * @version 1.0 2018年11月14日
 * @see BussRepFinProductInfoDto
 * @since 1.0
 */
public class BussRepFinProductInfoDto extends RepProdDeclare {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1136074748901229674L;

    /**
     * 主画面传入的pageId
     */
    private String pageId;

    /**
     * 行内标识码/理财产品代码
     */
    private String repBankProdCode;

    /**
     * 募集起始日
     */
    private Date repRaiseVdate;

    /**
     * 募集结束日
     */
    private Date repRaiseEnddate;

    /**
     * 产品起始日期
     */
    private Date repPrdVdate;

    /**
     * 产品终止日期
     */
    private Date repPrdMdate;

    /**
     * 预计客户最高年收益率
     */
    private BigDecimal repExpMaxYield;

    /**
     * 预计客户最低年收益率
     */
    private BigDecimal repExpMinYield;

    /**
     * 节假日是否开放
     */
    private String repHolidayOnFlag;

    public String getRepBankProdCode() {
        return repBankProdCode;
    }

    public void setRepBankProdCode(String repBankProdCode) {
        this.repBankProdCode = repBankProdCode;
    }

    public Date getRepRaiseVdate() {
        return repRaiseVdate;
    }

    public void setRepRaiseVdate(Date repRaiseVdate) {
        this.repRaiseVdate = repRaiseVdate;
    }

    public Date getRepRaiseEnddate() {
        return repRaiseEnddate;
    }

    public void setRepRaiseEnddate(Date repRaiseEnddate) {
        this.repRaiseEnddate = repRaiseEnddate;
    }

    public Date getRepPrdVdate() {
        return repPrdVdate;
    }

    public void setRepPrdVdate(Date repPrdVdate) {
        this.repPrdVdate = repPrdVdate;
    }

    public Date getRepPrdMdate() {
        return repPrdMdate;
    }

    public void setRepPrdMdate(Date repPrdMdate) {
        this.repPrdMdate = repPrdMdate;
    }

    public BigDecimal getRepExpMaxYield() {
        return repExpMaxYield;
    }

    public void setRepExpMaxYield(BigDecimal repExpMaxYield) {
        this.repExpMaxYield = repExpMaxYield;
    }

    public BigDecimal getRepExpMinYield() {
        return repExpMinYield;
    }

    public void setRepExpMinYield(BigDecimal repExpMinYield) {
        this.repExpMinYield = repExpMinYield;
    }

    public String getRepHolidayOnFlag() {
        return repHolidayOnFlag;
    }

    public void setRepHolidayOnFlag(String repHolidayOnFlag) {
        this.repHolidayOnFlag = repHolidayOnFlag;
    }

    // 基础信息传入的属性 end

    /**
     * 平均预计客户年收益率
     */
    private BigDecimal repExpAvgYield;

    /**
     * 收益率分档情况说明
     */
    private String repYieldComment;

    /**
     * 规律开放周期
     */
    private String repOpenCircle;

    /**
     * 其他规律开放周期
     */
    private Integer repOpenCircleDays;

    /**
     * 无规律开放说明
     */
    private String repRuleComment;

    /**
     * 首次开放周期起始日
     */
    private Date repFirstOpenDay;

    /**
     * 平均开放次数（年化）
     */
    private BigDecimal repAvgOpenCount;

    /**
     * 开放期业务
     */
    private String repOpenBuss;

    /**
     * 开放期业务说明
     */
    private String repOpenBussRemark;

    /**
     * 开放类型(验证使用)
     */
    private String openType;

    /**
     * 开放模式(验证使用)
     */
    private String openMode;

    /**
     * 获取 pageId
     * 
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 pageId
     * 
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public BigDecimal getRepExpAvgYield() {
        return repExpAvgYield;
    }

    public void setRepExpAvgYield(BigDecimal repExpAvgYield) {
        this.repExpAvgYield = repExpAvgYield;
    }

    @Length(fieldName = "BussRepFinProductInfo.repYieldComment", max = 200)
    public String getRepYieldComment() {
        return repYieldComment;
    }

    public void setRepYieldComment(String repYieldComment) {
        this.repYieldComment = repYieldComment;
    }

    public String getRepOpenCircle() {
        return repOpenCircle;
    }

    public void setRepOpenCircle(String repOpenCircle) {
        this.repOpenCircle = repOpenCircle;
    }

    // @Amount(fieldName = "BussRepFinProductInfo.repOpenCircleDays", maxIntBit = 4, maxDecBit = 0)
    public Integer getRepOpenCircleDays() {
        return repOpenCircleDays;
    }

    public void setRepOpenCircleDays(Integer repOpenCircleDays) {
        this.repOpenCircleDays = repOpenCircleDays;
    }

    @Length(fieldName = "BussRepFinProductInfo.repRuleComment", max = 120)
    public String getRepRuleComment() {
        return repRuleComment;
    }

    public void setRepRuleComment(String repRuleComment) {
        this.repRuleComment = repRuleComment;
    }

    public Date getRepFirstOpenDay() {
        return repFirstOpenDay;
    }

    public void setRepFirstOpenDay(Date repFirstOpenDay) {
        this.repFirstOpenDay = repFirstOpenDay;
    }

    @Amount(fieldName = "BussRepFinProductInfo.repAvgOpenCount", maxIntBit = 3)
    public BigDecimal getRepAvgOpenCount() {
        return repAvgOpenCount;
    }

    public void setRepAvgOpenCount(BigDecimal repAvgOpenCount) {
        this.repAvgOpenCount = repAvgOpenCount;
    }

    public String getRepOpenBuss() {
        return repOpenBuss;
    }

    public void setRepOpenBuss(String repOpenBuss) {
        this.repOpenBuss = repOpenBuss;
    }

    @Length(fieldName = "BussRepFinProductInfo.repOpenBussRemark", max = 120)
    public String getRepOpenBussRemark() {
        return repOpenBussRemark;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public String getOpenMode() {
        return openMode;
    }

    public void setOpenMode(String openMode) {
        this.openMode = openMode;
    }

    public void setRepOpenBussRemark(String repOpenBussRemark) {
        this.repOpenBussRemark = repOpenBussRemark;
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repFinprodBrand", max = 50)
    public String getRepFinprodBrand() {
        return super.getRepFinprodBrand();
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repApproverName", max = 20)
    public String getRepApproverName() {
        return super.getRepApproverName();
    }

    @Override
    @IDCard(fieldName = "BussRepFinProductInfo.repApproverId")
    public String getRepApproverId() {
        return super.getRepApproverId();
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repDesignerName", max = 20)
    public String getRepDesignerName() {
        return super.getRepDesignerName();
    }

    @Override
    @IDCard(fieldName = "BussRepFinProductInfo.repDesignerId")
    public String getRepDesignerId() {
        return super.getRepDesignerId();
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repManagerName", max = 20)
    public String getRepManagerName() {
        return super.getRepManagerName();
    }

    @Override
    @IDCard(fieldName = "BussRepFinProductInfo.repManagerId")
    public String getRepManagerId() {
        return super.getRepManagerId();
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repLiaisonName", max = 20)
    public String getRepLiaisonName() {
        return super.getRepLiaisonName();
    }

    @Override
    @OfficePhone(fieldName = "BussRepFinProductInfo.repLiaisonTel")
    public String getRepLiaisonTel() {
        return super.getRepLiaisonTel();
    }

    @Override
    @MobilePhone(fieldName = "BussRepFinProductInfo.repLiaisonMobile")
    public String getRepLiaisonMobile() {
        return super.getRepLiaisonMobile();
    }

    @Override
    @Email(fieldName = "BussRepFinProductInfo.repLiaisonEmail")
    public String getRepLiaisonEmail() {
        return super.getRepLiaisonEmail();
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repInvestCountry", max = 50)
    public String getRepInvestCountry() {
        return super.getRepInvestCountry();
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repMname", max = 60)
    public String getRepMname() {
        return super.getRepMname();
    }

    @Override
    @Amount(fieldName = "BussRepFinProductInfo.repSaleAmtStr", maxIntBit = 13)
    public BigDecimal getRepSaleAmtStr() {
        return super.getRepSaleAmtStr();
    }

    @Override
    @Amount(fieldName = "BussRepFinProductInfo.repRaiseAmtPlan", maxIntBit = 13)
    public BigDecimal getRepRaiseAmtPlan() {
        return super.getRepRaiseAmtPlan();
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repInDepName", max = 60)
    public String getRepInDepName() {
        return super.getRepInDepName();
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repInDepCode", max = 12)
    public String getRepInDepCode() {
        return super.getRepInDepCode();
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repOutDepCtry", max = 50)
    public String getRepOutDepCtry() {
        return super.getRepOutDepCtry();
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repOutDepName", max = 100)
    public String getRepOutDepName() {
        return super.getRepOutDepName();
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repRiskType", max = 50)
    public String getRepRiskType() {
        return super.getRepRiskType();
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repCoopName", max = 60)
    public String getRepCoopName() {
        return super.getRepCoopName();
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repCreditType", max = 60)
    public String getRepCreditType() {
        return super.getRepCreditType();
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repInvTypeRat", max = 150)
    public String getRepInvTypeRat() {
        return super.getRepInvTypeRat();
    }

    @Override
    @Length(fieldName = "BussRepFinProductInfo.repRemark", max = 120)
    public String getRepRemark() {
        return super.getRepRemark();
    }
}
