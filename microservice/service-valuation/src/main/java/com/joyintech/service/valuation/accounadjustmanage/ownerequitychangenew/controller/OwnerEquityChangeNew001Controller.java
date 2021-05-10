/*
 * 文件名：OwnerEquityChangeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.ownerequitychangenew.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.accounadjustmanage.ownerequitychangenew.dto.OwnerEquityChangeNew001Dto;
import com.joyintech.service.valuation.accounadjustmanage.ownerequitychangenew.service.OwnerEquityChangeNew001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 所有者权益变动Controlle
 * 微服务
 * @author lby
 * @version 1.0 2020年11月24日
 * @see OwnerEquityChangeNew001Controller
 * @since 1.0
 */
@RestController(value="serviceOwnerEquityChangeNew001Controller")
@RequestMapping("OwnerEquityChangeNew001")
public class OwnerEquityChangeNew001Controller extends BaseController {

    @Autowired
    private OwnerEquityChangeNew001Service ownerEquityChangeNew001Service;

    @PostMapping("/selectBegin")
    public List<OwnerEquityChangeNew001Dto> selectBegin(@RequestBody Map<String, Object> param) {
        return ownerEquityChangeNew001Service.selectBegin(param);
    }

    @PostMapping("/selectAll")
    public List<OwnerEquityChangeNew001Dto> selectAll(@RequestBody Map<String, Object> param) {
        return ownerEquityChangeNew001Service.selectAll(param);
    }

}
