/*
 * 文件名：CalendarTypeManagementInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.productbalancestatementinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;

/**
 * 产品余额报表
 * @author huwei
 * @version 1.0 2018年7月30日
 * @see ProductBalanceStatement001Controller
 * @since 1.0
 */
@Controller
@PageId("ProductBalanceStatement001")
@RequestMapping("/invest/report/productbalancestatementinfo/ProductBalanceStatement001")
public class ProductBalanceStatement001Controller extends BaseController{
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        return super.init(model);
    }
}



































