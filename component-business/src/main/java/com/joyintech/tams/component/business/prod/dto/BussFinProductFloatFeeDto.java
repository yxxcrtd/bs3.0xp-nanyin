/*
 * 文件名：BussFinProductFloatFeeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.component.business.prod.validator.BussFinProductFloatFeeValidator;
import com.joyintech.tams.constant.DictCode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;

/**
 * 浮动管理费业务组件dto
 * 
 * @author zhangliang
 * @version 1.0 2018年10月12日
 * @see BussFinProductFloatFeeDto
 * @since 1.0
 */
@ValidLogic(targetClass = BussFinProductFloatFeeValidator.class)
public class BussFinProductFloatFeeDto {
    
	/**
	 * 期限现金流Id
	 */
	private String periodCashId;
	
	
    public String getPeriodCashId() {
		return periodCashId;
	}

	public void setPeriodCashId(String periodCashId) {
		this.periodCashId = periodCashId;
	}

	/**
     * 付费计划列表   ----丁俊峰
     */
    private List<FinCashPlan> finCashPlanList;
    
    
    public List<FinCashPlan> getFinCashPlanList() {
		return finCashPlanList;
	}

	public void setFinCashPlanList(List<FinCashPlan> finCashPlanList) {
		this.finCashPlanList = finCashPlanList;
	}

	//子产品款型
	private String prodStyle;
	/**
	 * 投资周期
	 */
	private String investmentCycle;
	
	private List<BussFinProductFloatFeeDto> bussFinProductFloatFeeInfoDtoList;
    /**
     * 主画面传入的pageId
     */
    private String pageId;
    
    /**
     * 实体名
     */
    private String entityName;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 交易日里
     */
    private String dealCalId;

    /**
     * 费用代码
     */
    private String feeId;

    /**
     * 计费基数
     */
    private String basicBill;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 资产净值日
     */
    private String netAssetValueDay;

    /**
     * 首次计提日
     */
    private Date provisionFirstDay;

    /**
     * 业绩基准规则
     */
    private String floatBaseRule;
    
    /**
     * 计提方式
     */
    private String accrType;
    
    /**
     * 区间规则
     * INTERVAL_RULE
     */
    private String interValRule;
    
    /**
     * E_ACCR_DATE 计提结束日
     */
    private Date eAccrDate;
    
    /**
     * F_ACCR_DATE 首次计提日
     */
    private Date fAccrDate;
    /**
     * F_ACCR_END_DATE 首个计提区间结束日
     */
    private Date fAccrEndDate;
    /**
     * ACCR_TDAY_RULE 计提营业日准则
     */
    private String accrTdayRule;
    
    /**
     * 付费规则
     */
    private String payCycle;
    
    /**
     * 首次付费结束日
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
     * 利率类型
     */
    private String rateType;
    
    /**
     * 初始利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 基准编号
     */
    private String indexId;
    
    /**
     * 系数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal coefficient;

    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadRate;

    /**
     * 重置规则，期初、期末等
     */
    private String resetType;
    
    private String remark;
    
    private String resetTypeName;

    private String dealMode;
    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	public String getResetTypeName() {
		return SysDataUtil.getNameFromDataDict(DictCode.重置规则, this.getResetType());
	}

	public void setResetTypeName(String resetTypeName) {
		this.resetTypeName = resetTypeName;
	}

	/**
     * 业绩报酬计算规则明细DtoList
     */
    @Valid
    private transient List<FinProductFloatFeeDto> finFloatCalcRuleDetailDtoList;

    /**
     * 获取 pageId
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 pageId
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    
    /**
     * 获取 entityName
     * @return entityName.
     */
    public String getEntityName() {
        return entityName;
    }

    
    public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public BigDecimal getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(BigDecimal coefficient) {
		this.coefficient = coefficient;
	}

	public BigDecimal getSpreadRate() {
		return spreadRate;
	}

	public void setSpreadRate(BigDecimal spreadRate) {
		this.spreadRate = spreadRate;
	}

	public String getResetType() {
		return resetType;
	}

	public void setResetType(String resetType) {
		this.resetType = resetType;
	}

