/*
 * 文件名：PayTheVatQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chufengjie
 * 修改时间：2019年02月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.paythevatquery.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.product.paythevatquery.dto.PayTheVatQuery001Dto;


/**
 * 纳税增值税查询
 * 
 * @author chufengjie
 * @version 1.0 2019年02月23日
 * @see PayTheVatQuery001Dto
 * @since 1.0
 */
@Repository("finproductPayTheVatQuery001Mapper")
public interface PayTheVatQuery001Mapper {

	/**
	 * 根据条件查询产品 ：TA交易树查询
	 * 
	 * @param params
	 * @return
	 */
	List<PayTheVatQuery001Dto> selectList(Map<String, Object> params);

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<PayTheVatQuery001Dto> getBooksetId(@Param("param") String param);
	
	/**
	 * 获取组合
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> selectPortfolioId(Map<String, Object> param);
	
	/**
	 * 获取通道信息
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> queryChannelIdInfo(Map<String, Object> param);
}
