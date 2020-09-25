/*
 * 文件名：AccountsAdjustBusScenEleConf002Controller.java
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dto.AccountsAdjustBusScenEleConf001Dto;
import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.service.AccountsAdjustBusScenEleConf002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;


/**
 * 业务场景新建  controller 
 * 
 * @author ruanhongming
 * @version 1.0 2016年12月26日
 * @see AccountsAdjustBusScenEleConf002Controller
 * @since 1.0
 */
//@RestController(value="serviceAccountsAdjustBusScenEleConf002Controller")
@RestController("accountsAdjustBusScenEleConf002Ctl_dtx")
@RequestMapping("AccountsAdjustBusScenEleConf002")
public class AccountsAdjustBusScenEleConf002Controller extends BaseController {

    /**
     * 业务场景配置新建  service 
     */
    @Autowired
    private AccountsAdjustBusScenEleConf002Service accountsAdjustBusScenEleConf002Service;
    
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
//    	if(GlobalConstant.DETAIL_PAGE_MODEL_EDIT.equals(this.getPageModel())){
//    		String tableName = this.getParam("tableName");
//    		model.addAttribute("tableName", tableName);
//    		model.addAttribute("tableId", this.getParam("tableId"));
//    		model.addAttribute("remark", this.getParam("remark"));
//    	}
//        return super.init(model);
//    }
    
    /**
     * 保存check
     * 
     * @param dto 页面参数dto
     * @param result check验证结果
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    @PostMapping ("/saveCheck")
    public String saveCheck(@RequestBody Map<String , Object> params) {
    	String msg = "";
    	String tableId = (String)params.get("tableId");
    	String tableName = (String)params.get("tableName");
    	AccountsAdjustBusScenEleConf001Dto dto = new AccountsAdjustBusScenEleConf001Dto();
    	dto.setTableId(tableId);
    	dto.setTableName(tableName);
    	boolean flagId = accountsAdjustBusScenEleConf002Service.tableIdIsExists(tableId);
    	//业务场景代码已存在
    	if(flagId){
    		msg = "1";
    	}
    	//业务场景名称已存在
    	boolean flagName = accountsAdjustBusScenEleConf002Service.tableNameIsExists(dto);
    	if(flagName){
    		msg = "2";
    	}
    	 return msg;
    }
    
    /**
     * 保存
     * 
     * @param dto 页面参数dto
     * @param result check验证结果
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping ("/save")
    public boolean save(@RequestBody AccountsAdjustBusScenEleConf001Dto dto) {
//    	String datas = this.getParam("datas");
//    	AccountsAdjustBusScenEleConf001Dto dto = JsonUtils.json2Object(datas, AccountsAdjustBusScenEleConf001Dto.class);
//    	dto.setStatus("E");
    	//保存成功
    	boolean flag = accountsAdjustBusScenEleConf002Service.saveData(dto);
    	return flag;
    }
    
    /**
     * 新建保存时，查询业务场景代码是否已经存在
     * 
     * @param tableId 业务场景代码
     * @return true:已存在；false：不存在
     * @see
     * @since 1.0
     */
    @PostMapping("/tableIdIsExists")
    public boolean tableIdIsExists(@RequestBody String tableId) {
        return accountsAdjustBusScenEleConf002Service.tableIdIsExists(tableId);
    }
    
    /**
     * 修改或新建时check业务场景名称是否已存在
     * 
     * @param dto 页面dto
     * @return 1：已存在；0：不存在
     * @see
     * @since 1.0
     */
    @PostMapping("/tableNameIsExists")
    public boolean tableNameIsExists(@RequestBody AccountsAdjustBusScenEleConf001Dto dto) {
        return accountsAdjustBusScenEleConf002Service.tableNameIsExists(dto);
    }
    
    /**
     * 页面初始化，根据业务场景代码查询业务场景配置信息
     * 
     * @param paraMap 参数map
     * @return 业务场景配置信息
     * @see
     * @since 1.0
     */
    @PostMapping("/selectBokTableListByTableId")
    public AccountsAdjustBusScenEleConf001Dto selectBokTableListByTableId(@RequestBody Map<String, Object> paraMap) {
        return accountsAdjustBusScenEleConf002Service.selectBokTableListByTableId(paraMap);
    }
}
