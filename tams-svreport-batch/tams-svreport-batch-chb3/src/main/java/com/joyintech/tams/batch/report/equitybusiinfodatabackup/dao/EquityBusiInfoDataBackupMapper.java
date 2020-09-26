package com.joyintech.tams.batch.report.equitybusiinfodatabackup.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 投资者明细信息数据归档批处理mapper
 * 
 * @author zhengxiaolian
 * @version 1.0 2019年12月5日
 * @see EquityBusiInfoDataBackupMapper
 * @since 1.0
 */
@Repository
public interface EquityBusiInfoDataBackupMapper {
	/**
     * 根据表名查出所有分区名和对应的月份
     */
    List<String> queryAllPartition(@Param("tableName")String tableName);
    
	/**
	 * 创建CHB_EQUDET_SUB归档表
	 * @param newTableName
	 * @param tableName
	 */
	void createBackupTable(@Param("newTableName") String newTableName,@Param("tableName") String tableName);
	
	/**
	 * 将数据插入备份表
	 * @param NewTableName归档表
	 * @param tableName表名
	 * @param partitionName分区名  
	 */
	int  insertBackupData(@Param("newTableName") String newTableName,
						@Param("tableName") String tableName, @Param("partitionName") String partitionName);
	
	/**
	 * 删除表分区
	 * @param tableName  
	 * @param partitionName 
	 */
	int  deleteTablePartition(@Param("tableName") String tableName,@Param("partitionName") String partitionName);
	
	/**
	 * 重建索引
	 * @param tableName  
	 * @param partitionName 
	 */
	int rebuildIndex(@Param("indexName") String indexName);
	
	
	String getPartitionTimeByName(@Param("tableName") String tableName,@Param("partitionName") String partitionName);
}
