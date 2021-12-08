/*
 * 文件名：EtribRepoInfo003Dto.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2021年01月20日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.cfets.etrib.repo.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="组合树查询",description="组合树查询")
public class CfetsTreePortfolioRequest{

    /**
     * 质押库id
     */
	@ApiModelProperty(name="pledgeStockId",value="质押库id")
    private String pledgeStockId;

    /**
     * 组合代码list
     */
	@ApiModelProperty(name="portfolioIdList",value="组合代码list")
    private List<String> portfolioIdList;

    /**
     * 收益类型
     */
	@ApiModelProperty(name="profitType",value="收益类型")
    private String profitType;

    /**
     * 开发类型
     */
	@ApiModelProperty(name="openType",value="开发类型")
    private String openType;

    /**
     * 募集方式
     */
	@ApiModelProperty(name="raiseType",value="募集方式")
    private String raiseType;
    
    /**
     * 投资性质
     */
	@ApiModelProperty(name="investNature",value="投资性质")
    private String investNature;
    
    /**
     * 用户
     */
	@ApiModelProperty(name="username",value="用户")
    private String username;

	public String getPledgeStockId() {
		return pledgeStockId;
	}

	public void setPledgeStockId(String pledgeStockId) {
		this.pledgeStockId = pledgeStockId;
	}

	public List<String> getPortfolioIdList() {
		return portfolioIdList;
	}

	public void setPortfolioIdList(List<String> portfolioIdList) {
		this.portfolioIdList = portfolioIdList;
	}

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
    
}
