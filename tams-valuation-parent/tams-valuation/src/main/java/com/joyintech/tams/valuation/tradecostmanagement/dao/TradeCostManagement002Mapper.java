package com.joyintech.tams.valuation.tradecostmanagement.dao;

import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.valuation.tradecostmanagement.dto.TradeCostFinProductDto;

/**
 * 交易费用管理 新建
 * @Author wangXiaoYong
 * @create 2019/11/19
 */
public interface TradeCostManagement002Mapper {

    // 根据交易编号更新指令表
    void updateStlOrdersByTradeId(StlOrders stlOrders);

    /**
     * 根据账套代码 提取标的类金融产品
     *
     * @param bookSetId 账套代码
     * @return 标的类金融产品
     */
    TradeCostFinProductDto getTradeCostFinProductDto(String bookSetId);
}
