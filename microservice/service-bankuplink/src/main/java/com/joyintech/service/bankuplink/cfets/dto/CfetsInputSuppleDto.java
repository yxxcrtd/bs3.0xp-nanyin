/*
 * 文件名：CfetsInputSuppleDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年12月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.cfets.dto;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductType;

/**
 * 业务数据以外，补充信息dto
 * 
 * @author etliu
 * @version 1.0 2020年12月21日
 * @see CfetsInputSuppleDto
 * @since 1.0
 */
public class CfetsInputSuppleDto {

    // @ApiModelProperty(required=true,name="securityID",value="48域-债券代码",example="010004")
    private String securityID;

    // @ApiModelProperty(required=false,name="symbol",value="55域-债券名称",example="")
    private String symbol;

    /**
     * 标的信息
     */
    private FinProduct finProduct;

    /**
     * 标的分类
     */
    private FinProductType finProductType;

    /**
     * 获取 securityID
     * 
     * @return securityID.
     */
    public String getSecurityID() {
        return securityID;
    }

    /**
     * 设置 securityID
     * 
     * @param securityID securityID
     */
    public void setSecurityID(String securityID) {
        this.securityID = securityID;
    }

    /**
     * 获取 symbol
     * 
     * @return symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * 设置 symbol
     * 
     * @param symbol symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * 获取 finProduct
     * 
     * @return finProduct.
     */
    public FinProduct getFinProduct() {
        return finProduct;
    }

    /**
     * 设置 finProduct
     * 
     * @param finProduct finProduct
     */
    public void setFinProduct(FinProduct finProduct) {
        this.finProduct = finProduct;
    }

    /**
     * 获取 finProductType
     * 
     * @return finProductType.
     */
    public FinProductType getFinProductType() {
        return finProductType;
    }

    /**
     * 设置 finProductType
     * 
     * @param finProductType finProductType
     */
    public void setFinProductType(FinProductType finProductType) {
        this.finProductType = finProductType;
    }

}
