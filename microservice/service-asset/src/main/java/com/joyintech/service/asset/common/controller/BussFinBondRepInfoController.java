/*
 * 文件名：BussFinBondRepInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.asset.common.dto.BussFinBondRepInfoDto;
import com.joyintech.service.asset.common.service.BussFinBondRepInfoService;

/**
 *  债券类中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月23日
 * @see BussFinBondRepInfoController
 * @since 1.0
 */
@RestController("backBussFinBondRepInfoController")
@RequestMapping("/BussFinBondRepInfo")
public class BussFinBondRepInfoController {

    @Autowired
    private BussFinBondRepInfoService bussFinBondRepInfoService;


    
    @GetMapping("getBussFinBondRepInfoByProductId")
    public BussFinBondRepInfoDto getBussFinBondRepInfoByProductId(String finprodId){
        return bussFinBondRepInfoService.getBussFinBondRepInfoByProductId(finprodId);
    }
    
}
