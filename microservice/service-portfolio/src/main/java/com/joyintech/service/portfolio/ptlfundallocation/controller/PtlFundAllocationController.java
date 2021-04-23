/*
 * 文件名：PtlFundAllocationController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年1月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlfundallocation.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlfundallocation.dto.PtlFundAllocationDto;
import com.joyintech.service.portfolio.ptlfundallocation.service.PtlFundAllocationService;

/**
 * 资金划拨微服务
 * @author fanshuanghu
 * @version 1.0 2019年1月29日
 * @see PtlFundAllocationController
 * @since 1.0
 */
@RestController("ptlFundAllocationController_dtx")
@RequestMapping("/PtlFundAllocation")
public class PtlFundAllocationController {
    /**
     * 注入Service
     */
    @Autowired
    private PtlFundAllocationService ptlFundAllocationService;
    
    /**
     * 获取账号
     * @param map 请求参数
     * @return 账号下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getAcct")
    public List<SuggestDto> getAcct(@RequestBody Map<String,Object> map){
        return ptlFundAllocationService.getAcct(map);
    }
    
    /**
     * 查询组合（资金划拨管理）
     * @param map 请求参数
     * @return 组合集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getPortfolioList")
    public List<SelectOptionDto> getPortfolioList(@RequestBody Map<String,Object> map){
        return ptlFundAllocationService.getPortfolioList(map);
    }
    
    /**
     * 查询组合
     * @param map 请求参数
     * @return 组合集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getPortfolioId")
    public List<SelectOptionDto> getPortfolioId(@RequestBody Map<String,Object> map){
        return ptlFundAllocationService.getPortfolioId(map);
    }
    
    /**
     * 查询收款账号
     * @param map 请求参数
     * @return 收款账号集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getCounterAcctNo")
    public List<SelectOptionDto> getCounterAcctNo(@RequestBody Map<String,Object> map){
        return ptlFundAllocationService.getCounterAcctNo(map);
    }
    
    /**
     * 查询付款账号
     * @param map 请求参数
     * @return 付款账号集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getMyAcctNo")
    public List<SelectOptionDto> getMyAcctNo(@RequestBody Map<String,Object> map){
        return ptlFundAllocationService.getMyAcctNo(map);
    }
    /**
     * 获取现金余额
     * @param map 请求参数
     * @return 现金余额
     * @see
     * @since 1.0
     */
    @PostMapping("/getAmount")
    public BigDecimal getAmount(@RequestBody Map<String,Object> map){
        return ptlFundAllocationService.getAmount(map);
    }
    
    /**
     * 画面查询方法
     * @param param 请求参数
     * @param page 页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<PtlFundAllocationDto> selectAll(@RequestBody Map<String, Object> param, 
                                                @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return ptlFundAllocationService.findPage(page,length,param);
    }
    /**
     * 代办处理
     * @param entity 资金划拨实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    @RequestMapping("/doComplete")
    @Transactional
    @DTXTransaction
    public int doComplete(@RequestBody PtlFundAllocationDto entity){
        if(StringUtil.equals(entity.getOperateType(), "NEW")){
            return ptlFundAllocationService.insertData(entity);
        }
        else{
            return ptlFundAllocationService.revokeData(entity);
        }
    }

    @RequestMapping("/doInsert")
    @Transactional
    @DTXTransaction
    public String doInsert(@RequestBody PtlFundAllocationDto entity) {
        return ptlFundAllocationService.doInsert(entity);
    }

    @RequestMapping("/doInsertAction")
    @Transactional
    @DTXTransaction
    public String doInsertAction(@RequestBody PtlFundAllocationDto entity) {
        return ptlFundAllocationService.doInsertAction(entity);
    }

    @RequestMapping("/doDelete")
    @Transactional
    @DTXTransaction
    public int doDelete(@RequestBody String orderId) {
        return ptlFundAllocationService.doDelete(orderId);
    }
    
    @RequestMapping("/doBack")
    @Transactional
    @DTXTransaction
    public String doBack(@RequestBody PtlFundAllocationDto entity) {
        return ptlFundAllocationService.doBack(entity);
    }
}
