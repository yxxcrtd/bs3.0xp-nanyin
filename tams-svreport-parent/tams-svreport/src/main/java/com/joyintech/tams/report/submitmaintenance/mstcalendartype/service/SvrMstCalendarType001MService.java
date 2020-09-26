/*
 * 文件名：MstCalendarType001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.report.submitmaintenance.mstcalendartype.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.submitmaintenance.mstcalendartype.dto.SvrMstCalendarType001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 日历类型管理Service
 * 
 * @author liuwei
 * @version 1.0 2018年8月15日
 * @see SvrMstCalendarType001MService
 * @since 1.0
 */

@Service
public class SvrMstCalendarType001MService extends CommonExportService<SvrMstCalendarType001Dto> implements BaseProcessService<SvrMstCalendarType001Dto>{

    /**
     * 日历类型下拉框查询
     * 
     * @param param 查询参数
     * @return 日历类型
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCalendarType(Map<String, Object> param) {
        //获取下拉框选中的数据
        String initValue = (String)param.get("initValue");
        //将选中数据以逗号分隔成list形式
        if(StringUtil.isNotEmpty(initValue)){
            param.put("initValueList",StringUtil.splitToList(GlobalConstant.COMMA, initValue));
        }
        param.put("YESORNO_YES_02", DictCodeList.是否_否.getValue());
        return restTemplate.postForList(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstCalendarType/getCalendarType",
            param, SelectOptionDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(SvrMstCalendarType001Dto dto, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstCalendarType/" + action, dto,
                Integer.class);
    }

    /**
     * 交易日历名称的联想文本框事件
     * 查询界面的交易日历名称的联想文本框事件
     * @param map 查询参数
     * @return 交易日历名称
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getCalendarNameList(Map<String, Object> map) {
        return restTemplate.postForList(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstCalendarType/getCalendarNameList",
            map, SuggestDto.class);
    }

    /**
     * 查询操作
     * 通过输入的数据进行查询
     * @param pageParam 页面参数
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<SvrMstCalendarType001Dto> selectForPage(Map<String, Object> pageParam){
        pageParam.put("YESORNO_YES_01", DictCodeList.是否_是.getValue());
        if(StringUtil.isNotEmpty(pageParam.get("calendarType"))){
            pageParam.put("calendarTypeList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("calendarType")));
        }
        Page<SvrMstCalendarType001Dto> page = restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstCalendarType/selectForPage",
                pageParam, SvrMstCalendarType001Dto.class);
        return page;
    }

    /**
     * 交易日历名称的下拉框查询
     * 节假日查询界面的交易日历名称的下拉框查询
     * @param param 查询参数
     * @return 交易日历名称
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCalendarName(Map<String, Object> param) {
        return restTemplate.postForList(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstCalendarType/getCalendarName",
            param, SelectOptionDto.class);
    }

    /**
     * 节假日查询
     * 通过输入的交易日历名称和查询日期查询是否是节假日
     * @param map 查询参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public int getResult(Map<String, Object> map) {
        return restTemplate.postForObject(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstCalendarType/getResult",
            map, Integer.class);
    }

    /**
     * 双击查看界面和修改界面的初始化
     * 通过点击的数据信息查询相关数据
     * @param param 查询参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public SvrMstCalendarType001Dto selectCalendar(Map<String, Object> param) {
        return restTemplate.postForObject(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstCalendarType/selectCalendar",
            param, SvrMstCalendarType001Dto.class);
    }

    /**
     * 交易日历名称的明细查询
     * 
     * @param map 查询参数
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<SvrMstCalendarType001Dto> selectCanlendarDetail(Map<String, Object> map) {
        return restTemplate.postForPage(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstCalendarType/selectCanlendarDetail",
            map, SvrMstCalendarType001Dto.class);
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/report/submitmaintenance/mstcalendartype/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }
    
    @Override
    public Page<SvrMstCalendarType001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    /**
     * 交易日历下拉框---模糊查询--存款标的信息
     * @param param param
     * @param isTradeCalendar isTradeCalendar
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getTradeCalendarName(String param,String isTradeCalendar) {
        Map<String,Object>map = new HashMap<>();
        map.put("param", param);
        map.put("isTradeCalendar", isTradeCalendar);
        return restTemplate.postForList(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstCalendarType/getTradeCalendarName",
            map, SuggestDto.class);
    }
    
    /**
     * 交易日历下拉框---模糊查询--存款标的信息
     * @param param param
     * @param isTradeCalendar isTradeCalendar
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    public List<SvrMstCalendarType001Dto> selectForExport(Map<String, Object> param) {
        return restTemplate.postForList(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstCalendarType/selectForExport",
            param, SvrMstCalendarType001Dto.class);
    }
}
