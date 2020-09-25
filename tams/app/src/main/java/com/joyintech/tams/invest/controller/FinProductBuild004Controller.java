/*
 * 文件名：FinProductBuild004Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2019年7月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.invest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;

/**
 * 跳转自定义发送详情页面
 * @author 王波
 * @version 1.0 2019年7月13日
 * @see FinProductBuild004Controller
 * @since 1.0
 */
@Controller
@PageId(value = "FinProductBuild004")
@RequestMapping("/invest/product/productelement/build/FinProductBuild004")
public class FinProductBuild004Controller extends BaseController {

	/**
	 * 初始化页面方法
	 * {@inheritDoc}
	 */
	@Override
    public String init(Model model) {
        String finprodId = this.getParam("finprodId");

        return super.init(model);
    }
}
