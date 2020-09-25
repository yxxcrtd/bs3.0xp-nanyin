/*
 * 文件名：FinProductGoalInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张亮
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.PtlGoalMapper;
import com.joyintech.tams.common.base.entity.PtlGoal;
import com.joyintech.service.product.common.dao.FinProductGoalInfoMapper;

/**
 * 产品投向信息共通
 * @author 张亮
 * @version 1.0 2018年10月8日
 * @see FinProductGoalInfoService
 * @since 1.0
 */
@Service("backFinProductGoalInfoService")
public class FinProductGoalInfoService {
	
	/**
	 * {@linkplain}
	 */
	@Autowired(required=false)
	private PtlGoalMapper ptlGoalMapper;
	
	/**
	 * {@linkplain}
	 */
	@Autowired(required=false)
	private FinProductGoalInfoMapper finProductGoalInfoMapper;
	
//	/**
//	 * 插入产品投向信息
//	 * @param finProductGoalInfoDto
//	 */
//	@Transactional
//	public void insertFinProdGoalInfo(FinProductGoalInfoDto finProductGoalInfoDto){
//		if (ptlGoalMapper != null){
//			if (finProductGoalInfoDto != null){
//				List<PtlGoal> list= finProductGoalInfoDto.getFinProductPtlGoalDtoList();
//				if (!StringUtil.isEmptyList(list)){
//					for (PtlGoal ptlGoal : list){
//						ptlGoal.setFinprodId(finProductGoalInfoDto.getFinprodId());
//						ptlGoalMapper.insert(ptlGoal);
//					}
//				}
//			}
//		}
//	}
//	
//	/**
//	 * 更新产品投向信息
//	 * @param finProductGoalInfoDto
//	 */
//	@Transactional
//	public void updateFinProdGoalInfo(FinProductGoalInfoDto finProductGoalInfoDto){
//		this.deleteFinProdGoalInfo(finProductGoalInfoDto.getFinprodId());
//		this.insertFinProdGoalInfo(finProductGoalInfoDto);
//	}
	
	/**
	 * 根据金融产品代码查询产品投向信息
	 * @param finprodId 金融产品代码
	 * @return 产品投向信息
	 */
	public List<PtlGoal> queryFinProdGoalInfo(String finprodId){
		if (finProductGoalInfoMapper != null){
			return finProductGoalInfoMapper.queryFinProdGoalInfo(finprodId);
		}
		return new ArrayList<PtlGoal>();
	}

	/**
	 * 删除产品投向信息
	 * @param finProductGoalInfoDto
	 */
	@Transactional
	public void deleteFinProdGoalInfo(String finprodId){
		if (finProductGoalInfoMapper != null){
			finProductGoalInfoMapper.deleteFinProdGoalInfo(finprodId);
		}
	}
	
	
}
