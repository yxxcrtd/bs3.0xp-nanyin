/*
 * 文件名：MstCalendarType001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.svrcom.submitmaintenance.mstcalendartype.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.submitmaintenance.mstcalendartype.dto.SvrMstCalendarType001Dto;
import com.joyintech.tams.report.submitmaintenance.mstcalendartype.service.SvrMstCalendarType001Service;
import com.joyintech.tams.report.submitmaintenance.mstcalendartype.service.SvrMstCalendarType002Service;
import com.joyintech.tams.report.submitmaintenance.mstmarketcalendarinfo.dto.SvrMstMarketCalendarInfo001Dto;

/**
 * 日历类型信息管理MstCalendarType001Controller
 * 
 * @author liuwei
 * @version 1.0 2018年8月18日
 * @see SvrMstCalendarTypeController
 * @since 1.0
 */
@RestController
@RequestMapping("/SvrMstCalendarType")
public class SvrMstCalendarTypeController  {

    /**
     * 日历类型信息管理Service
     */
    @Autowired
    private SvrMstCalendarType001Service mstCalendarType001Service;

    /**
     * 日历类型信息管理Service
     */
    @Autowired
    private SvrMstCalendarType002Service mstCalendarType002Service;
    /**
     * 交易日历类型的下拉框事件
     * @param map 页面参数
     * @return 查询的交易日历类型列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/getCalendarType")
    List<SelectOptionDto>getCalendarType(@RequestBody Map<String, Object> map){
        return mstCalendarType001Service.getCalendarType(map);
    }
    /**
     * 删除
     *
     * @param dto
     *            页面选中的数据
     * @return 结果
     * @throws Exception
     *             异常
     */
    @PostMapping("delete")
    public int delete(@RequestBody SvrMstCalendarType001Dto dto) throws Exception {
        return mstCalendarType001Service.delete(dto);
    }

    /**
     * 插入
     *
     * @param dto
     *            页面选中的数据
     * @return 结果
     * @throws Exception
     *             异常
     */
    @PostMapping("insert")
    public int insert(@RequestBody SvrMstCalendarType001Dto dto) throws Exception {
        return mstCalendarType001Service.insert(dto);
    }

    /**
     * 更新
     *
     * @param dto
     *            页面选中的数据
     * @return 结果
     * @throws Exception
     *             异常
     */
    @PostMapping("update")
    public int update(@RequestBody SvrMstCalendarType001Dto dto) throws Exception {
        return mstCalendarType001Service.update(dto);
    }
    /**
     * 交易日历名称的联想文本框事件
     * 
     * @param map 页面参数
     * @return 查询的交易日历代码和交易日历名称列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/getCalendarNameList")
    public List<SuggestDto> getCalendarNameList(@RequestBody Map<String, Object> map)
        throws Exception {
        List<SuggestDto> temp = mstCalendarType001Service.getCalendarNameList(map);
        return temp;
    }
    
    /**
     * 查询操作
     * 
     * @param map 页面参数
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<SvrMstCalendarType001Dto> selectForPage(@RequestBody Map<String, Object> map,@PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return mstCalendarType001Service.selectForPage(map);
    }
    
    /**
     * 交易日历名称的下拉框查询
     * 节假日查询界面的交易日历名称的下拉框查询
     * @param param 查询参数
     * @return 交易日历名称
     * @see
     * @since 1.0
     */
    @PostMapping("/getCalendarName")
    List<SelectOptionDto> getCalendarName(@RequestBody Map<String, Object> param){
        return mstCalendarType001Service.getCalendarName(param);
    }
    
    /**
     * 查询结果画面联动 通过输入的交易日历名称和查询日期联动出查询结果
     * @param map 画面参数
     * @return dto
     * @see
     * @since 1.0
     */
    @PostMapping("/getResult")
    public int getResult(@RequestBody Map<String, Object> map) {
        // 将需要的数据字典和参数添加到map中
        return mstCalendarType001Service.getResult(map);
    }
    /**
     * 双击查看界面和修改界面的初始化
     * 通过点击的数据信息查询相关数据
     * @param param 查询参数
     * @return 结果
     * @see
     * @since 1.0
     */
    @PostMapping("/selectCalendar")
    SvrMstCalendarType001Dto selectCalendar(@RequestBody Map<String, Object> param) {
        return mstCalendarType001Service.selectCalendar(param);
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
    @PostMapping("/selectCanlendarDetail/{page}/{length}")
    public Page<SvrMstCalendarType001Dto> selectCanlendarDetail(@RequestBody Map<String, Object> map,@PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception{
        return mstCalendarType001Service.selectCanlendarDetail(map);
    }
    /**
     * 交易日历下拉框---模糊查询--存款标的信息
     * @param map 参数
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getTradeCalendarName")
    public List<SuggestDto> getTradeCalendarName(@RequestBody Map<String, Object> map){
        return mstCalendarType001Service.getTradeCalendarName(map);
    }
    
    /**
     * 日历信息--用于wind 自动加券
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForExport")
    public List<SvrMstCalendarType001Dto>  selectForExport(@RequestBody Map<String, Object> param){
        return mstCalendarType001Service.selectForExport(param);
    }
}
