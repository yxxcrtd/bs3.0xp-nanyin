/*
 * 文件名：AssetIntdateCarryoverInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.assetintdatecarryover.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

/**
 * 资产理论结息日结转信息
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see AssetIntdateCarryoverInfo
 * @since 1.0
 */
public class AssetIntdateCarryoverInfo extends NeedOperateEntity {

    /**
     * 资产理论结息日结转信息明细
     */
    private List<AssetIntdateCarryoverReaderDetail> assetIntdateCarryoverReaderDetail = new ArrayList<AssetIntdateCarryoverReaderDetail>();;
    
    /**
     * 现金流一级类型，本金、利息、费用
     */
    private String cashType1;

    /**
     * 现金流二级类型，本金、利息、托管费、固定管理费、浮动管理费等
     */
    private String cashType2;
    
    /**
     * 发行规模
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issueAmt;
    
    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 通道代码
     */
    private String chlFinprodId;
    

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 实际到期日
     */
    private Date actmdate;

    /**
     * 未调整的计算开始日
     */
    private Date vdateUnadjust;

    /**
     * 未调整的计算结束日
     */
    private Date mdateUnadjust;

    /**
     * 计划支付日
     */
    private Date payDateUnadjust;

    /**
     * 调整后的计算开始日
     */
    private Date vdate;

    /**
     * 调整后的计算结束日
     */
    private Date mdate;

    /**
     * 调整后的计划支付日
     */
    private Date payDate;

    /**
     * 计算周期天数
     */
    private Long termdays;

    /**
     * 理论计息年度开始日，A/A_BOND适用
     */
    private Date vdateY;

    /**
     * 完整周期开始日
     */
    private Date vdateTerm;

    /**
     * 完整周期结束日
     */
    private Date mdateTerm;

    /**
     * 完整周期计息天数
     */
    private Long termdaysTerm;

    /**
     * 计划支付本金，针对现金流类型为本金的
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinAmt;

    /**
     * 计息本金，存计划支付利息对应的本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intPrinAmt;

    /**
     * 计划支付利息，还本计息区间对应的利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 是否支付利息，针对现金流类型为本金的
     */
    private String isPayInt;

    /**
     * 计划支付现金流，计划支付本金+计划支付利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cashAmt;

    /**
     * 现金流单位，百元、 发行规模
     */
    private String cashUnitType;

    /**
     * 计息算法，分摊法、累计法
     */
    private String calcFunction;

    /**
     * 年付息次数
     */
    private Long frequency;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 支付方式，现金、红利再投等
     */
    private String payType;
    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 现金流序号
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cashNum;

    /**
     * 取得 现金流代码
     * @return 现金流代码
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 现金流代码
     * @param cashId 现金流代码
     */
    public void setCashId(String cashId) {
        this.cashId = cashId == null ? null : cashId.trim();
    }

    /**
     * 取得 现金流序号
     * @return 现金流序号
     */
    public BigDecimal getCashNum() {
        return cashNum;
    }

    /**
     * 设置 现金流序号
     * @param cashNum 现金流序号
     */
    public void setCashNum(BigDecimal cashNum) {
        this.cashNum = cashNum;
    }
    
    /**
     * 取得 未调整的计算开始日
     * @return 未调整的计算开始日
     */
    public Date getVdateUnadjust() {
        return vdateUnadjust;
    }

    /**
     * 设置 未调整的计算开始日
     * @param vdateUnadjust 未调整的计算开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdateUnadjust(Date vdateUnadjust) {
        this.vdateUnadjust = vdateUnadjust;
    }

    /**
     * 取得 未调整的计算结束日
     * @return 未调整的计算结束日
     */
    public Date getMdateUnadjust() {
        return mdateUnadjust;
    }

    /**
     * 设置 未调整的计算结束日
     * @param mdateUnadjust 未调整的计算结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdateUnadjust(Date mdateUnadjust) {
        this.mdateUnadjust = mdateUnadjust;
    }

    /**
     * 取得 计划支付日
     * @return 计划支付日
     */
    public Date getPayDateUnadjust() {
        return payDateUnadjust;
    }

