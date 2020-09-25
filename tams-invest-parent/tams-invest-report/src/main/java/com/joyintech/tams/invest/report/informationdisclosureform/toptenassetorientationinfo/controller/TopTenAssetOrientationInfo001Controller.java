/*
 * 文件名：TopTenAssetOrientationInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：范双虎
 * 修改时间：2018年7月30日
 * 修改内容：
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.report.informationdisclosureform.toptenassetorientationinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;

/**
 * 产品前十大资产投向
 * @author fanshuanghu
 * @version 1.0 2018年7月30日
 * @see TopTenAssetOrientationInfo001
 * @since 1.0
 */
@Controller
@PageId("TopTenAssetOrientationInfo001")
@RequestMapping("/invest/report/informationdisclosureform/toptenassetorientationinfo/TopTenAssetOrientationInfo001")
public class TopTenAssetOrientationInfo001Controller extends BaseController{
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model){
		return super.init(model);
	}
	
	@RequestMapping("/export")
	public String export(Model model){
		return "";
	}
}
