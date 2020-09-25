/*
 * 文件名：FinProductFeePay001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.nolimitmanagement.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 非标限额管理
 *
 * @author jiangyunlong
 * @version 1.0 2019年12月18日
 * @see Nolimitmanagement001Controller
 * @since 1.0
 */
@Controller
@PageId("Nolimitmanagement001")
@RequestMapping("/invest/product/nolimitmanagement/Nolimitmanagement001")
public class Nolimitmanagement001Controller extends BaseController {

    @Override
    public String init(Model model) {
        return super.init(model);
    }

}