    /**
     * 设置 计划支付日
     * @param payDateUnadjust 计划支付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPayDateUnadjust(Date payDateUnadjust) {
        this.payDateUnadjust = payDateUnadjust;
    }

    /**
     * 取得 调整后的计算开始日
     * @return 调整后的计算开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 调整后的计算开始日
     * @param vdate 调整后的计算开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 调整后的计算结束日
     * @return 调整后的计算结束日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 调整后的计算结束日
     * @param mdate 调整后的计算结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 调整后的计划支付日
     * @return 调整后的计划支付日
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 调整后的计划支付日
     * @param payDate 调整后的计划支付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 取得 计算周期天数
     * @return 计算周期天数
     */
    public Long getTermdays() {
        return termdays;
    }

    /**
     * 设置 计算周期天数
     * @param termdays 计算周期天数
     */
    public void setTermdays(Long termdays) {
        this.termdays = termdays;
    }

    /**
     * 取得 理论计息年度开始日，A/A_BOND适用
     * @return 理论计息年度开始日，A/A_BOND适用
     */
    public Date getVdateY() {
        return vdateY;
    }

    /**
     * 设置 理论计息年度开始日，A/A_BOND适用
     * @param vdateY 理论计息年度开始日，A/A_BOND适用
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdateY(Date vdateY) {
        this.vdateY = vdateY;
    }

    /**
     * 取得 完整周期开始日
     * @return 完整周期开始日
     */
    public Date getVdateTerm() {
        return vdateTerm;
    }

    /**
     * 设置 完整周期开始日
     * @param vdateTerm 完整周期开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdateTerm(Date vdateTerm) {
        this.vdateTerm = vdateTerm;
    }

    /**
     * 取得 完整周期结束日
     * @return 完整周期结束日
     */
    public Date getMdateTerm() {
        return mdateTerm;
    }

    /**
     * 设置 完整周期结束日
     * @param mdateTerm 完整周期结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdateTerm(Date mdateTerm) {
        this.mdateTerm = mdateTerm;
    }

    /**
     * 取得 完整周期计息天数
     * @return 完整周期计息天数
     */
    public Long getTermdaysTerm() {
        return termdaysTerm;
    }

    /**
     * 设置 完整周期计息天数
     * @param termdaysTerm 完整周期计息天数
     */
    public void setTermdaysTerm(Long termdaysTerm) {
        this.termdaysTerm = termdaysTerm;
    }

    /**
     * 取得 计划支付本金，针对现金流类型为本金的
     * @return 计划支付本金，针对现金流类型为本金的
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 计划支付本金，针对现金流类型为本金的
     * @param prinAmt 计划支付本金，针对现金流类型为本金的
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 取得 计息本金，存计划支付利息对应的本金
     * @return 计息本金，存计划支付利息对应的本金
     */
    public BigDecimal getIntPrinAmt() {
        return intPrinAmt;
    }

    /**
     * 设置 计息本金，存计划支付利息对应的本金
     * @param intPrinAmt 计息本金，存计划支付利息对应的本金
     */
    public void setIntPrinAmt(BigDecimal intPrinAmt) {
        this.intPrinAmt = intPrinAmt;
    }

    /**
     * 取得 计划支付利息，还本计息区间对应的利息
     * @return 计划支付利息，还本计息区间对应的利息
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 计划支付利息，还本计息区间对应的利息
     * @param intAmt 计划支付利息，还本计息区间对应的利息
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 取得 是否支付利息，针对现金流类型为本金的
     * @return 是否支付利息，针对现金流类型为本金的
     */
    public String getIsPayInt() {
        return isPayInt;
    }

    /**
     * 设置 是否支付利息，针对现金流类型为本金的
     * @param isPayInt 是否支付利息，针对现金流类型为本金的
     */
    public void setIsPayInt(String isPayInt) {
        this.isPayInt = isPayInt == null ? null : isPayInt.trim();
    }

    /**
     * 取得 计划支付现金流，计划支付本金+计划支付利息
     * @return 计划支付现金流，计划支付本金+计划支付利息
     */
    public BigDecimal getCashAmt() {
        return cashAmt;
    }

