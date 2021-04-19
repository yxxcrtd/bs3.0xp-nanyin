/*
 * 文件名：TaxScheme007.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.marketdata.rateswapvaluationinfo.controller;

import com.joyintech.service.basicdata.marketdata.rateswapvaluationinfo.dto.ComRateSwapValuationInfo002Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.basicdata.marketdata.rateswapvaluationinfo.service.ComRateSwapValuationInfo002Service;
import com.joyintech.workflow.engine.constant.OperationType;

import java.util.Map;

/**
 *
 *
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see ComRateSwapValuationInfo002Controller
 * @since 1.0
 */
@RestController(value = "serviceComRateSwapValuationInfo002Controller")
@RequestMapping("/ComRateSwapValuationInfo002")
public class ComRateSwapValuationInfo002Controller extends BaseController {
    
    @Autowired
    private ComRateSwapValuationInfo002Service taxSchBokCfgManage002Service;

    /**
     * 查询 资产应税信息
     *
     * @param configId
     * @return
     */
    @PostMapping("/findByPrimaryKey")
    public ComRateSwapValuationInfo002Dto findByPrimaryKey(@RequestBody Map<String, Object> map) {
        return taxSchBokCfgManage002Service.findByPrimaryKey(map);
    }

    /**
     * 流程完成后的操作
     *
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping(value = "doComplete/{action}")
    public void doComplete(@RequestBody ComRateSwapValuationInfo002Dto dto, @PathVariable("action") String action) {
        taxSchBokCfgManage002Service.doComplete(dto, OperationType.valueOf(action));
    }
}
