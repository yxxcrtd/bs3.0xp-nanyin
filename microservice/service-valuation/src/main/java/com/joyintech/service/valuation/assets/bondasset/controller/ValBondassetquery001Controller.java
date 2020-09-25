/*
 * 文件名：ValVoucherQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.assets.bondasset.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.assets.bondasset.dto.ValBondassetquery001Dto;
import com.joyintech.service.valuation.assets.bondasset.service.ValBondassetquery001Service;

/**
 * 模板示例<br>
 * 债券类资产查询
 * 
 * @author zhangzhao
 * @version 2.0 2018年7月19日
 * @see ValBondassetquery001Controller
 * @since 2.0
 */
//@RestController(value="serviceValBondassetquery001Controller")
@RestController("valBondassetquery001Ctl_dtx")
@RequestMapping("/ValBondassetquery001")
public class ValBondassetquery001Controller {

	/**
	 * ValBondassetqueryService
	 */
	@Autowired
	private ValBondassetquery001Service valBondassetquery001Service;

	/**
	 * 债券下拉框获取
	 *
	 * @return 返回的数据
	 * @exception Exception
	 *                异常
	 * @since 1.0
	 */
	@PostMapping("/getBondId")
	public List<SelectOptionDto> getBondId(@RequestBody Map<String, Object> params) {
		return valBondassetquery001Service.getBondId(params);
	}

	/**
	 * 债券类资产数据取得
	 *
	 * @return 记账凭证数据
	 * @throws Exception
	 *             异常
	 * @see
	 */
	@PostMapping("findPage/{page}/{length}")
	public Page<ValBondassetquery001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return valBondassetquery001Service.findPage(page, length, param);
	}

	/**
	 * 导出功能 <功能详细描述>
	 * 
	 * @param paraMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("/findPageout")
	public List<ValBondassetquery001Dto> findPageout(@RequestBody Map<String, Object> params) throws Exception {
		return valBondassetquery001Service.findPageout(params);
	}

	/**
	 * 导出功能 <功能详细描述>
	 * 
	 * @param paraMap
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("/getDataInWorklow")
	public int getDataInWorklow(@RequestBody ProcessMark processMark) throws Exception {
		return valBondassetquery001Service.getDataInWorklow(processMark);
	}
	
	/**
	 * 删除功能 <功能详细描述>
	 * 
	 * @param paraMap
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("/doComplete")
	public void doComplete(@RequestBody ValBondassetquery001Dto entity) throws Exception {
		valBondassetquery001Service.doComplete(entity);
	}
	
}
