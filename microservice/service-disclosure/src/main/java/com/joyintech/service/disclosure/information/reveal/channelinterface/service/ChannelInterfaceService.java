/*
 * 文件名：ChannelInterface001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2019年8月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.disclosure.information.reveal.channelinterface.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.reveal.channelinterface.dao.ChannelInterface001Mapper;
import com.joyintech.service.disclosure.information.reveal.channelinterface.dto.ChannelInterfaceNew001Dto;


@Service
public class ChannelInterfaceService extends CommonExportService<ChannelInterfaceNew001Dto> {

	@Autowired
	private ChannelInterface001Mapper channelInterface001Mapper;


	public Page<ChannelInterfaceNew001Dto> queryChannelInterfacePageList(Map<String, Object> params) throws Exception {

		return super.queryForPage(() -> channelInterface001Mapper.queryChannelInterfacePageList(params));
	}

	public Page<ChannelInterfaceNew001Dto> findPage(int page, int length, Map<String, Object> params) {

		// 获取产品编号
		String prodId = (String) params.get("prodId");
		String[] prodIdArr = null;
		if (StringUtil.isNotEmpty(prodId)) {
			prodIdArr = prodId.split(",");
		}
		params.put("prodIdArr", prodIdArr);

		// 获取发送状态
		String sendStatus = (String) params.get("sendStatus");
		String[] sendStatusArr = null;
		if (StringUtil.isNotEmpty(sendStatus)) {
			sendStatusArr = sendStatus.split(",");
		}
		params.put("sendStatusArr", sendStatusArr);

		// 获取反馈状态
		String receiveStatus = (String) params.get("receiveStatus");
		String[] receiveStatusArr = null;
		if (StringUtil.isNotEmpty(receiveStatus)) {
			receiveStatusArr = receiveStatus.split(",");
		}
		params.put("receiveStatusArr", receiveStatusArr);

		// 获取报表类别
		String reportCategory = (String) params.get("reportCategory");
		String[] reportCategoryArr = null;
		if (StringUtil.isNotEmpty(reportCategory)) {
			reportCategoryArr = reportCategory.split(",");
		}
		params.put("reportCategoryArr", reportCategoryArr);

		// 获取文件名称
		String fileName = (String) params.get("fileName");
		/*String[] fileNameArr = null;
		if (StringUtil.isNotEmpty(fileName)) {
			fileNameArr = fileName.split(",");
		}
		params.put("fileNameArr", fileNameArr);*/

		// 获取披露渠道
		String channelCode = (String) params.get("channelCode");
		String[] channelCodeArr = null;
		if (StringUtil.isNotEmpty(channelCode)) {
			channelCodeArr = channelCode.split(",");
		}
		params.put("channelCodeArr", channelCodeArr);

		return super.queryForPage(page, length,() -> channelInterface001Mapper.queryChannelInterfacePageList(params));
	}
	
	
	
	
	

}
