/*
 * 文件名：FinFloatMgtExpQuotation001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.validator.FinFloatMgtExpQuotationValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 浮动管理费报价dto
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年10月19日
 * @see FinFloatMgtExpQuotation001Dto
 * @since 1.0
 */
@PageId("FinFloatMgtExpQuotation002")
@ValidLogic(targetClass = FinFloatMgtExpQuotationValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class FinFloatMgtExpQuotation001Dto extends FinProduct implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -4610894987224794574L;
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
     * 适用款型 针对分档产品
     */
    private String prodStyle;
    /**
     * 投资周期（产品代码+投资周期）
     */
    private String investmentCycle;
    /**
     * 投资周期（天）
     */
    private String investmentCycleText;
    /**
     * 处理模式
     */
    private String dealMode;

    /**
     * 业绩基准组件
     */
    @Valid
    private BussBenchMarkInfoDto bussBenchMarkInfoDto;
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
     * 获取计提结束日字符串
     * @return
     */
    public String geteAccrDateStr() {
        return DateUtil.format(this.geteAccrDate());
    }
    
    /**
     * 设置 eAccrDate
     * @param eAccrDate eAccrDate
     */
    public void seteAccrDate(Date eAccrDate) {
        this.eAccrDate = eAccrDate;
    }
    
    /**
     * 获取 accrDayType
     * @return accrDayType.
     */
    public String getAccrType() {
        return accrType;
    }
    
    /**
     * 设置 accrDayType
     * @param accrDayType accrDayType
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
     * 获取首个计提区间结束日字符串
     * @return
     */
    public String getfAccrEndDateStr() {
        return DateUtil.format(this.getfAccrEndDate());
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
     * 获取首次付费日字符串
     * @return
     */
    public String getFirstPayDateStr() {
        return DateUtil.format(this.getFirstPayDate());
    }
    
    /**
     * 设置 fPayDate
     * @param fPayDate fPayDate
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
     * 操作类型
     */
    private String operateType;
    
    /**
     * 
     */
    private String elementId;
    /**
     * 基准来源
     */
    private String indexSource;
    /**
     * 利率
     */
    private BigDecimal intRate;
    /**
     * 
     */
    private List<FinFloatMgtExpQuotation001Dto> selectedItem1;
    /**
     * 
     */
    private List<FinFloatMgtExpQuotation001Dto> selectedItem;
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
     * 业绩基准计算规则
     */
    private String baseRule;
    /**
     * 修改的生效日
     */
    private Date effDateOld;
    /**
     * 费用编号
     */
    private String feeId;
    
    /**
     * 分支序号
     */
    private long branch;
    
    /**
     * 
     */
    private String PageModel;
    
    private boolean checkFlag;
    
    @Override
    @PrimaryKey(value=Field.PK_1)
    public String getFinprodId() {
        // TODO Auto-generated method stub
        return super.getFinprodId();
    }
    /**
     * 获取基准编号
     * @return indexId
     */
    public String getIndexId() {
        return indexId;
    }
    /**
     * 设置基准编号
     * @param indexId indexId
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }
    /**
     * 获取系数
     * @return coefficient
     */
    @Amount(fieldName = "FinFloatMgtExpQuotation001.coefficient" , maxIntBit=30)
    public BigDecimal getCoefficient() {
        return coefficient;
    }
    /**
     * 设置系数
     * @param coefficient coefficient
     */
    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }
    /**
     * 获取利差
     * @return spreadRate
     */
    @Amount(fieldName = "FinFloatMgtExpQuotation001.spreadRate" , maxIntBit=30)
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
     * @return feeName
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
     * @return lev
     */
    public Long getLev() {
        return lev;
    }
    /**
     * 设置层级
     * @param lev lev
     */
    public void setLev(Long lev) {
        this.lev = lev;
    }
    /**
     * 获取银行收取比例
     * @return chargeRatio
     */
    public BigDecimal getChargeRatio() {
        return chargeRatio;
    }
    /**
     * 设置银行收取比例
     * @param chargeRatio chargeRatio
     */
    public void setChargeRatio(BigDecimal chargeRatio) {
        this.chargeRatio = chargeRatio;
    }
    /**
     * 获取生效日期
     * @return effDate
     */
    public Date getEffDate() {
        return effDate;
    }
    /**
     * 获取 effDate
     * @return effDate.
     */
    @PrimaryKey(value=Field.PK_2)
    public String getEffDateStr(){
        return DateUtil.format(this.getEffDate());
    }
    /**
     * 设置生效日期
     * @param effDate effDate
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }
    /**
     * 获取公布日期
     * @return relaeseDate
     */
    public Date getRelaeseDate() {
        return relaeseDate;
    }
    /**
     * 获取公布日期字符串
     * @return
     */
    public String getRelaeseDateStr() {
        return DateUtil.format(this.getRelaeseDate());
    }
    
    /**
     * 设置公布日期
     * @param relaeseDate relaeseDate
     */
    public void setRelaeseDate(Date relaeseDate) {
        this.relaeseDate = relaeseDate;
    }
    /**
     * 获取现金流代码
     * @return cashId
     */ 
    public String getCashId() {
        return cashId;
    }
    /**
     * 设置现金流代码
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }
    /**
     * 
     * @return operateType
     */
    public String getOperateType() {
        return operateType;
    }
    /**
     * 
     * <功能详细描述>
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    /**
     * 
     * @return elementId
     */
    public String getElementId() {
        return elementId;
    }
    /**
     * 
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
    /**
     * 
     * @return selectedItem
     */
    public List<FinFloatMgtExpQuotation001Dto> getSelectedItem1() {
        return selectedItem1;
    }
    /**
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem1(List<FinFloatMgtExpQuotation001Dto> selectedItem1) {
        this.selectedItem1 = selectedItem1;
    }
    /**
     * 得到基准来源
     * @return indexSource
     */
    public String getIndexSource() {
        return indexSource;
    }
    /**
     * 设置基准来源
     * @param indexSource indexSource
     */
    public void setIndexSource(String indexSource) {
        this.indexSource = indexSource;
    }
    /**
     * 得到利率
     * @return intRate
     */
    public BigDecimal getIntRate() {
        return intRate;
    }
    /**
     * 设置利率
     * @param intRate intRate
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }
    /**
     * 得到计费基数 
     * @return basicBill
     */
    public String getBasicBill() {
        return basicBill;
    }
    /**
     * 设置计费基数
     * @param basicBill basicBill
     */
    public void setBasicBill(String basicBill) {
        this.basicBill = basicBill;
    }
    /**
     * 得到 计费基础
     * @return basis
     */
    public String getBasis() {
        return basis;
    }
    /**
     * 设置 计费基础
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }
    /**
     * 得到资产净值日类型
     * @return astVdateType
     */
    public String getAstVdateType() {
        return astVdateType;
    }
    /**
     * 设置资产净值日类型
     * @param astVdateType astVdateType
     */
    public void setAstVdateType(String astVdateType) {
        this.astVdateType = astVdateType;
    }
    /**
     * 得到支付频率
     * @return payCycle
     */
    public String getPayCycle() {
        return payCycle;
    }
    /**
     * 设置支付频率
     * @param payCycle payCycle
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle;
    }
    /**
     * 得到首次计提日
     * @return fAccrDate
     */
    public Date getfAccrDate() {
        return fAccrDate;
    }
    /**
     * 得到首次计提日字符串
     * @return fAccrDateStr
     */
    public String getfAccrDateStr(){
        return DateUtil.format(this.getfAccrDate());
    }
    /**
     * 设置首次计提日
     * @param fAccrDate fAccrDate
     */
    public void setfAccrDate(Date fAccrDate) {
        this.fAccrDate = fAccrDate;
    }
    /**
     * 得到基准代码
     * @return  baseRuleId
     */
    public String getBaseRuleId() {
        return baseRuleId;
    }
    /**
     * 设置基准代码
     * @param baseRuleId baseRuleId
     */
    public void setBaseRuleId(String baseRuleId) {
        this.baseRuleId = baseRuleId;
    }
    /**
     * 得到件数
     * @return  count
     */
    public int getCount() {
        return count;
    }
    /**
     * 设置件数
     * @param count count
     */
    public void setCount(int count) {
        this.count = count;
    }
    /**
     * 得到业绩基准计算规则
     * @return baseRule
     */
    public String getBaseRule() {
        return baseRule;
    }
    /**
     * 设置业绩基准计算规则
     * @param baseRule baseRule
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
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<FinFloatMgtExpQuotation001Dto> getSelectedItem() {
        return selectedItem;
    }
    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<FinFloatMgtExpQuotation001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return PageModel;
    }
    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        PageModel = pageModel;
    }
    
    public String getBaseRuleStr(){
    	// 一体化对应etliu start
//    	return SysDataUtil.getNameFromDataDict(DictCode.业绩基准规则, this.getBaseRule());
    	return SysDataUtil.getNameFromDataDict(DictCode.业绩报酬计算规则, this.getBaseRule());
    	// 一体化对应etliu end
    }
	public boolean isCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(boolean checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getProdStyle() {
		return prodStyle;
	}

	public void setProdStyle(String prodStyle) {
		this.prodStyle = prodStyle;
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

	public BussBenchMarkInfoDto getBussBenchMarkInfoDto() {
		return bussBenchMarkInfoDto;
	}

	public void setBussBenchMarkInfoDto(BussBenchMarkInfoDto bussBenchMarkInfoDto) {
		this.bussBenchMarkInfoDto = bussBenchMarkInfoDto;
	}

	public String getInvestmentCycle() {
		return investmentCycle;
	}

	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}

	public String getInvestmentCycleText() {
		return investmentCycleText;
	}

	public void setInvestmentCycleText(String investmentCycleText) {
		this.investmentCycleText = investmentCycleText;
	}

	public String getDealMode() {
		return dealMode;
	}

	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

	public List<FinCashPlan> getFinCashPlanList() {
		return finCashPlanList;
	}

	public void setFinCashPlanList(List<FinCashPlan> finCashPlanList) {
		this.finCashPlanList = finCashPlanList;
	}
    
}