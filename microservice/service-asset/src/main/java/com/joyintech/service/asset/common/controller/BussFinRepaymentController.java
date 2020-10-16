/*
 * 文件名：BussFinRepaymentController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.service.asset.common.service.BussFinRepaymentService;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;

/**
 *  还本信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月14日
 * @see BussFinRepaymentController
 * @since 1.0
 */
@RestController("backBussFinRepaymentController")
@RequestMapping("/BussFinRepayment")
public class BussFinRepaymentController {

    @Autowired
    private BussFinRepaymentService bussFinRepaymentService;
    
    /**
     * 初始化还本计划
     * <功能详细描述>
     * @param finprodId
     * @param cashType
     * @param finprodType--业务类型(金融产品、资金帐号)
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getFinCashPlanByProductId")
    public List<FinCashPlanDto> getFinCashPlanByProductId(String finprodId,String cashType,String finprodType,String finprodType2){
        return bussFinRepaymentService.getFinCashPlanByProductId(finprodId,cashType,finprodType,finprodType2);
    }
    
    /**
     * 查询资产付息营业日准则
     *
     * @param paramMap 参数
     * @return 资产付息营业日准则
     * @see
     * @since 1.0
     */
    @PostMapping("selectAssetPayTdayRule")
    public String selectAssetPayTdayRule(@RequestBody  Map<String, Object> paramMap) {
        return bussFinRepaymentService.selectAssetPayTdayRule(paramMap);
    }
}
