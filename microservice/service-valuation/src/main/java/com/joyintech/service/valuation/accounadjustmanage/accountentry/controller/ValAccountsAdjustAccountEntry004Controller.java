/*
 * 文件名：AccountsAdjustAccountEntry004Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.accountentry.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry004Dto;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.service.ValAccountsAdjustAccountEntry004Service;

/**
 * 查看会计分录详情
 * @author weipeng
 * @version 1.0 2018年7月18日
 * @see ValAccountsAdjustAccountEntry004Controller
 * @since 1.0
 */
@RestController(value="serviceValAccountsAdjustAccountEntry004Controller")
@RequestMapping("ValAccountsAdjustAccountEntry004")
public class ValAccountsAdjustAccountEntry004Controller extends BaseController{
    
    @Autowired
    private ValAccountsAdjustAccountEntry004Service valAccountsAdjustAccountEntry004Service;
    
    /**
     * 根据凭证编号bookId获取对应相同编号的所有数据
     *
     * @return 记账凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @PostMapping("searchList")
    public List<ValAccountsAdjustAccountEntry004Dto> searchList(@RequestBody Map<String, Object> params) {
        // 凭证编号
        return valAccountsAdjustAccountEntry004Service.searchList((String)params.get("booksetId"), (String)params.get("sceneNodeId"));
    }
}
