/*
 * 文件名：FinNCProductBuild002Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.finncproductbuild.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.finncproductbuild.dto.FinNCProductBuild001Dto;
import com.joyintech.service.valuation.finncproductbuild.service.FinNCProductBuild001Service;
import com.joyintech.service.valuation.finncproductbuild.service.FinNCProductBuild002Service;
import com.joyintech.tams.common.base.entity.BokBooksetExt;

/**
 * 新建、修改Controller
 * 
 * @author tanxuan
 * @version 2.0 2020年02月26日
 * @see FinNCProductBuild002Contoller
 * @since 2.0
 */
@RestController(value="serviceFinNCProductBuild002Contoller")
@RequestMapping("/FinNCProductBuild002")
public class FinNCProductBuild002Contoller extends BaseController {

    @Autowired
    private FinNCProductBuild001Service finNCProductBuild001Service;
    
    @Autowired
    private FinNCProductBuild002Service finNCProductBuild002Service;

    @PostMapping("/queryProductByfinprodId")
    public FinNCProductBuild001Dto queryProductByfinprodId(@RequestBody Map<String, Object> map) {
       return finNCProductBuild001Service.queryProductByfinprodId(map);
        
    }
    
    @PostMapping("/queryBooksetExt")
    public List<BokBooksetExt> queryBooksetExt(@RequestBody Map<String, Object> map){
    	return finNCProductBuild001Service.queryBooksetExt(map);
    }
    
    @PostMapping("/queryBooksetExtByKey")
    public List<BokBooksetExt> queryBooksetExtByKey(@RequestBody Map<String, Object> map){
    	return finNCProductBuild001Service.queryBooksetExtByKey(map);
    }
    
    /**
     * 母产品下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getpFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getpFinprodId(@RequestBody Map<String,Object> map,
    		@PathVariable("page") int page, @PathVariable("length") int length) {
        return finNCProductBuild002Service.getpFinprodId(page,length,map);
    }
    
    /**
     * 获取机构信息
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getCustomerId/{page}/{length}")
    public Page<SelectOptionDto> getCustomerId(@RequestBody Map<String,Object> map,
    		@PathVariable("page") int page, @PathVariable("length") int length) {
        return finNCProductBuild002Service.getCustomerId(page,length,map);
    }
    
    /**
     * 唯一性验证
     * <功能详细描述>
     * @param finprodId
     * @see
     * @since 1.0
     */
    @PostMapping("/findByFinprodId")
    public FinNCProductBuild001Dto findByFinprodId(@RequestBody String finprodId) {
       return finNCProductBuild002Service.findByFinprodId(finprodId);
        
    }
    
}
