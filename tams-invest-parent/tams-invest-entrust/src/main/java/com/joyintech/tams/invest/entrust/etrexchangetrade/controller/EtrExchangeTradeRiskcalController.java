/*
 * 文件名：EtrTradePlace001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrexchangetrade.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.EtrExchangeTrade001Dto;

/**
 * 交易所指令交易风险试算
 * @author chendijun
 * @version 1.0 2020年7月1日
 * @see EtrExchangeTradeRiskcalController
 * @since 1.0
 */
@Controller
@PageId("EtrExchangeTradeRiskcal")
@RequestMapping("/invest/entrust/etrexchangetrade/EtrExchangeTradeRiskcal")
public class EtrExchangeTradeRiskcalController extends BaseController{
    @Override
    public String init(Model model) {
        return super.init(model);
    }
}
