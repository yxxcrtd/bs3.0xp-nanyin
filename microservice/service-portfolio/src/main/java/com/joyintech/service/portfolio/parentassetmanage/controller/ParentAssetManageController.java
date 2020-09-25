/*
 * 文件名：ParentPlanAssetController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：ZhouYu
 * 修改时间：2019年11月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.portfolio.parentassetmanage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.portfolio.parentassetmanage.dto.ParentAssetManageDto;
import com.joyintech.service.portfolio.parentassetmanage.service.ParentAssetManageService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ZhouYu
 * @version 1.0 2019年11月26日
 * @see ParentPlanAssetController
 * @since 1.0
 */
@RestController
@RequestMapping("/ParentAssetManage")
public class ParentAssetManageController {
	
    /**
     * 计划类资产（利率型）Service
     */
    @Autowired
    private ParentAssetManageService parentAssetManageService;
	
	@PostMapping("selectForList")
	public List<ParentAssetManageDto> selectForList(@RequestBody Map<String, Object> param){
		return parentAssetManageService.getParentInfos(param);
	}
}
