/*
 * 文件名：FinOpenPlanMaintenance006.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：adrian.zhou
 * 修改时间：2019/8/13 17:02
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
 * @version 1.0 2019年08月13日
 * @see
 * @since 1.0
 */
@Controller
@PageId(value = "FinOpenPlanMaintenance006")
@RequestMapping("/invest/product/messageadjust/maintenance/FinOpenPlanMaintenance006")
public class FinOpenPlanMaintenance006Controller extends BaseController {
    @Override
    public String init(Model model) {
        String cashId = this.getParam("cashId");
        String openType = this.getParam("openType");
        String openDateEnd = this.getParam("openDateEnd");

        return super.init(model);
    }
}
