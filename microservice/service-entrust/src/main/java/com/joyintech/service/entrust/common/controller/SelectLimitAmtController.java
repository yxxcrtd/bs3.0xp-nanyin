/*
 * 文件名：SelectLimitAmtController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年12月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.common.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.entrust.common.service.SelectLimitAmtService;

/**
 * 查询授权额度
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年12月23日
 * @see SelectLimitAmtController
 * @since 1.0
 */
@RestController
@RequestMapping("/common/SelectLimitAmt")
public class SelectLimitAmtController {

    @Autowired
    private SelectLimitAmtService selectLimitAmtService;
    
    /**
     * 
     * 查询授权额度
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectLimitAmt")
    public BigDecimal selectLimitAmt(@RequestBody Map<String, Object> map){
        return selectLimitAmtService.selectLimitAmt(map);
    }
}
