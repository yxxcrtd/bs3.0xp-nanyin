package com.joyintech.tams.report.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ColumnTypeDto;


/**
 * 字段相关信息接口
 * @author Administrator
 *
 */
@Repository
public interface ColumnTypeMapper {
	/**
	 * 根据表名查询字段名和字段说明
	 * @param tableId 表名
	 * @return 字段信息
	 */
	List<ColumnTypeDto> queryColmnInfo(@Param("tableId") String tableId);
	
	/**
	 * 根据表名获取其所有字段名集合
	 * @param tableId 表名
	 * @return 所有字段名集合
	 */
	List<String> queryAllColumnByTableId(@Param("tableId") String tableId);
	
	/**
	* @Title: queryColumn
	* @Description: (根据表名获取字段名称和类型)
	* @param @param tableId
	* @param @return    参数
	* @return List<ColumnTypeDto>    返回类型
	* @throws
	*/
	List<ColumnTypeDto> queryColumn(@Param("tableId") String tableId);
	
	/**
	* @Title: queryUUID
	* @Description: 查询指定表的指定字段是否为UUID
	* @param @param tableId
	* @param @param column
	* @param @return    参数
	* @return int    返回类型
	* @throws
	*/
	    
	int queryUUID(@Param("tableId") String tableId, @Param("column") String column);
}
