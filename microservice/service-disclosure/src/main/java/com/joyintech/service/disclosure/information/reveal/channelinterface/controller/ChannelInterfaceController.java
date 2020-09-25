/*
 * 文件名：ChannelInterface001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2019年8月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.disclosure.information.reveal.channelinterface.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.disclosure.information.reveal.channelinterface.dto.ChannelInterfaceNew001Dto;
import com.joyintech.service.disclosure.information.reveal.channelinterface.service.ChannelInterfaceService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.service.FtpDetailService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;

@Controller
@RestController(value="serviceChannelInterfaceController")
@RequestMapping("/ChannelInterface")
public class ChannelInterfaceController extends BaseController{
	
	/**
	 *  渠道接口信息查询service
	 */
	@Autowired
	private ChannelInterfaceService channelInterface001Service;
	
	@Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;
	
	@Autowired
	private FtpDetailService ftpDetailService;

	/**
	 * 重写init方法，加载页面
	 */
	@Override
	public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.披露渠道);
		this.setSelectOptionToModel(model, DictCode.报表类别);
		this.setSelectOptionToModel(model, DictCode.披露发送状态);
		this.setSelectOptionToModel(model, DictCode.披露反馈状态);
		
		return super.init(model);
	}
	
	
	@RequestMapping("findPage/{page}/{length}")
	@ResponseBody
	public Page<ChannelInterfaceNew001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) 
		throws Exception{
		return channelInterface001Service.findPage(page, length, param);
	}
	
	
	
	
}
