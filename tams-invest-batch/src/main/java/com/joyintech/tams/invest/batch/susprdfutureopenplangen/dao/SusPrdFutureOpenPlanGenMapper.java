/*
 * 文件名：ResetBaseRuleInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.susprdfutureopenplangen.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinFloatAccrualScheKey;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.AccrDateNeedOtherDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.AccrDateOtherDto;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.dto.SusPrdFutureOpenPlanGenDto;

/**
 * 批处理-续投时期次结束日调整批处理				
 * @version 1.0 2019年9月24日
 * @since 1.0
 */
@Repository
public interface SusPrdFutureOpenPlanGenMapper {
	//查询需要更新的开放计划
	List<SusPrdFutureOpenPlanGenDto> findSusPrdFutureOpenPlanGenList(Map<String,Object> paramMap);
	/*//查询需要更新的业绩回补计划
	List<SusPrdFutureOpenPlanGenDto> findProductAnapleroticPlanList(Map<String,Object> paramMap);*/
	//查询需要更新的付息计划
	List<SusPrdFutureOpenPlanGenDto> findProductPayPlanList(Map<String,Object> paramMapPayPlan);
	/**
	 * 如果是净值型产品则取净值日，如果是货币产品，则取区间结束日
	 */
	List<Date> findNdateOrEndDateByProdid(String finprodId);
	
	/**
	 * 查询浮动管理费的现金流代码
	 * @param map
	 * @return
	 */
	String selectfinCashInfo(Map<String,Object> map);
	
	/**
	 * 查询回补的现金流代码
	 * @param map
	 * @return
	 */
	String selectfinAnapleroticInfo(Map<String,Object> map);
	
	/**
	 * 根据产品代码查询其他计算浮动管理费计提计划的必须字段(业绩报酬计提规则表)
	 */
	AccrDateNeedOtherDto findOtherNeedDateByProdid(String finprodId);
	/**
	 * 根据产品代码查询其他计算回补计划的必须字段(业绩报酬计提规则表)
	 */
	AccrDateOtherDto findOtherNeedDate(String finprodId);
	/**
	 * 根据cashid删除原浮动管理费计提计划 
	 */
	int deleteFloatAccrualScheByPrimaryKey(FinFloatAccrualScheKey finFloatAccrualScheKey);
	
	Date queryMaxDealMdate(@Param("finprodId") String finprodId);
	
}
