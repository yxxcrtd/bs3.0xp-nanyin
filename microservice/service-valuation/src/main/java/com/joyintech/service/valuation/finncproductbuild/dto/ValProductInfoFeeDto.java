/*
 * 文件名：ValProductInfoFeeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：tanxuan
 * 修改时间：2020/02/26
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.finncproductbuild.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author tanxuan
 * @version 1.0 2020/02/26
 * @see ValProductInfoFeeDto
 * @since 1.0
 */
public class ValProductInfoFeeDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 费用名称
     */
    private String feeName;
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型，债券、理财产品、基金
     */
    private String finprodType;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 现金流一级类型
     */
    private String cashType1;

    /**
     * 现金流二级类型
     */
    private String cashType2;

    /**
     * 生效日期
     */
    private String effDate;

    /**
     * 生效利率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 基数类型
     */
    private String baseType;

    /**
     * 基数日期类型，上一工作日、上一自然日、当日、上一开放日等
     */
    private String baseDateType;

    /**
     * 计算规则
     */
    private String calcRule;

    /**
     * 首次支付日
     */
    private String firstPayDate;

    /**
     * 生效规则
     */
    private String actRule;

    /**
     * 业绩比较基准层级
     */
    private String basicLevel;
    
    /**
     * 银行收取比例
     */
    private BigDecimal bankRatio;
    
    /**
     * 业绩基准
     */
    private String baseRuleId;
    
    /**
     * 调整系数
     */
    private BigDecimal coefficient;
    
    /**
     * 利差
     */
    private BigDecimal spread;
    
    /**
     * 首次计提日
     */
    private String firCalculaDate;
    
    /**
     * 计提结束日
     */
    private String endCalculaDate;
    
    /**
     * 计提周期开始日
     */
    private String jtStrDate;

    /**
     * 计提周期结束日
     */
    private String jtEndDate;
    
    /**
     * 计提日
     */
    private String jtDate;
    
    /**
     * 资产净值日
     */
    private String actVdateType;
    
    /**
     * 计提频率
     */
    private String accrCycle;
    
    /**
     * 是否计提浮动管理费
     */
    private String isAccrFloManageFee;
    
    
    public String getJtStrDate() {
        return jtStrDate;
    }

    
    public void setJtStrDate(String jtStrDate) {
        this.jtStrDate = jtStrDate;
    }

    
    public String getJtEndDate() {
        return jtEndDate;
    }

    
    public void setJtEndDate(String jtEndDate) {
        this.jtEndDate = jtEndDate;
    }

    
    public String getJtDate() {
        return jtDate;
    }

    
    public void setJtDate(String jtDate) {
        this.jtDate = jtDate;
    }

    /**
	 * 获取 feeName
	 * @return feeName.
	 */
	public String getFeeName() {
		return feeName;
	}

	/**
	 * 设置 feeName
	 * @param feeName feeName
	 */
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	/**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 finprodType
     * 
     * @return finprodType
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 finprodType
     * 
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }
    
    /**
     * 获取 basis
     * 
     * @return basis
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
     * 获取 cashType1
     * 
     * @return cashType1.
     */
    public String getCashType1() {
        return cashType1;
    }

    /**
     * 设置 cashType1
     * 
     * @param cashType1 cashType1
     */
    public void setCashType1(String cashType1) {
        this.cashType1 = cashType1;
    }

    /**
     * 获取 现金流二级类型
     * 
     * @return 现金流二级类型.
     */
    public String getCashType2() {
        return cashType2;
    }

    /**
     * 设置 现金流二级类型
     * 
     * @param cashType2 现金流二级类型
     */
    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2;
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
     * 获取 基数类型
     * 
     * @return 基数类型.
     */
    public String getBaseType() {
        return baseType;
    }
    
    /**
     * 设置 基数类型
     * 
     * @param baseType 基数类型
     */
    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }
    
    /**
     * 获取 基数日期类型
     * 
     * @return 基数日期类型
     */
    public String getBaseDateType() {
        return baseDateType;
    }

    /**
     * 设置 基数日期类型
     * 
     * @param baseDateType 基数日期类型
     */
    public void setBaseDateType(String baseDateType) {
        this.baseDateType = baseDateType;
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
     * 获取 serialversionuid
     * 
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * 获取 calcRule
     * 
     * @return calcRule
     */
    public String getCalcRule() {
        return calcRule;
    }

    /**
     * 设置 calcRule
     * 
     * @param calcRule calcRule
     */
    public void setCalcRule(String calcRule) {
        this.calcRule = calcRule;
    }

    /**
     * 获取 firstPayDate
     * 
     * @return firstPayDate.
     */
    public String getFirstPayDate() {
        return firstPayDate;
    }

    /**
     * 设置 firstPayDate
     * 
     * @param firstPayDate firstPayDate
     */
    public void setFirstPayDate(String firstPayDate) {
        this.firstPayDate = firstPayDate;
    }

    /**
     * 获取 actRule
     * 
     * @return actRule.
     */
    public String getActRule() {
        return actRule;
    }

    /**
     * 设置 actRule
     * 
     * @param actRule actRule
     */
    public void setActRule(String actRule) {
        this.actRule = actRule;
    }

    /**
     * 获取 basicLevel
     * 
     * @return basicLevel.
     */
    public String getBasicLevel() {
        return basicLevel;
    }

    /**
     * 设置 basicLevel
     * 
     * @param basicLevel basicLevel
     */
    public void setBasicLevel(String basicLevel) {
        this.basicLevel = basicLevel;
    }

	/**
	 * 获取 bankRatio
	 * @return bankRatio.
	 */
	public BigDecimal getBankRatio() {
		return bankRatio;
	}

	/**
	 * 设置 bankRatio
	 * @param bankRatio bankRatio
	 */
	public void setBankRatio(BigDecimal bankRatio) {
		this.bankRatio = bankRatio;
	}

    /**
	 * 获取 baseRuleId
	 * @return baseRuleId.
	 */
	public String getBaseRuleId() {
		return baseRuleId;
	}

	/**
	 * 设置 baseRuleId
	 * @param baseRuleId baseRuleId
	 */
	public void setBaseRuleId(String baseRuleId) {
		this.baseRuleId = baseRuleId;
	}
	
	/**
	 * 获取 coefficient
	 * @return coefficient.
	 */
	public BigDecimal getCoefficient() {
		return coefficient;
	}

	/**
	 * 设置 coefficient
	 * @param coefficient coefficient
	 */
	public void setCoefficient(BigDecimal coefficient) {
		this.coefficient = coefficient;
	}
	
	/**
	 * 获取 spread
	 * @return spread.
	 */
	public BigDecimal getSpread() {
		return spread;
	}

	/**
	 * 设置 spread
	 * @param spread spread
	 */
	public void setSpread(BigDecimal spread) {
		this.spread = spread;
	}

	/**
	 * 获取 firCalculaDate
	 * @return firCalculaDate.
	 */
	public String getFirCalculaDate() {
		return firCalculaDate;
	}

	/**
	 * 设置 firCalculaDate
	 * @param firCalculaDate firCalculaDate
	 */
	public void setFirCalculaDate(String firCalculaDate) {
		this.firCalculaDate = firCalculaDate;
	}

	/**
	 * 获取 endCalculaDate
	 * @return endCalculaDate.
	 */
	public String getEndCalculaDate() {
		return endCalculaDate;
	}

	/**
	 * 设置 endCalculaDate
	 * @param endCalculaDate endCalculaDate
	 */
	public void setEndCalculaDate(String endCalculaDate) {
		this.endCalculaDate = endCalculaDate;
	}
	
	/**
	 * 获取 actVdateType
	 * @return actVdateType.
	 */
	public String getActVdateType() {
		return actVdateType;
	}

	/**
	 * 设置 actVdateType
	 * @param actVdateType actVdateType
	 */
	public void setActVdateType(String actVdateType) {
		this.actVdateType = actVdateType;
	}
	
	/**
	 * 获取 accrCycle
	 * @return accrCycle.
	 */
	public String getAccrCycle() {
		return accrCycle;
	}

	/**
	 * 设置 accrCycle
	 * @param accrCycle accrCycle
	 */
	public void setAccrCycle(String accrCycle) {
		this.accrCycle = accrCycle;
	}

	/**
	 * 获取 isAccrFloManageFee
	 * @return isAccrFloManageFee.
	 */
	public String getIsAccrFloManageFee() {
		return isAccrFloManageFee;
	}

	/**
	 * 设置 isAccrFloManageFee
	 * @param isAccrFloManageFee isAccrFloManageFee
	 */
	public void setIsAccrFloManageFee(String isAccrFloManageFee) {
		this.isAccrFloManageFee = isAccrFloManageFee;
	}

}
