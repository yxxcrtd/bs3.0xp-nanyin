/*
 * 文件名：EtrBlockTradeBondDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.business.dto;

import java.math.BigDecimal;

import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.background.component.common.pack.common.validation.annotation.Length;
import com.joyintech.background.component.common.pack.common.validation.annotation.NotEmpty;
import com.joyintech.background.component.order.exchange.common.constant.DictCodeList;
import com.joyintech.background.component.order.exchange.common.dto.EtrSinglePortfolioDto;


/**
 * 大宗债券指令Dto
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月8日
 * @see EtrBlockTradeBondDto
 * @since 1.0
 */
public class EtrBlockTradeBondDto extends EtrSinglePortfolioDto {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 6211777847245334946L;

    /**
     * 交易对手
     */
    private String counterId;

    /**
     * 对手席位
     */
    private String counterSeat;

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
     * 计算方式
     */
    private String calMode;

    public EtrBlockTradeBondDto() {
        super(DictCodeList.指令业务类型_大宗债券.getValue());
    }

    /**
     * 取得 交易对手
     * 
     * @return 交易对手
     */
    public String getCounterId() {
        return this.counterId;
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
     * 获取 对手席位
     * 
     * @return 对手席位.
     */
    @Length(max = 6, fieldName = "EtrBlockTradeBondDto.counterSeat")
    public String getCounterSeat() {
        return StringUtil.isEmpty(counterSeat) ? null : counterSeat;
    }

    /**
     * 设置 对手席位
     * 
     * @param counterSeat 对手席位
     */
    public void setCounterSeat(String counterSeat) {
        this.counterSeat = counterSeat;
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
     * @param agreeNo 约定号
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

    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "EtrBlockTradeBondDto.price")
    public BigDecimal getPrice() {
        return super.getPrice();
    }

    public BigDecimal getExerYield() {
        return exerYield;
    }

    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }
}
