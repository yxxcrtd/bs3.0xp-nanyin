/*
 * 文件名：CeAsset001Controller.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditmanagement.ceasset.dto.CreditLinesAstDto;
import com.joyintech.service.risk.creditmanagement.ceasset.service.CeAsset001Service;


/**
 * 资产授信
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeAsset001Controller
 * @since 1.0
 */
@RestController("backCeAsset001Controller")
@RequestMapping("/CeAsset001")
public class CeAsset001Controller {

	/**
	 * CeOrg001Service
	 */
	@Autowired
	private CeAsset001Service ceAsset001Service;
	
	/**
     * 资产代码联想
     * @return 查询结果集
     * @throws Exception 异常
     */
	@PostMapping("/getFinprodMarketIds")
    public List<SelectOptionDto> getFinprodMarketIds(@RequestBody Map<String, Object> map){
        return ceAsset001Service.getFinprodMarketIds(map);
    }
	 

	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<CreditLinesAstDto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return ceAsset001Service.selectForPage(page, length, param);
    }

}
