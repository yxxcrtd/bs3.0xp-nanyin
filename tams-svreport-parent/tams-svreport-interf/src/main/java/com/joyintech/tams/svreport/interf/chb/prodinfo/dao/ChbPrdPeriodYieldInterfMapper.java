package com.joyintech.tams.svreport.interf.chb.prodinfo.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.ChbPrdPeriodYield;

public interface ChbPrdPeriodYieldInterfMapper {

	/**
	 * @Description 根据更新时间和创建时间查询报价信息
	 * @param updatetime
	 * @return List<ChbPrdPeriodYield>
	 */
	List<ChbPrdPeriodYield> selectPrdPeriodYieldData(@Param("updatetime")Date updatetime,@Param("prodidList")List<String> prodidList,@Param("finprodType2List")List<String> finprodType2List);
	
	/**
	 * @Description 批量插入
	 * @param list
	 * @return int
	 */
	int insertBatchList(@Param("list")List<ChbPrdPeriodYield> list);
	
	int deleteBatch(@Param("list")List<ChbPrdPeriodYield> list);
	
	/**
	 * @Description 根据产品代码和发布日期删除该产品的报价
	 * @param fprodid
	 * @param prodid
	 * @param changedate
	 * @return int
	 */
	int deleteBatchList(@Param("prodid")String prodid,@Param("changedate")String changedate);

	void insert(ChbPrdPeriodYield yiled);
	
	int deleteBatch2(@Param("list")List<ChbPrdPeriodYield> list);
	
	void mergeChbPrdPeriodYieldData(@Param("list")List<ChbPrdPeriodYield> list);
	
	int deleteTodayData();
}
