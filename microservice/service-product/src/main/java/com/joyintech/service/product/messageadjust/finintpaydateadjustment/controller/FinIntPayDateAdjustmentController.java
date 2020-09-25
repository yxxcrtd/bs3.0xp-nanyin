/*
 * 文件名：FinIntPayDateAdjustmentController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finintpaydateadjustment.controller;

import java.lang.reflect.InvocationTargetException;
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

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.messageadjust.finintpaydateadjustment.dto.FinIntPayDateAdjustmentDto;
import com.joyintech.service.product.messageadjust.finintpaydateadjustment.service.FinIntPayDateAdjustmentService;

/**
 * 信息调整管理_付息日调整 
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月23日
 * @see FinIntPayDateAdjustmentController
 * @since 1.0
 */
@RestController("backFinIntPayDateAdjustmentController_dtx")
@RequestMapping("/FinIntPayDateAdjustment")
public class FinIntPayDateAdjustmentController {
    
    /**
     * 付息日调整servcie
     */
    @Autowired
    private FinIntPayDateAdjustmentService finIntPayDateAdjustmentService;
    
    /**
     * 
     * 产品代码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getCashId")
    public List<SelectOptionDto> getCashId(@RequestBody Map<String, Object> param){
        return finIntPayDateAdjustmentService.getCashId(param);
    }
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param param Map<String, Object>
     * @param page int
     * @param length int
     * @return Page<FinIntPayDateAdjustmentDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("selectForPage")
    public List<FinIntPayDateAdjustmentDto> selectForPage(@RequestBody Map<String, Object> param)
            throws Exception{
        return finIntPayDateAdjustmentService.selectForPage(param);
    }
    
    /**
     * 
     * 获取付息日计划
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return List<FinIntPayDateAdjustmentDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForList")
    public List<FinIntPayDateAdjustmentDto> selectForList(@RequestBody Map<String, Object> pageParam){
        return finIntPayDateAdjustmentService.selectForList(pageParam);
    }
    
    /**
     * 
     * 工作流完成操作
     * <功能详细描述>
     * @param map 业务实体
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete")
    public void doComplete(@RequestBody FinIntPayDateAdjustmentDto dto) throws IllegalAccessException, InvocationTargetException {
        finIntPayDateAdjustmentService.doComplete(dto);
    }
    
    /**
     * 
     * 获取到期日与起息日
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getVdateMdate")
    public FinProduct getVdateMdate(@RequestBody Map<String, Object> param){
        return finIntPayDateAdjustmentService.getVdateMdate(param);
    }

    /**
     * 
     * 获取产品是否永续
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/queryProductIsSus")
    public String queryProductIsSus(@RequestBody Map<String, Object> param){
        return finIntPayDateAdjustmentService.queryProductIsSus(param);
    }
}
