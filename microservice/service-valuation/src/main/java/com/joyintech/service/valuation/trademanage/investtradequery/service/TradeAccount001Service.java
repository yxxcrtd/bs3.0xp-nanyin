/*
 * 文件名：TradeAccount001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年8月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.investtradequery.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.service.valuation.trademanage.investtradequery.dao.TradeAccount001Mapper;
import com.joyintech.service.valuation.trademanage.investtradequery.dto.TradeAccountDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author Administrator
 * @version 1.0 2018年8月13日
 * @see TradeAccount001Service
 * @since 1.0
 */
@Service(value="serviceTradeAccount001Service")
public class TradeAccount001Service extends CommonExportService<TradeAccountDto> {

    @Autowired
    private TradeAccount001Mapper tradeAccount001Mapper;

    /**
     * 交易账户收划款双击查看
     * 
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    public TradeAccountDto queryTradeAccount(String tradeId,String prodId) {
        Map<String, Object> map = new HashMap<>();
        map.put("tradeId", tradeId);
        map.put("prodId", prodId);
        map.put("busiType", DictCodeList.业务类型_指令交易.getValue());
        return tradeAccount001Mapper.queryTradeAccount(map);

    }
}
