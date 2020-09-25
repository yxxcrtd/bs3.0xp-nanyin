/*
 * 文件名：SecCpriceInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：malinlin
 * 修改时间：2019年11月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.bond.dto;

import java.math.BigDecimal;

/**
 * 债券净价查询dto
 * @author malinlin
 * @version 1.0 2019年11月26日
 * @see SecCpriceInfoDto
 * @since 1.0
 */
public class SecCpriceInfoDto {
    
    /**
     * 产品收益类型
     */
    private String profitType;
    
    /**
     * 债券资产代码
     */
    private String finprodId;
    
    /**
     * 市值净价
     */
    private BigDecimal netprice;
    
    /**
     * 摊销净价
     */
    private BigDecimal dscprice;

    
    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    
    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    /**
     * 获取 netprice
     * @return netprice.
     */
    public BigDecimal getNetprice() {
        return netprice;
    }

    
    /**
     * 设置 netprice
     * @param netprice netprice
     */
    public void setNetprice(BigDecimal netprice) {
        this.netprice = netprice;
    }

    
    /**
     * 获取 dscprice
     * @return dscprice.
     */
    public BigDecimal getDscprice() {
        return dscprice;
    }

    
    /**
     * 设置 dscprice
     * @param dscprice dscprice
     */
    public void setDscprice(BigDecimal dscprice) {
        this.dscprice = dscprice;
    }
    
}
