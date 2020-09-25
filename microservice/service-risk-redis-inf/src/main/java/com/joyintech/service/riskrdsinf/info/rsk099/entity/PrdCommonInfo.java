/*
 * 文件名：PrdCommonInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.info.rsk099.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.service.riskrdsinf.core.annotation.DictLink;
import com.joyintech.service.riskrdsinf.core.annotation.Index;
import com.joyintech.service.riskrdsinf.core.constant.InfDictCode;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.prodinfo.dto.IPtlProd;

/**
 * 产品基本信息受体类
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月12日
 * @see PrdCommonInfo
 * @since 1.0
 */
public class PrdCommonInfo extends BaseEntity implements IPtlProd {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -2128552502376275665L;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 投资性质
     */
    private String investType;

    /**
     * 开放频率
     */
    private String issueMode;

    /**
     * 开放类型
     */
    private String openType;

    /**
     * 募集方式
     */
    private String raiseType;

    /**
     * 产品收益类型
     */
    private String prodType;

    /**
     * 产品业绩比较基准
     */
    private BigDecimal pcbAmt;

    /**
     * 交易日历代码
     */
    private String calendarCode;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 投资经理姓名
     */
    private String investMgr;
    
    /**
     * 交易币种
     */
    private String ccy;
    
    /**
     * 资管组合代码
     */
    private String relPortfolio;
    /**
     * 是否启用风险
     */
    private String riskEnabled;

    /**
     * 取得 产品代码
     * 
     * @return 产品代码
     */
    public String getProdId() {
        return this.prodId;
    }

    /**
     * 设置 产品代码
     * 
     * @param prodId 产品代码
     */
    @Index(4)
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 取得 产品名称
     * 
     * @return 产品名称
     */
    public String getProdName() {
        return this.prodName;
    }

    /**
     * 设置 产品名称
     * 
     * @param prodName 产品名称
     */
    @Index(5)
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     * 取得 投资性质
     * 
     * @return 投资性质
     */
    public String getInvestType() {
        return this.investType;
    }

    /**
     * 设置 投资性质
     * 
     * @param investType 投资性质
     */
    @Index(64)
    @DictLink(link = InfDictCode.投资性质)
    public void setInvestType(String investType) {
        this.investType = investType;
    }

    /**
     * 取得 开放类型
     * 
     * @return 开放类型
     */
    public String getIssueMode() {
        return this.issueMode;
    }

    /**
     * 设置 开放类型
     * 
     * @param issueMode 开放类型
     */
    @Index(18)
    @DictLink(link = InfDictCode.开放频率)
    public void setIssueMode(String issueMode) {
        this.issueMode = issueMode;
    }

    /**
     * 取得 开放频率
     * 
     * @return 开放频率
     */
    public String getOpenType() {
        return this.openType;
    }

    /**
     * 设置 开放频率
     * 
     * @param openType 开放频率
     */
    @Index(10)
    @DictLink(link = InfDictCode.开放类型)
    public void setOpenType(String openType) {
        this.openType = openType;
    }

    /**
     * 取得 募集方式
     * 
     * @return 募集方式
     */
    public String getRaiseType() {
        return this.raiseType;
    }

    /**
     * 设置 募集方式
     * 
     * @param raiseType 募集方式
     */
    @Index(80)
    @DictLink(link = InfDictCode.募集方式)
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }

    /**
     * 取得 产品收益类型
     * 
     * @return 产品收益类型
     */
    public String getProdType() {
        return this.prodType;
    }

    /**
     * 设置 产品收益类型
     * 
     * @param prodType 产品收益类型
     */
    @Index(9)
    @DictLink(link = InfDictCode.产品收益类型)
    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    /**
     * 取得 产品业绩比较基准
     * 
     * @return 产品业绩比较基准
     */
    public BigDecimal getPcbAmt() {
        return this.pcbAmt;
    }

    /**
     * 设置 产品业绩比较基准
     * 
     * @param pcbAmt 产品业绩比较基准
     */
    public void setPcbAmt(BigDecimal pcbAmt) {
        this.pcbAmt = pcbAmt;
    }

    /**
     * 取得 交易日历代码
     * 
     * @return 交易日历代码
     */
    public String getCalendarCode() {
        return this.calendarCode;
    }

    /**
     * 设置 交易日历代码
     * 
     * @param calendarCode 交易日历代码
     */
    public void setCalendarCode(String calendarCode) {
        this.calendarCode = calendarCode;
    }

    /**
     * 取得 起息日
     * 
     * @return 起息日
     */
    public Date getVdate() {
        return this.vdate;
    }

    /**
     * 设置 起息日
     * 
     * @param vdate 起息日
     */
    @Index(30)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * 
     * @return 到期日
     */
    public Date getMdate() {
        return this.mdate;
    }

    /**
     * 设置 到期日
     * 
     * @param mdate 到期日
     */
    @Index(31)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 投资经理姓名
     * 
     * @return 投资经理姓名
     */
    public String getInvestMgr() {
        return this.investMgr;
    }

    /**
     * 设置 投资经理姓名
     * 
     * @param investMgr 投资经理姓名
     */
    @Index(62)
    public void setInvestMgr(String investMgr) {
        this.investMgr = investMgr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPortfolioId() {
        return getProdId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPortfolioName() {
        return getProdName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPortfolioType() {
        return BussinessDictCodeListRds.组合类型_投资类组合.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProfitType() {
        return getProdType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIsGuarantee() {
        return BussinessDictCodeListRds.是否_否.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInvestNature() {
        return getInvestType();
    }

    /**
     * 取得 交易币种
     * 
     * @return 交易币种
     */
	public String getCcy() {
		return ccy;
	}

	/**
	 * 设置 交易币种
	 * 
	 * @param ccy 交易币种
	 */
	@Index(24)
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	/**
	 * 取得 资管组合代码
	 * 
	 * @return 资管组合代码
	 */
	public String getRelPortfolio() {
		return relPortfolio;
	}

	/**
	 * 设置 资管组合代码
	 * 
	 * @param relPortfolio 资管组合代码
	 */
	@Index(14)
	public void setRelPortfolio(String relPortfolio) {
		this.relPortfolio = relPortfolio;
	}

	public String getRiskEnabled() {
		return riskEnabled;
	}

	public void setRiskEnabled(String riskEnabled) {
		this.riskEnabled = riskEnabled;
	}
	
}
