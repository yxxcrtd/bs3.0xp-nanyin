/*
 * 文件名：PtlFundAcctPosiMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年2月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlfundacctposi.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.portfolio.ptlfundacctposi.dto.PtlFundAcctPosiDto;

/**
 * 
 * 组合资金账户头寸查询
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2019年2月20日
 * @see PtlFundAcctPosiMapper
 * @since 1.0
 */
@Repository
public interface PtlFundAcctPosiMapper {
	
	/**
	 * 
	 * 组合资金账户头寸查询
	 * <功能详细描述>
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<PtlFundAcctPosiDto> select(Map<String, Object> pageParam);
	
	/**
	 * 
	 * 组合代码联想框
	 * <功能详细描述>
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> getPortfolioId(Map<String, Object> param);
	
	/**
	 * 
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	int selectForHasChildren(Map<String,Object> map);
	
	/**
	 * 
	 * 组合资金账户头寸查询
	 * <功能详细描述>
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<PtlFundAcctPosiDto> select1(Map<String, Object> pageParam);
	
	/**
	 * 
	 * 组合资金账户头寸查询
	 * <功能详细描述>
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<PtlFundAcctPosiDto> select2(Map<String, Object> pageParam);
	
	List<String> getAllCcy(Map<String,Object> pageParam);
}
