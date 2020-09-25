/*
 * 文件名：MarketRiskSceneManage003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;

/**
 * 市场性风险-情景详情
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月20日
 * @see MarketRiskSceneManage004Controller
 * @since 1.0
 */
@Controller
@PageId("MarketRiskSceneManage004")
@RequestMapping("/invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage004")
public class MarketRiskSceneManage004Controller extends BaseController {

	/**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	//初始化需要判断来源，如果来源是修改页面，则需要根据提供的数据重新计算，如果是管理页面则直接查询并显示
    	String arrayStr = getParam("array");
    	model.addAttribute(GlobalConstant.ENTITY, arrayStr);
    	return super.init(model);
    }
    
}
