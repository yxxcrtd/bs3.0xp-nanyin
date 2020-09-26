package com.joyintech.tams.svreport.interf.chb.prodinfo.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.ChbPrdOpensch;

public interface ChbPrdOpenschInterfMapper {
	/**
	 * @Description 根据产品代码删除开放计划
	 * @param prodid
	 * @return int
	 */
	int deletePrdOpensch(@Param("prodidlist")List<String> prodidlist);
	
	/**
	 * @Description 批量插入开放计划
	 * @param openschlist
	 * @return int
	 */
	int insertBatchList(@Param("openschlist")List<ChbPrdOpensch> openschlist);
	
	void insert(ChbPrdOpensch sch);
	
	/**
	 * @Description 根据产品代码查找开放计划
	 * @param plantype
	 * @param prodidlist
	 * @return List<ChbPrdOpensch>
	 */
	List<ChbPrdOpensch> selectOpenSchData(@Param("plantype")String plantype,@Param("prodidlist") List<String> prodidlist);
	
	/**
	 * @Description 根据创建时间和更新时间查询产品代码
	 * @param updatetime
	 * @return List<String>
	 */
	List<String> selectOpenSchProdid(@Param("updatetime") Date updatetime,@Param("prodidList") List<String> prodidlist);

	void deleteAllData();
	
	int deleteTodayData();
}
