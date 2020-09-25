/*
 * 文件名：FinProductFloatFeeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.common.controller;

import java.util.List;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleKey;
import com.joyintech.service.product.common.dto.FinProductFloatFeeDto;
import com.joyintech.service.product.common.service.FinProductFloatFeeService;

/**
 * 浮动管理费组件
 * 
 * @author wangzehui
 * @version 1.0 2018年10月9日
 * @see FinProductFeeInfoController
 * @since 1.0
 */
@RestController("backFinProductFloatFeeController_dtx")
@RequestMapping("/FinProductFloatFee")
public class FinProductFloatFeeController {

    @Autowired(required = false)
    private FinProductFloatFeeService finProductFloatFeeService;
    @Transactional
    @DTXTransaction
    @GetMapping("/delete")
    public int delete(String cashId) {
        return finProductFloatFeeService.delete(cashId);
    }
    @Transactional
    @DTXTransaction
    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(@RequestBody FinFloatCalcRuleKey key) {
        return finProductFloatFeeService.deleteByPrimaryKey(key);
    }
    
    /**
     * 查询浮动管理费信息
     * @param finprodId 金融产品代码
     * @param effDate 生效日期
     * @return 浮动管理费信息
     */
    @GetMapping("/queryFloatFeeDetailByFinprodId")
    public List<FinFloatCalcRuleDetail> queryFloatFeeDetailByFinprodId(String finprodId, String effDate){
        return finProductFloatFeeService.queryFloatFeeDetailByFinprodId(finprodId, effDate);
    }
    
    /**
     * 查询浮动管理费信息
     * @param finprodId 金融产品代码
     * @param effDate 生效日期
     * @return 浮动管理费信息
     */
    @GetMapping("/queryFloatFeeInfoByFinprodId")
    public FinProductFloatFeeDto queryFloatFeeInfoByFinprodId(String finprodId, String effDate){
        return finProductFloatFeeService.queryFloatFeeInfoByFinprodId(finprodId, effDate);
    }
    /**
     * 查询浮动管理费信息
     * @param finprodId 金融产品代码
     * @param effDate 生效日期
     * @return 浮动管理费信息
     */
    @GetMapping("/queryLayerFloatFeeInfoByFinprodId")
    public List<FinProductFloatFeeDto> queryLayerFloatFeeInfoByFinprodId(String finprodId, String effDate){
        return finProductFloatFeeService.queryLayerFloatFeeInfoByFinprodId(finprodId, effDate);
    }
}
