/*
 * 文件名：FinPayInterestStdInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.controller;

import com.joyintech.service.asset.common.dto.FinPayInterestStdInfoDto;
import com.joyintech.service.asset.common.service.FinPayInterestStdInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  付息信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月8日
 * @see FinPayInterestStdInfoController
 * @since 1.0
 */
@RestController("backFinPayInterestStdInfoController")
@RequestMapping("/FinPayInterestStdInfo")
public class FinPayInterestStdInfoController {
    
    @Autowired
    private FinPayInterestStdInfoService finPayInterestStdInfoService;
    
    @GetMapping("getFinPayInterestStdInfoByProductId")
    public FinPayInterestStdInfoDto getFinInterestFactorByProductId(String finprodId,String vdate,String cashType,String finprodType) throws Exception {
    	return finPayInterestStdInfoService.getFinPayInterestStdInfoByProductId(finprodId, vdate,cashType,finprodType);
    }
}
