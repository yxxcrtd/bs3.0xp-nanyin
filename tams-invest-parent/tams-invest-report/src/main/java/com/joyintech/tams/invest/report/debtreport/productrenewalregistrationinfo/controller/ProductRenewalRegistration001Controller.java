/*
 * 文件名：CalendarTypeManagementInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.productrenewalregistrationinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;

/**
 * 产品存续期登记
 * @author huwei
 * @version 1.0 2018年7月31日
 * @see ProductRenewalRegistration001Controller
 * @since 1.0
 */
@Controller
@PageId("ProductRenewalRegistration001")
@RequestMapping("/invest/report/debtreport/productrenewalregistrationinfo/ProductRenewalRegistration001")
public class ProductRenewalRegistration001Controller extends BaseController{
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        return super.init(model);
    }
}



































