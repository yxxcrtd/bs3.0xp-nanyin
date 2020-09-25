/*
 * 文件名：BussRepAssetAstBillInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.asset.common.dto.BussRepAssetAstBillInfoDto;
import com.joyintech.service.asset.common.service.BussRepAssetAstBillInfoService;

/**
 * 非标资产(票据或信用)报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月6日
 * @see BussRepAssetAstBillInfoController
 * @since 1.0
 */
@RestController
@RequestMapping("/BussRepAssetAstBillInfo")
public class BussRepAssetAstBillInfoController {

    @Autowired
    private BussRepAssetAstBillInfoService bussRepAssetAstBillInfoService;
    
    @GetMapping("getBussRepAssetAstBillInfoByProductId")
    public BussRepAssetAstBillInfoDto getBussRepAssetAstBillInfoByProductId(String finprodId){
        return bussRepAssetAstBillInfoService.getBussRepAssetAstBillInfoByProductId(finprodId);
    }
}
