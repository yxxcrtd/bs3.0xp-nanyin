/*
 * 文件名：FlowRiskSceneManage001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskFactroRManageDto;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.service.FlowRiskFactroRManageService;;


/**
 *
 * @author wangkang
 * @version 1.0 2019年8月21日
 * @see FlowRiskFactroRManageController
 * @since 1.0
 */
@RestController
@RequestMapping("/FlowRiskFactroRManage")
public class FlowRiskFactroRManageController {

	/**
	 * FlowRiskSceneManage001Service
	 */
	@Autowired
	private FlowRiskFactroRManageService FlowRiskFactroRManageService;
	@PostMapping("querycheck")
    public List<FlowRiskFactroRManageDto> querycheck(@RequestBody Map<String, Object> param) throws Exception {
       
        return FlowRiskFactroRManageService.querycheck(param);
    }

}
