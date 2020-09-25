/*
 * 文件名：ReconcileSchemeInfo002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算POC
 * 修改人：jiangchuanzhi
 * 修改时间：2018年1月22日
 * 修改内容：新建
 * 系统名称：估值核算POC
 */

package com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeInfo002Dto;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.service.ValReconcileSchemeInfo002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;


/**
 * 对账规则方案配置新建Controller
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValReconcileSchemeInfo002Controller
 * @since 1.0
 */
@RestController(value="serviceValReconcileSchemeInfo002Controller")
@RequestMapping("/ValReconcileSchemeInfo002")
public class ValReconcileSchemeInfo002Controller extends BaseController {

    /** 对账规则方案配置新建Service */
    @Autowired
    private ValReconcileSchemeInfo002Service reconcileSchemeInfo002Service;
    
    
    /**
     * 检索账套代码(只取理财账套)
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getBooksetId")
    public List<SuggestDto> getBooksetId(String param) {
        return reconcileSchemeInfo002Service.getBooksetId(param);
    }

    /**
     * 保存处理
     * 
     * @param record 对账规则方案配置新建信息
     * @param result 保存结果
     * @return 保存结果
     * @since 1.0
     */
    @PostMapping("/doSave")
    public void doSave(@RequestBody ValReconcileSchemeInfo002Dto record) {
        reconcileSchemeInfo002Service.doSave(record);
    }

}
