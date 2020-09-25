/*
 * 文件名：ValVoucherQuery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.financemanage.accountbook.valsubjectbalancequery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectbalancequery.service.ValSubjectBalanceQuery002Service;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001Dto;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001SearchDto;

/**
 * 模板示例<br>
 * 凭证详情查看
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValSubjectBalanceQuery002Contoller
 * @since 2.0
 */
@RestController(value="serviceValSubjectBalanceQuery002Contoller")
@RequestMapping("/ValSubjectBalanceQuery002")
public class ValSubjectBalanceQuery002Contoller extends BaseController {

    /**
     * ValSubjectBalanceQuery002Service
     */
    @Autowired
    private ValSubjectBalanceQuery002Service valSubjectBalanceQuery002Service;

   

    /**
     * 根据凭证编号bookId获取对应相同编号的所有数据
     *
     * @return 记账凭证数据
     * @throws throws Exception 
     * @throws Exception
     *             异常
     * @see
     */
    @PostMapping("/searchList")
    public List<ValSubjectWaterQuery001Dto> searchList(@RequestBody ValSubjectWaterQuery001SearchDto dto) throws Exception {
        return valSubjectBalanceQuery002Service.getSubjectWater(dto);
    }

}
