/*
 * 文件名：BondTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.investtradequery.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.BondTradeDto;

/**
 * 债券交易查询
 * 
 * @author zhangwei
 * @version 1.0 2018年7月27日
 * @see BondTradeQuery001Service
 * @since 1.0
 */
@Service
public class BondTradeQuery001Service extends BaseService<BondTradeDto> {

    public BondTradeDto findByTradeId(Map<String, Object> params) {
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/BondTradeQuery001/findByTradeId", params, BondTradeDto.class);
    }

}
