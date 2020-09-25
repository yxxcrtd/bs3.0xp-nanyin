/*
 * 文件名：CeOrg001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.ceorg.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditLinesOrgDto;
import com.joyintech.service.risk.creditmanagement.ceorg.service.CeOrg001Service;


/**
 * 机构授信
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeOrg001Controller
 * @since 1.0
 */
@RestController("CeOrg001Ctl_dtx")
@RequestMapping("/CeOrg001")
public class CeOrg001Controller {

	/**
	 * CeOrg001Service
	 */
	@Autowired
	private CeOrg001Service ceOrg001Service;
	
	/**
     * 客户代码联想
     * @return 查询结果集
     * @throws Exception 异常
     */
	@PostMapping("getCustomerId/{page}/{length}")
	public Page<SelectOptionDto> getCustomerId(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
		return ceOrg001Service.getCustomerId(map, page, length);
	}
	 

	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<CreditLinesOrgDto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return ceOrg001Service.selectForPage(page, length, param);
    }


}
