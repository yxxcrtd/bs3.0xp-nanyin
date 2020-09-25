/*
 * 文件名：RepTradeRegistController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.reptraderegist.controller;

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
import com.joyintech.service.report.debtreport.reptraderegist.dto.RepTradeRegistDto;
import com.joyintech.service.report.debtreport.reptraderegist.service.RepTradeRegistService;

/**
 * 交易要素登记
 * @author fanshuanghu
 * @version 1.0 2018年12月28日
 * @see RepTradeRegistController
 * @since 1.0
 */
@RestController
@RequestMapping("/RepTradeRegist")
public class RepTradeRegistController {
    @Autowired
    private RepTradeRegistService repTradeRegistService;
    
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<RepTradeRegistDto> findPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return repTradeRegistService.findPage(page, length, param);
    }
    
    /**
     * 获取金融产品代码下拉框集合
     * @return 金融产品代码下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getProductCode")
    public List<SelectOptionDto> getProductCode(@RequestBody Map<String,Object> map){
        return repTradeRegistService.getProductCode(map);
    }
    /**
     * 获取金融产品代码下拉框集合
     * @return 金融产品代码下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getRepFinprodId")
    public List<SelectOptionDto> getRepFinprodId(@RequestBody Map<String,Object> map){
        return repTradeRegistService.getRepFinprodId(map);
    }
    /**
     * 获取行内交易编号下拉框集合
     * @return 行内交易编号下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getRepBankTradeCode")
    public List<SelectOptionDto> getRepBankTradeCode(@RequestBody Map<String,Object> map){
        return repTradeRegistService.getRepBankTradeCode(map);
    }
    /**
     * 获取资产代码下拉框集合
     * @return 资产代码下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getRepBankAssetCode")
    public List<SelectOptionDto> getRepBankAssetCode(@RequestBody Map<String,Object> map){
        return repTradeRegistService.getRepBankAssetCode(map);
    }
    /**
     * 获取金融产品登记编码下拉框集合
     * @return 金融产品登记编码下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getRepFinprodRegId")
    public List<SelectOptionDto> getRepFinprodRegId(@RequestBody Map<String,Object> map){
        return repTradeRegistService.getRepFinprodRegId(map);
    }
}
