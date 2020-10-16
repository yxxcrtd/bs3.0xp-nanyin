/*
 * 文件名：FinInterestFactorController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.asset.common.dto.FinInterestFactorDto;
import com.joyintech.service.asset.common.service.FinInterestFactorService;

/**
 * 计息要素
 * @author zhangnian
 * @version 1.0 2018年9月26日
 * @see FinInterestFactorController
 * @since 1.0
 */
@RestController("backFinInterestFactorController")
@RequestMapping("/FinInterestFactor")
public class FinInterestFactorController {
        
    @Autowired
    private FinInterestFactorService finInterestFactorService;
    
    /**
     * 修改初始化
     * <功能详细描述>
     * @param finprodId
     * @param vdate
     * @param cashType
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getFinInterestFactorByProductId")
    public FinInterestFactorDto getFinInterestFactorByProductId(String finprodId,String vdate,String cashType) throws Exception {
        return finInterestFactorService.getFinInterestFactorByProductId(finprodId,vdate,cashType);
    }
}
