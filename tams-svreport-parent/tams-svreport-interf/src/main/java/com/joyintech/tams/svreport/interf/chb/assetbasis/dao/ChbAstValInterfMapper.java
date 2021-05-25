/*
 * 文件名：GetInvestorsHoldInfoRegistAutoSendCBMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年9月25日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.svreport.interf.chb.assetbasis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbAstValue;



/**
 * 资产估值信息mapper
 * @author 张忠良
 * @version 1.0 2019年1月28日
 * @see ChbAstValInterfMapper
 * @since 1.0
 */
@Repository
public interface ChbAstValInterfMapper {
	/**
	 * 批量插入信息表
	 * @param investHoldList
	 * @return
	 */
	int insertInfo(ChbAstValue chbAstValue);
	
	
	/**
	 * 删除登记表数据
	 * @param investHoldList
	 * @return
	 */
	int deleteInfo(Map<String,Object> map);
	
	List<ChbAstValue> queryAssetValueInfoList(@Param("generateDate")String generateDate,@Param("exchangeSource")String exchangeSource,@Param("isBtm")String isBtm,@Param("InstitutionCode")String InstitutionCode);

	/**
	 * 检查今日是否有估值数据
	 * @param generateDate
	 * @return
	 */
	int checkTdyValData(@Param("generateDate")String generateDate);

	/**
	 * 根据最近一日数据生成今日估值数据
	 * @param generateDate
	 * @return
	 */
	int insertTdyInfo(@Param("generateDate")String generateDate);
}