	/**
     * 设置 entityName
     * @param entityName entityName
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getDealCalId() {
        return dealCalId;
    }

    public void setDealCalId(String dealCalId) {
        this.dealCalId = dealCalId;
    }

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public String getBasicBill() {
        return basicBill;
    }

    public void setBasicBill(String basicBill) {
        this.basicBill = basicBill;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getNetAssetValueDay() {
        return netAssetValueDay;
    }

    public void setNetAssetValueDay(String netAssetValueDay) {
        this.netAssetValueDay = netAssetValueDay;
    }

    public Date getProvisionFirstDay() {
        return provisionFirstDay;
    }

    public void setProvisionFirstDay(Date provisionFirstDay) {
        this.provisionFirstDay = provisionFirstDay;
    }

    public String getFloatBaseRule() {
        return floatBaseRule;
    }

    public void setFloatBaseRule(String floatBaseRule) {
        this.floatBaseRule = floatBaseRule;
    }

    public List<FinProductFloatFeeDto> getFinFloatCalcRuleDetailDtoList() {
        return finFloatCalcRuleDetailDtoList;
    }

    public void setFinFloatCalcRuleDetailDtoList(List<FinProductFloatFeeDto> finFloatCalcRuleDetailDtoList) {
        this.finFloatCalcRuleDetailDtoList = finFloatCalcRuleDetailDtoList;
    }

	

	public String getAccrType() {
		return accrType;
	}

	public void setAccrType(String accrType) {
		this.accrType = accrType;
	}

	public String getInterValRule() {
		return interValRule;
	}

	public void setInterValRule(String interValRule) {
		this.interValRule = interValRule;
	}

	

	public String getAccrTdayRule() {
		return accrTdayRule;
	}

	public void setAccrTdayRule(String accrTdayRule) {
		this.accrTdayRule = accrTdayRule;
	}

	public String getPayCycle() {
		return payCycle;
	}

	public void setPayCycle(String payCycle) {
		this.payCycle = payCycle;
	}

	

	public Date geteAccrDate() {
		return eAccrDate;
	}

	public void seteAccrDate(Date eAccrDate) {
		this.eAccrDate = eAccrDate;
	}

	public Date getfAccrDate() {
		return fAccrDate;
	}

	public void setfAccrDate(Date fAccrDate) {
		this.fAccrDate = fAccrDate;
	}

	public Date getfAccrEndDate() {
		return fAccrEndDate;
	}

	public void setfAccrEndDate(Date fAccrEndDate) {
		this.fAccrEndDate = fAccrEndDate;
	}

	public Date getFirstPayDate() {
		return firstPayDate;
	}

	public void setFirstPayDate(Date firstPayDate) {
		this.firstPayDate = firstPayDate;
	}

	public String getEndTdayRule() {
		return endTdayRule;
	}

	public void setEndTdayRule(String endTdayRule) {
		this.endTdayRule = endTdayRule;
	}

	public String getPayTdayRule() {
		return payTdayRule;
	}

	public void setPayTdayRule(String payTdayRule) {
		this.payTdayRule = payTdayRule;
	}

    /**
     * 获取 prodStyle
     * @return prodStyle.
     */
    public String getProdStyle() {
        return prodStyle;
    }

    
    /**
     * 设置 prodStyle
     * @param prodStyle prodStyle
     */
    public void setProdStyle(String prodStyle) {
        this.prodStyle = prodStyle;
    }

    public List<BussFinProductFloatFeeDto> getBussFinProductFloatFeeInfoDtoList() {
		return bussFinProductFloatFeeInfoDtoList;
	}

	public void setBussFinProductFloatFeeInfoDtoList(List<BussFinProductFloatFeeDto> bussFinProductFloatFeeInfoDtoList) {
		this.bussFinProductFloatFeeInfoDtoList = bussFinProductFloatFeeInfoDtoList;
	}

	public String getInvestmentCycle() {
		return investmentCycle;
	}

	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}

    /**
     * 获取 dealMode
     *
     * @return dealMode
     */
    public String getDealMode() {
        return dealMode;
    }

    /**
     * 设置 dealMode
     *
     * @param dealMode dealMode
     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }
}
