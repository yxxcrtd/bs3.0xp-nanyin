/*
 * 文件名：DataCheckResultController.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年10月9日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */
 
package com.joyintech.tams.report.common.controller;

import com.joyintech.framework.annotation.PageId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseController;


/**
 * 数据检查结果显示画面
 * 
 * @author 何阳阳
 * @version 1.0 2017年10月9日
 * @see DataCheckResultController
 * @since 1.0
 */
@Controller
@PageId("DataCheckResult")
@RequestMapping("/dataCheckResult")
public class DataCheckResultController extends BaseController {

    @Override
    public String init(Model model) {
        return "report/common/dataCheckResult";
    }
}
