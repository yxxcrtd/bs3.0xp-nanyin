/*
 * 文件名：CustomerReportConfig002Controller.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：陈卓
 * 修改时间：2018年1月11日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.assitsaccount.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dto.ValAssistAccounting002Dto;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.service.ValAssistAccounting002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author  殷亚
 * @description 辅助核算配置服务       
 * @date 2022-1-17 15:07
 */
//@RestController(value="servicevalAssistAccounting002Controller")
@RestController("valAssistAccounting002Ctl_dtx")
@RequestMapping("ValAssistAccounting002")
public class ValAssistAccounting002Controller extends BaseController {
    
    /**
     * 辅助核算项新建服务
     */
    @Autowired
    private ValAssistAccounting002Service valAssistAccounting002Service;
    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @param operateType 操作类型
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid ValAssistAccounting002Dto dto, BindingResult result, String operateType) {
        return commonCheck(result, operateType);
    }
    @Transactional
    @DTXTransaction
    @PostMapping("/update")
    public int update(@RequestBody ValAssistAccounting002Dto entity) {
        return valAssistAccounting002Service.update(entity);
    }
}
