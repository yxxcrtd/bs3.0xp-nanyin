/*
 * 文件名：EtrBondFxdIncmPlatSHDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.order.exchange.business.dto;

import java.math.BigDecimal;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.background.component.common.pack.common.validation.annotation.Length;
import com.joyintech.background.component.order.exchange.common.constant.DictCodeList;
import com.joyintech.background.component.order.exchange.common.dto.EtrSinglePortfolioDto;

/**
 * 上海固收平台现券指令子组件-Dto
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月14日
 * @see EtrBondFxdIncmPlatSHDto
 * @since 1.0
 */
public class EtrBondFxdIncmPlatSHDto extends EtrSinglePortfolioDto {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1157804341340811248L;

    /**
     * 交易对手
     */
    private String counterId;

    /**
     * 对手方交易员
     */
    private String counterTrader;

    /**
     * 对手方交易商
     */
    private String counterOrg;

    /**
     * 约定号
     */
    private String counterConNo;

    /**
     * 全价价格
     */
    private BigDecimal fullPrice;

    /**
     * 到期收益率
     */
    private BigDecimal matureYield;

    /**
     * 行权收益率
     */
    private BigDecimal exerYield;

    /**
     * 本方交易员
     */
    private String trader;

    /**
     * 计算方式
     */
    private String calMode;
    
    /**
     * 清算速度
     */
    private Long settleSpreed;
    

    public EtrBondFxdIncmPlatSHDto() {
        super(DictCodeList.指令业务类型_上海固收平台现券.getValue());
    }

    /**
     * 获取 交易对手
     * 
     * @return 交易对手.
     */
    public String getCounterId() {
        return counterId;
    }

    /**
     * 设置 交易对手
     * 
     * @param counterId 交易对手
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    /**
     * 获取 对方交易员
     * 
     * @return 对方交易员.
     */
    @Length(length = 6, min = 6, max = 6, fieldName = "EtrBondFxdIncmPlatSHDto.counterTrader")
    public String getCounterTrader() {
        return StringUtil.isEmpty(counterTrader) ? null : counterTrader;
    }

    /**
     * 设置 对方交易员
     * 
     * @param counterTrader 对方交易员
     */
    public void setCounterTrader(String counterTrader) {
        this.counterTrader = counterTrader;
    }

    /**
     * 获取 对方交易商
     * 
     * @return 对方交易商.
     */
    @Length(length = 3, min = 3, max = 3, fieldName = "EtrBondFxdIncmPlatSHDto.counterOrg")
    public String getCounterOrg() {
        return StringUtil.isEmpty(counterOrg) ? null : counterOrg;
    }

    /**
     * 设置 对方交易商
     * 
     * @param counterOrg 对方交易商
     */
    public void setCounterOrg(String counterOrg) {
        this.counterOrg = counterOrg;
    }

    /**
     * 获取 约定号
     * 
     * @return 约定号.
     */
    public String getCounterConNo() {
        return counterConNo;
    }

    /**
     * 设置 约定号
     * 
     * @param counterConNo 约定号
     */
    public void setCounterConNo(String counterConNo) {
        this.counterConNo = counterConNo;
    }

    /**
     * 获取 全价价格
     * 
     * @return 全价价格.
     */
    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    /**
     * 设置 全价价格
     * 
     * @param fullPrice 全价价格
     */
    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    /**
     * 获取 到期收益率
     * 
     * @return 到期收益率.
     */
    public BigDecimal getMatureYield() {
        return matureYield;
    }

    /**
     * 设置 到期收益率
     * 
     * @param matureYield 到期收益率
     */
    public void setMatureYield(BigDecimal matureYield) {
        this.matureYield = matureYield;
    }

    /**
     * 获取 本方交易员
     * 
     * @return 本方交易员.
     */
    @Length(length = 6, min = 6, max = 6, fieldName = "EtrBondFxdIncmPlatSHDto.trader")
    public String getTrader() {
        return StringUtil.isEmpty(trader) ? null : trader;
    }

    /**
     * 设置 本方交易员
     * 
     * @param trader 本方交易员
     */
    public void setTrader(String trader) {
        this.trader = trader;
    }

    /**
     * 获取 计算方式
     * 
     * @return 计算方式.
     */
    public String getCalMode() {
        return calMode;
    }

    /**
     * 设置 计算方式
     * 
     * @param calMode 计算方式
     */
    public void setCalMode(String calMode) {
        this.calMode = calMode;
    }

    public BigDecimal getExerYield() {
        return exerYield;
    }

    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

	public Long getSettleSpreed() {
		return settleSpreed;
	}

	public void setSettleSpreed(Long settleSpreed) {
		this.settleSpreed = settleSpreed;
	}
}
