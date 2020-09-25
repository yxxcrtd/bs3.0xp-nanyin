/*
 * 文件名：TaxScheme002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.taxparametermanage.taxscheme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.dto.TaxScheme002Dto;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.service.TaxScheme002Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 税务方案 
 * 
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see TaxScheme002Controller
 * @since 1.0
 */
//@RestController(value="serviceTaxScheme002Controller")
@RestController("taxScheme002Ctl_dtx")
@RequestMapping("/TaxScheme002")
public class TaxScheme002Controller extends BaseController {

	@Autowired
	private TaxScheme002Service taxScheme002Service;

    @GetMapping("/findBySchId")
    public TaxScheme002Dto findBySchId(String schId) {
        return taxScheme002Service.findBySchId(schId);
    }
	
    @GetMapping("/countBokBookSetPlan")
    public int countBokBookSetPlan(String schId) {
        return taxScheme002Service.countBokBookSetPlan(schId);
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
    @Transactional
    @DTXTransaction
    @PostMapping(value = "doComplete/{action}")
    public void doComplete(@RequestBody TaxScheme002Dto dto, @PathVariable("action") String action) {
       taxScheme002Service.doComplete(dto, OperationType.valueOf(action));
    }
}
