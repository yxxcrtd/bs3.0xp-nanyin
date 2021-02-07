package com.joyintech.tams.svreport.interf.chb.investmentsinfo.dao;

import com.joyintech.tams.common.base.entity.ChbPrdPeriodYield;
import com.joyintech.tams.svreport.interf.chb.investmentsinfo.dto.ChbProdInfoRate;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ChbProdInfoRateInterfMapper {

	/**
	 * @Description 根据更新时间和创建时间查询报价信息
	 * @param updatetime
	 * @return List<ChbPrdPeriodYield>
	 */
	List<ChbProdInfoRate> selectProdInfoRateData(@Param("updatetime")Date updatetime, @Param("prodidList")List<String> prodidList, @Param("finprodType2List")List<String> finprodType2List);
	
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
	
	void mergeChbPodInfoRatedData(@Param("list")List<ChbProdInfoRate> list);
	
	int deleteTodayData();
}
