/*
 * 文件名：ValAssetsFundQuery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.assets.fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.assets.fund.dto.ValAssetsFundQuery002Dto;
import com.joyintech.service.valuation.assets.fund.service.ValAssetsFundQuery002Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 基金类资产详情页面
 * 
 * @author zhangwei
 * @version 1.0 2018年7月20日
 * @see ValAssetsFundQuery002Controller
 * @since 1.0
 */
//@RestController(value="serviceValAssetsFundQuery002Controller")
@RestController("valAssetsFundQuery002Ctl_dtx")
@RequestMapping("/ValAssetsFundQuery002")
public class ValAssetsFundQuery002Controller extends BaseController {

    @Autowired
    private ValAssetsFundQuery002Service valAssetsFundQuery002Service;


    
    @GetMapping("findByfinprodId")
    public ValAssetsFundQuery002Dto findByfinprodId(String finprodId) {
    	return valAssetsFundQuery002Service.findByfinprodId(finprodId);
    }
    
    
    
    @Transactional
	@DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody ValAssetsFundQuery002Dto dto) throws Exception {
    	valAssetsFundQuery002Service.doComplete(dto,OperationType.NEW);
    }
    
    /**
     * 修改
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
	@DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody ValAssetsFundQuery002Dto dto) throws Exception{
    	valAssetsFundQuery002Service.doComplete(dto,OperationType.MODIFY);
    }
    
    /**
     * 删除
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
	@DTXTransaction
    @PostMapping("delete")
    public void delete(@RequestBody ValAssetsFundQuery002Dto dto) throws Exception{
    	valAssetsFundQuery002Service.doComplete(dto,OperationType.DELETE);
    }
    
    @GetMapping("checkFindprodId")
    public boolean checkFindprodId(String finprodId) {
    	return valAssetsFundQuery002Service.checkFindprodId(finprodId);
    }
    
}
