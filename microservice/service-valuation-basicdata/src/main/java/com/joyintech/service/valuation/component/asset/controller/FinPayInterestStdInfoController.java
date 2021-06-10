/*
 * 文件名：FinPayInterestStdInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.component.asset.controller;

import com.joyintech.service.valuation.component.asset.dto.FinCashPlanDto;
import com.joyintech.service.valuation.component.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.service.valuation.component.asset.service.FinPayInterestStdInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  付息信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月8日
 * @see FinPayInterestStdInfoController
 * @since 1.0
 */
@RestController(value = "serviceFinPayInterestStdInfoController")
@RequestMapping("/FinPayInterestStdInfo")
public class FinPayInterestStdInfoController {

    @Autowired
    private FinPayInterestStdInfoService finPayInterestStdInfoService;

    @GetMapping("getFinPayInterestStdInfoByProductId")
    public FinPayInterestStdInfoDto getFinInterestFactorByProductId(String finprodId, String vdate, String cashType, String finprodType) throws Exception {
    	return finPayInterestStdInfoService.getFinPayInterestStdInfoByProductId(finprodId, vdate,cashType,finprodType);
    }

    @GetMapping("getFinCashPlanByProductId")
    public List<FinCashPlanDto> getFinCashPlanByProductId(String finprodId, String cashType, String finCashBusinessType, String finprodType) throws Exception {
        return finPayInterestStdInfoService.getFinCashPlanByProductId(finprodId,cashType,finCashBusinessType, finprodType);
    }
}
