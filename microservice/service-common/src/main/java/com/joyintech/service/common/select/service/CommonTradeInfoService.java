/*
 * 文件名：BussBlendSecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年6月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.common.select.service;

import com.joyintech.service.common.select.dao.CommonTradeInfoMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 融入劵管理共通组件-微服务Service
 */
@Service
public class CommonTradeInfoService {

    @Autowired
    private CommonTradeInfoMapper commonTradeInfoMapper;

    public List<FinCashPlanDto> getFinCashPlanByProductId(String finprodId, String cashType, String finprodType,
                                                          String finprodType2) {
        if (DictCodeList.共通业务类型_付息计划调整.getValue().equals(finprodType2)) {
            Map<String, Object> params = new HashMap<>();
            params.put("finprodId", finprodId);
            params.put("cashType", cashType);
            params.put("finprodType", finprodType);
            params.put("finprodType2", finprodType2);
            return commonTradeInfoMapper.getFinCashPlanByProductIdOther(params);
        }
        return commonTradeInfoMapper.getFinCashPlanByProductId(finprodId, cashType, finprodType);
    }
}
