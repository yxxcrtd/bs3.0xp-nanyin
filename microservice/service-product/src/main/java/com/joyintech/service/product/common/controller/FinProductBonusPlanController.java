/*
 * 文件名：FinProductBonusPlanController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.product.common.dto.BussFinProductBonusPlanDto;
import com.joyintech.service.product.common.service.FinProductBonusPlanService;

/**
 * 分红信息组件
 * 
 * @author zhangliang
 * @version 1.0 2018年10月25日
 * @see FinProductBonusPlanController
 * @since 1.0
 */
@RestController("backFinProductBonusPlanController")
@RequestMapping("/FinProductBonusPlan")
public class FinProductBonusPlanController {
    
    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductBonusPlanService finProductBonusPlanService;

    /**
     * 查询分红信息
     * @param finprodId 金融产品代码
     * @param effDate 生效日期
     * @return 分红信息
     */
    @GetMapping("/queryBonusPlanInfoByFinprodId")
    public BussFinProductBonusPlanDto queryBonusPlanInfoByFinprodId(String finprodId, String effDate){
        return finProductBonusPlanService.queryBonusPlanInfoByFinprodId(finprodId, effDate);
    }
}
