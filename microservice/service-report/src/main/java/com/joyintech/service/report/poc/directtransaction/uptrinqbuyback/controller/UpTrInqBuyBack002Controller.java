/*
 * 文件名：UpTrInqBuyBack002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年2月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.poc.directtransaction.uptrinqbuyback.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.poc.directtransaction.uptrinqbuyback.service.UpTrInqBuyBack002Service;

/**
 * 询价交易-质押式回购
 * @author wanghb
 * @version 1.0 2019年2月15日
 * @see UpTrInqBuyBack002Controller
 * @since 1.0
 */
@RestController("backUpTrInqBuyBack002Controller")
@RequestMapping("/UpTrInqBuyBack002")
public class UpTrInqBuyBack002Controller {
    /**
     * 注入service类
     */
    @Autowired
    private UpTrInqBuyBack002Service upTrInqBuyBack002Service;
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
        return upTrInqBuyBack002Service.getCounterId(map);
    }
    /**
     * 组合代码下拉框
     *
     * @param params 画面参数
     * @return 组合代码：下拉框
     * @see
     * @since 1.0
     */
    @RequestMapping("getPortfolioId")
    public List<SelectOptionDto> getPortfolioId(@RequestBody Map<String, Object> param) {
        return upTrInqBuyBack002Service.getPortfolioId(param);
    }
    /**
     * 托管账户信息
     * @param params 画面参数
     * @return 组合代码：下拉框
     * @see
     * @since 1.0
     */
    @RequestMapping("getAcctNo")
    public List<SelectOptionDto> getAcctNo(@RequestBody Map<String, Object> param) {
        return upTrInqBuyBack002Service.getAcctNo(param);
    }
    /**
     * 资金账户信息
     * @param params 画面参数
     * @return 组合代码：下拉框
     * @see
     * @since 1.0
     */
    @RequestMapping("getTrdAcctId")
    public List<SelectOptionDto> getTrdAcctId(@RequestBody Map<String, Object> param) {
        return upTrInqBuyBack002Service.getTrdAcctId(param);
    }
}
