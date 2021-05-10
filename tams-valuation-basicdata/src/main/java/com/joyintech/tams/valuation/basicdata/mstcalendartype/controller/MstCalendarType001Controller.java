/*
 * 文件名：MstCalendarType001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basicdata.mstcalendartype.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.basicdata.mstcalendartype.dto.MstCalendarType001Dto;
import com.joyintech.tams.valuation.basicdata.mstcalendartype.service.MstCalendarType001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 日历类型信息管理MstCalendarType001Controller
 * 
 * @author liuwei
 * @version 1.0 2018年8月18日
 * @see MstCalendarType001Controller
 * @since 1.0
 */
@Controller
@PageId("MstCalendarType001")
@RequestMapping("/valuation/basicdata/mstcalendartype/MstCalendarType001")
public class MstCalendarType001Controller extends BaseController {

    /**
     * 日历类型信息管理Service
     */
    @Autowired
    private MstCalendarType001Service mstCalendarType001001service;

    /**
     * 查询界面的初始化
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
     * 查询操作
     *
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstCalendarType001Dto> selectAllCalendar()
        throws Exception {
        Map<String, Object> map = RequestUtil.getPageParam();
        return mstCalendarType001001service.selectForPage(map);
    }

    /**
     * 交易日历名称的联想文本框事件
     * 
     * @return 查询的交易日历代码和交易日历名称列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCalendarNameList")
    public List<SuggestDto> getCalendarNameList()
        throws Exception {
        Map<String, Object> map = new HashMap<>();
        String param = super.getRequest().getParameter("param");
        map.put("calendarName", param);
        map.put("YESORNO_YES_01", DictCodeList.是否_是.getValue());
        List<SuggestDto> temp = mstCalendarType001001service.getCalendarNameList(map);
        return temp;
    }

    /**
     * 交易日历类型的下拉框事件
     * 
     * @return 查询的交易日历类型列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCalendarType")
    public List<SelectOptionDto> getCalendarType()
        throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("YESORNO_YES_01", DictCodeList.是否_是.getValue());
        param.put("calendarType", super.getRequest().getParameter("param"));
        List<SelectOptionDto> calendarTypeList = mstCalendarType001001service.getCalendarType(
            param);
        return calendarTypeList;
    }

    /**
     * 删除按钮点击时的check
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
        ValidateGroupDelete.class}) MstCalendarType001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 删除操作
     *
     * @param dto dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstCalendarType001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstCalendarType001Dto> processParameter = new ProcessParameter<MstCalendarType001Dto>();
        WorkFlowParameter<MstCalendarType001Dto> params = new WorkFlowParameter<MstCalendarType001Dto>();
        params.setPageId("MstCalendarType002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getTradeCalendarId());
        params.setBusinessType(DictCodeList.共通业务类型_日历类型管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> this.getMessage("MstCalendarType001.tradeCalendarId") + "(" + entity.getTradeCalendarId() + ")"
            + this.getMessage("MstCalendarType001.calendarName")+ "(" + entity.getCalendarName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstCalendarType001001service.start(processParameter,
            dto.getSelectedItem().toArray(
                new MstCalendarType001Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    /*@RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return mstCalendarType001001service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }*/

    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        List<MstCalendarType001Dto> listdto = new ArrayList<>();
        if (datas == null) {
            listdto = mstCalendarType001001service.selectForExport(paraMap);
        } else {
            listdto = JsonUtils.json2List(datas, MstCalendarType001Dto.class);
        }
        File outFile = mstCalendarType001001service.makeFile(
                "/com/joyintech/tams/valuation/basicdata/mstcalendartype/conf/export_conf.xml",
                listdto);
        return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(DateUtil.parse(DateUtil.getSysDate())) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }
    
    /**
     * getTradeCalendarName
     * @return List<SuggestDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getTradeCalendarName")
    public List<SuggestDto> getInstitutionName()
        throws Exception {
        String param = super.getRequest().getParameter("param");
        String isTradeCalendar = super.getRequest().getParameter("isTradeCalendar");
        List<SuggestDto> temp = mstCalendarType001001service.getTradeCalendarName(param,isTradeCalendar);
        return temp;
    } 
}
