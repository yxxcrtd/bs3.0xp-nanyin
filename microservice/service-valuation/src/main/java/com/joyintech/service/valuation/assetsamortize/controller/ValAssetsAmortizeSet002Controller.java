/*
 * 文件名：ValIncomeStatisticsQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.assetsamortize.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.grid.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.assetsamortize.dto.ValAssetsAmortizeSet001Dto;
import com.joyintech.service.valuation.assetsamortize.service.ValAssetsAmortizeSet002Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 资产摊销设置
 * 
 * @author HuangJian
 * @version 1.0 2018年12月4日
 * @see ValAssetsAmortizeSet002Controller
 * @since 1.0
 */
@RestController("valAssetsAmortizeSet002Ctl_dtx")
//@RestController(value="serviceValAssetsAmortizeSet002Controller")
@RequestMapping("/ValAssetsAmortizeSet002")
public class ValAssetsAmortizeSet002Controller extends BaseController {


    /**
     * valAssetsAmortizeSet002Service
     */
    @Autowired
    private ValAssetsAmortizeSet002Service valAssetsAmortizeSet002Service;

   

    /**
     * 新建表格初始化 不要求分页
     * 
     * @return List<ValAssetsAmortizeSet001Dto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findAll")
    public List<ValAssetsAmortizeSet001Dto> findAll(@RequestBody Map<String,Object> map){
        return valAssetsAmortizeSet002Service.findAll(map);
    }
    /**
     * 表格 下拉框加载
     * 
     * @return List<SelectOptionDto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @PostMapping("/getRecalType")
    public List<SelectOptionDto> getRecalType()
        throws Exception {
        return valAssetsAmortizeSet002Service.getRecalType();
    }
    
    
    @PostMapping("checkBokAmoConfig")
    public boolean checkBokAmoConfig(@RequestBody ValAssetsAmortizeSet001Dto dto) {
    	return valAssetsAmortizeSet002Service.checkBokAmoConfig(dto);
    }
    
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody ValAssetsAmortizeSet001Dto dto) {
    	 return valAssetsAmortizeSet002Service.doComplete(dto,OperationType.NEW);
    }

    @PostMapping("/getFinprodId2/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId2(@RequestBody Map<String, Object> pageParam,
                                               @PathVariable("page") int page, @PathVariable("length") int length) {
        return valAssetsAmortizeSet002Service.getFinprodId2(page,length,pageParam);
    }

}
