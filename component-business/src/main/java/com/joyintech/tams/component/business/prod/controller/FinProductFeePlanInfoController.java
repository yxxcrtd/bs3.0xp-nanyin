/*
 * 文件名：FinProductFeePlanInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseController;

/**
 * 产品费用计划业务组件
 * 
 * @author 张亮
 * @version 1.0 2018年10月11日
 * @see BussFinProductFeeInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/FinProductFeePlanInfo")
public class FinProductFeePlanInfoController extends BaseController {

    @Override
    public String init(Model model) {
        /*
         * 费用信息
         */
        model.addAttribute("feeInfoData", getParam("feeInfo"));

        return "component/prod/FinProductFeePlanInfo";
    }

}
