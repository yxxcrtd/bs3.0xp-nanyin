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

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PtlGoal;

/**
 * 产品投向描述业务组件mapper
 *
 * @author 张亮
 * @version 1.0 2018年10月08日
 * @see FinProductGoalInfoMapper
 * @since 1.0
 */
@Repository
public interface FinProductGoalInfoMapper {
	
	/**
	 * 根据金融产品代码查询产品投向信息
	 * @param finprodId 金融产品代码
	 * @return 产品投向信息
	 */
	List<PtlGoal> queryFinProdGoalInfo(String finprodId);
    
	/**
	 * 根据金融产品代码删除产品投向信息
	 * @param finprodId 金融产品代码
	 * @return DB影响行数
	 */
	int deleteFinProdGoalInfo(String finprodId);
}
