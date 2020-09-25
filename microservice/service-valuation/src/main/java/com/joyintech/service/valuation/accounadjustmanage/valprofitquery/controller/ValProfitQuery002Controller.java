/*
 * 文件名：ValProfitQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valprofitquery.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto.ValProfitQuery001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.service.ValProfitQuery002Service;

/**
 * 利润表-明细列表
 * 
 * @author wanghb
 * @version 1.0 2018年9月18日
 * @see ValProfitQuery002Controller
 * @since 1.0
 */
@RestController(value="serviceValProfitQuery002Controller")
@RequestMapping("ValProfitQuery002")
public class ValProfitQuery002Controller extends BaseController {

    @Autowired
    private ValProfitQuery002Service valProfitQuery002Service;


    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    /**
     * 分页查询 
     * @param parameters
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    @PostMapping("/findPageAll")
    public List<ValProfitQuery001Dto> findPageAll(@RequestBody Map<String, Object> parameters) throws Exception {
        //update yanya 20190912 根据账套组账套ID获取账套
        // 全账套
        //List<SuggestDto> accountList = valAssetBook001Service.getBooksetId2(null);
        List<SuggestDto> accountList = valAssetBook001Service.getBooksetId3(parameters);
        parameters.put("accounts", accountList);
        return valProfitQuery002Service.findPageAll(parameters);
    }
}
