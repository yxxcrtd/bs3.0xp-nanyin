/*
 * 文件名：EtrBlockTradePledgeRepoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.business.dto;

import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.background.component.common.pack.common.validation.annotation.Length;
import com.joyintech.background.component.order.exchange.common.constant.DictCodeList;
import com.joyintech.background.component.order.exchange.common.dto.EtrSinglePortfolioDto;


/**
 * 大宗债券质押式回购指令Dto
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月8日
 * @see EtrBlockTradePledgeRepoDto
 * @since 1.0
 */
public class EtrBlockTradePledgeRepoDto extends EtrSinglePortfolioDto {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 6175471935796320391L;

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

    public EtrBlockTradePledgeRepoDto() {
        super(DictCodeList.指令业务类型_大宗债券质押回购.getValue());
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
    @Length(max = 6, fieldName = "EtrBlockTradePledgeRepoDto.counterSeat")
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
}
