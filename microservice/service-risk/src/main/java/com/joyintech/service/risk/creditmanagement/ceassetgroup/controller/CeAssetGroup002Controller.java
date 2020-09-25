/*
 * 文件名：CeAssetGroup002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.ceassetgroup.controller;

import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.risk.creditmanagement.ceassetgroup.dto.CreditLinesAstGrpDto;
import com.joyintech.service.risk.creditmanagement.ceassetgroup.service.CeAssetGroup002Service;


/**
 * 资产系列授信
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeAssetGroup002Controller
 * @since 1.0
 */
@RestController("CeAssetGroup002Ctl_dtx")
@RequestMapping("/CeAssetGroup002")
public class CeAssetGroup002Controller {

	/**
	 * ceAsset002Service
	 */
	@Autowired
	private CeAssetGroup002Service ceAssetGroup002Service;
	
	 /**
	 * 额度信息获取
	 * @param indId 指标代码
	 * @return PrdValParam
	 */
    @PostMapping("queryCeAssetGroupBySeqno")
	public CreditLinesAstGrpDto queryCeAssetGroupBySeqno(@RequestBody Map<String, Object> map) {
		return ceAssetGroup002Service.queryCeAssetGroupBySeqno(map);
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
    public void insert(@RequestBody CreditLinesAstGrpDto dto) throws Exception {
    	ceAssetGroup002Service.insert(dto);
    }
    
    /**
     * 更新
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("update")
    public void update(@RequestBody CreditLinesAstGrpDto dto) throws Exception {
    	ceAssetGroup002Service.update(dto);
    }
	    
	/**
     * 删除
     * @param 页面选中的数据
     * @return 结果
     * @throws Exception
     */
    @PostMapping("delete")
    public int delete(@RequestBody CreditLinesAstGrpDto dto) throws Exception {
        return ceAssetGroup002Service.delete(dto);
    }
}
