/*
 * 文件名：MarketCalendarInfoController001.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：
 * 修改时间：2018年7月16日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.service.svrcom.submitmaintenance.mstmarketcalendarinfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.MstCalendarInfo;
import com.joyintech.tams.report.submitmaintenance.mstmarketcalendarinfo.dto.SvrMstCalendarInfoDto;
import com.joyintech.tams.report.submitmaintenance.mstmarketcalendarinfo.dto.SvrMstMarketCalendarInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.mstmarketcalendarinfo.service.SvrMstMarketCalendarInfo001Service;


/**
 * 市场日历信息
 * @author xiexinting
 *
 */

@RestController
@RequestMapping("/SvrMstMarketCalendarInfo")
public class SvrMstMarketCalendarInfoController{
    
    /**
     * 
     */
    @Autowired
    private SvrMstMarketCalendarInfo001Service mstMarketCalendarInfo001Service;
    
    /**
     * 市场日历信息查询 <功能详细描述>
     * 
     * @param param 查询参数
     * @return 查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @PostMapping("selectInfo/{page}/{length}")
    public Page<SvrMstMarketCalendarInfo001Dto> selectInfo(@RequestBody Map<String, Object> param,@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return mstMarketCalendarInfo001Service.selectInfo(param);
    }
    /**
     * 市场日历类型下拉框查询 <功能详细描述>
     * @param map 参数
     * @return 返回结果
     * @see
     * @since 1.0
     */
    @PostMapping("/selectCalendarInfo")
    public List<SvrMstCalendarInfoDto> selectCalendarInfo(@RequestBody Map<String,Object> map) {
        return mstMarketCalendarInfo001Service.selectCalendarInfo(map);
    }
    /**
     * 
     * 根据key获取dto
     * <功能详细描述>
     * @param dto key
     * @return 数据
     * @see
     * @since 1.0
     */
    @PostMapping("/getMstMarketCalendarInfo001DtoByKey")
    public SvrMstMarketCalendarInfo001Dto getMstMarketCalendarInfo001DtoByKey(@RequestBody SvrMstMarketCalendarInfo001Dto dto){
        return mstMarketCalendarInfo001Service.getMstMarketCalendarInfo001DtoByKey(dto);
    }
    /**
     * 
     * 插入数据
     * <功能详细描述>
     * @param list 参数
     * @see
     * @since 1.0
     */
    @PostMapping("/insertBtachEquityInfo")
    public void insertBtachEquityInfo(@RequestBody List<SvrMstMarketCalendarInfo001Dto> list){
        mstMarketCalendarInfo001Service.insertBtachEquityInfo(list);
    }
    /**
     * 
     * 更新数据
     * <功能详细描述>
     * @param list 参数
     * @see
     * @since 1.0
     */
    @PostMapping("/updateBtachEquityInfo")
    public void  updateBtachEquityInfo(@RequestBody List<SvrMstMarketCalendarInfo001Dto> list){
        mstMarketCalendarInfo001Service.updateBtachEquityInfo(list);
    }
    
    /**
     * 
     * 工作日和节假日互改
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    @PostMapping("/doComplete")
    public void doComplete(@RequestBody SvrMstMarketCalendarInfo001Dto entity) {
        mstMarketCalendarInfo001Service.doComplete(entity);
    }
    
    /**
     * 日历信息--用于wind 自动加券
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("/getMstCalendarInfo")
    public List<MstCalendarInfo>  getMstCalendarInfo(){
        return mstMarketCalendarInfo001Service.getMstCalendarInfo();
    }
    
    /**
     * 日历信息--用于wind 自动加券
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForExport")
    public List<SvrMstMarketCalendarInfo001Dto>  selectForExport(@RequestBody Map<String, Object> param){
        return mstMarketCalendarInfo001Service.selectForExport(param);
    }
}
