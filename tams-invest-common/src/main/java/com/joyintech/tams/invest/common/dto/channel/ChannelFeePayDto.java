/*
 * 文件名：ChannelAssetQueryDto.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：yangjm 修改时间：2019年3月13日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.channel;

import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;

import java.math.BigDecimal;
import java.util.List;

/**
 * 通道资产查询 〈功能详细描述〉
 * 
 * @author yangjm
 * @version 1.0 2019年3月13日
 * @see ChannelFeePayDto
 * @since 1.0
 */
public class ChannelFeePayDto extends TrdProductDeal {

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 费用名称
     */
    private String feeName;

    /**
     * 通道名称
     */
    private String channelName;

    /**
     * 理论费用
     */
    private BigDecimal theoryInt;

    /**
     * 通道代码
     */
    private String finprodMarketId;

    /**
     * 划款指令
     */
    private List<StlPayOrders> orders;
    /**
     * 有效状态
     */
    private String effectStatus;

    private List<ChannelFeeInfoDto> feeInfoList;

    public List<ChannelFeeInfoDto> getFeeInfoList() {
        return feeInfoList;
    }

    public void setFeeInfoList(List<ChannelFeeInfoDto> feeInfoList) {
        this.feeInfoList = feeInfoList;
    }
    /**
     * 获取 orders
     *
     * @return orders
     */
    public List<StlPayOrders> getOrders() {
        return orders;
    }

    /**
     * 设置 orders
     *
     * @param orders orders
     */
    public void setOrders(List<StlPayOrders> orders) {
        this.orders = orders;
    }

    /**
     * 获取 finprodMarketId
     *
     * @return finprodMarketId
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     *
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 theoryInt
     *
     * @return theoryInt
     */
    public BigDecimal getTheoryInt() {
        return theoryInt;
    }

    /**
     * 设置 theoryInt
     *
     * @param theoryInt theoryInt
     */
    public void setTheoryInt(BigDecimal theoryInt) {
        this.theoryInt = theoryInt;
    }

    /**
     * 撤销理由
     */
    public String revokeReason;

    /**
     * 获取 revokeReason
     *
     * @return revokeReason
     */
    public String getRevokeReason() {
        return revokeReason;
    }

    /**
     * 设置 revokeReason
     *
     * @param revokeReason revokeReason
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    /**
     * 获取 feeName
     *
     * @return feeName
     */
    public String getFeeName() {
        return feeName;
    }

    /**
     * 设置 feeName
     *
     * @param feeName feeName
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    /**
     * 获取 portfolioId
     *
     * @return portfolioId
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 channelName
     *
     * @return channelName
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 设置 channelName
     *
     * @param channelName channelName
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

	public String getEffectStatus() {
		return effectStatus;
	}

	public void setEffectStatus(String effectStatus) {
		this.effectStatus = effectStatus;
	}

}
