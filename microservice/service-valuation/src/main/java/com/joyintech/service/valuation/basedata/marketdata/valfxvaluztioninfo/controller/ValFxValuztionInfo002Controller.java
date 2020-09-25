/*
 * 文件名：TaxScheme007.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dto.ValFxValuztionInfo002Dto;
import com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.service.ValFxValuztionInfo002Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 外汇估值信息
 *
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see ValFxValuztionInfo002Controller
 * @since 1.0
 */
@RestController(value = "serviceValFxValuztionInfo002Controller")
@RequestMapping("/ValFxValuztionInfo002")
public class ValFxValuztionInfo002Controller extends BaseController {
    
    @Autowired
    private ValFxValuztionInfo002Service valFxValuztionInfo002Service;
    
    
    /**
     * 交易编号获取
     *
     * @return 返回的数据
     */
    @PostMapping("/getFinprodId2")
	public List<SuggestDto> getFinprodId2(@RequestBody Map<String, Object> param) throws Exception {
    	
		return valFxValuztionInfo002Service.getFinprodId2(param);
	}
    
    /**
     * 查询 外汇估值信息
     *
     * @param configId
     * @return
     */
    @PostMapping("/findByPrimaryKey")
    public ValFxValuztionInfo002Dto findByPrimaryKey(@RequestBody Map<String, Object> map) {
        return valFxValuztionInfo002Service.findByPrimaryKey(map);
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
    public void doComplete(@RequestBody ValFxValuztionInfo002Dto dto, @PathVariable("action") String action) {
        valFxValuztionInfo002Service.doComplete(dto, OperationType.valueOf(action));
    }
}
