/*
 * 文件名：OccupyRelParam.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.occupy.entity;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 预占实占存储结构
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月26日
 * @see OccupyRelParam
 * @since 1.0
 */
public class OccupyRelParam implements IOccupyRelParam {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 2183256284258443076L;

    /**
     * 预占/实占编号
     */
    private String occupyCode;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOccupyCode() {
        return occupyCode;
    }

    /**
     * 预占/实占类型
     */
    private int occupyType;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOccupyType() {
        return occupyType;
    }

    /**
     * 设置 预占/实占编号
     * 
     * @param occupyCode 预占/实占编号
     */
    public void setOccupyCode(String occupyCode) {
        this.occupyCode = occupyCode;
    }

    /**
     * 设置 预占/实占类型
     * 
     * @param occupyType 预占/实占类型
     */
    public void setOccupyType(int occupyType) {
        this.occupyType = occupyType;
    }

    /**
     * 预占/实占参数信息列表
     */
    private Map<String, IOcpRelParamItem> items;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, IOcpRelParamItem> getItems() {
        return items;
    }

    /**
     * 设置 预占/实占参数信息列表
     * 
     * @param items 预占/实占参数信息列表
     */
    public void setItems(Map<String, IOcpRelParamItem> items) {
        this.items = items;
    }

    /**
     * 净值
     */
    private BigDecimal netPrice;

    /**
     * 设置 净值
     * 
     * @param netPrice 净值
     */
	public void setNetPrice(BigDecimal netPrice) {
		this.netPrice = netPrice;
	}

	@Override
	public BigDecimal getNetPrice() {
		return netPrice;
	}
}
