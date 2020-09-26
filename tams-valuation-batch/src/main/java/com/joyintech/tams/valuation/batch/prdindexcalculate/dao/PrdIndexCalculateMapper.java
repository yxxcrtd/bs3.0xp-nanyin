/*
 * 文件名：PrdIndexCalculateMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年12月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prdindexcalculate.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokAcctLayeringInfo;
import com.joyintech.tams.common.base.entity.RepProdIndex;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dto.BokValTableDataForIndexDto;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dto.FinBenchmarklDto;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dto.PrdIndexCalculateReaderIntoResult;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dto.PrdInfoDto;


/**
 * 信息披露-产品指标计算-数据库数据获取
 * 
 * @author yangjian
 * @version 1.0 2018年12月21日
 * @see PrdIndexCalculateMapper
 * @since 1.0
 */
@Repository
public interface PrdIndexCalculateMapper {

	/**
	 * 查询reader返回数据 - 指定帐套的核算主体和TA产品
	 * 
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<PrdIndexCalculateReaderIntoResult> getIntoReaderResult(Map<String, Object> map);
	
	/**
	 * 删除表【REP_PROD_INDEX】数据
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	int deleteTableDate(Map<String, Object> map);
	
	/**
	 * 根据产品id，获取金融产品信息
	 * @return
	 * @see
	 * @since 1.0
	 */
	PrdInfoDto getPrdInfo(@Param("prdId")String prdId);
	
	/**
	 * 获取估值表指标数据
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<BokValTableDataForIndexDto> getBokValTableDataForIndex(Map<String, Object> map);
	
	/**
	 * 从科目余额表中获取【产品指标表.当日本期利润已实现】
	 * @param booksetId
	 * @param balanceDate
	 * @return
	 * @see
	 * @since 1.0
	 */
	BigDecimal getProfitCurrentYesFromBlance(@Param("booksetId")String booksetId,@Param("balanceDate")Date balanceDate);
	
	/**
	 * 从科目余额表中获取【产品指标表.当日本期利润未实现】
	 * @param booksetId
	 * @param balanceDate
	 * @return
	 * @see
	 * @since 1.0
	 */
	BigDecimal getProfitCurrentNoFromBlance(@Param("booksetId")String booksetId,@Param("balanceDate")Date balanceDate);
	
	/**
	 * 从科目余额表中获取【产品指标表.当日可供分配利润】
	 * @param booksetId
	 * @param balanceDate
	 * @return
	 * @see
	 * @since 1.0
	 */
	BigDecimal getProfitTdyAbleFromBlance(@Param("booksetId")String booksetId,@Param("balanceDate")Date balanceDate);
    
	/**
	 * 货币型
	 * 从产品指标表中获取前一日数据
	 * @param bookDate
	 * @param prodId
	 * @return
	 * @see
	 * @since 1.0
	 */
	RepProdIndex   selectRepProdIndexbypd(Map<String, Object> paramMap);
	
	/**
	 * 净值型型
	 * 从产品指标表中获取前一日数据
	 * @param bookDate
	 * @param prodId
	 * @return
	 * @see
	 * @since 1.0
	 */
	RepProdIndex   selectRepProdIndexNetworth(Map<String, Object> paramMap);
	
	
	/**
	 * 获取节假日中是节假日的日期
	 * 
	 */
	List<Date>  slectIsHoliday(String prodId);
	
	
	FinBenchmarklDto  getFloatBaseValue(Map<String, Object> paramMap);
	
	
	
	/**
	 * 获取预期收益型产品预期收益率
	 * @param map
	 * @return
	 */
	BigDecimal getYeildE(Map<String,Object> map);
	
	
	
	
	List<Map<String,Object>> queryFormGrant(Map<String,Object> map);
	
	
	
	/**
	 * 获取预期收益型产品预期收益率
	 * @param map
	 * @return
	 */
	BigDecimal queryRedemIntamtTdy(Map<String,Object> map);
	/**
	 * 根据产品id，获取金融产品信息（语气收益型）
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<PrdInfoDto> getPrdInfoYQ(@Param("prdId")String prdId);
	
	/**
	 * 取产品分层信息
	 * @param map
	 * @return
	 */
	List<BokAcctLayeringInfo>   getBokAcctLayeringInfo(Map<String,Object> map);
}
