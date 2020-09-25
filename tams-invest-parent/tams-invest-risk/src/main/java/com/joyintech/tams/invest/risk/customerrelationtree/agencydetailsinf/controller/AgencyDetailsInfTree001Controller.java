/*
 * 文件名：AgencyDetailsInfTree001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.risk.customerrelationtree.agencydetailsinf.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;

/**
 * 机构详细信息tree页面
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年12月06日
 * @see AgencyDetailsInfTree001Controller
 * @since 1.0
 */
@Controller
@PageId("AgencyDetailsInfTree001")
@RequestMapping("/invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInfTree001")
public class AgencyDetailsInfTree001Controller extends BaseController{

    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        model.addAttribute("agencyId", getParam("agencyId"));
        return super.init(model);
    }


    
    
    

}
