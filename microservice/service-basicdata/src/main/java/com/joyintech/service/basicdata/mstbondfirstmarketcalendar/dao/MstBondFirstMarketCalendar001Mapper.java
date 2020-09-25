/*
 * 文件名：MstBondFirstMarketCalendar001Mapper.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhaodongdong
 * 修改时间：2019年11月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.basicdata.mstbondfirstmarketcalendar.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.basicdata.mstbondfirstmarketcalendar.dto.MstBondFirstMarketCalendar001Dto;

/**
* 
* @author zhaodongdong
*
*/
@Repository
public interface MstBondFirstMarketCalendar001Mapper {
	/**
	 * 根据条件查找数据
	 * 
	 * @param pageParam
	 *            查询条件
	 * @return 返回数据库里符合条件的数据
	 */
List<MstBondFirstMarketCalendar001Dto> select(Map<String, Object> pageParam);
/**
 * 查询finprodId
 * @param finprodId
 * @return
 */
String getFinprodId(String finprodId);
}
