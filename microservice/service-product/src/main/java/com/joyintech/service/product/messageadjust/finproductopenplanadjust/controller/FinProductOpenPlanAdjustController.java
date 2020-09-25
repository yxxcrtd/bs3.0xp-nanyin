/*
 * 文件名：FinProductOpenPlanController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finproductopenplanadjust.controller;

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
import com.joyintech.service.product.messageadjust.finproductopenplanadjust.dto.FinProductOpenPlanAdjustDto;
import com.joyintech.service.product.messageadjust.finproductopenplanadjust.service.FinProductOpenPlanAdjustService;

/**
 * 信息调整管理_开放计划查询
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月17日
 * @see FinProductOpenPlanAdjustController
 * @since 1.0
 */
@RestController("backFinProductOpenPlanAdjustController")
@RequestMapping("/FinProductOpenPlanAdjust")
public class FinProductOpenPlanAdjustController {
    
    /**
     * 开放计划查询service
     */
    @Autowired
    private FinProductOpenPlanAdjustService finProductOpenPlanService;
    
    /**
     * 产品代码联想框 
     * @param param
     * @return
     */
    @PostMapping("getFinprodId")
    public List<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param) {
        return finProductOpenPlanService.getFinprodId(param);
    }
    
    /**
     * 
     * 开放计划查询
     * <功能详细描述>
     * @param param Map<String, Object>
     * @param page int
     * @param length int
     * @return Page<FinProductOpenPlanAdjustDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<FinProductOpenPlanAdjustDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception{
        return finProductOpenPlanService.selectForPage(page, length, param);
    }
}
