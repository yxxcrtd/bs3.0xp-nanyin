/*
 * 文件名：ChannelInterface001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2019年8月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.disclosure.information.reveal.channelinterface.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.reveal.channelinterface.dto.ChannelInterfaceNew001Dto;


@Service
@Transactional(rollbackFor = Exception.class)
public class ChannelInterface001Service extends CommonExportService<ChannelInterfaceNew001Dto> {

//	@Autowired
//	private ChannelInterface001Mapper channelInterface001Mapper;


	public Page<ChannelInterfaceNew001Dto> queryChannelInterfacePageList(Map<String, Object> params) throws Exception {

		return restTemplate.postForPage(
	               ServiceApplication.VALUATION_SERVICE_NAME + "/ChannelInterface/queryChannelInterfacePageList",
	               params, ChannelInterfaceNew001Dto.class);
	}

	public Page<ChannelInterfaceNew001Dto> queryAllInstitutionPageList(Map<String, Object> params) 
     throws Exception {
     	 return restTemplate.postForPage(
               ServiceApplication.VALUATION_SERVICE_NAME + "/ChannelInterface/findPage",
               params, ChannelInterfaceNew001Dto.class);
	}
	

}
