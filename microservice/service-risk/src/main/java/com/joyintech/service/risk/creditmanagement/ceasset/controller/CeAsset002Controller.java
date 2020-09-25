/*
 * 文件名：CeAsset002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.ceasset.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.risk.creditmanagement.ceasset.dto.CreditLinesAstDto;
import com.joyintech.service.risk.creditmanagement.ceasset.service.CeAsset002Service;


/**
 * 资产授信
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeAsset002Controller
 * @since 1.0
 */
@RestController("CeAsset002Ctl_dtx")
@RequestMapping("/CeAsset002")
public class CeAsset002Controller {

	/**
	 * ceAsset002Service
	 */
	@Autowired
	private CeAsset002Service ceAsset002Service;
	
	 /**
	 * 额度信息获取
	 * @param indId 指标代码
	 * @return PrdValParam
	 */
    @PostMapping("queryCeAssetBySeqno")
	public CreditLinesAstDto queryCeAssetBySeqno(@RequestBody Map<String, Object> map) {
		return ceAsset002Service.queryCeAssetBySeqno(map);
	}
    
    /**
     *  查询资产在资产系列中是否已存在
     * @param Map
     */
    @GetMapping("/searchSameAssetGroup")
	public List<CreditLinesAstDto> searchSameAssetGroup(String customerId) {
		return ceAsset002Service.searchSameAssetGroup(customerId);
	}
    
	 /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
	 @Transactional
	 @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody CreditLinesAstDto dto) throws Exception {
    	ceAsset002Service.insert(dto);
    }
    
    /**
     * 更新
     * @param dto
     * @throws Exception
     * @since 1.0
     */
	@Transactional
	@DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody CreditLinesAstDto dto) throws Exception {
    	ceAsset002Service.update(dto);
    }
	    
	/**
     * 删除
     * @param 页面选中的数据
     * @return 结果
     * @throws Exception
     */
	@Transactional
	@DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody CreditLinesAstDto dto) throws Exception {
        return ceAsset002Service.delete(dto);
    }
}
