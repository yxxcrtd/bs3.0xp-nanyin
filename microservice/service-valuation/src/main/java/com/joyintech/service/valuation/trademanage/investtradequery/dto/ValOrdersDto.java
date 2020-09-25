/*
 * 文件名：ValOrdersDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.investtradequery.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 结算指令列表Dto
 * 
 * @author zhangwei
 * @version 1.0 2018年7月30日
 * @see ValOrdersDto
 * @since 1.0
 */
public class ValOrdersDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /** 指令编号 **/
    private String orderId;

    /** 业务类型Code **/
    private String busiTypeCode;

    /** 业务类型 **/
    private String busiType;

    /** 交易编号 **/
    private String tradeId;

    /** 投资主体代码 **/
    private String prodId;

    /** 投资主体名称 **/
    private String prodAbbr;

    /** 资产代码 **/
    private String assetId;

    /** 资产简称 **/
    private String assetAbbr;

    /** 指令类型Code **/
    private String orderTypeCode;

    /** 指令类型 **/
    private String orderType;

    /** 结算日期 **/
    private String settleDate;

    /** 通道费 **/
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tdFeeAmt;

    /** 结算金额 **/
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal settleAmt;
    
    /**费用金额*/
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal feeAmt;

    /** 结算状态Code **/
    private String settleStatusCode;

    /** 结算状态 **/
    private String settleStatus;

    /** 收付方向Code **/
    private String psCode;

    /** 收付方向 **/
    private String ps;

    /** 本方资金账户 **/
    private String myAcctNo;

    /** 对手方资金账户 **/
    private String counterAcctNo;

    /** 本方资金账户户名 **/
    private String myAcctName;

    /** 对手方资金账户户名 **/
    private String counterAcctName;
 
    /** 币种Code **/
    private String ccyCode;

    /** 币种 **/
    private String ccy;
    
    /**拆借方向Code - 产品类型**/
    private String finprodTypeCode;
    
    /**拆借方向 - 产品类型**/
    private String finprodType;
    
    /** 是否结清 **/
    private String isClean;
    
    private String feeType;
    
    
    public String getFeeType() {
    	return this.feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public BigDecimal getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdAbbr() {
        return prodAbbr;
    }

    public void setProdAbbr(String prodAbbr) {
        this.prodAbbr = prodAbbr;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetAbbr() {
        return assetAbbr;
    }

    public void setAssetAbbr(String assetAbbr) {
        this.assetAbbr = assetAbbr;
    }

    public String getOrderType() {
    	return this.orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTypeCode() {
        return orderTypeCode;
    }

    public void setOrderTypeCode(String orderTypeCode) {
        this.orderTypeCode = orderTypeCode;
    }

    public BigDecimal getTdFeeAmt() {
        return tdFeeAmt;
    }

    public void setTdFeeAmt(BigDecimal tdFeeAmt) {
        this.tdFeeAmt = tdFeeAmt;
    }

    public BigDecimal getSettleAmt() {
        return settleAmt;
    }

    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }

    public String getSettleStatus() {
         return this.settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getSettleStatusCode() {
        return settleStatusCode;
    }

    public void setSettleStatusCode(String settleStatusCode) {
        this.settleStatusCode = settleStatusCode;
    }

    public String getPs() {
        return this.ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getPsCode() {
        return psCode;
    }

    public void setPsCode(String psCode) {
        this.psCode = psCode;
    }

    public String getMyAcctNo() {
        return myAcctNo;
    }

    public void setMyAcctNo(String myAcctNo) {
        this.myAcctNo = myAcctNo;
    }

    public String getCounterAcctNo() {
        return counterAcctNo;
    }

    public void setCounterAcctNo(String counterAcctNo) {
        this.counterAcctNo = counterAcctNo;
    }

    public String getCcy() {
        return this.ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getCcyCode() {
        return ccyCode;
    }

    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getBusiTypeCode() {
        return busiTypeCode;
    }

    public void setBusiTypeCode(String busiTypeCode) {
        this.busiTypeCode = busiTypeCode;
    }

    public String getBusiType() {
        return this.busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }



	public String getMyAcctName() {
		return myAcctName;
	}


	public void setMyAcctName(String myAcctName) {
		this.myAcctName = myAcctName;
	}


	public String getCounterAcctName() {
		return counterAcctName;
	}


	public void setCounterAcctName(String counterAcctName) {
		this.counterAcctName = counterAcctName;
	}


	public String getFinprodTypeCode() {
		return finprodTypeCode;
	}


	public void setFinprodTypeCode(String finprodTypeCode) {
		this.finprodTypeCode = finprodTypeCode;
	}


	public String getFinprodType() {
		return this.finprodType;
	}


	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}

	public String getIsClean() {
		return isClean;
	}

	public void setIsClean(String isClean) {
		this.isClean = isClean;
	}
    
	

}
