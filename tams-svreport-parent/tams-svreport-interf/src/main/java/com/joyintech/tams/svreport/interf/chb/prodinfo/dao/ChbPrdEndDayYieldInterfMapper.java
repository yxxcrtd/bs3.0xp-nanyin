package com.joyintech.tams.svreport.interf.chb.prodinfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.ChbPrdPeriodYield;

public interface ChbPrdEndDayYieldInterfMapper {

	/**
	 * @Description 根据更新时间查询每日日终接取收益信息
	 * @param updatetime
	 * @return List<ChbPrdPeriodYield>
	 */
	List<ChbPrdPeriodYield> selectPrdPeriodYieldData(@Param("updatetime")String updatetime);
	
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
	
	/**
	 * 判断当日是否有需要导入的变动数据
	 * @param batchDate
	 * @return
	 */
	int isChange(@Param("batchDate")String batchDate);
}
