/*
 * 文件名：ValProductInfoFeeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：Aug 17, 2018
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.basedata.productinfoquery.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author qiangchengxi
 * @version 1.0 Aug 17, 2018
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
     * 金融产品类型，债券、理财产品、基金
     */
    private String finprodTypeCode;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 计息基础
     */
    private String basisCode;

    /**
     * 现金流一级类型
     */
    private String cashType1Code;

    /**
     * 现金流一级类型
     */
    private String cashType1;

    /**
     * 现金流二级类型
     */
    private String cashType2Code;

    /**
     * 现金流二级类型
     */
    private String cashType2;

    /**
     * 生效日期
     */
    private String effDate;

    /**
     * 计算类型，固定利率、浮动利率
     */
    private String calcType;

    /**
     * 计算类型，固定利率、浮动利率
     */
    private String calcTypeCode;

//    /**
//     * 浮动利率编号
//     */
//    <!-- 	      	现金流计算规则表浮动利率编号删除   by huangjian 20190103 -->
//    private String intRateId;

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
     * 基数类型
     */
    private String baseTypeCode;

    /**
     * 基数日期类型，上一工作日、上一自然日、当日、上一开放日等
     */
    private String baseDateType;

    /**
     * 基数日期类型，上一工作日、上一自然日、当日、上一开放日等
     */
    private String baseDateTypeCode;

    /**
     * 支付频率
     */
    private String payCycle;

    /**
     * 支付频率
     */
    private String payCycleCode;

