/*
 * 文件名：AccountsAdjustBusScenEleConf001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhngming
 * 修改时间：2018年5月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0

 */

package com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dto.AccountsAdjustBusScenEleConf001Dto;
import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.service.AccountsAdjustBusScenEleConf001Service;

/**
 * 业务场景配置管理 controller 
 * 
 * @author ruanhongming
 * @version 1.0 2018年5月25日
 * @see AccountsAdjustBusScenEleConf001Controller
 * @since 1.0
 */
//@RestController(value="serviceAccountsAdjustBusScenEleConf001Controller")
@RestController("accountsAdjustBusScenEleConf001Ctl_dtx")
@RequestMapping("AccountsAdjustBusScenEleConf001")
public class AccountsAdjustBusScenEleConf001Controller extends BaseController {

    /**
     * 业务场景配置查询 service
     */
    @Autowired
    private AccountsAdjustBusScenEleConf001Service accountsAdjustBusScenEleConf001Service;

    /**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
//    @Override
//    public String init(Model model) {
//        return super.init(model);
//    }

    /**
     * 分页查询产品账套信息 <功能详细描述>
     * 
     * @return 查询出的产品账套配置列表
     * @exception Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<AccountsAdjustBusScenEleConf001Dto> queryPage(@RequestBody Map<String, Object> pageParam,
                                                              @PathVariable("page") int page, @PathVariable("length") int length) 
        throws Exception {
        Page<AccountsAdjustBusScenEleConf001Dto> pages = accountsAdjustBusScenEleConf001Service.findPage(page,length,pageParam);
        return pages;
    }

    /**
     * 行内删除
     * 
     * @param dto 行内数据dto
     * @return 底层资产查询列表
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/deleteBokTableListInfo")
    public AjaxResult deleteBokTableListInfo(@RequestBody String tableId) 
		throws Exception{
    	String msg = "";
    	accountsAdjustBusScenEleConf001Service.deleteBokTableListInfo(tableId);
    	msg = "删除成功！";
        return new AjaxResult(msg);
    }
    
    /**
     * 查询业务场景代码是否已被使用 
     * 
     * @param tableId 业务场景代码
     * @return 1：已使用，0：未使用
     * @see
     * @since 1.0
     */
    @PostMapping("/tableIdIsUsed")
    public boolean tableIdIsUsed(@RequestBody String tableId) {
        return accountsAdjustBusScenEleConf001Service.tableIdIsUsed(tableId);
    }
}
