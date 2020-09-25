/*
 * 文件名：TrdBondInvestTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Zane.Y
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.report.poc.directtransaction.uptrquotedsec.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.poc.directtransaction.uptrquotedsec.service.UpTrQuoTedSecService;

/**
 * 债券交易微服务
 *
 * @author Zane.Y
 * @version 1.0 2018年9月7日
 * @see TrdBondInvestTradeController
 * @since 1.0
 */
@RestController("backUpTrQuoTedSecController")
@RequestMapping("/UpTrQuoTedSec")
public class UpTrQuoTedSecController {
    /**
     * 注入service类
     */
    @Autowired
    private UpTrQuoTedSecService upTrQuoTedSecService;

    /**
     * 组合代码下拉框
     *
     * @param params 画面参数
     * @return 组合代码：下拉框
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getPortfolioId")
    public List<SelectOptionDto> getPortfolioId(@RequestBody Map<String, Object> param) {
        return upTrQuoTedSecService.getPortfolioId(param);
    }

    /**
     * 分页查询 市场代码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getFinprodMarketId")
    public List<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String, Object> param) {
        return upTrQuoTedSecService.getFinprodMarketId(param);
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
        return upTrQuoTedSecService.getCounterId(map);
    }
    
    /**
     * 托管账户信息
     * @param param 画面参数
     * @return 组合代码：下拉框
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getAcctNo")
    public List<SelectOptionDto> getAcctNo(@RequestBody Map<String, Object> param) {
        return upTrQuoTedSecService.getAcctNo(param);
    }
    /**
     * 资金账户信息
     * @param param 画面参数
     * @return 组合代码：下拉框
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getTrdAcctId")
    public List<SelectOptionDto> getTrdAcctId(@RequestBody Map<String, Object> param) {
        return upTrQuoTedSecService.getTrdAcctId(param);
    }
    
    /**
     * 
     * 获取新建界面的资产代码联想下拉框框
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @PostMapping("getfinprodIdForAdd")
    public List<SelectOptionDto> getfinprodIdForAdd(@RequestBody Map<String, Object> param)
        throws Exception {
        List<SelectOptionDto> temp = upTrQuoTedSecService.getfinprodIdForAdd(param);
        return temp;
    }
    
    /**
     * 
     * 资产名称联动
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @PostMapping("getFinprodName")
    public String getFinprodName(@RequestBody Map<String, Object> param){
        return upTrQuoTedSecService.getFinprodName(param);
    }
}
