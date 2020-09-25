/*
 * 文件名：bussBenchMarkLayeredInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2019年6月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.autogeneration.proledger.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年6月22日
 * @see bussBenchMarkLayeredInfoDto
 * @since 1.0
 */
public class BussBenchMarkLayeredInfoDto{
    
    /**
     * 产品款型
     */
    private String prodStyle;
    /**
     * 投资周期
     */
    private String investmentCycle;
    
    private BigDecimal floatRate;    
    /**
     * 产品分档款型业基准
     */
    private List<BussBenchMarkLayeredInfoDto> bussBenchMarkLayeredInfoDtoList;
    
private String indexIdSource;
    
    private Date vdate;
    
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
     * 基准来源，复合基准、利率行情、指数行情、曲线
     */
    private String indexSource;

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

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 备注
     */
    private String remark;

    /**
     * 业绩基准类型,产品披露，浮动管理费，业绩回补
     */
    private String baseRuleType;

    /**
     * 业绩基准披露类型，区间披露，单点披露
     */
    private String publishType;

    /**
     * 利率下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateLower;

    /**
     * 利率上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateLimit;

    private String lowLimitRate;
    
    /**
     * 业绩基准代码，同金融产品代码
     */
    private String baseRuleId;

    /**
     * 生效日期
     */
    private Date effDate;
    
    
    public BigDecimal getFloatRate() {
		return floatRate;
	}


	public void setFloatRate(BigDecimal floatRate) {
		this.floatRate = floatRate;
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


    
    /**
     * 获取 bussBenchMarkLayeredInfoDtoList
     * @return bussBenchMarkLayeredInfoDtoList.
     */
    public List<BussBenchMarkLayeredInfoDto> getBussBenchMarkLayeredInfoDtoList() {
        return bussBenchMarkLayeredInfoDtoList;
    }


    
    /**
     * 设置 bussBenchMarkLayeredInfoDtoList
     * @param bussBenchMarkLayeredInfoDtoList bussBenchMarkLayeredInfoDtoList
     */
    public void setBussBenchMarkLayeredInfoDtoList(List<BussBenchMarkLayeredInfoDto> bussBenchMarkLayeredInfoDtoList) {
        this.bussBenchMarkLayeredInfoDtoList = bussBenchMarkLayeredInfoDtoList;
    }


	public String getInvestmentCycle() {
		return investmentCycle;
	}


	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}


	/**
	 * @return the indexIdSource
	 */
	public String getIndexIdSource() {
		return indexIdSource;
	}


	/**
	 * @param indexIdSource the indexIdSource to set
	 */
	public void setIndexIdSource(String indexIdSource) {
		this.indexIdSource = indexIdSource;
	}


	/**
	 * @return the vdate
	 */
	public Date getVdate() {
		return vdate;
	}


	/**
	 * @param vdate the vdate to set
	 */
	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}


	/**
	 * @return the rateType
	 */
	public String getRateType() {
		return rateType;
	}


	/**
	 * @param rateType the rateType to set
	 */
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}


	/**
	 * @return the rate
	 */
	public BigDecimal getRate() {
		return rate;
	}


	/**
	 * @param rate the rate to set
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}


	/**
	 * @return the indexId
	 */
	public String getIndexId() {
		return indexId;
	}


	/**
	 * @param indexId the indexId to set
	 */
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}


	/**
	 * @return the indexSource
	 */
	public String getIndexSource() {
		return indexSource;
	}


	/**
	 * @param indexSource the indexSource to set
	 */
	public void setIndexSource(String indexSource) {
		this.indexSource = indexSource;
	}


	/**
	 * @return the coefficient
	 */
	public BigDecimal getCoefficient() {
		return coefficient;
	}


	/**
	 * @param coefficient the coefficient to set
	 */
	public void setCoefficient(BigDecimal coefficient) {
		this.coefficient = coefficient;
	}


	/**
	 * @return the spreadRate
	 */
	public BigDecimal getSpreadRate() {
		return spreadRate;
	}


	/**
	 * @param spreadRate the spreadRate to set
	 */
	public void setSpreadRate(BigDecimal spreadRate) {
		this.spreadRate = spreadRate;
	}


	/**
	 * @return the resetType
	 */
	public String getResetType() {
		return resetType;
	}


	/**
	 * @param resetType the resetType to set
	 */
	public void setResetType(String resetType) {
		this.resetType = resetType;
	}


	/**
	 * @return the finprodId
	 */
	public String getFinprodId() {
		return finprodId;
	}


	/**
	 * @param finprodId the finprodId to set
	 */
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}


	/**
	 * @return the branch
	 */
	public Long getBranch() {
		return branch;
	}


	/**
	 * @param branch the branch to set
	 */
	public void setBranch(Long branch) {
		this.branch = branch;
	}


	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}


	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
	 * @return the baseRuleType
	 */
	public String getBaseRuleType() {
		return baseRuleType;
	}


	/**
	 * @param baseRuleType the baseRuleType to set
	 */
	public void setBaseRuleType(String baseRuleType) {
		this.baseRuleType = baseRuleType;
	}


	/**
	 * @return the publishType
	 */
	public String getPublishType() {
		return publishType;
	}


	/**
	 * @param publishType the publishType to set
	 */
	public void setPublishType(String publishType) {
		this.publishType = publishType;
	}


	/**
	 * @return the rateLower
	 */
	public BigDecimal getRateLower() {
		return rateLower;
	}


	/**
	 * @param rateLower the rateLower to set
	 */
	public void setRateLower(BigDecimal rateLower) {
		this.rateLower = rateLower;
	}


	/**
	 * @return the rateLimit
	 */
	public BigDecimal getRateLimit() {
		return rateLimit;
	}


	/**
	 * @param rateLimit the rateLimit to set
	 */
	public void setRateLimit(BigDecimal rateLimit) {
		this.rateLimit = rateLimit;
	}


	/**
	 * @return the lowLimitRate
	 */
	public String getLowLimitRate() {
		return lowLimitRate;
	}


	/**
	 * @param lowLimitRate the lowLimitRate to set
	 */
	public void setLowLimitRate(String lowLimitRate) {
		this.lowLimitRate = lowLimitRate;
	}


	/**
	 * @return the baseRuleId
	 */
	public String getBaseRuleId() {
		return baseRuleId;
	}


	/**
	 * @param baseRuleId the baseRuleId to set
	 */
	public void setBaseRuleId(String baseRuleId) {
		this.baseRuleId = baseRuleId;
	}


	/**
	 * @return the effDate
	 */
	public Date getEffDate() {
		return effDate;
	}


	/**
	 * @param effDate the effDate to set
	 */
	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}
}
