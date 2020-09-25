/*
 * 文件名：FlowForecastReport001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2019年08月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.flowforecastreport.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.flowforecastreport.dto.FlowCommonDto;

/**
 * 产品费用支付管理
 * 
 * @author weipeng
 * @version 1.0 2019年08月16日
 * @see FlowForecastReport001Service
 * @since 1.0
 */
@Service
public class FlowForecastReport001Service extends CommonExportService<FlowCommonDto> {

    /**
     * 查询流动性备付情况信息
     * 
     * @param param 查询条件
     * @return 流动性备付情况信息
     * @see
     * @since 1.0
     */
    public Page<FlowCommonDto> selectReserve(Map<String, Object> param) {
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FlowForecastReport/selectReserve", param,
            FlowCommonDto.class);
    }

    /**
     * 查询流动性预测信息
     * 
     * @param param 查询条件
     * @return 流动性预测信息
     * @see
     * @since 1.0
     */
    public Page<FlowCommonDto> selectForcast(Map<String, Object> param) {
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FlowForecastReport/selectForcast", param,
            FlowCommonDto.class);
    }

}
