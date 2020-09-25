/*
 * 文件名：ChannelInterfaceMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2019年8月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.disclosure.information.reveal.channelinterface.dao;


import java.util.List;
import java.util.Map;

import com.joyintech.service.disclosure.information.reveal.channelinterface.dto.ChannelInterfaceNew001Dto;


public interface ChannelInterface001Mapper {
	
	
	List<ChannelInterfaceNew001Dto> queryChannelInterfacePageList(Map<String, Object> params);
	
	
}
