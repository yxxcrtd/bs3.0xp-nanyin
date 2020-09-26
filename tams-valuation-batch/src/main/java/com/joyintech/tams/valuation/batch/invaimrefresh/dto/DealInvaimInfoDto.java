/*
 * 文件名：DealInvaimIntoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年9月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.invaimrefresh.dto;

import java.util.Date;

/**
 * 交易关联出来的投资目的信息
 * 
 * @author yangjian
 * @version 1.0 2018年9月8日
 * @see DealInvaimInfoDto
 * @since 1.0
 */
public class DealInvaimInfoDto {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 交易编号
     */
    private String tradeId;
    
    /**
     * 交易日
     */
    private Date tradeDate;

    /**
     * 金融产品代码id
     */
    private String finprodId;

    /**
     * 金融产品简称
     */
    private String finprodAbbr;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 业务表
     */
    private String busiTable;
    
    /**
     * 方案代码
     */
    private String schemeId;
    
    /**
     * 资产细分类
     */
    private String secDetailType;

    /**
     * 原始交易的投资目的
     */
    private String orgDealInvaim;

    /**
     * 一般.资产.投资目的
     */
    private String assetInvaim;

    /**
     * 一般.细分类.投资目的
     */
    private String secDetailTypeInvaim;

    /**
     * 特殊.资产.投资目的
     */
    private String specialInvaim;

    /**
     * 最终需要修改成的投资目的
     */
    private String changeInvaim;

    /**
     * 需要修改成的账务明细代码
     */
    private String changeBookDetailId;

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public String getFinprodType() {
		return finprodType;
	}

	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}

	public String getBusiTable() {
		return busiTable;
	}

	public void setBusiTable(String busiTable) {
		this.busiTable = busiTable;
	}

	public String getOrgDealInvaim() {
		return orgDealInvaim;
	}

	public void setOrgDealInvaim(String orgDealInvaim) {
		this.orgDealInvaim = orgDealInvaim;
	}

	public String getAssetInvaim() {
		return assetInvaim;
	}

	public void setAssetInvaim(String assetInvaim) {
		this.assetInvaim = assetInvaim;
	}

	public String getSecDetailTypeInvaim() {
		return secDetailTypeInvaim;
	}

	public void setSecDetailTypeInvaim(String secDetailTypeInvaim) {
		this.secDetailTypeInvaim = secDetailTypeInvaim;
	}

	public String getSpecialInvaim() {
		return specialInvaim;
	}

	public void setSpecialInvaim(String specialInvaim) {
		this.specialInvaim = specialInvaim;
	}

	public String getChangeInvaim() {
		return changeInvaim;
	}

	public void setChangeInvaim(String changeInvaim) {
		this.changeInvaim = changeInvaim;
	}

	public String getChangeBookDetailId() {
		return changeBookDetailId;
	}

	public void setChangeBookDetailId(String changeBookDetailId) {
		this.changeBookDetailId = changeBookDetailId;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getSecDetailType() {
		return secDetailType;
	}

	public void setSecDetailType(String secDetailType) {
		this.secDetailType = secDetailType;
	}
}
