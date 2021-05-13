/*
 * 文件名：TaxScheme007.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.dto.ComFxValuztionInfo002Dto;
import com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.service.ComFxValuztionInfo002Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 外汇估值信息
 *
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see ComFxValuztionInfo002Controller
 * @since 1.0
 */
@RestController(value = "serviceComFxValuztionInfo002Controller")
@RequestMapping("/ComFxValuztionInfo002")
public class ComFxValuztionInfo002Controller extends BaseController {
    
    @Autowired
    private ComFxValuztionInfo002Service comFxValuztionInfo002Service;
    
    
    /**
     * 交易编号获取
     *
     * @return 返回的数据
     */
    @PostMapping("/getFinprodId2")
	public List<SuggestDto> getFinprodId2(@RequestBody Map<String, Object> param) throws Exception {

		return comFxValuztionInfo002Service.getFinprodId2(param);
	}
    
    /**
     * 查询 外汇估值信息
     *
     * @param configId
     * @return
     */
    @PostMapping("/findByPrimaryKey")
    public ComFxValuztionInfo002Dto findByPrimaryKey(@RequestBody Map<String, Object> map) {
        return comFxValuztionInfo002Service.findByPrimaryKey(map);
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
    public void doComplete(@RequestBody ComFxValuztionInfo002Dto dto, @PathVariable("action") String action) {
        comFxValuztionInfo002Service.doComplete(dto, OperationType.valueOf(action));
    }
}
