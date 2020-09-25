/*
 * 文件名：Rsk002InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.event.rsk002.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.joyintech.tams.common.base.entity.RskPreOccupyInfo;

/**
 * RSK002接口-输入
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月29日
 * @see Rsk002InEntity
 * @since 1.0
 */
public class Rsk002InEntity extends RskPreOccupyInfo {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -6841057733067806548L;

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public Date getEventDate() {
        return super.getEventDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty
    @Size(max = 50)
    public String getEventType() {
        return super.getEventType();
    }

    private String portfolioCode;

    /**
     * {@inheritDoc}
     */
    @NotEmpty
    @Size(max = 32)
    public String getPortfolioCode() {
        return portfolioCode;
    }

    /**
     * {@inheritDoc}
     */
    public void setPortfolioCode(String portfolioCode) {
        this.portfolioCode = portfolioCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Size(max = 50)
    public String getAssetType() {
        return super.getAssetType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty
    @Size(max = 50)
    public String getPs() {
        return super.getPs();
    }
    
    /**
     * 市场代码
     */
    private String marketCode;
    
    /**
     * 交易场所
     */
    private String tradePlace;

	public String getMarketCode() {
		return marketCode;
	}

	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}

	public String getTradePlace() {
		return tradePlace;
	}

	public void setTradePlace(String tradePlace) {
		this.tradePlace = tradePlace;
	}
}
