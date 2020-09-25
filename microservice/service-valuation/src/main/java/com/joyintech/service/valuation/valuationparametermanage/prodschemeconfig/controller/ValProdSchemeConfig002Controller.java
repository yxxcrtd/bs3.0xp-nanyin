/*
 * 文件名：ValProdSchemeConfig001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.valuationparametermanage.prodschemeconfig.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.valuationparametermanage.prodschemeconfig.dto.ValProdSchemeConfig002Dto;
import com.joyintech.service.valuation.valuationparametermanage.prodschemeconfig.service.ValProdSchemeConfig001Service;
import com.joyintech.service.valuation.valuationparametermanage.prodschemeconfig.service.ValProdSchemeConfig002Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 产品对账方案配置
 * @author weipeng
 * @version 1.0 2018年11月20日
 * @see ValProdSchemeConfig002Controller
 * @since 1.0
 */
//@RestController(value="serviceValProdSchemeConfig002Controller")
@RestController("valProdSchemeConfig002Ctl_dtx")
@RequestMapping("/ValProdSchemeConfig002")
public class ValProdSchemeConfig002Controller extends BaseController{
    
    @Autowired
    private ValProdSchemeConfig001Service valProdSchemeConfig001Service;
    
    @Autowired
    private ValProdSchemeConfig002Service valProdSchemeConfig002Service;
    
    
    /**
     * 记账凭证数据取得
     *
     * @return 记账凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @PostMapping("findPage")
    public List<ValProdSchemeConfig002Dto> findPage(@RequestBody Map<String, Object> param) throws Exception {
        return valProdSchemeConfig002Service.findPage(param);
    }
    
    /**
     * 保存处理
     * 
     * @param record 对账规则方案配置新建信息
     * @param result 保存结果
     * @return 保存结果
     * @throws IOException 
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
	@PostMapping("doInsertOrupdate")
    public void doInsertOrupdate(@RequestBody Map<String, Object> param) throws IOException{
    	String schId = (String) param.get("schId");
    	String schemeType = (String) param.get("schemeType");
    	String datas = (String) param.get("datas");
    	List<ValProdSchemeConfig002Dto> listdto = JsonUtils.json2List(datas, ValProdSchemeConfig002Dto.class);
    	valProdSchemeConfig002Service.doInsertOrupdate(schId, schemeType, listdto);
    	
    }
    
}
