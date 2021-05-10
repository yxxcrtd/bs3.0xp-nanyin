/*
 * 文件名：MstCalendarType004Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basicdata.mstcalendartype.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.basicdata.mstcalendartype.dto.MstCalendarType001Dto;
import com.joyintech.tams.valuation.basicdata.mstcalendartype.dto.MstCalendarType002Dto;
import com.joyintech.tams.valuation.basicdata.mstcalendartype.service.MstCalendarType001Service;
import com.joyintech.tams.valuation.basicdata.mstcalendartype.service.MstCalendarType002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 交易日历名称明细查看
 * 
 * @author liuwei
 * @version 1.0 2018年8月17日
 * @see MstCalendarType004Controller
 * @since 1.0
 */
@Controller
@PageId("MstCalendarType004")
@RequestMapping("/valuation/basicdata/mstcalendartype/MstCalendarType004")
public class MstCalendarType004Controller extends BaseController {

    /**
     * 日历类型管理Service
     */
    @Autowired
    private MstCalendarType002Service mstCalendarType002service;

    @Autowired
    private MstCalendarType001Service mstCalendarType001service;

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
        this.setSelectOptionToModel(model, DictCode.是否);
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
            ValidateGroupEdit.class}) MstCalendarType002Dto dto, BindingResult result) {
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
    public Page<MstCalendarType002Dto> selectCanlendarDetail()
        throws Exception {
        Map<String, Object> map = RequestUtil.getPageParam();
        return mstCalendarType002service.selectCanlendarDetail(map);
    }

    /*add by sunshuai shuai 20210315 start*/
    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        Map<String, Object> map = RequestUtil.getPageParam();
        String datas = this.getParam("datas");
        MstCalendarType002Dto dto = (MstCalendarType002Dto)JsonUtils.map2entity(map, MstCalendarType002Dto.class);
        List<MstCalendarType001Dto> dtoList;
        dtoList = mstCalendarType001service.selectAll(map);

        File  outFile = mstCalendarType001service.makeFile(
                "/com/joyintech/tams/valuation/basicdata/mstcalendartype/conf/export_conf1.xml",
                dtoList);

        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
    /*add by sunshuai shuai 20210315 end*/
}
