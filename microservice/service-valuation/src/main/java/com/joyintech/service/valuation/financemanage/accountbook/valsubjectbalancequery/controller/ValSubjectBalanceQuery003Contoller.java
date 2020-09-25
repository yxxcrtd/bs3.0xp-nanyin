/*
 * 文件名：ValVoucherQuery003Controller.java
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectbalancequery.service.ValSubjectBalanceQuery003Service;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001Dto;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001SearchDto;

/**
 * 模板示例<br>
 * 凭证详情查看
 * 
 * @author 
 * @version 2.0 2018年5月3日
 * @see ValSubjectBalanceQuery003Contoller
 * @since 2.0
 */
@RestController(value="serviceValSubjectBalanceQuery003Contoller")
@RequestMapping("/ValSubjectBalanceQuery003")
public class ValSubjectBalanceQuery003Contoller extends BaseController {

    /**
     * ValSubjectBalanceQuery002Service
     */
    @Autowired
    private ValSubjectBalanceQuery003Service valSubjectBalanceQuery003Service;

   

    /**
     * 根据凭证编号subjectNo获取对应相同编号的所有数据
     *
     * @return 记账凭证数据
     * @throws throws Exception 
     * @throws Exception
     *             异常
     * @see
     */
    @PostMapping("/searchList")
    public List<ValSubjectWaterQuery001Dto> searchList(ValSubjectWaterQuery001SearchDto dto) throws Exception {
        return valSubjectBalanceQuery003Service.getSubjectWater(dto);
    }

}
