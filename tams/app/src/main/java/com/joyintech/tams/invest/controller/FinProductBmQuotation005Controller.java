/*
 * 文件名：FinProductBmQuotation005Controller.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouyongjun
 * 修改时间：2020/1/16 17:02
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
 * @author zhouyongjun
 * @version 1.0 2020年01月16日
 * @see
 * @since 1.0
 */
@Controller
@PageId(value = "FinProductBmQuotation005")
@RequestMapping("/invest/product/messageadjust/bmquotation/FinProductBmQuotation005")
public class FinProductBmQuotation005Controller extends BaseController {
    @Override
    public String init(Model model) {
        return super.init(model);
    }
}