//    /**
//     * 营业日准则
//     */
//    private String tdayRule;
//
//    /**
//     * 营业日准则
//     */
//    private String tdayRuleCode;
    
    /**
     * 截止日营业日准则
     */
    private String endTdayRule;

    /**
     * 截止日营业日准则Code
     */
    private String endTdayRuleCode;
    
    /**
     * 支付日营业日准则
     */
    private String payTdayRule;
    
    /**
     * 支付日营业日准则Code
     */
    private String payTdayRuleCode;

    /**
     * 计算规则
     */
    private String calcRule;

    /**
     * 计算规则code
     */
    private String calcRuleCode;

    /**
     * 首次支付日
     */
    private String firstPayDate;

    /**
     * 现金流单位，百元法、总额法
     */
    private String cashUnitType;

    /**
     * 现金流单位，百元法、总额法
     */
    private String cashUnitTypeCode;

    /**
     * 支付方式，现金、红利再投等
     */
    private String payType;

    /**
     * 支付方式，现金、红利再投等
     */
    private String payTypeCode;

    /**
     * 生效规则
     */
    private String actRule;

    /**
     * 计费营业日准则
     */
    private String accrTdayRule;

    /**
     * 业绩比较基准层级
     */
    private String basicLevel;
    
    /**
     * 层级描述
     */
    private String levelRemark;

    /**
     * 银行收取比例
     */
    private BigDecimal bankRatio;

    /**
     * 客户分配比例
     */
    private BigDecimal customerRatio;
    
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
     * @return finprodType.
     */
    public String getFinprodType() {
//        return SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, this.finprodType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, this.finprodType))?
    			SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, this.finprodType):this.finprodType;
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
     * 获取 finprodTypeCode
     * 
     * @return finprodTypeCode.
     */
    public String getFinprodTypeCode() {
        return finprodTypeCode;
    }

    /**
     * 设置 finprodTypeCode
     * 
     * @param finprodTypeCode finprodTypeCode
     */
    public void setFinprodTypeCode(String finprodTypeCode) {
        this.finprodTypeCode = finprodTypeCode;
    }

    /**
     * 获取 basis
     * 
     * @return basis.
     */
    public String getBasis() {
//        return SysDataUtil.getNameFromDataDict(DictCode.计息基础, this.basis);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.计息基础, this.basis))?
    			SysDataUtil.getNameFromDataDict(DictCode.计息基础, this.basis):this.basis;
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
     * 获取 basisCode
     * 
     * @return basisCode.
     */
    public String getBasisCode() {
        return basisCode;
    }

    /**
     * 设置 basisCode
     * 
     * @param basisCode basisCode
     */
    public void setBasisCode(String basisCode) {
        this.basisCode = basisCode;
    }

    /**
     * 获取 cashType1Code
     * 
     * @return cashType1Code.
     */
    public String getCashType1Code() {
        return cashType1Code;
    }

    /**
     * 设置 cashType1Code
     * 
     * @param cashType1Code cashType1Code
     */
    public void setCashType1Code(String cashType1Code) {
        this.cashType1Code = cashType1Code;
    }

    /**
     * 获取 cashType1
     * 
     * @return cashType1.
     */
    public String getCashType1() {
//        return SysDataUtil.getNameFromDataDict(DictCode.现金流一级类型, this.cashType1);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.现金流一级类型, this.cashType1))?
    			SysDataUtil.getNameFromDataDict(DictCode.现金流一级类型, this.cashType1):this.cashType1;
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
     * 获取 cashType2Code
     * 
     * @return cashType2Code.
     */
    public String getCashType2Code() {
        return cashType2Code;
    }

    /**
     * 设置 cashType2Code
     * 
     * @param cashType2Code cashType2Code
     */
    public void setCashType2Code(String cashType2Code) {
        this.cashType2Code = cashType2Code;
    }

    /**
     * 获取 cashType2
     * 
     * @return cashType2.
     */
    public String getCashType2() {
//        return SysDataUtil.getNameFromDataDict(DictCode.现金流二级类型, this.cashType2);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.现金流二级类型, this.cashType2))?
    			SysDataUtil.getNameFromDataDict(DictCode.现金流二级类型, this.cashType2):this.cashType2;
    }

    /**
     * 设置 cashType2
     * 
     * @param cashType2 cashType2
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
     * 获取 calcType
     * 
     * @return calcType.
     */
    public String getCalcType() {
//        return SysDataUtil.getNameFromDataDict(DictCode.增值税_计算方式, this.calcType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.增值税_计算方式, this.calcType))?
    			SysDataUtil.getNameFromDataDict(DictCode.增值税_计算方式, this.calcType):this.calcType;
    }

    /**
     * 设置 calcType
     * 
     * @param calcType calcType
     */
    public void setCalcType(String calcType) {
        this.calcType = calcType;
    }

    /**
     * 获取 calcTypeCode
     * 
     * @return calcTypeCode.
     */
    public String getCalcTypeCode() {
        return calcTypeCode;
    }

    /**
     * 设置 calcTypeCode
     * 
     * @param calcTypeCode calcTypeCode
     */
    public void setCalcTypeCode(String calcTypeCode) {
        this.calcTypeCode = calcTypeCode;
    }

//    /**
//     * 获取 intRateId
//     * 
//     * @return intRateId.
//     */
//    public String getIntRateId() {
//        return intRateId;
//    }
//<!-- 	      	现金流计算规则表浮动利率编号删除   by huangjian 20190103 -->
//    /**
//     * 设置 intRateId
//     * 
//     * @param intRateId intRateId
//     */
//    public void setIntRateId(String intRateId) {
//        this.intRateId = intRateId;
//    }

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
     * 获取 baseType
     * 
     * @return baseType.
     */
    public String getBaseType() {
        return baseType;
//        return SysDataUtil.getNameFromDataDict(DictCode.基数类型, this.baseType);
    }
    
    /**
     * 获取 baseType
     * 
     * @return baseType.
     */
    public String getBaseTypeFt() {
//        return baseType;
//        return SysDataUtil.getNameFromDataDict(DictCode.基数类型, this.baseType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.基数类型, this.baseType))?
    			SysDataUtil.getNameFromDataDict(DictCode.基数类型, this.baseType):this.baseType;
    }
    
    
    /**
     * 设置 baseType
     * 
     * @param baseType baseType
     */
    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

    /**
     * 获取 baseTypeCode
     * 
     * @return baseTypeCode.
     */
    public String getBaseTypeCode() {
        return baseTypeCode;
    }

    /**
     * 设置 baseTypeCode
     * 
     * @param baseTypeCode baseTypeCode
     */
    public void setBaseTypeCode(String baseTypeCode) {
        this.baseTypeCode = baseTypeCode;
    }

    /**
     * 获取 baseDateType
     * 
     * @return baseDateType.
     */
    public String getBaseDateType() {
//        return SysDataUtil.getNameFromDataDict(DictCode.基数日期类型, this.baseDateType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.基数日期类型, this.baseDateType))?
    			SysDataUtil.getNameFromDataDict(DictCode.基数日期类型, this.baseDateType):this.baseDateType;
    }

    /**
     * 设置 baseDateType
     * 
     * @param baseDateType baseDateType
     */
    public void setBaseDateType(String baseDateType) {
        this.baseDateType = baseDateType;
    }

    /**
     * 获取 baseDateTypeCode
     * 
     * @return baseDateTypeCode.
     */
    public String getBaseDateTypeCode() {
        return baseDateTypeCode;
    }

    /**
     * 设置 baseDateTypeCode
     * 
     * @param baseDateTypeCode baseDateTypeCode
     */
    public void setBaseDateTypeCode(String baseDateTypeCode) {
        this.baseDateTypeCode = baseDateTypeCode;
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
     * 获取 payCycle
     * 
     * @return payCycle.
     */
    public String getPayCycle() {
//        return SysDataUtil.getNameFromDataDict(DictCode.频率, this.payCycle);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.频率, this.payCycle))?
    			SysDataUtil.getNameFromDataDict(DictCode.频率, this.payCycle):this.payCycle;
    }

    /**
     * 设置 payCycle
     * 
     * @param payCycle payCycle
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle;
    }

    /**
     * 获取 payCycleCode
     * 
     * @return payCycleCode.
     */
    public String getPayCycleCode() {
        return payCycleCode;
    }

    /**
     * 设置 payCycleCode
     * 
     * @param payCycleCode payCycleCode
     */
    public void setPayCycleCode(String payCycleCode) {
        this.payCycleCode = payCycleCode;
    }

//    /**
//     * 获取 tdayRule
//     * 
//     * @return tdayRule.
//     */
//    public String getTdayRule() {
//        return SysDataUtil.getNameFromDataDict(DictCode.营业日准则, this.tdayRule);
//    }
//
//    /**
//     * 设置 tdayRule
//     * 
//     * @param tdayRule tdayRule
//     */
//    public void setTdayRule(String tdayRule) {
//        this.tdayRule = tdayRule;
//    }
//
//    /**
//     * 获取 tdayRuleCode
//     * 
//     * @return tdayRuleCode.
//     */
//    public String getTdayRuleCode() {
//        return tdayRuleCode;
//    }
//
//    /**
//     * 设置 tdayRuleCode
//     * 
//     * @param tdayRuleCode tdayRuleCode
//     */
//    public void setTdayRuleCode(String tdayRuleCode) {
//        this.tdayRuleCode = tdayRuleCode;
//    }

    
    public String getEndTdayRule() {
//    	return SysDataUtil.getNameFromDataDict(DictCode.营业日准则, endTdayRule);
    	return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.营业日准则, endTdayRule))?
    			SysDataUtil.getNameFromDataDict(DictCode.营业日准则, endTdayRule):this.endTdayRule;
	}


	public void setEndTdayRule(String endTdayRule) {
		this.endTdayRule = endTdayRule;
	}


	public String getEndTdayRuleCode() {
		return endTdayRuleCode;
	}


	public void setEndTdayRuleCode(String endTdayRuleCode) {
		this.endTdayRuleCode = endTdayRuleCode;
	}


	public String getPayTdayRule() {
//		return SysDataUtil.getNameFromDataDict(DictCode.营业日准则, payTdayRule);
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.营业日准则, payTdayRule))?
    			SysDataUtil.getNameFromDataDict(DictCode.营业日准则, payTdayRule):this.payTdayRule;
	}


	public void setPayTdayRule(String payTdayRule) {
		this.payTdayRule = payTdayRule;
	}


	public String getPayTdayRuleCode() {
		return payTdayRuleCode;
	}


	public void setPayTdayRuleCode(String payTdayRuleCode) {
		this.payTdayRuleCode = payTdayRuleCode;
	}
    
    
    /**
     * 获取 calcRule
     * 
     * @return calcRule.
     */
    public String getCalcRule() {
//        return SysDataUtil.getNameFromDataDict(DictCode.业绩报酬计算规则, this.calcRule);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.业绩报酬计算规则, this.calcRule))?
    			SysDataUtil.getNameFromDataDict(DictCode.业绩报酬计算规则, this.calcRule):this.calcRule;
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
     * 获取 calcRuleCode
     * 
     * @return calcRuleCode.
     */
    public String getCalcRuleCode() {
        return calcRuleCode;
    }

    /**
     * 设置 calcRuleCode
     * 
     * @param calcRuleCode calcRuleCode
     */
    public void setCalcRuleCode(String calcRuleCode) {
        this.calcRuleCode = calcRuleCode;
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
     * 获取 cashUnitType
     * 
     * @return cashUnitType.
     */
    public String getCashUnitType() {
//        return SysDataUtil.getNameFromDataDict(DictCode.现金流单位, this.cashUnitType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.现金流单位, this.cashUnitType))?
    			SysDataUtil.getNameFromDataDict(DictCode.现金流单位, this.cashUnitType):this.cashUnitType;
    }

    /**
     * 设置 cashUnitType
     * 
     * @param cashUnitType cashUnitType
     */
    public void setCashUnitType(String cashUnitType) {
        this.cashUnitType = cashUnitType;
    }

    /**
     * 获取 cashUnitTypeCode
     * 
     * @return cashUnitTypeCode.
     */
    public String getCashUnitTypeCode() {
        return cashUnitTypeCode;
    }

    /**
     * 设置 cashUnitTypeCode
     * 
     * @param cashUnitTypeCode cashUnitTypeCode
     */
    public void setCashUnitTypeCode(String cashUnitTypeCode) {
        this.cashUnitTypeCode = cashUnitTypeCode;
    }

    /**
     * 获取 payType
     * 
     * @return payType.
     */
    public String getPayType() {
//        return SysDataUtil.getNameFromDataDict(DictCode.支付方式, this.payType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.支付方式, this.payType))?
    			SysDataUtil.getNameFromDataDict(DictCode.支付方式, this.payType):this.payType;
    }

    /**
     * 设置 payType
     * 
     * @param payType payType
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取 payTypeCode
     * 
     * @return payTypeCode.
     */
    public String getPayTypeCode() {
        return payTypeCode;
    }

    /**
     * 设置 payTypeCode
     * 
     * @param payTypeCode payTypeCode
     */
    public void setPayTypeCode(String payTypeCode) {
        this.payTypeCode = payTypeCode;
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
     * 获取 accrTdayRule
     * 
     * @return accrTdayRule.
     */
    public String getAccrTdayRule() {
        return accrTdayRule;
    }

    /**
     * 设置 accrTdayRule
     * 
     * @param accrTdayRule accrTdayRule
     */
    public void setAccrTdayRule(String accrTdayRule) {
        this.accrTdayRule = accrTdayRule;
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
	 * 获取 levelRemark
	 * @return levelRemark.
	 */
	public String getLevelRemark() {
		return levelRemark;
	}

	/**
	 * 设置 levelRemark
	 * @param levelRemark levelRemark
	 */
	public void setLevelRemark(String levelRemark) {
		this.levelRemark = levelRemark;
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
	 * 获取 customerRatio
	 * @return customerRatio.
	 */
	public BigDecimal getCustomerRatio() {
		return customerRatio;
	}

	/**
	 * 设置 customerRatio
	 * @param customerRatio customerRatio
	 */
	public void setCustomerRatio(BigDecimal customerRatio) {
		this.customerRatio = customerRatio;
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

	

}
