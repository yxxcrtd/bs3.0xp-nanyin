/*
 * 文件名：FeePlan.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年7月8日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.tams.settle.common.dto;

import com.joyintech.tams.common.base.entity.TrdFeeConfig;
import com.joyintech.tams.common.base.entity.TrdFeeConfigDetail;

import java.util.List;

/**
 * 费用方案
 * @description 
 * @author 王波
 * @version 1.0 2020年7月8日
 * @see FeePlan
 * @since 1.0
 */
public class FeePlan {

    /**
     * 交易费用配置
     */
    private TrdFeeConfig trdFeeConfig;

    /**
     * 交易费用明细
     */
    private List<TrdFeeConfigDetail> trdFeeConfigDetails;

    /**
     * 获取 - 交易费用配置
     * @return
     */
    public TrdFeeConfig getTrdFeeConfig() {
        return trdFeeConfig;
    }

    /**
     * 设置 - 交易费用配置
     * @param trdFeeConfig
     */
    public void setTrdFeeConfig(TrdFeeConfig trdFeeConfig) {
        this.trdFeeConfig = trdFeeConfig;
    }

    /**
     * 获取 - 交易费用明细
     * @return
     */
    public List<TrdFeeConfigDetail> getTrdFeeConfigDetails() {
        return trdFeeConfigDetails;
    }

    /**
     * 设置 - 交易费用明细
     * @return
     */
    public void setTrdFeeConfigDetails(List<TrdFeeConfigDetail> trdFeeConfigDetails) {
        this.trdFeeConfigDetails = trdFeeConfigDetails;
    }
}
