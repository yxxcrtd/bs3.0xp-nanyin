/*
 * 文件名：MarketCalendarInfoController001.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：
 * 修改时间：2018年7月16日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.service.basicdata.marketdata.mstmarketcalendarinfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.basicdata.marketdata.mstmarketcalendarinfo.dto.ComCalendarInfoDto;
import com.joyintech.service.basicdata.marketdata.mstmarketcalendarinfo.dto.ComMarketCalendarInfo001Dto;
import com.joyintech.service.basicdata.marketdata.mstmarketcalendarinfo.service.ComMarketCalendarInfoService;
import com.joyintech.tams.common.base.entity.MstCalendarInfo;


/**
 * 市场日历信息
 * @author xiexinting
 *
 */

@RestController("serviceComMarketCalendarInfoController")
@RequestMapping("/ComMarketCalendarInfo")
public class ComMarketCalendarInfoController {
    
    /**
     * 
     */
    @Autowired
    private ComMarketCalendarInfoService comMarketCalendarInfoService;

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
    public Page<ComMarketCalendarInfo001Dto> selectInfo(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return comMarketCalendarInfoService.selectInfo(page,length,param);
    }
    /**
     * 市场日历类型下拉框查询 <功能详细描述>
     * @param map 参数
     * @return 返回结果
     * @see
     * @since 1.0
     */
    @PostMapping("/selectCalendarInfo")
    public List<ComCalendarInfoDto> selectCalendarInfo(@RequestBody Map<String,Object> map) {
        return comMarketCalendarInfoService.selectCalendarInfo(map);
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
    public ComMarketCalendarInfo001Dto getMstMarketCalendarInfo001DtoByKey(@RequestBody ComMarketCalendarInfo001Dto dto){
        return comMarketCalendarInfoService.getMstMarketCalendarInfo001DtoByKey(dto);
    }
    /**
     * 
     * 插入数据
     * <功能详细描述>
     * @param list 参数
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/insertBtachEquityInfo")
    public void insertBtachEquityInfo(@RequestBody List<ComMarketCalendarInfo001Dto> list){
        comMarketCalendarInfoService.insertBtachEquityInfo(list);
    }
    /**
     * 
     * 更新数据
     * <功能详细描述>
     * @param list 参数
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/updateBtachEquityInfo")
    public void  updateBtachEquityInfo(@RequestBody List<ComMarketCalendarInfo001Dto> list){
        comMarketCalendarInfoService.updateBtachEquityInfo(list);
    }
    
    /**
     * 
     * 工作日和节假日互改
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody ComMarketCalendarInfo001Dto entity) {
        comMarketCalendarInfoService.doComplete(entity);
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
        return comMarketCalendarInfoService.getMstCalendarInfo();
    }
}
