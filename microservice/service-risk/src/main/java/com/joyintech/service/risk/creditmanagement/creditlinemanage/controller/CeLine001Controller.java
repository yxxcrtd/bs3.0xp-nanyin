/*
 * 文件名：CeOrg001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.creditlinemanage.controller;

import java.util.Map;

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
import com.joyintech.service.risk.creditmanagement.creditlinemanage.dto.CreditTagInfoDto;
import com.joyintech.service.risk.creditmanagement.creditlinemanage.service.CeLine001Service;



/**
 * 额度标签管理
 * @author chenlei1
 * @version 1.0 2019年02月15日
 * @see CeLine001Controller
 * @since 1.0
 */
@RestController("CeLine001Ctl_dtx")
@RequestMapping("/CeLine001")
public class CeLine001Controller {

	/**
	 * ceLine001Service
	 */
	@Autowired
	private CeLine001Service ceLine001Service;
	
	/**
     * 客户代码联想
     * @return 查询结果集
     * @throws Exception 异常
     */
	@PostMapping("getLimitLabel/{page}/{length}")
	public Page<SelectOptionDto> getLimitLabel(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
		return ceLine001Service.getLimitLabel(map, page, length);
	}
	 

	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<CreditTagInfoDto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return ceLine001Service.selectForPage(page, length, param);
    }

}
