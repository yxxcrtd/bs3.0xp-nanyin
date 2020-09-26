/*
 * 文件名：BatchProcessing001.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：tangliang
 * 修改时间：2017年9月19日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.system.batchprocessing.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.report.system.batchprocessing.dto.BatchProcessing001Dto;
import com.joyintech.tams.report.system.batchprocessing.service.BatchProcessing001Service;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 批处理
 * 〈功能详细描述〉
 * @author tangliang
 * @version 1.0 2017年9月19日
 * @see BatchProcessing001Controller
 * @since 1.0
 */
@Controller
@PageId("BatchProcessing001")
@RequestMapping("/svreport/system/batchprocessing/BatchProcessing001")
public class BatchProcessing001Controller extends BaseController {
    /**
     * batchProcessing001Service
     */
    @Autowired
    private BatchProcessing001Service batchProcessing001Service;
    /**
     * {@inheritDoc}
     */
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        //下拉菜单赋值
        Date now = new Date();
        now=DateUtil.addMonthDate(now, -1);
        Date start=DateUtil.getFirstDay(now);
        Date end = DateUtil.getLastDay(now);
        model.addAttribute("start",DateUtil.format(start));
        model.addAttribute("end",DateUtil.format(end));
        model.addAttribute("taskDataInit", batchProcessing001Service.queryTaskList());
        model.addAttribute("yes",DictCodeList.是否_中债_是.getValue());
//        super.setSelectOptionToModel(model, DictCode.数据状态);
        return super.init(model);
    }
    /**
     * 刷新列表
     * 
     * @return Page
     */
    @ResponseBody
    @RequestMapping("dealBatchProcess")
    public Page<BatchProcessing001Dto> dealBatchProcess(){
        Map<String, Object> param = RequestUtil.getPageParam();
        return batchProcessing001Service.dealBatchProcess(param);
    }
    /**
     * 检查画面元素
     * 
     * @return Page
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(){
        Map<String, Object> param = RequestUtil.getPageParam();
        //检查画面元素
        AjaxResult result = batchProcessing001Service.check(param);
        result.setData(DateUtil.format(new Date(), DateUtil.DATETIME_FORMAT_WITH_SSS_YEAR1));
        return result;
    }
    /**
     * 检查画面元素
     * 
     * @return Page
     */
    @ResponseBody
    @RequestMapping("reflushLog")
    public AjaxResult reflushLog(){
        Map<String, Object> param = RequestUtil.getPageParam();
        //检查画面元素
        AjaxResult result = batchProcessing001Service.reflushLog(param);
        return result;
    }
}
