/*
 * 文件名：ValBondRealRate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.bondrate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.valuation.assets.bondrate.dto.ValBondRealRate001Dto;
import com.joyintech.tams.constant.DictCode;

/**
 * 债券实际利率
 * 
 * @author HuangJian
 * @version 1.0 2019年1月19日
 * @see ValBondRealRate002Controller
 * @since 1.0
 */
@Controller
@PageId("ValBondRealRate002")
@RequestMapping("/valuation/assets/bondrate/ValBondRealRate002")
public class ValBondRealRate002Controller extends BaseController {

    
    // /**
    // * ValBondRealRate002Service
    // */
    // @Autowired
    // private ValBondRealRate002Service valBondRealRate002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String data = this.getParam("data");
        ValBondRealRate001Dto dto = JsonUtils.json2Object(data, ValBondRealRate001Dto.class);
        model.addAttribute("entity", dto);
        this.setSelectOptionToModel(model, DictCode.估值交易市场);
        return super.init(model);
    }

}
