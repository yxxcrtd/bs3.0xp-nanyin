/*
 * 文件名：DelayVerification001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月25日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.service.svrcom.errormessage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.errormessage.dto.ErrorMessage001;
import com.joyintech.tams.report.errormessage.service.ErrorMessage001Service;


/**
 * 延时报送检查服务
 * 〈功能详细描述〉
 * @author zhagnzhongliang
 * @version 1.0 2019年04月19日
 * @see ChbErrorMessageController
 * @since 1.0
 */
@RestController("backChbErrorMessageController")
@RequestMapping("/CheckErrorMessage")
public class ChbErrorMessageController{

	/**
	 * 错误信息service
	 */
	@Autowired
	private ErrorMessage001Service errorMessageService;

    

	/**
	 * 错误信息的查询方法
	 * @author liuyu
	 * @time 2019年5月13日 上午11:00:54
	 * @param selectParam
	 *            查询参数
	 * @return infoList
	 */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ErrorMessage001>  searchData(@RequestBody Map<String, Object> param){
        return errorMessageService.queryPage(param);
    }

    /**
    * 获取接口下拉列表.
    * @author liuyu
    * @time 2019年5月14日 上午11:26:48
    * @return options 选择集合
     */
    @PostMapping("getInterTypeSelects")
    public List<SelectOptionDto> getInterTypeSelects(@RequestBody Map<String, Object> param) {
    	 String ss=param.get("search")+"";
        return errorMessageService.getInterTypeSelects(ss);
    }
}
