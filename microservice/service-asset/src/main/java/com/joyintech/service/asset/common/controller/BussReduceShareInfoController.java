/*
 * 文件名：BussReduceShareInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.FinReduceShareInfo;
import com.joyintech.service.asset.common.service.BussReduceShareInfoService;

/**
 * 减持信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月29日
 * @see BussReduceShareInfoController
 * @since 1.0
 */
@RestController
@RequestMapping("/BussReduceShareInfo")
public class BussReduceShareInfoController {

    @Autowired
    private BussReduceShareInfoService bussReduceShareInfoService;
    
    @GetMapping("getBussReduceShareInfoByProductId")
    public List<FinReduceShareInfo> getBussReduceShareInfoByProductId(String finprodId){
        return bussReduceShareInfoService.getBussReduceShareInfoByProductId(finprodId);
    }
}
