/*
 * 文件名：FinProductGoalInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张亮
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.common.dto.BussFinProductFeeInfoDto;

/**
 * 产品投向描述业务组件mapper
 *
 * @author 张亮
 * @version 1.0 2018年10月08日
 * @see FinProductFeeInfoMapper
 * @since 1.0
 */
@Repository
public interface FinProductFeeInfoMapper {
	
	/**
	 * 查询费用信息
	 * @param map 参数
	 * @return 费用信息下拉框
	 */
	List<SelectOptionDto> queryFeeInfoList(Map<String, Object> map);
	
	/**
	 * 查询产品费用列表信息
	 * @param map 参数
	 * @return 费用列表信息
	 */
	List<BussFinProductFeeInfoDto> queryFeeInfoByFinprodId(Map<String, Object> map);


	/**
	 * 查询通道费用列表信息
	 * @param map 参数
	 * @return 费用列表信息
	 */
	List<BussFinProductFeeInfoDto> queryFeeInfoByFinprodIdForChannel(Map<String, Object> map);
	/**
	 * 查询产品费用列表信息包含浮动管理费
	 * @param map 参数
	 * @return 费用列表信息
	 */
	List<BussFinProductFeeInfoDto> queryFeeInfoByFinprodIdEx(Map<String, Object> map);
	
	/**
	 * 根据参数查询付费计划
	 * @param map 参数
	 * @return 付费计划信息
	 */
	List<FinCashPlan> queryFeePlanListByFinprodId(Map<String, Object> map);
	
	/**
     * 根据组合代码查询产品费用列表信息
     * @param map 参数
     * @return 费用列表信息
     */
	List<BussFinProductFeeInfoDto> queryFeeInfoByPtlId(Map<String, Object> map);
	
	/**
	 * 查询资金账户费用信息
	 * @param map 参数
	 * @return 费用信息下拉框
	 */
	List<SelectOptionDto> queryFundFeeInfoList(Map<String, Object> map);
	
	  /**
     * 获取账号信息
     * @param acctNo 账号
     * @param ccy 币种
     * @return 清算账户表实体类
     * @see
     * @since 1.0
     */
    StlCapacctInfo getCctInfo(@Param("acctNo")String acctNo,@Param("ccy")String ccy);
 
	BussFinProductFeeInfoDto queryMstFeeinfoForRateAdjByFeeId(Map<String, Object> map);
}
