/*
 * 文件名：EtrTradePlace001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etribbondinfo.controller;

import com.joyintech.tams.constant.DictCodeList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.JsonUtils;

/**
 * 交易所指令交易风险试算
 * @author chendijun
 * @version 1.0 2020年7月1日
 * @see EtrIbBondinfoRiskcal002Controller
 * @since 1.0
 */
@Controller
@PageId("EtrIbBondinfoRiskcal002")
@RequestMapping("/invest/entrust/etribbondinfo/EtrIbBondinfoRiskcal002")
public class EtrIbBondinfoRiskcal002Controller extends BaseController{
    @Override
    public String init(Model model) {
        return super.init(model);
    }
}
