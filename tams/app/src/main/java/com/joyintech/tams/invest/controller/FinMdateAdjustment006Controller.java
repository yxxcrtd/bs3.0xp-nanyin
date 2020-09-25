/*
 * 文件名：FinMdateAdjustment006Controller.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：adrian.zhou
 * 修改时间：2019/8/12 17:02
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author adrian.zhou
 * @version 1.0 2019年08月12日
 * @see
 * @since 1.0
 */
@Controller
@PageId(value = "FinMdateAdjustment006")
@RequestMapping("/invest/product/messageadjust/finmdateadjustment/FinMdateAdjustment006")
public class FinMdateAdjustment006Controller extends BaseController {
    @Override
    public String init(Model model) {
        String finprodId = this.getParam("finprodId");

        return super.init(model);
    }
}
