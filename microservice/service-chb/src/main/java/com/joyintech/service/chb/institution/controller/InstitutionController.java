/*
 * 文件名：ChbAstValueRegist001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zhangzhongliang
 * 修改时间：2019年1月24日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.service.chb.institution.controller;

import java.util.List;

import com.joyintech.tams.systemtools.service.ServiceInstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.select.SuggestDto;


/**
 * 机构管理服务
 * 〈功能详细描述〉
 * @author 张忠良
 * @version 1.0 2019年02月24日
 * @see InstitutionController
 * @since 1.0
 */
@RestController
@RequestMapping("/Institution")
public class InstitutionController{
    
	/**
	 * 机构管理service
	 */
	@Autowired
	private ServiceInstitutionService institutionService;
	
    

	/**
	 *
	 * 机构代码下拉框取得值
	 *
	 * @return 下拉框值
	 * @see
	 * @since 1.0
	 */
	@PostMapping("/getInstitutionCode")
	public List<SuggestDto> getInstitutionCode(@RequestBody String param) {
		return institutionService.getInstitutionCode(param);
	}
}
