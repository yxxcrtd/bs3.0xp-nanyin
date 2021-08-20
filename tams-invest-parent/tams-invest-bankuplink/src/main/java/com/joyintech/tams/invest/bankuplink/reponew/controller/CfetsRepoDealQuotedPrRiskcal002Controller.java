/*
 * 文件名：EtrTradePlace001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.bankuplink.reponew.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 交易所指令交易风险试算
 * @author wl
 * @version 1.0 2020年7月1日
 * @see CfetsRepoDealQuotedPrRiskcal002Controller
 * @since 1.0
 */
@Controller
@PageId("CfetsRepoDealQuotedPrRiskcal002")
@RequestMapping("/invest/bankuplink/reponew/CfetsRepoDealQuotedPrRiskcal002")
public class CfetsRepoDealQuotedPrRiskcal002Controller extends BaseController{
    @Override
    public String init(Model model) {
        return super.init(model);
    }
}