    /**
     * 设置 计划支付现金流，计划支付本金+计划支付利息
     * @param cashAmt 计划支付现金流，计划支付本金+计划支付利息
     */
    public void setCashAmt(BigDecimal cashAmt) {
        this.cashAmt = cashAmt;
    }

    /**
     * 取得 现金流单位，百元、 发行规模
     * @return 现金流单位，百元、 发行规模
     */
    public String getCashUnitType() {
        return cashUnitType;
    }

    /**
     * 设置 现金流单位，百元、 发行规模
     * @param cashUnitType 现金流单位，百元、 发行规模
     */
    public void setCashUnitType(String cashUnitType) {
        this.cashUnitType = cashUnitType == null ? null : cashUnitType.trim();
    }

    /**
     * 取得 计息算法，分摊法、累计法
     * @return 计息算法，分摊法、累计法
     */
    public String getCalcFunction() {
        return calcFunction;
    }

    /**
     * 设置 计息算法，分摊法、累计法
     * @param calcFunction 计息算法，分摊法、累计法
     */
    public void setCalcFunction(String calcFunction) {
        this.calcFunction = calcFunction == null ? null : calcFunction.trim();
    }

    /**
     * 取得 年付息次数
     * @return 年付息次数
     */
    public Long getFrequency() {
        return frequency;
    }

    /**
     * 设置 年付息次数
     * @param frequency 年付息次数
     */
    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 支付方式，现金、红利再投等
     * @return 支付方式，现金、红利再投等
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置 支付方式，现金、红利再投等
     * @param payType 支付方式，现金、红利再投等
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }
    
    /**
     * 获取 cashType1
     * @return cashType1.
     */
    public String getCashType1() {
        return cashType1;
    }

    
    /**
     * 设置 cashType1
     * @param cashType1 cashType1
     */
    public void setCashType1(String cashType1) {
        this.cashType1 = cashType1;
    }

    
    /**
     * 获取 cashType2
     * @return cashType2.
     */
    public String getCashType2() {
        return cashType2;
    }

    
    /**
     * 设置 cashType2
     * @param cashType2 cashType2
     */
    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2;
    }

    
    /**
     * 获取 issueAmt
     * @return issueAmt.
     */
    public BigDecimal getIssueAmt() {
        return issueAmt;
    }

    
    /**
     * 设置 issueAmt
     * @param issueAmt issueAmt
     */
    public void setIssueAmt(BigDecimal issueAmt) {
        this.issueAmt = issueAmt;
    }

    
    /**
     * 获取 ccy
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    
    /**
     * 设置 ccy
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    
    /**
     * 获取 chlFinprodId
     * @return chlFinprodId.
     */
    public String getChlFinprodId() {
        return chlFinprodId;
    }

    
    /**
     * 设置 chlFinprodId
     * @param chlFinprodId chlFinprodId
     */
    public void setChlFinprodId(String chlFinprodId) {
        this.chlFinprodId = chlFinprodId;
    }

    
    /**
     * 获取 tradeMarket
     * @return tradeMarket.
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    
    /**
     * 设置 tradeMarket
     * @param tradeMarket tradeMarket
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    
    /**
     * 获取 actmdate
     * @return actmdate.
     */
    public Date getActmdate() {
        return actmdate;
    }

    
    /**
     * 设置 actmdate
     * @param actmdate actmdate
     */
    public void setActmdate(Date actmdate) {
        this.actmdate = actmdate;
    }


    
    /**
     * 获取 assetIntdateCarryoverReaderDetail
     * @return assetIntdateCarryoverReaderDetail.
     */
    public List<AssetIntdateCarryoverReaderDetail> getAssetIntdateCarryoverReaderDetail() {
        return assetIntdateCarryoverReaderDetail;
    }


    
    /**
     * 设置 assetIntdateCarryoverReaderDetail
     * @param assetIntdateCarryoverReaderDetail assetIntdateCarryoverReaderDetail
     */
    public void setAssetIntdateCarryoverReaderDetail(List<AssetIntdateCarryoverReaderDetail> assetIntdateCarryoverReaderDetail) {
        this.assetIntdateCarryoverReaderDetail = assetIntdateCarryoverReaderDetail;
    }

}
