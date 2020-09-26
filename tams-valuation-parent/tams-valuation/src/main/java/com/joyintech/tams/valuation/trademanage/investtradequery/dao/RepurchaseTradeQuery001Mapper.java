/*
 * 文件名：BondTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.trademanage.investtradequery.dao;


import java.util.Map;

import com.joyintech.tams.valuation.trademanage.investtradequery.dto.RepurchaseTradeDto;

/**
 * 回购交易查询
 * @author zhangwei
 * @version 1.0 2018年7月27日
 * @see RepurchaseQuery001Mapper2
 * @since 1.0
 */
public interface RepurchaseTradeQuery001Mapper {
    
    RepurchaseTradeDto selectByTradeId(Map<String, Object> params);

}
