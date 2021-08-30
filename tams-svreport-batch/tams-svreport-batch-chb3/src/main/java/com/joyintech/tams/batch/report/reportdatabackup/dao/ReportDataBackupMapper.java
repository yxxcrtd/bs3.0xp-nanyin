/*
 * 文件名：ReportDataBackupMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.batch.report.reportdatabackup.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;




/**
 * 数据备份mapper
 * @author wangmin
 * @version 1.0 2018年10月9日
 * @see ReportDataBackupMapper
 * @since 1.0
 */
@Repository
public interface ReportDataBackupMapper {

	int isExistBackupData(@Param("dataBackupDate") String dataBackupDate);

	/**
	 * 通过表名查看库中的表名是否存在
	 * @param tableName 表名
	 * @return 表名
	 */
	String queryTableIsExist(String tableName);

	/**
	 * 创建chb_submit_status备份表
	 * @param newTableName
	 */
	void createChbSubmitStatus(@Param("newTableName") String newTableName);

	/**
	 * 插入chb_submit_status备份表
	 * @param newTableName
	 * @param startDate
	 * @param endDate
	 */
	void insertChbSubmitStatusData(@Param("newTableName") String newTableName,
								   @Param("startDate") String startDate,
								   @Param("endDate") String endDate);
	/**
	 * 删除chb_submit_status备份表
	 * @param startDate
	 * @param endDate
	 */
	void  deleteChbSubmitStatusData(@Param("startDate") String startDate,@Param("endDate") String endDate);

	/**
	 * 创建chb_investhold_info备份表
	 * @param newTableName
	 */
	void createChbInvestHoldInfo(@Param("newTableName") String newTableName);

	/**
	 * 插入chb_investhold_info备份表
	 * @param newTableName
	 * @param startDate
	 * @param endDate
	 */
	void  insertChbInvestHoldInfoData(@Param("newTableName") String newTableName,
									 @Param("startDate") String startDate,
									 @Param("endDate") String endDate);

	/**
	 * 创建chb_investhold_info_sub备份表
	 * @param newTableName
	 */
	void createChbInvestHoldInfoSub(@Param("newTableName") String newTableName);

	/**
	 * 插入chb_investhold_info_sub备份表
	 * @param newTableName
	 * @param startDate
	 * @param endDate
	 */
	void  insertChbInvestHoldInfoSubData(@Param("newTableName") String newTableName,
										@Param("startDate") String startDate,
										@Param("endDate") String endDate);

	/**
	 * 根据备份保留时间查找需要备份的数据区间
	 * @param dataBackupDate
	 * @return
	 */
	Map<String,Date> getDateByBackupDataEx(@Param("dataBackupDate") String dataBackupDate);

	/**
	 * 关闭日志，开启并发
	 * @param newTableName
	 */
	void nologgingParrel(@Param("newTableName") String newTableName);

	/**
	 * 开启日志，关闭并发
	 * @param newTableName
	 */
	void loggingNoparallel(@Param("newTableName") String newTableName);

	/**
	 * 创建check的备份表
	 * @param tableName
	 */
	void createChbInvestHoldInfoCheck(String tableName);

	/**
	 * 插入chb_investhold_info_check备份表
	 * @param tableName
	 * @param startDate
	 * @param endDate
	 */
	void insertChbInvestHoldInfoCheckData(String tableName, String startDate, String endDate);

	/**
	 * 表分析
	 * @param tableOwner
	 * @param tableName
	 */
	void gathertableStatistics(@Param("tableOwner") String tableOwner,
							   @Param("tableName") String tableName);

	/**
	 * 索引分析
	 * @param tableOwner
	 * @param indexName
	 */
	void gatherindexStatistics(@Param("tableOwner") String tableOwner,
							   @Param("indexName") String indexName);

	/**
	 * 查询某张表下的分区快
	 * @param tableName
	 * @return
	 */
	List<String> queryPartitions(@Param("tableName")String tableName);

	/**
	 * 查询某张表的索引名称
	 * @param tableName
	 * @return
	 */
	List<String> queryIndexName(@Param("tableName")String tableName);

	/**
	 * 查询某个表某个分区块是否有数据
	 * @param tableName
	 * @param patitionName
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	int isHaveData(@Param("tableName")String tableName,
				   @Param("patitionName")String patitionName,
				   @Param("startDate")String startDate,
				   @Param("endDate")String endDate);

	/**
	 * 删除分区块数据
	 * @param tableName
	 * @param patitionName
	 */
	void dropPartition(@Param("tableName")String tableName,
					   @Param("patitionName")String patitionName);
}
