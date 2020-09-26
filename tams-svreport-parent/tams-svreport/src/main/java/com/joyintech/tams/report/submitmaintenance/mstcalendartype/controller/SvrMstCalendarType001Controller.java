/*
 * 文件名：MstCalendarType001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.report.submitmaintenance.mstcalendartype.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.report.submitmaintenance.mstcalendartype.dto.SvrMstCalendarType001Dto;
import com.joyintech.tams.report.submitmaintenance.mstcalendartype.service.SvrMstCalendarType001MService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 日历类型信息管理MstCalendarType001Controller
 * 
 * @author liuwei
 * @version 1.0 2018年8月18日
 * @see SvrMstCalendarType001Controller
 * @since 1.0
 */
@Controller
@PageId("SvrMstCalendarType001")
@RequestMapping("/submitmaintenance/mstcalendartype/SvrMstCalendarType001")
public class SvrMstCalendarType001Controller extends BaseController {
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 日历类型信息管理Service
     */
    @Autowired
    private SvrMstCalendarType001MService mstCalendarType001001service;

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
    public Page<SvrMstCalendarType001Dto> selectAllCalendar()
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
        map.put("YESORNO_YES_01", "Y");
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
        param.put("YESORNO_YES_01", "Y");
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
        ValidateGroupDelete.class}) SvrMstCalendarType001Dto dto, BindingResult result) {
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
    public AjaxResult delete()
        throws Exception {
    	String msg = this.getMessage("REPORT_COMMON_I_0006");
    	String keyStr = super.getParam("deleteKeys");
    	if (StringUtil.isNotEmpty(keyStr)) {
            List<String> keys = StringUtil.splitToList(GlobalConstant.COMMA, keyStr);
            keys.forEach(key -> {
                List<String> keyList = StringUtil.splitToList("|",key);
                SvrMstCalendarType001Dto dto = new SvrMstCalendarType001Dto();
                dto.setTradeCalendarId(keyList.get(0));
                mstCalendarType001001service.doComplete(dto, OperationType.DELETE);
            });
    	}
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
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
    	if(param.containsKey("keyList")){
        	String keyList = param.get("keyList").toString();
			try {
				List<SvrMstCalendarType001Dto> investList = JsonUtils.json2List(keyList,SvrMstCalendarType001Dto.class);
				param.put("investList", investList);
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
			List<SvrMstCalendarType001Dto> dtoList = mstCalendarType001001service.selectForExport(param);
			File outFile = mstCalendarType001001service.makeFile(
					"/com/joyintech/tams/report/submitmaintenance/mstcalendartype/conf/export_conf.xml", dtoList);
		         return ExportUtil.export(
		             this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
		                                  + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
		                                  + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
		             outFile, true);
        }else{
        	return mstCalendarType001001service.export(this.getPageId(),param);
        }
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
