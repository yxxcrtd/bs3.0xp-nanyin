/*
 * 文件名：TrdRepoTradeCSTPDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年4月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.trdrepotrade.dto;

import com.joyintech.tams.common.base.entity.InterfOpicsTradRepo;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;

import java.util.List;


/**
 * 回购CSTP交易
 * 
 * @author zhangliang
 * @version 1.0 2019年4月9日
 * @see TrdRepoTradeCSTPDto
 * @since 1.0
 */
public class TrdRepoTradeCSTPDto extends InterfOpicsTradRepo {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2179078984548955994L;

    /**
     * 组合代码
     */
    private String portfolioId;

    private String entrustId;
    
    /**
     * 是否有报价信息（01：是，02：否）
     */
    private String isOfferFlag;

    /**
     * {@linkplain}
     */
    private transient List<PledgeBondInfoDto> pledgeBondInfoDtoList;

    private Integer cashHoldingDays;//实际占款天数

    public Integer getCashHoldingDays() {
        return cashHoldingDays;
    }

    public void setCashHoldingDays(Integer cashHoldingDays) {
        this.cashHoldingDays = cashHoldingDays;
    }

    /**
     * 获取 pledgeBondInfoDtoList
     * @return pledgeBondInfoDtoList.
     */
    public List<PledgeBondInfoDto> getPledgeBondInfoDtoList() {
        return pledgeBondInfoDtoList;
    }

    
    /**
     * 设置 pledgeBondInfoDtoList
     * @param pledgeBondInfoDtoList pledgeBondInfoDtoList
     */
    public void setPledgeBondInfoDtoList(List<PledgeBondInfoDto> pledgeBondInfoDtoList) {
        this.pledgeBondInfoDtoList = pledgeBondInfoDtoList;
    }

    
    /**
     * 获取 portfolioId
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    
    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

	public String getIsOfferFlag() {
		return isOfferFlag;
	}

	public void setIsOfferFlag(String isOfferFlag) {
		this.isOfferFlag = isOfferFlag;
	}
    
    
}
