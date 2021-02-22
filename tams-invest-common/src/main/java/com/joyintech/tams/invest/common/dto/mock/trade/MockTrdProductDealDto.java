/*
 * 文件名：MockTrdProductDealDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.dto.mock.trade;

import com.joyintech.tams.common.base.entity.TrdProductDeal;

/**
 * 金融产品交易扩展
 * @author etliu
 * @version 1.0 2020年4月14日
 * @see MockTrdProductDealDto
 * @since 1.0
 */
public class MockTrdProductDealDto extends TrdProductDeal {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1968201874994610143L;

    /**
     * 是否已处理现金
     */
    private String cashProcessedOrNot;

    /**
     * 获取 cashProcessedOrNot
     * @return cashProcessedOrNot.
     */
    public String getCashProcessedOrNot() {
        return cashProcessedOrNot;
    }

    /**
     * 设置 cashProcessedOrNot
     * @param cashProcessedOrNot cashProcessedOrNot
     */
    public void setCashProcessedOrNot(String cashProcessedOrNot) {
        this.cashProcessedOrNot = cashProcessedOrNot;
    }
    
}
