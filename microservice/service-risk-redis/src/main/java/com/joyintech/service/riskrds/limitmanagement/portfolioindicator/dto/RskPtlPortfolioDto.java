/*
 * 文件名：RskPtlPortfolioDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年03月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto;

import com.joyintech.tams.common.base.entity.PtlPortfolio;


/**
 * 
 * 组合成分dto
 * @author 裴家俊
 * @version 1.0 2019年03月18日
 * @see RskPtlPortfolioDto
 * @since 1.0
 */
public class RskPtlPortfolioDto  extends  PtlPortfolio  {

	private static final long serialVersionUID = 1L;
	
    
    /**
     * 开放类型，开放式、封闭式
     */
    private String openType;

    /**
     * 募集方式，公募、私募
     */
    private String raiseType;

    /**
     * 投资性质，固定收益类、权益类、商品及金融衍生品类、混合类
     */
    private String investNature;
	/*
	 * 组合包代码
	 */
	private String ptlBagId;
	
	public String getPtlBagId() {
		return ptlBagId;
	}
	public void setPtlBagId(String ptlBagId) {
		this.ptlBagId = ptlBagId;
	}
	
	public String getOpenType() {
		return openType;
	}
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	public String getRaiseType() {
		return raiseType;
	}
	public void setRaiseType(String raiseType) {
		this.raiseType = raiseType;
	}
	public String getInvestNature() {
		return investNature;
	}
	public void setInvestNature(String investNature) {
		this.investNature = investNature;
	}
}
