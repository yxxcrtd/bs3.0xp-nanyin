/*
 * 文件名：ValProfitQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valprofitquerynew.controller;


import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquerynew.dto.ValProfitNewQuery001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquerynew.service.ValProfitNewQuery001Service;
import com.joyintech.tams.common.base.entity.BokBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.joyintech.framework.base.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 利润表
 *
 * @author 黄浩
 * @version 1.0 2020年5月31日
 * @see ValProfitNewQuery001Controller
 * @since 1.0
 */
@RestController(value = "serviceValProfitNewQuery001Controller")
@RequestMapping("ValProfitNewQuery001")
public class ValProfitNewQuery001Controller extends BaseController {
    @Autowired
    private ValProfitNewQuery001Service valProfitNewQuery001Service;

    @PostMapping("/getBooksetId")
    public List<SuggestDto> getBooksetId(@RequestBody Map<String, Object> param) {
        return valProfitNewQuery001Service.getBooksetId(param);
    }

    @PostMapping("/selectProfit")
    public List<ValProfitNewQuery001Dto> selectProfit(@RequestBody Map<String, Object> param) {
        return valProfitNewQuery001Service.selectProfit(param);
    }

    @PostMapping("/selectProfitAll")
    public List<ValProfitNewQuery001Dto> selectProfitAll(@RequestBody Map<String, Object> param) {
        return valProfitNewQuery001Service.selectProfitAll(param);
    }

    @PostMapping("/selectAllBalance")
    public List<BokBalance> selectAllBalance(@RequestBody Map<String, Object> param) {
        return valProfitNewQuery001Service.selectAllBalance(param);
    }

    @PostMapping("/getLinkSelectOption")
    public List<SelectOptionDto> getLinkSelectOption(@RequestBody Map<String, Object> param) {
        return valProfitNewQuery001Service.getLinkSelectOption(param);
    }
    @PostMapping("/findPageAll")
    public List<ValProfitNewQuery001Dto> findPageAll(@RequestBody Map<String, Object> parameters) throws Exception {
        //update yanya 20190912 根据账套组账套ID获取账套
        // 全账套
        //List<SuggestDto> accountList = valAssetBook001Service.getBooksetId2(null);
        List<SuggestDto> accountList = valProfitNewQuery001Service.getBooksetId3(parameters);
        parameters.put("accounts", accountList);
        return valProfitNewQuery001Service.findPageAll(parameters);
    }
}
