package com.joyintech.tams.disclosure.busicommon.poireport.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisPoiQueryDto;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisPoiSubDto;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisPoiTabDto;

@Repository
public interface PoiReportMapper {

	/**
	 * 根据报表编号查询报表基础表
	 * 
	 * @param tabNum 报表编号
	 * @return
	 */
	DisPoiTabDto selectTabInfoByTabNum(String tabNum);

	/**
	 * 根据报表编号查询报表子表集合
	 * 
	 * @param tabNum 报表编号
	 * @return
	 */
	List<DisPoiSubDto> selectSubListByTabNum(String tabNum);

	/**
	 * 查询sql集合
	 * 
	 * @param poiId 关联编号（报表编号或者子表编号）
	 * @param poiType 关联类型(02-主表,01-子表)
	 * @return
	 */
	List<DisPoiQueryDto> selectSqlList(@Param("poiId")String poiId, @Param("poiType")String poiType);

	/**
	 * 执行sql，获取list
	 * @param sql
	 * @return
	 */
	@Select("${sql}")
    List<List<String>> selectList(@Param("sql") String sql);
	
	/**
	 * 执行sql，获取map
	 * @param sql
	 * @return
	 */
	@Select("${sql}")
	Map<String,String> selectMap(@Param("sql") String sql);
	
	/**
	 * 执行sql，获取map
	 * @param sql
	 * @return
	 */
	@Select("${sql}")
	List<Map<String,String>> selectMapList(@Param("sql") String sql);
	
	/**
	 * 执行sql，获取map
	 * @param sql
	 * @return
	 */
	@Select("${sql}")
	List<Set<String>> selectSet(@Param("sql") String sql);
}
