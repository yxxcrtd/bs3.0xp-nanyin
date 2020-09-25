/*
 * 文件名：AssetElementRegistration001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年7月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.assetelementregistration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;

/**
 * 资产要素登记
 * @author xiexinting
 * @version 1.0 2018年7月31日
 * @see AssetElementRegistration001Controller
 * @since 1.0
 */
@Controller
@PageId("AssetElementRegistration001")
@RequestMapping("/invest/report/debtreport/assetelementregistration/AssetElementRegistration001")
public class AssetElementRegistration001Controller extends BaseController{
/**
 * {@inheritDoc}
 */
@Override
public String init(Model model) {
    // TODO Auto-generated method stub
    return super.init(model);
}
}
