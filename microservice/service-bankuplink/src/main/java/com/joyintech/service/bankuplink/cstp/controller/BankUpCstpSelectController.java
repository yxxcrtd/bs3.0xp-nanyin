/*
 * 文件名：CSTPSelect.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.bankuplink.cstp.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.bankuplink.cstp.dto.CstpSelectDto;
import com.joyintech.service.bankuplink.cstp.service.BankUpCstpSelectService;

/**
 * CSTP下行交易查询
 * @author fanshuanghu
 * @version 1.0 2018年12月3日
 * @see CSTPSelect
 * @since 1.0
 */
@RestController
@RequestMapping("/BankUpCSTPSelect")
public class BankUpCstpSelectController {
    /**
     * 注入Service
     */
    @Autowired
    private BankUpCstpSelectService cstpSelectService;
    
    /**
     * 分页查询
     * @param map 查询
     * @return 分页查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @PostMapping("findPage/{page}/{length}")
    public Page<CstpSelectDto> findPage(@RequestBody Map<String,Object> map, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return cstpSelectService.findPage(page, length, map);
    }
    
    /**
     * 分页查询
     * @param map 查询
     * @return 分页查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @PostMapping("getCstpQryTableList")
    public List<CstpSelectDto> getCstpQryTableList(@RequestBody Map<String,Object> map) throws Exception{
        return cstpSelectService.getCstpQryTableList(map);
    }
    
    /**
     * 获取组合代码下拉框实体类
     * @param map 查询参数
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @PostMapping("getPortfolioId")
    public List<SelectOptionDto> getPortfolioId(@RequestBody Map<String,Object> map){
        return cstpSelectService.getPortfolioId(map);
    }
    /**
     * 获取成交编号下拉框实体类
     * @param map 查询参数
     * @return 下拉框集合
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @PostMapping("getExecid")
    public List<SuggestDto> getExecid(@RequestBody Map<String,Object> map){
        return cstpSelectService.getExecid(map);
    }
    /**
     * 获取资产代码下拉框
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @PostMapping("getFinprodId")
    public List<SuggestDto> getFinprodId(@RequestBody Map<String,Object> map){
        return cstpSelectService.getFinprodId(map);
    }
}
