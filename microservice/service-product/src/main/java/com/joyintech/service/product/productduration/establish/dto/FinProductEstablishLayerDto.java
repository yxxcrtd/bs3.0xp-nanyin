/*
 * 文件名：FinProductEstablishLayerDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年12月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productduration.establish.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 分层金额明细
 * 
 * @author zhangliang
 * @version 1.0 2018年12月22日
 * @see FinProductEstablishLayerDto
 * @since 1.0
 */
public class FinProductEstablishLayerDto extends TrdFinProductDealTotal{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -471625764014526630L;
    
    /**
     * 产品款型
     */
    private String prodType;

    /**
     * 本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamt;

    
    /**
     * 获取 prinamt
     * @return prinamt.
     */
    public BigDecimal getPrinamt() {
        return prinamt;
    }

    /**
     * 设置 prinamt
     * @param prinamt prinamt
     */
    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
    }


	public String getProdType() {
		return prodType;
	}


	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
    
}
