/*
 * 文件名：ValIntRateQuote002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.finproductvaluation.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.service.valuation.basedata.marketdata.finproductvaluation.dto.ResultDto;

/**
 * 新建
 * 
 * @author HuangJian
 * @version 1.0 2018年7月19日
 * @see ValFinProductValuation002Controller
 * @since 1.0
 */
@RestController(value="serviceValFinProductValuation002Controller")
@RequestMapping("/service/valuation/basedata/marketdata/finproductvaluation/ValFinProductValuation002")
public class ValFinProductValuation002Controller extends BaseController {

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String datas = this.getParam("datas");
        ResultDto entity = JsonUtils.json2Object(datas, ResultDto.class);
        model.addAttribute("entity", entity);
        return super.init(model);
    }
}
