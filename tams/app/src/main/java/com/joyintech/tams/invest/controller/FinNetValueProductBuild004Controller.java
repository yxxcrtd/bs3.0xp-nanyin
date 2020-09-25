/*
 * 文件名：FinNetValueProductBuild004Controller.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王波
 * 修改时间：2019/7/1 17:02
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
 * @author 王波
 * @version 1.0 2019年07月01日
 * @see
 * @since 1.0
 */
@Controller
@PageId(value = "FinNetValueProductBuild004")
@RequestMapping("/invest/product/productelement/netvalueproduct/FinNetValueProductBuild004")
public class FinNetValueProductBuild004Controller extends BaseController {
    @Override
    public String init(Model model) {
        String finprodId = this.getParam("finprodId");

        return super.init(model);
    }
}
