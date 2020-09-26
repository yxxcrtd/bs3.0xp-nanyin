/*
 * 文件名：AccountsAdjustBusScenEleConf004Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhngming
 * 修改时间：2018年5月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
package com.joyintech.tams.valuation.accounadjustmanage.bussceneleconf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.accounadjustmanage.bussceneleconf.dto.AccountsAdjustBusScenEleConf002Dto;
import com.joyintech.tams.valuation.accounadjustmanage.bussceneleconf.service.AccountsAdjustBusScenEleConf003Service;
import com.joyintech.tams.valuation.accounadjustmanage.bussceneleconf.service.AccountsAdjustBusScenEleConf004Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;


/**
 * 业务要素配置新建 controller
 * 
 * @author ruanhongming
 * @version 1.0 2018年5月25日
 * @see AccountsAdjustBusScenEleConf004Controller
 * @since 1.0
 */
@Controller
@PageId("AccountsAdjustBusScenEleConf004")
@RequestMapping("/valuation/accounadjustmanage/bussceneleconf/AccountsAdjustBusScenEleConf004")
public class AccountsAdjustBusScenEleConf004Controller extends BaseController {

    /**
     * 业务要素配置新建 service
     */
    @Autowired
    private AccountsAdjustBusScenEleConf004Service accountsAdjustBusScenEleConf004Service;

//    @Autowired
//    private AccountsAdjustBusScenEleConf003Mapper accountsAdjustBusScenEleConf003Mapper;
    @Autowired
    private AccountsAdjustBusScenEleConf003Service accountsAdjustBusScenEleConf003Service;

    /**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
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
    	this.setSelectOptionToModel(model, DictCode.元素数据类型);
    	if(GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(this.getPageModel())){
    		String tableId = this.getParam("tableId");
        	String tableName = accountsAdjustBusScenEleConf003Service.qryTableNameByTableId(tableId);
        	model.addAttribute("tableId", tableId);
            model.addAttribute("tableName", tableName);
            model.addAttribute("dealType", GlobalConstant.DETAIL_PAGE_MODEL_NEW);
    	}else {
            String dictId = this.getParam("dictId");
            if (StringUtil.isNotEmpty(dictId)) {
            	AccountsAdjustBusScenEleConf002Dto entity = accountsAdjustBusScenEleConf004Service.selectBokTableDictInfo(dictId); 
            	model.addAttribute("tableId", entity.getTableId());
                model.addAttribute("tableName", entity.getTableName());
                model.addAttribute("dictId", entity.getDictId());
                model.addAttribute("dictCode", entity.getDictCode());
                model.addAttribute("dictType", entity.getDictType());
                model.addAttribute("dictSource", entity.getDictSource());
                model.addAttribute("dictName", entity.getDictName());
                model.addAttribute("dealType", GlobalConstant.DETAIL_PAGE_MODEL_EDIT);
            }
    	}
    	
        return super.init(model);
    }

    /**
     * 保存
     * 
     * @param dto 页面参数dto
     * @param result check验证结果
     * @return 初始化路径
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public boolean save() throws Exception {
    	String datas = this.getParam("datas");
    	AccountsAdjustBusScenEleConf002Dto dto = JsonUtils.json2Object(datas, AccountsAdjustBusScenEleConf002Dto.class);
    	dto.setStatus("E");
    	//保存成功
    	boolean flag = accountsAdjustBusScenEleConf004Service.saveData(dto);
    	return flag;
    }
}
