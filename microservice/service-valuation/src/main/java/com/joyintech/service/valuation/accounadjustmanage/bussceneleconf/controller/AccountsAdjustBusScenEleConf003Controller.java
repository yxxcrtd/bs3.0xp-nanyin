/*
 * 文件名：AccountsAdjustBusScenEleConf003Controller.java
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
import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dao.AccountsAdjustBusScenEleConf003Mapper;
import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dto.AccountsAdjustBusScenEleConf002Dto;
import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.service.AccountsAdjustBusScenEleConf003Service;


/**
 * 业务要素配置管理 controller
 * 
 * @author ruanhongming
 * @version 1.0 2016年5月25日
 * @see AccountsAdjustBusScenEleConf003Controller
 * @since 1.0
 */
//@RestController(value="serviceAccountsAdjustBusScenEleConf003Controller")
@RestController("accountsAdjustBusScenEleConf003Ctl_dtx")
@RequestMapping("AccountsAdjustBusScenEleConf003")
public class AccountsAdjustBusScenEleConf003Controller extends BaseController {


    /**
     * 业务要素配置 service
     */
    @Autowired
    private AccountsAdjustBusScenEleConf003Service accountsAdjustBusScenEleConf003Service;
    
    @Autowired
    private AccountsAdjustBusScenEleConf003Mapper accountsAdjustBusScenEleConf003Mapper;

//    /**
//     * 页面初始化，显示画面<br>
//     * 
//     * @param model Model
//     * @return 初始化路径
//     * @see
//     * @since 1.0
//     */
//    @Override
//    public String init(Model model) {
//    	String tableId = this.getParam("tableId");
//    	String tableName = accountsAdjustBusScenEleConf003Mapper.qryTableNameByTableId(tableId);
//    	model.addAttribute("tableId", tableId);
//        model.addAttribute("tableName", tableName);
//        return super.init(model);
//    }
    @PostMapping("/qryTableNameByTableId")
    public String qryTableNameByTableId(@RequestBody String tableId) {
        return accountsAdjustBusScenEleConf003Service.qryTableNameByTableId(tableId);
    }
    
    /**
     * 页面查询业务要素信息
     * 
     * @return 查询出的产品账套配置列表
     * @exception Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<AccountsAdjustBusScenEleConf002Dto> queryPage(@RequestBody Map<String,Object> params,
                                                              @PathVariable int page,@PathVariable int length)
        throws Exception {
    	Page<AccountsAdjustBusScenEleConf002Dto> pages = accountsAdjustBusScenEleConf003Service.findPage(page,length,params);
        return pages;
    }

    /**
     * 行内修改、删除check
     * 
     * @param dto 行数据
     * @return check结果
     * @exception Exception 异常
     * @see
     * @since 1.0
     */

    @Transactional
    @DTXTransaction
    @PostMapping("/delete")
    public AjaxResult delete(@RequestBody AccountsAdjustBusScenEleConf002Dto dto) 
		throws Exception{
    	String msg = "";
    	accountsAdjustBusScenEleConf003Service.deleteBokTableDict(dto);
    	msg = "删除成功！";
        return new AjaxResult(msg);
    }
}
