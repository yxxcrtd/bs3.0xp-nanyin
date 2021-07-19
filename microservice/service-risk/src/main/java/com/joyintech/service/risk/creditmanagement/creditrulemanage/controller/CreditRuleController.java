/*
 * 文件名：CeOrg001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.creditrulemanage.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.joyintech.service.risk.creditmanagement.creditrulemanage.dto.CreditRuleInfoDto;
import com.joyintech.service.risk.creditmanagement.creditrulemanage.service.CreditRuleService;
import com.joyintech.tams.common.base.entity.CreditRuleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;

/**
 * 额度校验规则管理
 * @author gejiaao
 * @version 1.0 2020年02月15日
 * @see CreditRuleController
 * @since 1.0
 */
@RestController("CreditRuleCtl_dtx")
@RequestMapping("/CreditRule")
public class CreditRuleController {

	/**
	 * creditRuleService
	 */
	@Autowired
	private CreditRuleService creditRuleService;
	
	/**
     * 客户代码联想
     * @return 查询结果集
     * @throws Exception 异常
     */
	@PostMapping("getLimitLabel/{page}/{length}")
	public Page<SelectOptionDto> getLimitLabel(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
		return creditRuleService.getLimitLabel(map, page, length);
	}
	 

	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<CreditRuleInfoDto> selectForPage(@RequestBody Map<String, Object> param,
												 @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return creditRuleService.selectForPage(page, length, param);
    }

	/**
	 * 插入
	 * @param dto
	 * @throws Exception
	 * @since 1.0
	 */
	@PostMapping("insert")
	public void insert(@RequestBody CreditRuleInfoDto dto) throws Exception {
		creditRuleService.insert(dto);
	}

	/**
	 * 更新
	 * @param dto
	 * @throws Exception
	 * @since 1.0
	 */
	@PostMapping("update")
	public void update(@RequestBody CreditRuleInfoDto dto) throws Exception {
		creditRuleService.update(dto);
	}
	/**
	 * 删除
	 * @param
	 * @return 结果
	 * @throws Exception
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("delete")
	public int delete(@RequestBody CreditRuleInfoDto dto) throws Exception {
		return creditRuleService.delete(dto);
	}


	/**
	 * 额度信息获取
	 * @param
	 * @return PrdValParam
	 */
	@PostMapping("queryCreditRuleBySeqno")
	public CreditRuleInfo queryCreditRuleBySeqno(@RequestBody Map<String, Object> map) {
		return creditRuleService.queryCreditRuleBySeqno(map);
	}

	/**
	 * 校验标签名称
	 * @param param
	 */
	@PostMapping("/checkTag")
	public Boolean checkTag(@RequestBody Map<String, Object> param) {
		return creditRuleService.checkTag(param);
	}
	/**
	 * 校验规则是否重复
	 * @param dto
	 */
	@PostMapping("/checkRule")
	public List<CreditRuleInfoDto> checkRule(@RequestBody CreditRuleInfoDto dto) throws InvocationTargetException, IllegalAccessException {
		return creditRuleService.checkRule(dto);
	}
}
