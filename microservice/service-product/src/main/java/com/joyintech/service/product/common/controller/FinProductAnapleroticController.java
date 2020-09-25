/*
 * 文件名：FinProductAnapleroticController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2019年4月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.common.controller;

import java.util.List;
import java.util.Map;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.joyintech.service.product.common.dto.FinProductAnapleroticInfoDto;
import com.joyintech.service.product.common.dto.FinProductAnapleroticPlanDto;
import com.joyintech.service.product.common.service.FinProductAnapleroticService;

/**
 *  业绩回补业务组件
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年4月25日
 * @see FinProductAnapleroticController
 * @since 1.0
 */
@RestController("backFinProductAnapleroticController")
@RequestMapping("/FinProductAnaplerotic")
public class FinProductAnapleroticController {
    @Autowired
    private FinProductAnapleroticService finProductAnapleroticService;
    
    @PostMapping("selectForPage/{page}/{length}")
    public Page<FinProductAnapleroticInfoDto> selectForPage(@RequestBody Map<String, Object> param,
        @PathVariable("page") int page, @PathVariable("length") int length){
        return finProductAnapleroticService.selectForPage(page, length, param);
    }
    
    @PostMapping("queryAnapleroticInfoById")
    public List<FinProductAnapleroticInfoDto>  queryAnapleroticInfoById(@RequestBody Map<String,Object> param){
        return finProductAnapleroticService.queryAnapleroticInfoByIdForDetail(param);
    }
    
    @PostMapping("deleteByPrimaryKey")
    public void deleteByPrimaryKey(@RequestBody Map<String,Object> param){
        finProductAnapleroticService.deleteByPrimaryKey(param);
    }
    @PostMapping("updateAnapleroticInfo")
    public void updateAnapleroticInfo(@RequestBody FinProductAnapleroticInfoDto dto){
        finProductAnapleroticService.updateAnapleroticInfo(dto);
    }
    @PostMapping("insertAnapleroticInfo")
    public void insertAnapleroticInfo(@RequestBody FinProductAnapleroticInfoDto dto){
        finProductAnapleroticService.insertAnapleroticInfo(dto);
    }
    @PostMapping("queryProdIdInfo")
    public List<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String,Object> param){
       return finProductAnapleroticService.queryProdIdInfo(param);
    }
    @GetMapping("queryAnapAndProdInfo")
    public FinProductAnapleroticInfoDto queryAnapAndProdInfo(String finprodId, String dealMode){
        return finProductAnapleroticService.queryAnapAndProdInfo(finprodId, dealMode);
    }
    
    @GetMapping("queryAnapleroticInfoByFinprodId")
    public List<FinProductAnapleroticInfoDto> queryAnapleroticInfoByFinprodId(String finprodId){
        return finProductAnapleroticService.queryAnapleroticInfoByFinprodId(finprodId);
    }
    
    @PostMapping("/selectForlist")
    public List<FinProductAnapleroticInfoDto> selectForlist(@RequestBody Map<String, Object> param){
    	return finProductAnapleroticService.selectForlist(param);
    }
    
    @GetMapping("/selectAnapleroticPlan")
    public List<FinProductAnapleroticPlanDto> selectAnapleroticPlan(String anapleroticId){
    	return finProductAnapleroticService.selectAnapleroticPlan(anapleroticId);
    }
}