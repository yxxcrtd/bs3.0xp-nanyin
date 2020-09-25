/*
 * 文件名：AccountCurrentRate002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.accountcurrentrate.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRate002Dto;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRateDto;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.service.AccountCurrentRate002Service;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.service.AccountCurrentRateService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 帐户活期利率 新建修改 双击查看
 * 
 * @author HuangJian
 * @version 1.0 2018年11月13日
 * @see AccountCurrentRate002Controller
 * @since 1.0
 */
//@RestController(value="serviceAccountCurrentRate002Controller")
@RestController("accountCurrentRate002Ctl_dtx")
@RequestMapping("/AccountCurrentRate002")
public class AccountCurrentRate002Controller extends BaseController {

    /**
     * accountCurrentRate002Service
     */
    @Autowired
    private AccountCurrentRate002Service accountCurrentRate002Service;

    /**
     * accountCurrentRateService
     */
    @Autowired
    private AccountCurrentRateService accountCurrentRateService;

    @PostMapping("getfind002Dto")
    public AccountCurrentRate002Dto getfind002Dto(@RequestBody Map<String, Object> param) {
        return accountCurrentRate002Service.getfind002Dto(param);
    }

    /**
     * 提交 插入 修改
     * 
     * @param dto AccountCurrentRate002Dto
     * @param result AjaxResult
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete/{action}")
    public void doCommit(@RequestBody AccountCurrentRateDto dto, @PathVariable("action") String action) {
        accountCurrentRateService.doComplete(dto,
            OperationType.valueOf(action));
    }

    /**
     * 帐户代码
     *
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getAccNo")
    public List<SelectOptionDto> getAccNo(@RequestBody Map<String, Object> param) {
        return accountCurrentRate002Service.getAcctNo(param);
    }

    /**
     * 帐户代码
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getAccNo1")
    public List<SuggestDto> getBusiId(@RequestBody Map<String, Object> param) {
        return accountCurrentRate002Service.getAcctNo1(param, (String)param.get("param"));
    }
}
