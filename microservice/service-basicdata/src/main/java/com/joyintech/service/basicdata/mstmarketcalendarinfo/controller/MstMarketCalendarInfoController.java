/*
 * 文件名：MarketCalendarInfoController001.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：
 * 修改时间：2018年7月16日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.service.basicdata.mstmarketcalendarinfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.basicdata.mstmarketcalendarinfo.dto.MstCalendarInfoDto;
import com.joyintech.service.basicdata.mstmarketcalendarinfo.dto.MstMarketCalendarInfo001Dto;
import com.joyintech.service.basicdata.mstmarketcalendarinfo.service.MstMarketCalendarInfoService;
import com.joyintech.tams.common.base.entity.MstCalendarInfo;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;


/**
 * 市场日历信息
 * @author xiexinting
 *
 */

@RestController("MstMarketCalendarInfoCtr_dtx")
@RequestMapping("/MstMarketCalendarInfo")
public class MstMarketCalendarInfoController{
    
    /**
     * 
     */
    @Autowired
    private MstMarketCalendarInfoService mstMarketCalendarInfo001Service;
    
    /**
     * 日期组件
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
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
    public Page<MstMarketCalendarInfo001Dto> selectInfo(@RequestBody Map<String, Object> param,@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return mstMarketCalendarInfo001Service.selectInfo(page,length,param);
    }
    /**
     * 市场日历类型下拉框查询 <功能详细描述>
     * @param map 参数
     * @return 返回结果
     * @see
     * @since 1.0
     */
    @PostMapping("/selectCalendarInfo")
    public List<MstCalendarInfoDto> selectCalendarInfo(@RequestBody Map<String,Object> map) {
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
    public MstMarketCalendarInfo001Dto getMstMarketCalendarInfo001DtoByKey(@RequestBody MstMarketCalendarInfo001Dto dto){
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
    @Transactional
    @DTXTransaction
    @PostMapping("/insertBtachEquityInfo")
    public void insertBtachEquityInfo(@RequestBody List<MstMarketCalendarInfo001Dto> list){
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
    @Transactional
    @DTXTransaction
    @PostMapping("/updateBtachEquityInfo")
    public void  updateBtachEquityInfo(@RequestBody List<MstMarketCalendarInfo001Dto> list){
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
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody MstMarketCalendarInfo001Dto entity) {
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


    /***
     * 第N个交易日查询
     * @param para
     * @return
     */
    @PostMapping("/queryTradeDate")
    public Map<String,Object> queryTradeDate(@RequestBody Map<String,Object> para){
        //日历类型
        String tradeCalendarType = (String)para.get("tradeCalendarType");
        //日期
        String cdate = (String)para.get("CDATE");
        //第N个交易日
        Integer nDay = (Integer)para.get("NDay");

        MstMarketCalendarInfo001Dto dto = new MstMarketCalendarInfo001Dto();
        if("3".equals(tradeCalendarType)) {
            dto.setCalendarId("04");
        } else if("4".equals(tradeCalendarType)) {
            dto.setCalendarId("05");
        }
        dto.setCdate(DateUtil.parse(cdate));
        String tradeDate =  mstMarketCalendarInfo001Service.queryTradeDate(dto,nDay);

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("tradeDate",tradeDate);
        return map;
    }
}
