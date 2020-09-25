/*
 * 文件名：MockPtlFeePositionDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.PtlFeePosition;

/**
 * 组合费用头寸扩展
 * 组合费用头寸查询 返回结果
 * @author etliu
 * @version 1.0 2020年4月13日
 * @see MockPtlFeePositionDto
 * @since 1.0
 */
public class MockPtlFeePositionDto extends PtlFeePosition {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -9188249974565120518L;

    /**
     * 资产价值
     */
    private BigDecimal assetValue;
    
    /**
     * 获取 assetValue
     * 
     * @return assetValue.
     */
    public BigDecimal getAssetValue() {
        return assetValue;
    }

    /**
     * 设置 assetValue
     * 
     * @param assetValue assetValue
     */
    public void setAssetValue(BigDecimal assetValue) {
        this.assetValue = assetValue;
    }
}
