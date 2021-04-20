/*
 * 文件名：BondQuetoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoguo
 * 修改时间：2020年5月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.uplink.interbank.business.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.background.component.common.pack.common.json.BigDecimalSerializer;

/**
 * 现券报价扩展DTO 供前台查询使用
 * 
 * @author caoguo
 * @version 1.0 2020年5月7日
 * @see BondQuoteExtDto
 * @since 1.0
 */
public class BondQuoteExtDto extends IbuQuoteBondDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2766491650834409687L;

    /**
     * 现券报价对手及金额信息
     */
    private IbuQuoteBondDetDto ibuQuoteBondDet;
    
    /**
     * 多机构明细
     */
    private List<IbuQuoteBondDetDto> ibuQuoteBondDetList;

    /**
     * 金融产品简称
     */
    private String finprodAbbr;

    /**
     * 金融产品全称
     */
    private String finprodName;

    /**
     * 本方交易员姓名
     */
    private String ouTraderName;

    /**
     * 交易成员简称(机构简称、交易机构名称、本方银行间机构名称)
     */
    private String partyAbbr;

    /**
     * 交易成员全称(机构全称)
     */
    private String partyName;

    /**
     * 交易机构ID，21位编号
     */
    private String partyCustomerId;

    /**
     * 交易成员英文简称
     */
    private String partyAbbrEn;

    /**
     * 交易成员英文全称
     */
    private String partyNameEn;

    /**
     * 资产一级分类
     */
    private String type6;

    /**
     * 资产二级分类
     */
    private String type7;
    
    /**
     * 有效状态
     */
    private String validStatus;
    
    /**
     * 交易份额，债券数量等
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bondShareAmt;

    private String cpPartyName;

    private String cpTraderName;

    private String execid;
    
    /**
     * 是否多组合
     */
    private String isDzh;

    /**
     * 获取 ibuQuoteBondDet
     * 
     * @return ibuQuoteBondDet.
     */
    public IbuQuoteBondDetDto getIbuQuoteBondDet() {
        return ibuQuoteBondDet;
    }

    /**
     * 设置 ibuQuoteBondDet
     * 
     * @param ibuQuoteBondDet ibuQuoteBondDet
     */
    public void setIbuQuoteBondDet(IbuQuoteBondDetDto ibuQuoteBondDet) {
        this.ibuQuoteBondDet = ibuQuoteBondDet;
    }

    /**
     * 获取 finprodAbbr
     * 
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 finprodAbbr
     * 
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 finprodName
     * 
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * 
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 ouTraderName
     * 
     * @return ouTraderName.
     */
    public String getOuTraderName() {
        return ouTraderName;
    }

    /**
     * 设置 ouTraderName
     * 
     * @param ouTraderName ouTraderName
     */
    public void setOuTraderName(String ouTraderName) {
        this.ouTraderName = ouTraderName;
    }

    /**
     * 获取 partyAbbr
     * 
     * @return partyAbbr.
     */
    public String getPartyAbbr() {
        return partyAbbr;
    }

    /**
     * 设置 partyAbbr
     * 
     * @param partyAbbr partyAbbr
     */
    public void setPartyAbbr(String partyAbbr) {
        this.partyAbbr = partyAbbr;
    }

    /**
     * 获取 partyName
     * 
     * @return partyName.
     */
    public String getPartyName() {
        return partyName;
    }

    /**
     * 设置 partyName
     * 
     * @param partyName partyName
     */
    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    /**
     * 获取 partyCustomerId
     * 
     * @return partyCustomerId.
     */
    public String getPartyCustomerId() {
        return partyCustomerId;
    }

    /**
     * 设置 partyCustomerId
     * 
     * @param partyCustomerId partyCustomerId
     */
    public void setPartyCustomerId(String partyCustomerId) {
        this.partyCustomerId = partyCustomerId;
    }

    /**
     * 获取 partyAbbrEn
     * 
     * @return partyAbbrEn.
     */
    public String getPartyAbbrEn() {
        return partyAbbrEn;
    }

    /**
     * 设置 partyAbbrEn
     * 
     * @param partyAbbrEn partyAbbrEn
     */
    public void setPartyAbbrEn(String partyAbbrEn) {
        this.partyAbbrEn = partyAbbrEn;
    }

    /**
     * 获取 partyNameEn
     * 
     * @return partyNameEn.
     */
    public String getPartyNameEn() {
        return partyNameEn;
    }

    /**
     * 设置 partyNameEn
     * 
     * @param partyNameEn partyNameEn
     */
    public void setPartyNameEn(String partyNameEn) {
        this.partyNameEn = partyNameEn;
    }

    /**
     * 获取 type6
     * 
     * @return type6.
     */
    public String getType6() {
        return type6;
    }

    /**
     * 设置 type6
     * 
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }

    /**
     * 获取 type7
     * 
     * @return type7.
     */
    public String getType7() {
        return type7;
    }

    /**
     * 设置 type7
     * 
     * @param type7 type7
     */
    public void setType7(String type7) {
        this.type7 = type7;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BondQuoteExtDto [ibuQuoteBondDet=" + ibuQuoteBondDet + ", finprodAbbr="
               + finprodAbbr + ", finprodName=" + finprodName + ", finprodMarketId="
               + super.getFinprodMarketId() + ", portfolioName=" + super.getPortfolioName() 
               + ", ouTraderName=" + ouTraderName + ", partyAbbr="
               + partyAbbr + ", partyName=" + partyName + ", partyCustomerId=" + partyCustomerId
               + ", partyAbbrEn=" + partyAbbrEn + ", partyNameEn=" + partyNameEn + ", type6="
               + type6 + ", type7=" + type7 + ", getFinprodMarketId()=" + getFinprodMarketId()
               + ", getQuoteId()=" + getQuoteId() + ", getQuoteType()=" + getQuoteType()
               + ", getQuoteRoleType()=" + getQuoteRoleType() + ", getVersion()=" + getVersion()
               + ", getExeType()=" + getExeType() + ", getpQuoteId()=" + getpQuoteId()
               + ", getCfetsQuoteId()=" + getCfetsQuoteId() + ", getFinprodId()=" + getFinprodId()
               + ", getFinprodType2()=" + getFinprodType2() + ", getBranch()=" + getBranch()
               + ", getPortfolioId()=" + getPortfolioId() + ", getEntrustId()=" + getEntrustId()
               + ", getQuoteDate()=" + getQuoteDate() + ", getSettleDate()=" + getSettleDate()
               + ", getSettleSpeed()=" + getSettleSpeed() + ", getDeliveryType()="
               + getDeliveryType() + ", getSettType()=" + getSettType() + ", getSettCcy()="
               + getSettCcy() + ", getPs()=" + getPs() + ", getShareAmt()=" + getShareAmt()
               + ", getFpriceAmt()=" + getFpriceAmt() + ", getTradeStatus()=" + getTradeStatus()
               + ", getSendStatus()=" + getSendStatus() + ", getEffTime()=" + getEffTime()
               + ", getOuPartyId()=" + getOuPartyId() + ", getOuTraderId()=" + getOuTraderId()
               + ", getbVersion()=" + getbVersion() + ", getRemark()=" + getRemark()
               + ", getStatus()=" + getStatus() + ", getCreateUser()=" + getCreateUser()
               + ", getCreateDept()=" + getCreateDept() + ", getCreateTime()=" + getCreateTime()
               + ", getUpdateUser()=" + getUpdateUser() + ", getUpdateTime()=" + getUpdateTime()
               + ", getWorkflowId()=" + getWorkflowId() + ", getUpdateFlg()=" + getUpdateFlg()
               + "]";
    }

	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	public BigDecimal getBondShareAmt() {
		return bondShareAmt;
	}

	public void setBondShareAmt(BigDecimal bondShareAmt) {
		this.bondShareAmt = bondShareAmt;
	}

	public List<IbuQuoteBondDetDto> getIbuQuoteBondDetList() {
		return ibuQuoteBondDetList;
	}

	public void setIbuQuoteBondDetList(List<IbuQuoteBondDetDto> ibuQuoteBondDetList) {
		this.ibuQuoteBondDetList = ibuQuoteBondDetList;
	}

    public String getCpPartyName() {
        return cpPartyName;
    }

    public void setCpPartyName(String cpPartyName) {
        this.cpPartyName = cpPartyName;
    }

    public String getCpTraderName() {
        return cpTraderName;
    }

    public void setCpTraderName(String cpTraderName) {
        this.cpTraderName = cpTraderName;
    }

    public String getIsDzh() {
		return isDzh;
	}

	public void setIsDzh(String isDzh) {
		this.isDzh = isDzh;
	}

    public String getExecid() {
        return execid;
    }

    public void setExecid(String execid) {
        this.execid = execid;
    }
}
