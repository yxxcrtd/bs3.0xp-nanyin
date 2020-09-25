/*
 * 文件名：TrdAvailableCashQueryController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年6月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.common.select.controller;

import com.joyintech.service.common.select.service.CommonTradeInfoService;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/CommonTradeInfo")
public class CommonTradeInfoController {

    @Autowired
    private CommonTradeInfoService commonTradeInfoService;

    @GetMapping("getFinCashPlanByProductId")
    public List<FinCashPlanDto> getFinCashPlanByProductId(String finprodId, String cashType, String finprodType, String finprodType2){
        return commonTradeInfoService.getFinCashPlanByProductId(finprodId,cashType,finprodType, finprodType2);
    }
}
