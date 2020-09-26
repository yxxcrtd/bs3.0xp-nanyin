/*
 * 文件名：ValIntRateQuote002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.intratequote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.valuation.basedata.marketdata.intratequote.dto.ResultDto;

/**
 * 新建
 * 
 * @author HuangJian
 * @version 1.0 2018年7月19日
 * @see ValIntRateQuote002Controller
 * @since 1.0
 */
@Controller
@PageId("ValIntRateQuote002")
@RequestMapping("/valuation/basedata/marketdata/intratequote/ValIntRateQuote002")
public class ValIntRateQuote002Controller extends BaseController{
            
    
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String datas = this.getParam("datas");
        ResultDto entity = JsonUtils.json2Object(datas,
            ResultDto.class);
        model.addAttribute("entity", entity);
        return super.init(model);
    }
}
