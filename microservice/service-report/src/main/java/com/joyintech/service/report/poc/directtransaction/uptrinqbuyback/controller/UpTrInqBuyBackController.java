/*
 * 文件名：TrdBondInvestTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Zane.Y
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.report.poc.directtransaction.uptrinqbuyback.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.poc.directtransaction.uptrinqbuyback.dto.UpTrInqBuyBackDto;
import com.joyintech.service.report.poc.directtransaction.uptrinqbuyback.service.UpTrInqBuyBackService;

/**
 * 债券交易微服务
 *
 * @author Zane.Y
 * @version 1.0 2018年9月7日
 * @see TrdBondInvestTradeController
 * @since 1.0
 */
@RestController("backUpTrInqBuyBackController")
@RequestMapping("/UpTrInqBuyBack")
public class UpTrInqBuyBackController {
    /**
     * 注入service类
     */
    @Autowired
    private UpTrInqBuyBackService upTrInqBuyBackService;

    /**
     * 组合代码下拉框
     *
     * @param param 画面参数
     * @return 组合代码：下拉框
     * @see
     * @since 1.0
     */
    @RequestMapping("getPortfolioId")
    public List<SelectOptionDto> getPortfolioId(@RequestBody Map<String, Object> param) {
        return upTrInqBuyBackService.getPortfolioId(param);
    }

    /**
     * 分页查询 市场代码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getFinprodMarketId")
    public List<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String, Object> param) {
        return upTrInqBuyBackService.getFinprodMarketId(param);
    }
    
    /**
     * 
     * 查询交易对手
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getCounterId")
    public List<SelectOptionDto> getCounterId(@RequestBody Map<String, Object> map){
        return upTrInqBuyBackService.getCounterId(map);
    }
    
   /**
    * 通过主键查询委托信息
    * @param entrustId
    * @return
    * @throws IllegalAccessException
    * @throws InvocationTargetException
    */
    @GetMapping("selectByPrimryKey")
    public UpTrInqBuyBackDto selectByPrimryKey(String entrustId)
        throws IllegalAccessException, InvocationTargetException {
        return upTrInqBuyBackService.selectByPrimryKey(entrustId);
    }
}
