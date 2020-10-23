/*
 * 文件名：ValBondValuationInfo002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
package com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.controller.ValAssetBook002Controller;
import com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.dto.ValBondValuationInfo002Dto;
import com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.service.ValBondValuationInfo002Service;

/**
 * 双击显示债券国债行情
 * 
 * @author niuqianyu
 * @version 1.0 2018年7月18日
 * @see ValAssetBook002Controller
 * @since 1.0
 */
@RestController(value="serviceValBondValuationInfo002Controller")
@RequestMapping("/service/valuation/basedata/marketdata/bondvaluationinfo/ValBondValuationInfo002")
public class ValBondValuationInfo002Controller extends BaseController {

    /**
     * valBondValuationInfo002Service
     */
    @Autowired
    private ValBondValuationInfo002Service valBondValuationInfo002Service;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String secId = this.getParam("secId");
        model.addAttribute("secId", secId);
        return super.init(model);
    }

    /**
     * 根据债券代码secId获取对应的债券估值行情
     *
     * @return 债券估值行情
     * @throws Exception 异常
     * @see
     */
    @PostMapping("/searchList")
    public List<ValBondValuationInfo002Dto> searchList(@RequestBody String secId) {
        // 债券代码
//        String secId = this.getParam("secId");
        return valBondValuationInfo002Service.searchList(secId);
    }

}
