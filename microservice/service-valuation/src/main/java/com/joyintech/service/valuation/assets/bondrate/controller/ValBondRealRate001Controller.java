/*
 * 文件名：ValBondRealRate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.assets.bondrate.controller;

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
import com.joyintech.service.valuation.assets.bondrate.dto.ValBondRealRate001Dto;
import com.joyintech.service.valuation.assets.bondrate.service.ValBondRealRate001Service;

/**
 * 债券实际利率
 * 
 * @author HuangJian
 * @version 1.0 2019年1月19日
 * @see ValBondRealRate001Controller
 * @since 1.0
 */
@RestController(value="serviceValBondRealRate001Controller")
@RequestMapping("/ValBondRealRate001")
public class ValBondRealRate001Controller extends BaseController {

    /**
     * ValBondRealRate001Service
     */
    @Autowired
    private ValBondRealRate001Service valBondRealRate001Service;


    /**
     * 表格查询
     * 
     * @return Page<ValBondRealRate001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ValBondRealRate001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
//        Map<String, Object> reqMap = RequestUtil.getPageParam();
        return valBondRealRate001Service.findPage(page, length, param);
    }
    
    
    @PostMapping("findAll")
    public List<ValBondRealRate001Dto> findAll(@RequestBody Map<String, Object> param)
        throws Exception {
//        Map<String, Object> reqMap = RequestUtil.getPageParam();
        return valBondRealRate001Service.findAll(param);
    }

    /**
     * 账套获取
     * 
     * @param request HttpServletRequest
     * @return Page<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("getBooksetId/{page}/{length}")
    public Page<SelectOptionDto> getBooksetId(@RequestBody Map<String,Object> map,
			@PathVariable("page") int page, @PathVariable("length") int length) {
        return valBondRealRate001Service.getBooksetId(page,length,map);
    }

    /**
     * 债券获取
     * 
     * @param request HttpServletRequest
     * @return Page<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("getBondId/{page}/{length}")
    public Page<SelectOptionDto> getBondId(@RequestBody Map<String,Object> map,
			@PathVariable("page") int page, @PathVariable("length") int length) {
        return valBondRealRate001Service.getBondId(page,length,map);
    }

}
