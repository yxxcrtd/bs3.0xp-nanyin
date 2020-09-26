/*
 * 文件名：AccountsAdjustBusScenEleConf002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhngming
 * 修改时间：2018年5月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.bussceneleconf.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.accounadjustmanage.bussceneleconf.dto.AccountsAdjustBusScenEleConf001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.bussceneleconf.service.AccountsAdjustBusScenEleConf002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;


/**
 * 业务场景新建  controller 
 * 
 * @author ruanhongming
 * @version 1.0 2016年12月26日
 * @see AccountsAdjustBusScenEleConf002Controller
 * @since 1.0
 */
@Controller
@PageId("AccountsAdjustBusScenEleConf002")
@RequestMapping("/valuation/accounadjustmanage/bussceneleconf/AccountsAdjustBusScenEleConf002")
public class AccountsAdjustBusScenEleConf002Controller extends BaseController {

    /**
     * 业务场景配置新建  service 
     */
    @Autowired
    private AccountsAdjustBusScenEleConf002Service accountsAdjustBusScenEleConf002Service;
    
    /**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    @Override
    public String init(Model model) {
    	if(GlobalConstant.DETAIL_PAGE_MODEL_EDIT.equals(this.getPageModel())){
    		String tableName = this.getParam("tableName");
    		model.addAttribute("tableName", tableName);
    		model.addAttribute("tableId", this.getParam("tableId"));
    		model.addAttribute("remark", this.getParam("remark"));
    	}
        return super.init(model);
    }
    
    /**
     * 保存check
     * 
     * @param dto 页面参数dto
     * @param result check验证结果
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("saveCheck")
    public String saveCheck() {
        Map<String, Object> params = RequestUtil.getPageParam();
        return accountsAdjustBusScenEleConf002Service.saveCheck(params);
//    	String msg = "";
//    	String tableId = this.getParam("tableId");
//    	AccountsAdjustBusScenEleConf001Dto dto = new AccountsAdjustBusScenEleConf001Dto();
//    	dto.setTableId(tableId);
//    	dto.setTableName(this.getParam("tableName"));
//    	boolean flagId = accountsAdjustBusScenEleConf002Service.tableIdIsExists(tableId);
//    	//业务场景代码已存在
//    	if(flagId){
//    		msg = "1";
//    	}
//    	//业务场景名称已存在
//    	boolean flagName = accountsAdjustBusScenEleConf002Service.tableNameIsExists(dto);
//    	if(flagName){
//    		msg = "2";
//    	}
//    	 return msg;
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
    @ResponseBody
    @RequestMapping("save")
    public boolean save() {
    	String datas = this.getParam("datas");
    	AccountsAdjustBusScenEleConf001Dto dto = JsonUtils.json2Object(datas, AccountsAdjustBusScenEleConf001Dto.class);
    	dto.setStatus("E");
    	//保存成功
    	boolean flag = accountsAdjustBusScenEleConf002Service.saveData(dto);
    	return flag;
    }
    
}
