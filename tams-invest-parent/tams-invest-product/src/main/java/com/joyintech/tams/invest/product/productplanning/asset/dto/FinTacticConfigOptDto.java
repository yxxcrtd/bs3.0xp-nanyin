/*
 * 文件名：FinTacticConfigOptDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouyongjun
 * 修改时间：2019年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.product.productplanning.asset.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 资产配置
 * 
 * @author zhouyongjun
 * @version 1.0 2019年12月26日
 * @see FinTacticConfigOptDto
 * @since 1.0
 */
public class FinTacticConfigOptDto extends BaseEntity implements ProcessEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4723725569410781871L;

    /**
     * 资产大类
     */
	private String assetType;
	@JsonSerialize(using=BigDecimalSerializer.class)
    /**
     * 资产比例
     */
	private BigDecimal corRatio;
    /**
     * 资产大类
     */
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
    /**
     * 资产比例
     */
	public BigDecimal getCorRatio() {
		return corRatio;
	}
	public void setCorRatio(BigDecimal corRatio) {
		this.corRatio = corRatio;
	}
	
	
	
}
