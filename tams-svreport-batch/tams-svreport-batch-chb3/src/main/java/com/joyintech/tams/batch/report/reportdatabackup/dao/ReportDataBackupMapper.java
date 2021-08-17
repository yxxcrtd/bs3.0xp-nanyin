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
	/**
	 * 通过表名查看库中的表名是否存在
	 * @param tableName 表名
	 * @return 表名
	 */
	String queryTableIsExist(String tableName);
	/**
	 * 创建chb_info_report备份表
	 * @param newTableName
	 */
	void createChbInfoReport(@Param("newTableName") String newTableName);
	/**
	 * 插入chb_info_report备份表
	 * @param newTableName
	 * @param dataBackupDate  
	 */
	int  insertChbInfoReportData(@Param("newTableName") String newTableName,
            @Param("dataBackupDate") String dataBackupDate);
	/**
	 * 删除chb_info_report备份表
	 * @param dataBackupDate  
	 */
	int  deleteChbInfoReportData(@Param("dataBackupDate") String dataBackupDate);
	/**
	 * 创建chb_submit_status备份表
	 * @param newTableName
	 */
	void createChbSubmitStatus(@Param("newTableName") String newTableName);
	/**
	 * 插入chb_submit_status备份表
	 * @param newTableName
	 * @param dataBackupDate  
	 */
	int  insertChbSubmitStatusData(@Param("newTableName") String newTableName,
            @Param("dataBackupDate") String dataBackupDate);
	/**
	 * 删除chb_submit_status备份表
	 * @param dataBackupDate  
	 */
	int  deleteChbSubmitStatusData(@Param("dataBackupDate") String dataBackupDate);
	/**
	 * 创建chb_investhold_info备份表
	 * @param newTableName
	 */
	void createChbInvestHoldInfo(@Param("newTableName") String newTableName);
	/**
	 * 插入chb_investhold_info备份表
	 * @param newTableName
	 * @param dataBackupDate  
	 */
	int  insertChbInvestHoldInfoData(@Param("newTableName") String newTableName,
            @Param("dataBackupDate") String dataBackupDate);
	/**
	 * 删除chb_investhold_info备份表
	 * @param dataBackupDate  
	 */
	int  deleteChbInvestHoldInfoData(@Param("dataBackupDate") String dataBackupDate);
	/**
	 * 创建chb_investhold_info_sub备份表
	 * @param newTableName
	 */
	void createChbInvestHoldInfoSub(@Param("newTableName") String newTableName);
	/**
	 * 插入chb_investhold_info_sub备份表
	 * @param newTableName
	 * @param dataBackupDate  
	 */
	int  insertChbInvestHoldInfoSubData(@Param("newTableName") String newTableName,
            @Param("dataBackupDate") String dataBackupDate);
	/**
	 * 删除chb_investhold_info_sub备份表
	 * @param dataBackupDate  
	 */
	int  deleteChbInvestHoldInfoSubData(@Param("dataBackupDate") String dataBackupDate);
	
	int isExistBackupData(@Param("dataBackupDate") String dataBackupDate);
	/**
	 * 获取需要归档的日期列表
	 * @param dataBackupDate 归档日
	 * @return
	 */
	List<Date> getDateByBackupData(@Param("dataBackupDate") String dataBackupDate);
	
	Map<String,Date> getDateByBackupDataEx(@Param("dataBackupDate") String dataBackupDate);
	/**
	 * 创建临时表
	 * @param newTableName 临时表名称
	 */
	void insertChbSubmitStatusTemp(@Param("newTableName") String newTableName, @Param("dataBackupDate") String dataBackupDate);
	/**
	 * 清理原表
	 * @param tableName 表名
	 */
	void truncateTable(@Param("tableName") String tableName);
	/**
	 * 将备份的temp表中数据移至原表中
	 * @param newTableName 原表
	 * @param tempTableName temp表
	 */
	void insertDataToOriTable(@Param("newTableName") String newTableName,@Param("tempTableName") String tempTableName);
	/**
	 * drop临时表
	 * @param newTableName 需要drop的临时表名
	 */
	void dropTempTable(@Param("newTableName") String newTableName);
	/**
	 * 创建chb_investhold_info备份表
	 * @param newTableName
	 */
	void insertChbInvestHoldInfoTemp(@Param("newTableName") String newTableName, @Param("dataBackupDate") String dataBackupDate);
	/**
	 * 创建chb_investhold_info备份表
	 * @param newTableName
	 */
	void insertChbInvestHoldInfoSubTemp(@Param("newTableName") String newTableName, @Param("dataBackupDate") String dataBackupDate);
// add by zcw 增加处理分区逻辑 start
	/**
	 * 删除表分区
	 * @param tableName
	 * @param partitionName
	 */
	void  deleteTablePartition(@Param("tableName") String tableName, @Param("date") String date);

	/**
	 * 重建索引
	 * @param tableName
	 * @param partitionName
	 */
	void rebuildIndex(@Param("indexName") String indexName);
    // add by zcw 增加处理分区逻辑 end
	
	//表分析
	void gathertableStatistics(@Param("tableOwner") String tableOwner,@Param("tableName") String tableName);
	//索引分析
	void gatherindexStatistics(@Param("tableOwner") String tableOwner,@Param("indexName") String indexName);
	//调用存过
	void callChbInvesthold(Map<String,Object> param);
	
	void callInterfAnalyzeTable();
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
	 * 插入chb_submit_status备份表
	 * @param newTableName
	 * @param startDate
	 * @param endDate
	 */
	void insertChbSubmitStatusDataEx(@Param("newTableName") String newTableName,
								   @Param("startDate") String startDate,
								   @Param("endDate") String endDate);
	
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
	/**
	 * 查询某张表下的分区快
	 * @param tableName
	 * @return
	 */
	List<String> queryPartitions(@Param("tableName")String tableName);
	
	/**
	 * 插入chb_investhold_info_check备份表
	 * @param tableName
	 * @param startDate
	 * @param endDate
	 */
	void insertChbInvestHoldInfoCheckDataEx(String tableName, String startDate, String endDate);
	
	/**
	 * 删除chb_submit_status备份表
	 * @param startDate
	 * @param endDate
	 */
	void  deleteChbSubmitStatusDataEx(@Param("startDate") String startDate,@Param("endDate") String endDate);
	

	/**
	 * 插入chb_investhold_info备份表
	 * @param newTableName
	 * @param startDate
	 * @param endDate
	 */
	void  insertChbInvestHoldInfoDataEx(@Param("newTableName") String newTableName,
									 @Param("startDate") String startDate,
									 @Param("endDate") String endDate);

	/**
	 * 插入chb_investhold_info_sub备份表
	 * @param newTableName
	 * @param startDate
	 * @param endDate
	 */
	void  insertChbInvestHoldInfoSubDataEx(@Param("newTableName") String newTableName,
										@Param("startDate") String startDate,
										@Param("endDate") String endDate);
	
	
	/**
	 * 创建check的备份表
	 * @param tableName
	 */
	void createChbInvestHoldInfoCheckEx(String tableName);
}
