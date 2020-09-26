/*
 * 文件名：MstCalendarType004Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.report.submitmaintenance.mstcalendartype.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.report.submitmaintenance.mstcalendartype.dto.SvrMstCalendarType002Dto;
import com.joyintech.tams.report.submitmaintenance.mstcalendartype.service.SvrMstCalendarType002MService;
import com.joyintech.tams.constant.DictCode;

/**
 * 交易日历名称明细查看
 * 
 * @author liuwei
 * @version 1.0 2018年8月17日
 * @see SvrMstCalendarType004Controller
 * @since 1.0
 */
@Controller
@PageId("SvrMstCalendarType004")
@RequestMapping("/submitmaintenance/mstcalendartype/SvrMstCalendarType004")
public class SvrMstCalendarType004Controller extends BaseController {

    /**
     * 日历类型管理Service
     */
    @Autowired
    private SvrMstCalendarType002MService mstCalendarType002service;

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
        Map<String, Object> map = RequestUtil.getPageParam();
        model.addAttribute("tradeCalendarId", map.get("tradeCalendarId"));
        model.addAttribute("calendarName",map.get("calendarName"));
        this.setSelectOptionToModel(model, DictCode.是否_中债);
        return super.init(model);

    }

    /**
     * 查询按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) SvrMstCalendarType002Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 查询交易日历名称明细
     * 
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<SvrMstCalendarType002Dto> selectCanlendarDetail()
        throws Exception {
        Map<String, Object> map = RequestUtil.getPageParam();
        return mstCalendarType002service.selectCanlendarDetail(map);
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return mstCalendarType002service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }
}
