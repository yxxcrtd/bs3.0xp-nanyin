/*
 * 文件名：BussRepDlRepoInfoController.java
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

import com.joyintech.service.asset.common.dto.BussRepDlRepoInfoDto;
import com.joyintech.service.asset.common.service.BussRepDlRepoInfoService;

/**
 * 拆放同业及买入返售报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月6日
 * @see BussRepDlRepoInfoController
 * @since 1.0
 */
@RestController
@RequestMapping("/BussRepDlRepoInfo")
public class BussRepDlRepoInfoController {
    @Autowired
    private BussRepDlRepoInfoService bussRepDlRepoInfoService;
    
    @GetMapping("getBussRepDlRepoInfoByProductId")
    public BussRepDlRepoInfoDto getBussRepDlRepoInfoByProductId(String finprodId){
        return bussRepDlRepoInfoService.getBussRepDlRepoInfoByProductId(finprodId);
    }
}
