/*
 * 文件名：BussSubscriptionInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.FinSubscriptionInfo;
import com.joyintech.service.asset.common.service.BussSubscriptionInfoService;

/**
 *  认购信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月30日
 * @see BussSubscriptionInfoController
 * @since 1.0
 */
@RestController("backBussSubscriptionInfoController")
@RequestMapping("/BussSubscriptionInfo")
public class BussSubscriptionInfoController{

    @Autowired
    private BussSubscriptionInfoService bussSubscriptionInfoService;
    
    @GetMapping("getBussSubscriptionInfoByProductId")
    public List<FinSubscriptionInfo> getBussSubscriptionInfoByProductId(String finprodId){
        return bussSubscriptionInfoService.getBussSubscriptionInfoByProductId(finprodId);
    }
}
