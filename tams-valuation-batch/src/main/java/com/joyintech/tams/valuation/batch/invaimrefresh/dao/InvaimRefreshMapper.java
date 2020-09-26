/*
 * 文件名：InvaimRefreshMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年9月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.invaimrefresh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.invaimrefresh.dto.DealInvaimInfoDto;

/**
 * 投资目的刷新批数据获取
 * 
 * @author yangjian
 * @version 1.0 2018年9月8日
 * @see InvaimRefreshMapper
 * @since 1.0
 */
@Repository
public interface InvaimRefreshMapper {

	/**
	 * 获取投资目的需要刷新的交易及投资目的信息（此sql保留，用于测试记录）
	 * 
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<DealInvaimInfoDto> getNeedChangeDealInvaimList(Map<String, Object> param);

	/**
	 * 获取当前要处理的交易信息及获取对应估值方案信息的数据
	 * 
	 * @param param
	 * @return
	 */
	public List<DealInvaimInfoDto> getDealInfoList(Map<String, Object> param);

	/**
	 * 一般投资目的（关联估值方案明细,资产关联）（取起息日离交易日最近的一条）
	 * 
	 * @param param
	 * @return
	 */
	public String getNormalInvaimWithSchemeId(Map<String, Object> param);

	/**
	 * 一般投资目的（关联估值方案明细,细分类关联）（取起息日离交易日最近的一条）
	 * 
	 * @param param
	 * @return
	 */
	public String getNormalInvaimWithSecDetailType(Map<String, Object> param);

	/**
	 * 特殊投资目的（关联特殊资产估值方案明细,资产关联）(已确认区间只有一条，故无需过滤）
	 * 
	 * @param param
	 * @return
	 */
	public String getSpecialInvaimWithSchemeId(Map<String, Object> param);

}
