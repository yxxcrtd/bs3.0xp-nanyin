/*
 * 文件名：CstpAutoSelectMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoxue
 * 修改时间：2020年01月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.cstp.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.trade.investtrade.commandreceive.dto.TrdCommandReceive001Dto;
import com.joyintech.tams.common.base.entity.TrdMatchInfo;


/**
 * CSTP下行交易自动匹配Mapper
 * @author caoxue
 * @version 1.0 2020年01月10日
 * @see CstpAutoSelectMapper
 * @since 1.0
 */
@Repository
public interface CstpAutoSelectMapper {
	
	List<TrdMatchInfo> selectTrdProductDealPre(Map<String, Object> map);

	/**
	 * 指令已接收查询
	 * 
	 * @param map
	 * @return
	 */
	List<TrdCommandReceive001Dto> selectEntrust(Map<String, Object> map);

}
