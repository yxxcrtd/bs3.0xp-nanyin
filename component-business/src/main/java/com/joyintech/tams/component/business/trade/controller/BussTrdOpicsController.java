/*
 * 文件名：BussTrdOpicsController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.trade.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * Opics共通组件
 *
 * @author 刘二通
 * @version 1.0 2018年11月15日
 * @see BussTrdOpicsController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/trade/BussTrdOpics")
public class BussTrdOpicsController extends BaseController {

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.是否, DictCode.币种);

        return "component/trade/BussTrdOpics";
    }

    /**
     * 获取opics下拉框选项
     *
     * @return 下拉框选项
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getSelectOptions")
    public List<SelectOptionDto> getSelectOptions() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return new ArrayList<SelectOptionDto>();
    }
}
