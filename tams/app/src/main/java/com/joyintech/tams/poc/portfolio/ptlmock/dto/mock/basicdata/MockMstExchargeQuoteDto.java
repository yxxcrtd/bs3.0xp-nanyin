/*
 * 文件名：MockMstExchargeQuoteDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.basicdata;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.MstExchargeQuote;


/**
 * 汇率行情
 * @author etliu
 * @version 1.0 2020年4月14日
 * @see MockMstExchargeQuoteDto
 * @since 1.0
 */
public class MockMstExchargeQuoteDto extends MstExchargeQuote {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -6068251249135301669L;

    /**
     * 报价
     */
    private BigDecimal price;

    /**
     * 获取 price
     * @return price.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置 price
     * @param price price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
