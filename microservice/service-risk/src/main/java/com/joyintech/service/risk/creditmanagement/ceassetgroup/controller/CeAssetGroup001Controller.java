/*
 * 文件名：CeAssetGroup001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.ceassetgroup.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditmanagement.ceassetgroup.dto.CreditLinesAstGrpDto;
import com.joyintech.service.risk.creditmanagement.ceassetgroup.service.CeAssetGroup001Service;


/**
 * 资产系列授信
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeAssetGroup001Controller
 * @since 1.0
 */
@RestController
@RequestMapping("/CeAssetGroup001")
public class CeAssetGroup001Controller {

	/**
	 * CeOrg001Service
	 */
	@Autowired
	private CeAssetGroup001Service ceAssetGroup001Service;
	
	/**
     * 资产代码联想
     * @return 查询结果集
     * @throws Exception 异常
     */
	@PostMapping("/getAssetGroupAliasIds")
    public List<SelectOptionDto> getAssetGroupAliasIds(@RequestBody Map<String, Object> map){
        return ceAssetGroup001Service.getAssetGroupAliasIds(map);
    }
	 

	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<CreditLinesAstGrpDto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return ceAssetGroup001Service.selectForPage(page, length, param);
    }

}
