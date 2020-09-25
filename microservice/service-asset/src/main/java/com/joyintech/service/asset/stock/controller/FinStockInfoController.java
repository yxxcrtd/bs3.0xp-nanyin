/*
 * 文件名：FinDepositTargetController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.stock.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.stock.dto.FinStockInfoDto;
import com.joyintech.service.asset.stock.service.FinStockInfoService;

/**
 * 股票标的信息
 * @author yegang
 * @version 1.0 2019年6月5日
 * @see FinStockInfoController
 * @since 1.0
 */
@RestController("FinStockInfo_dtx")
@RequestMapping("/FinStockInfo")
public class FinStockInfoController {

    
    @Autowired
    private FinStockInfoService finStockInfoService;
    /**
     * 查询
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<FinStockInfoDto> selectAll(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finStockInfoService.selectForPage(page, length, param);
    }
    @PostMapping("getShares")
    public FinStockInfoDto getShares(@RequestBody Map<String,Object> Param)
            throws Exception {
        return finStockInfoService.getShares(Param);
    }
    /**
     *
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto EtrBrowDepoInfoDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doInsert")
    public void doInsert(@RequestBody FinStockInfoDto dto) throws IllegalAccessException, InvocationTargetException {
        finStockInfoService.doInsert(dto);
    }
    /**
     * 查询资产代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("/getFinprodMarketIdList")
    public List<SuggestDto> getFinprodMarketIdList(String finprodMarketId)
        throws Exception {
        return finStockInfoService.getFinprodMarketIdList(finprodMarketId);
    }
    
    /**
     * 插入
     * @param dto
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody FinStockInfoDto dto) throws Exception {
         finStockInfoService.insert(dto);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody FinStockInfoDto dto) throws Exception{
        finStockInfoService.update(dto);
    }
    /**
     * 删除
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public void delete(@RequestBody FinStockInfoDto dto) throws Exception{
        finStockInfoService.delete(dto);
    }

    /**
     *
     * 通过金融产品代码查询dto
     * <功能详细描述>
     * @param finprodId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("selectByFinprodId")
    public FinStockInfoDto selectByFinprodId(String finprodId)
        throws Exception {
        return finStockInfoService.selectByFinprodId(finprodId);
    }
    /**
     * 交易日历下拉框
     * @param tradeMarket
     * @param //counterType
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getCalendarId")
    public List<SuggestDto> getCalendarId(String tradeMarket){
        return finStockInfoService.getCalendarId( tradeMarket);
    }
    /**
     * 通过市场代码查询dto
     * 资产代码重复Check
     * <功能详细描述>
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findEntity")
    public FinStockInfoDto findEntity(@RequestBody Map<String,Object> param)
        throws Exception {
        return finStockInfoService.findEntity(param);
    }
    /**
     * 市场机构
     * @param param
     * @param isTradeCalendar
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getCustomerName")
    public List<SuggestDto> getCustomerName(String param){
        return finStockInfoService.getCustomerName(param);
    }
    
    /**
     * 获取当前资产是否有用户编辑 如果有 返回用户
     * @param param
     * @param 
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getIsInWorkFlowReturnUser")
    public String getIsInWorkFlowReturnUser(@RequestBody Map<String,Object> param){
        return finStockInfoService.getIsInWorkFlowReturnUser(param);
    }

}
