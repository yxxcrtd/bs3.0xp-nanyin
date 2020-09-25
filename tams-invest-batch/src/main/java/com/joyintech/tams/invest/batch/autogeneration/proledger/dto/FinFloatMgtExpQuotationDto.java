/*
 * 文件名：FinFloatMgtExpQuotationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.autogeneration.proledger.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年10月19日
 * @see FinFloatMgtExpQuotationDto
 * @since 1.0
 */
public class FinFloatMgtExpQuotationDto extends FinProduct{

    /**lev spreadRate baseRuleId coefficient chargeRatio
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 3169345056013915719L;
    /**
     * 产品简称
     */
    private String finprodAbbr;
    /**
     * 计提结束日
     */
    private Date eAccrDate;
    /**
     * 计提方式
     */
    private String accrType;
    /**
     * 区间规则
     */
    private String intervalRule;
    /**
     * 计提营业日准则
     */
    private String accrTdayRule;
    /**
     * 首个计提区间结束日
     */
    private Date fAccrEndDate;
    /**
     * 首次付费日
     */
    private Date firstPayDate;
    /**
     * 计费营业日准则
     */
    private String endTdayRule;
    /**
     * 付费营业日准则
     */
    private String payTdayRule;
    /**
     * 处理模式
     */
    private String dealMode;
    /**
     * 投资周期（天）
     */
    private String investmentCycle;
    /**
     * 付费计划
     */
    private List<FinCashPlan> finCashPlanList;
    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }
    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }
    
    /**
     * 获取 eAccrDate
     * @return eAccrDate.
     */
    public Date geteAccrDate() {
        return eAccrDate;
    }
    
    /**
     * 设置 eAccrDate
     * @param eAccrDate eAccrDate
     */
    public void seteAccrDate(Date eAccrDate) {
        this.eAccrDate = eAccrDate;
    }
    
    /**
     * 获取 accrType
     * @return accrType.
     */
    public String getAccrType() {
        return accrType;
    }
    
    /**
     * 设置 accrType
     * @param accrType accrType
     */
    public void setAccrType(String accrType) {
        this.accrType = accrType;
    }
    
    /**
     * 获取 intervalRule
     * @return intervalRule.
     */
    public String getIntervalRule() {
        return intervalRule;
    }
    
    /**
     * 设置 intervalRule
     * @param intervalRule intervalRule
     */
    public void setIntervalRule(String intervalRule) {
        this.intervalRule = intervalRule;
    }
    
    /**
     * 获取 accrTdayRule
     * @return accrTdayRule.
     */
    public String getAccrTdayRule() {
        return accrTdayRule;
    }
    
    /**
     * 设置 accrTdayRule
     * @param accrTdayRule accrTdayRule
     */
    public void setAccrTdayRule(String accrTdayRule) {
        this.accrTdayRule = accrTdayRule;
    }
    
    /**
     * 获取 fAccrEndDate
     * @return fAccrEndDate.
     */
    public Date getfAccrEndDate() {
        return fAccrEndDate;
    }
    
    /**
     * 设置 fAccrEndDate
     * @param fAccrEndDate fAccrEndDate
     */
    public void setfAccrEndDate(Date fAccrEndDate) {
        this.fAccrEndDate = fAccrEndDate;
    }
    
    /**
     * 获取 firstPayDate
     * @return firstPayDate.
     */
    public Date getFirstPayDate() {
        return firstPayDate;
    }
    
    /**
     * 设置 firstPayDate
     * @param firstPayDate firstPayDate
     */
    public void setFirstPayDate(Date firstPayDate) {
        this.firstPayDate = firstPayDate;
    }
    
    /**
     * 获取 endTdayRule
     * @return endTdayRule.
     */
    public String getEndTdayRule() {
        return endTdayRule;
    }
    
    /**
     * 设置 endTdayRule
     * @param endTdayRule endTdayRule
     */
    public void setEndTdayRule(String endTdayRule) {
        this.endTdayRule = endTdayRule;
    }
    
    /**
     * 获取 payTdayRule
     * @return payTdayRule.
     */
    public String getPayTdayRule() {
        return payTdayRule;
    }
    
    /**
     * 设置 payTdayRule
     * @param payTdayRule payTdayRule
     */
    public void setPayTdayRule(String payTdayRule) {
        this.payTdayRule = payTdayRule;
    }

    /**
     * 基准编号
     */
    private String indexId;
    /**
     * 系数
     */
    private BigDecimal coefficient;
    /**
     * 利差
     */
    private BigDecimal spreadRate;
    /**
     * 费用名称
     */
    private String feeName;
    /**
     * 层级
     */
    private Long lev;
    /**
     * 银行收取比例
     */
    private BigDecimal chargeRatio;
    /**
     * 生效日期
     */
    private Date effDate;
    /**
     * 公布日期
     */
    private Date relaeseDate;
    /**
     * 现金流代码
     */
    private String cashId;
    /**
     * 基准来源
     */
    private String indexSource;
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 
     */
    private String elementId;
    /**
     * 利率
     */
    private BigDecimal intRate;
    /**
     * 
     */
    private List<FinFloatMgtExpQuotationDto> selectedItem1;
    /**
     * 计费基数
     */
    private String basicBill;
    /**
     * 计费基础
     */
    private String basis;
    /**
     * 资产净值日类型
     */
    private String astVdateType;
    /**
     * 支付频率
     */
    private String payCycle;
    /**
     * 首次计提日
     */
    private Date fAccrDate;
    /**
     * 基准代码
     */
    private String baseRuleId;
    /**
     * 件数
     */
    private int count;
    /**
     * 平台日期
     */
    private String SESSION_SYS_DATE;
    /**
     * 业绩基准计算规则
     */
    private String baseRule;
    /**
     * 费用编号
     */
    private String feeId;
    /**
     * 修改时的生效日
     */
    private Date effDateOld;
    
    /**
     * 分支序号
     */
    private long branch;
    
    /**
     * 产品款型
     */
    private String prodStyle;
    
    /**
     * 获取基准编号
     * @return
     */
    public String getIndexId() {
        return indexId;
    }
    /**
     * 设置基准编号
     * @param indexId
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }
    /**
     * 获取系数
     * @return
     */
    public BigDecimal getCoefficient() {
        return coefficient;
    }
    /**
     * 设置系数
     * @param coefficient
     */
    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }
    /**
     * 获取利差
     * @return
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }
    /**
     * 设置利差
     * @param spreadRate
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }
    /**
     * 获取费用名称
     * @return
     */
    public String getFeeName() {
        return feeName;
    }
    /**
     * 设置费用名称
     * @param feeName
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }
    /**
     * 获取层级
     * @return
     */
    public Long getLev() {
        return lev;
    }
    /**
     * 设置层级
     * @param lev
     */
    public void setLev(Long lev) {
        this.lev = lev;
    }
    /**
     * 获取银行收取比例
     * @return
     */
    public BigDecimal getChargeRatio() {
        return chargeRatio;
    }
    /**
     * 设置银行收取比例
     * @param chargeRatio
     */
    public void setChargeRatio(BigDecimal chargeRatio) {
        this.chargeRatio = chargeRatio;
    }
    /**
     * 获取生效日期
     * @return
     */
    public Date getEffDate() {
        return effDate;
    }
    /**
     * 设置生效日期
     * @param effDate
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }
    /**
     * 获取公布日期
     * @return
     */
    public Date getRelaeseDate() {
        return relaeseDate;
    }
    /**
     * 设置公布日期
     * @param relaeseDate
     */
    public void setRelaeseDate(Date relaeseDate) {
        this.relaeseDate = relaeseDate;
    }
    /**
     * 获取现金流代码
     * @return
     */
    public String getCashId() {
        return cashId;
    }
    /**
     * 设置现金流代码
     * @param cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }
    /**
     * 
     * @return
     */
    public String getOperateType() {
        return operateType;
    }
    /**
     * 
     * <功能详细描述>
     * @param operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    /**
     * 
     * @return
     */
    public String getElementId() {
        return elementId;
    }
    /**
     * 
     * @param elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
    /**
     * 
     * @return
     */
    public List<FinFloatMgtExpQuotationDto> getSelectedItem1() {
        return selectedItem1;
    }
    /**
     * 
     * @param selectedItem
     */
    public void setSelectedItem1(List<FinFloatMgtExpQuotationDto> selectedItem1) {
        this.selectedItem1 = selectedItem1;
    }
    /**
     * 得到基准来源
     * @return
     */
    public String getIndexSource() {
        return indexSource;
    }
    /**
     * 设置基准来源
     * @param indexSource
     */
    public void setIndexSource(String indexSource) {
        this.indexSource = indexSource;
    }
    /**
     * 得到利率
     * @return
     */
    public BigDecimal getIntRate() {
        return intRate;
    }
    /**
     * 设置利率
     * @param intRate
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }
    /**
     * 得到计费基数 
     * @return
     */
    public String getBasicBill() {
        return basicBill;
    }
    /**
     * 设置计费基数
     * @param basicBill
     */
    public void setBasicBill(String basicBill) {
        this.basicBill = basicBill;
    }
    /**
     * 得到 计费基础
     * @return
     */
    public String getBasis() {
        return basis;
    }
    /**
     * 设置 计费基础
     * @param basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }
    /**
     * 得到资产净值日类型
     * @return
     */
    public String getAstVdateType() {
        return astVdateType;
    }
    /**
     * 设置资产净值日类型
     * @param astVdateType
     */
    public void setAstVdateType(String astVdateType) {
        this.astVdateType = astVdateType;
    }
    /**
     * 得到支付频率
     * @return
     */
    public String getPayCycle() {
        return payCycle;
    }
    /**
     * 设置支付频率
     * @param payCycle
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle;
    }
    /**
     * 得到首次计提日
     * @return
     */
    public Date getfAccrDate() {
        return fAccrDate;
    }
    /**
     * 设置首次计提日
     * @param fAccrDate
     */
    public void setfAccrDate(Date fAccrDate) {
        this.fAccrDate = fAccrDate;
    }
    /**
     * 得到基准代码
     * @return 
     */
    public String getBaseRuleId() {
        return baseRuleId;
    }
    /**
     * 设置基准代码
     * @param baseRuleId
     */
    public void setBaseRuleId(String baseRuleId) {
        this.baseRuleId = baseRuleId;
    }
    /**
     * 得到件数
     * @return 
     */
    public int getCount() {
        return count;
    }
    /**
     * 设置件数
     * @param baseRuleId
     */
    public void setCount(int count) {
        this.count = count;
    }
    /**
     * 得到平台日期
     * @return 
     */
    public String getSESSION_SYS_DATE() {
        return SESSION_SYS_DATE;
    }
    /**
     * 设置平台日期
     * @param SESSION_SYS_DATE
     */
    public void setSESSION_SYS_DATE(String sESSION_SYS_DATE) {
        SESSION_SYS_DATE = sESSION_SYS_DATE;
    }
    /**
     * 得到业绩基准计算规则
     * @return 
     */
    public String getBaseRule() {
        return baseRule;
    }
    /**
     * 设置业绩基准计算规则
     * @param baseRule
     */
    public void setBaseRule(String baseRule) {
        this.baseRule = baseRule;
    }
    /**
     * 得到费用编号
     * @return 
     */
    public String getFeeId() {
        return feeId;
    }
    /**
     * 设置费用编号
     * @param feeId
     */
    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }
    /**
     * 获取 effDateOld
     * @return effDateOld.
     */
    public Date getEffDateOld() {
        return effDateOld;
    }
    /**
     * 设置 effDateOld
     * @param effDateOld effDateOld
     */
    public void setEffDateOld(Date effDateOld) {
        this.effDateOld = effDateOld;
    }
    
    /**
     * 获取 branch
     * @return branch.
     */
    public long getBranch() {
        return branch;
    }
    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(long branch) {
        this.branch = branch;
    }
    
    /**
     * 取得 产品款型
     * @return 产品款型
     */
    public String getProdStyle() {
        return prodStyle;
    }

    /**
     * 设置 产品款型
     * @param prodStyle 产品款型
     */
    public void setProdStyle(String prodStyle) {
        this.prodStyle = prodStyle == null ? null : prodStyle.trim();
    }
    
	// 发送状态
    private String sendStatus;
    // 发送状态备注
    private String sendRemark;
    
    public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getSendRemark() {
		return sendRemark;
	}

	public void setSendRemark(String sendRemark) {
		this.sendRemark = sendRemark;
	}
	
	public String getSendStatusStr() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.发送状态, getSendStatus())) ? SysDataUtil.getNameFromDataDict(DictCode.发送状态, getSendStatus()):"";
    }

	public String getDealMode() {
		return dealMode;
	}

	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

	public String getInvestmentCycle() {
		return investmentCycle;
	}

	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}

	public List<FinCashPlan> getFinCashPlanList() {
		return finCashPlanList;
	}

	public void setFinCashPlanList(List<FinCashPlan> finCashPlanList) {
		this.finCashPlanList = finCashPlanList;
	}
	
}