/*
 * 文件名：PayTheVatQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chufengjie
 * 修改时间：2019年02月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.paythevatquery.dto;

import java.math.BigDecimal;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.hibernate.validator.constraints.NotBlank;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.trademanage.paythevatquery.validator.PayTheVatQuery001CheckValidator;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;


/**
 * 纳税增值税查询
 * 
 * @author chufengjie
 * @version 1.0 2019年02月23日
 * @see PayTheVatQuery001Dto
 * @since 1.0
 */
@PageId("PayTheVatQuery003")
@ValidLogic(targetClass = PayTheVatQuery001CheckValidator.class)
public class PayTheVatQuery001Dto extends BaseEntity implements ProcessEntity {

	private static final long serialVersionUID = 1L;

	/** 产品代码 */
	@PrimaryKey(value = Field.PK_1)
	@NotBlank(message = "资产代码不能为空！")
	private String booksetId;
	/** 交易编号 */
	private String tradeId;
	/** 交易编号 */
	private String ntradeId;
	/** 产品名称 */
	private String booksetName;
	/** 缴税/退税开始日 */
	private String vDate;
	/** 缴税/退税结束日 */
	private String mDate;
	/** 缴税/退税日期 */
	private String seitleDate;
	/** 利息收入 */
	private BigDecimal interestIncome;
	/** 红利收入 */
	private BigDecimal dividendIncome;
	/** 价差收入 */
	private BigDecimal difbetIncome;
	/** 增值税 */
	private BigDecimal appreciationTax;
	/** 城建税附加 */
	private BigDecimal constructionTax;
	/** 教育税附加 */
	private BigDecimal educationTax;
	/** 地方教育费附加 */
	private BigDecimal placeeducationTax;
	/** 增值税附加 */
	private BigDecimal additionalTax;
	/** 增值税附加 */
	private BigDecimal feeAmt;
	/** 费用类型 */
	private String feeType;
	/** 缴税/退税 */
	private String taxidenType;
	/** 业务类型 */
	private String busiType;
	/** 金融产品类型 */
	private String finprodType;
	/** 金融产品代码 */
	private String finprodId;
	/** 交易币种 */
	private String cny;
	/** 现金流代码 */
	private String cashId;
	/** 税金额 */
	private BigDecimal tradeAmt;
	/** 账务明细代码 */
	private String bokDetailid;
	/** 指令编号生成 */
	private String orderId;
	/** 本方资金账户 */
	private String acctNo;
	/** 投资组合代码 */
	private String portfolioId;
	/** 理财产品代码 */
	private String prodId;
	/** 处理操作 */
	private String operateType;
	/** 账套.核算主体类别 */
	private String acctsubjectType;
	/** 本位币 */
	private String standardCcy;
	/** 交易状态 */
	private String tradeStatus;
	/**
     * 指令类型
     */
    private String orderType;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public String getBooksetName() {
		return booksetName;
	}

	public void setBooksetName(String booksetName) {
		this.booksetName = booksetName;
	}

	public String getvDate() {
		return vDate;
	}

	public void setvDate(String vDate) {
		this.vDate = vDate;
	}

	public String getmDate() {
		return mDate;
	}

	public void setmDate(String mDate) {
		this.mDate = mDate;
	}

	public String getSeitleDate() {
		return seitleDate;
	}

	public void setSeitleDate(String seitleDate) {
		this.seitleDate = seitleDate;
	}

	public BigDecimal getInterestIncome() {
		return interestIncome;
	}

	public void setInterestIncome(BigDecimal interestIncome) {
		this.interestIncome = interestIncome;
	}

	public BigDecimal getDividendIncome() {
		return dividendIncome;
	}

	public void setDividendIncome(BigDecimal dividendIncome) {
		this.dividendIncome = dividendIncome;
	}

	public BigDecimal getDifbetIncome() {
		return difbetIncome;
	}

	public void setDifbetIncome(BigDecimal difbetIncome) {
		this.difbetIncome = difbetIncome;
	}

	public BigDecimal getAppreciationTax() {
		return appreciationTax;
	}

	public void setAppreciationTax(BigDecimal appreciationTax) {
		this.appreciationTax = appreciationTax;
	}

	public BigDecimal getConstructionTax() {
		return constructionTax;
	}

	public void setConstructionTax(BigDecimal constructionTax) {
		this.constructionTax = constructionTax;
	}

	public BigDecimal getEducationTax() {
		return educationTax;
	}

	public void setEducationTax(BigDecimal educationTax) {
		this.educationTax = educationTax;
	}

	public BigDecimal getPlaceeducationTax() {
		return placeeducationTax;
	}

	public void setPlaceeducationTax(BigDecimal placeeducationTax) {
		this.placeeducationTax = placeeducationTax;
	}

	public BigDecimal getAdditionalTax() {
		return additionalTax;
	}

	public void setAdditionalTax(BigDecimal additionalTax) {
		this.additionalTax = additionalTax;
	}

	public String getTaxidenType() {
        return taxidenType;
    }
	
	public String getTaxidenTypeFmt() {
	    if(DictCodeList.交易方向_税费缴税.getValue().equals(taxidenType)){
	        return "缴税";
	    }
	    else{
	        return "退税";
	    }
	}

	public void setTaxidenType(String taxidenType) {
		this.taxidenType = taxidenType;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getFinprodType() {
		return finprodType;
	}

	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}

	public String getCny() {
		return cny;
	}

	public void setCny(String cny) {
		this.cny = cny;
	}

	public String getCashId() {
		return cashId;
	}

	public void setCashId(String cashId) {
		this.cashId = cashId;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public String getNtradeId() {
		return ntradeId;
	}

	public void setNtradeId(String ntradeId) {
		this.ntradeId = ntradeId;
	}

	public String getBokDetailid() {
		return bokDetailid;
	}

	public void setBokDetailid(String bokDetailid) {
		this.bokDetailid = bokDetailid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getAcctsubjectType() {
		return acctsubjectType;
	}

	public void setAcctsubjectType(String acctsubjectType) {
		this.acctsubjectType = acctsubjectType;
	}

	public String getStandardCcy() {
		return standardCcy;
	}

	public void setStandardCcy(String standardCcy) {
		this.standardCcy = standardCcy;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public BigDecimal getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}
	
	public String getTradeStatusFmt() {
	    if(DictCodeListExt1.交易状态_初始.getValue().equals(tradeStatus)){
	        return "新建";
	    }
        return SysDataUtil.getNameFromDataDict(DictCode.交易状态, tradeStatus);
    }

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

    /**
     * 获取 orderType
     * @return orderType.
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置 orderType
     * @param orderType orderType
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

}
