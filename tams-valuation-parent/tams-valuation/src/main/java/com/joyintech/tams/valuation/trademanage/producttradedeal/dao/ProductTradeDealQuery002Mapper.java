/*
 * 文件名：ProductTradeDealQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.trademanage.producttradedeal.dao;


import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.trademanage.producttradedeal.dto.ProductTradeDealQuery002Dto;


/**
 * TA交易明细查询 Mapper
 * @author zhangwei
 * @version 1.0 2018年8月7日
 * @see ProductTradeDealQuery002Mapper
 * @since 1.0
 */
public interface ProductTradeDealQuery002Mapper {

	/**
	 * TA交易:根据产品 id 和 确认日  条件查询递归所有交易
	 * @param params
	 * @return
	 */
    List<ProductTradeDealQuery002Dto> selectByCondition(Map<String, Object> params);
    
    
    
}
