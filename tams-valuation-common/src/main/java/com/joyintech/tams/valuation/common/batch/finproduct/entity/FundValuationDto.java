/*
 * 文件名：FundValuationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.finproduct.entity;

/**
 * 基金估值行情
 * 
 * @author weipeng
 * @version 1.0 2018年6月1日
 * @see FundValuationDto
 * @since 1.0
 */
public class FundValuationDto extends MarketInfoDto{
    
    /**
     * 基金代码
     */
    private String fundCode;
    /**
     * 估值来源
     */
    private String valueSource;

    /**
     * 取得 基金代码
     * 
     * @return 基金代码
     */
    public String getFundCode() {
        return fundCode;
    }

    /**
     * 设置 基金代码
     * 
     * @param fundCode
     *            基金代码
     */
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    /**
     * 取得 估值来源
     * 
     * @return 估值来源
     */
    public String getValueSource() {
        return valueSource;
    }

    /**
     * 设置 估值来源
     * 
     * @param valueSource
     *            估值来源
     */
    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
    }
    
    

}
