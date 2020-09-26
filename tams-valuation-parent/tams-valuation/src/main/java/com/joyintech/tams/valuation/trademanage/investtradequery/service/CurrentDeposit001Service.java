/*
 * 文件名：CurrentDeposit001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.investtradequery.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.CurrentDepositDto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author Administrator
 * @version 1.0 2018年8月3日
 * @see CurrentDeposit001Service
 * @since 1.0
 */
@Service
public class CurrentDeposit001Service extends CommonExportService<CurrentDepositDto> {

    /**
     * 双击查看
     * 
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    public CurrentDepositDto selectByTradeId(Map<String, Object> params) {
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/CurrentDeposit001/selectByTradeId", params,
            CurrentDepositDto.class);
    }

    /**
     * 对应投资主体收息信息列表
     * 
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    public List<CurrentDepositDto> selectByTradeId2(String tradeId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("tradeId", tradeId);
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/CurrentDeposit002/findPage2", map,
            CurrentDepositDto.class);
    }

    /**
     * 查看计提明细列表
     * 
     * @param tradeId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<CurrentDepositDto> selectByTradeId3(String tradeId)
        throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        map.put("tradeId", tradeId);
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/CurrentDeposit001/findPage", map,
            CurrentDepositDto.class);
    }

    /**
     * 查看计提明细列表
     * 
     * @param tradeId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<CurrentDepositDto> selectByTradeId4(String tradeId)
        throws Exception {
        // return currentDeposit001Mapper.selectByTradeId3(tradeId);
        Map<String, Object> map = Maps.newHashMap();
        map.put("tradeId", tradeId);
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/CurrentDeposit002/findPage", map,
            CurrentDepositDto.class);
    }
}
