/*
 * 文件名：StlOrderManagementResend002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2019年7月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.settle.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;

/**
 * 指令接口管理重新发送
 * @author wangqidong
 * @version 1.0 2019年7月12日
 * @see StlOrderManagementResend002Controller
 * @since 1.0
 */
@Controller
@PageId(value = "StlOrderManagementResend002")
@RequestMapping("/invest/settle/order/StlOrderManagementResend002")
public class StlOrderManagementResend002Controller extends BaseController{

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String finprodId = this.getParam("finprodId");
        
        return super.init(model);
    }

    
}
