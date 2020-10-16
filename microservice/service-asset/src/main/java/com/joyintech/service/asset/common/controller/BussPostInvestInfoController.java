/*
 * 文件名：BussPostInvestInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.FinPostInvestInfo;
import com.joyintech.service.asset.common.service.BussPostInvestInfoService;

/**
 *  投后信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月29日
 * @see BussPostInvestInfoController
 * @since 1.0
 */
@RestController("backBussPostInvestInfoController")
@RequestMapping("/BussPostInvestInfo")
public class BussPostInvestInfoController {

    @Autowired
    private BussPostInvestInfoService bussPostInvestInfoService;
    
    /**
     * 初始化
     * <功能详细描述>
     * @param finprodId
     * @param vdate
     * @param cashType
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getBussPostInvestInfoByProductId")
    public FinPostInvestInfo getBussPostInvestInfoByProductId(String finprodId,String vdate,String cashType) throws Exception {
        return bussPostInvestInfoService.getBussPostInvestInfoByProductId(finprodId);
    }
}
