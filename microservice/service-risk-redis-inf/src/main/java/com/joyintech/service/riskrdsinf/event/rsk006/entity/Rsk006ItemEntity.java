/*
 * 文件名：Rsk006ItemEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.event.rsk006.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.RskDimEntrustInfo;

/**
 * 模糊委托列表项
 * 
 * @author zhangrui1
 * @version 1.0 2019年10月10日
 * @see Rsk006ItemEntity
 * @since 1.0
 */
public class Rsk006ItemEntity extends RskDimEntrustInfo {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 3857394958502620501L;

    /**
     * 交易场所
     */
    private String tradePlace;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amountOld;

    /**
     * 数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeNumOld;

	public String getTradePlace() {
		return tradePlace;
	}

	public void setTradePlace(String tradePlace) {
		this.tradePlace = tradePlace;
	}

	public BigDecimal getAmountOld() {
		return amountOld;
	}

	public void setAmountOld(BigDecimal amountOld) {
		this.amountOld = amountOld;
	}

	public BigDecimal getTradeNumOld() {
		return tradeNumOld;
	}

	public void setTradeNumOld(BigDecimal tradeNumOld) {
		this.tradeNumOld = tradeNumOld;
	}
}
