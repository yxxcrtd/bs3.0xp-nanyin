/*
 * 文件名：ProdFactorInfo002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：qiangchengxi
 * 修改时间：2017年11月16日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitmaintenance.prodfactorinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;


/**
 * 产品要素信息新建界面Controller
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2017年10月31日
 * @see ProdFactorInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("ProdFactorInfo002")
@RequestMapping("/svreport/submitmaintenance/prodfactorinfo/ProdFactorInfo002")
public class ProdFactorInfo002Controller extends BaseController{
    /**
     * 页面初始化
     * {@inheritDoc}
     */
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        
        return super.init(model);
    }
}