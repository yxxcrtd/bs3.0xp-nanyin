/*
 * 文件名：EtribBondInfo003RiskcalController.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2021年01月23日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.invest.entrust.etribbondinfo.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 王波
 * @date 2021年01月23日
 * @see EtribBondInfo003RiskcalController
 * @since 1.0
 */
@Controller
@PageId("EtribBondInfo003Riskcal")
@RequestMapping("/invest/entrust/etribbondinfo/EtribBondInfo003Riskcal")
public class EtribBondInfo003RiskcalController extends BaseController {
    @Override
    public String init(Model model) {
        return super.init(model);
    }

}
