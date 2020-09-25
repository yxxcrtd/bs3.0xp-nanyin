/*
 * 文件名：PortfolioReport001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.informationdisclosureform.portfolioreport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年7月30日
 * @see PortfolioReport001Controller
 * @since 1.0
 */
@Controller
@PageId("PortfolioReport001")
@RequestMapping("/invest/report/informationdisclosureform/portfolioreport/PortfolioReport001")
public class PortfolioReport001Controller extends BaseController{
    
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        return super.init(model);
    }
}
