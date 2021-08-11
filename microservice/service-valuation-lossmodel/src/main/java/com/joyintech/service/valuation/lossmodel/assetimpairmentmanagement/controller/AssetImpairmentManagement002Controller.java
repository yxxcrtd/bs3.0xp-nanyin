/*
 * 文件名：AssetImpairmentManagement002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-28
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement002Dto;
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.service.AssetImpairmentManagement002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-06-28
 * @see AssetImpairmentManagement002Controller
 * @since 1.0
 */
@RestController(value="serviceAssetImpairmentManagement002Controller")
@RequestMapping("AssetImpairmentManagement002")
public class AssetImpairmentManagement002Controller extends BaseController {

	@Autowired
	private AssetImpairmentManagement002Service assetImpairmentManagement002Service;


	/**
	 * 查询数据是否存在
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findDuplicateData")
	public int findDuplicateData(@RequestBody AssetImpairmentManagement002Dto dto){
		return assetImpairmentManagement002Service.findDuplicateData(dto);
	}

	/**
	 * 流程完成后的操作
	 *
	 * @param dto 业务实体
	 * @return 画面地址
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@DTXTransaction
	@PostMapping(value = "doComplete/{action}")
	public void doComplete(@RequestBody AssetImpairmentManagement002Dto dto, @PathVariable("action") String action) {
		assetImpairmentManagement002Service.doComplete(dto, OperationType.valueOf(action));
	}


}
