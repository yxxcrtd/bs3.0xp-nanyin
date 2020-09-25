/*
 * 文件名：MstCalendarType003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstcalendartype.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.basicdata.mstcalendartype.service.MstCalendarType001Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;

/**
 * 节假日查询
 * 
 * @author liuwei
 * @version 1.0 2018年7月26日
 * @see MstCalendarType003Controller
 * @since 1.0
 */
@Controller
@PageId("MstCalendarType003")
@RequestMapping("/invest/basicdata/mstcalendartype/MstCalendarType003")
public class MstCalendarType003Controller extends BaseController {

    /**
     * 日历类型管理Service
     */
    @Autowired
    private MstCalendarType001Service mstCalendarType001001service;

    /**
     * 节假日查询界面的初始化
     * 
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 交易日历名称下拉框查询
     * 
     * @return dto
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCalendarNameList")
    public List<SelectOptionDto> getCalendarNameList() {
        // 将需要的数据字典和参数添加到map中
        Map<String, Object> param = new HashMap<>();
        param.put("YESORNO_YES_01", DictCodeList.是否_是.getValue());
        param.put("calendarName", super.getRequest().getParameter("calendarName"));
        return mstCalendarType001001service.getCalendarName(param);
    }

    /**
     * 查询结果画面联动 通过输入的交易日历名称和查询日期联动出查询结果
     * 
     * @return dto
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getResult")
    public int getResult() {
        // 将需要的数据字典和参数添加到map中
        Map<String, Object> map = RequestUtil.getPageParam();
        return mstCalendarType001001service.getResult(map);
    }
}
