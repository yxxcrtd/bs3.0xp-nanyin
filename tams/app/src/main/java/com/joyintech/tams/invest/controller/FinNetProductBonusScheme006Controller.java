/*
 * 文件名：FinNetProductBonusScheme006Controller.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouyongjun
 * 修改时间：2019/8/10 17:02
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author adrian.zhou
 * @version 1.0 2019年08月10日
 * @see
 * @since 1.0
 */
@Controller
@PageId(value = "FinNetProductBonusScheme006")
@RequestMapping("/invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme006")
public class FinNetProductBonusScheme006Controller extends BaseController {
    @Override
    public String init(Model model) {
        String bonusPlanId = this.getParam("bonusPlanId");

        return super.init(model);
    }
}
