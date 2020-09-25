/*
 * 文件名：AccountingCommonMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.component.business.evaluation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.component.business.evaluation.dto.BookLatestInfoDto;
import com.joyintech.tams.component.business.evaluation.dto.BooksetAndBokDetailDto;
import com.joyintech.tams.component.business.evaluation.dto.ChildCombination;
import com.joyintech.tams.component.business.evaluation.dto.StepProducts;
import com.joyintech.tams.component.business.evaluation.dto.SubportfolioIncomeCapitalRatio;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author wangzhiang
 * @version 1.0 2018年5月31日
 * @see AccountingCommonMapper
 * @since 1.0
 */
@Repository
public interface AccountingCommonMapper {

	/**
	 * 
	 * 凭证最新账务查询
	 * 
	 * @param paMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<BookLatestInfoDto> getBookLatestInfo(Map<String, Object> paMap);
	
	
	/**
	 * 凭证最新账务查询公共方法 获取这个方法的参数
	 */
	public List<BooksetAndBokDetailDto> getCysYearRateParam(Map<String,Object> paraMap);
	
	/**
	 * 分档产品查询
	 */
	public List<StepProducts> fileProductQuery(String finprodId);
	
	/**
	 * 子组合查询
	 */
	public List<ChildCombination> subcombinedQuery(String financialProductCode);
	
	/**
	 * 子组合收益资金占比计算
	 */
	public List<SubportfolioIncomeCapitalRatio> subportfolioIncomeCapitalRatio(Map<String,Object> paraMap);

	List<SubportfolioIncomeCapitalRatio> queryShareAmts(Map<String,Object> paraMap);
}
