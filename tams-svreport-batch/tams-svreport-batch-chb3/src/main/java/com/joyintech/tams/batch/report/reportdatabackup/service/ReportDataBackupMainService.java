/*
 * 文件名：GetInvestorsHoldInfoRegistAutoSendCBMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年9月25日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.batch.report.reportdatabackup.service;




import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.report.reportdatabackup.dao.ReportDataBackupMapper;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.interf.common.constant.DateUtil;

/**
 * 数据归档批处理service
 * @author wangmin
 * @version 1.0 2018年10月9日
 * @see ReportDataBackupMainService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportDataBackupMainService  extends AutoSendCBBaseService{

  
    /**
     * 投资者持有信息登记数据自动生成批处理 业务mapper
     */
    @Autowired
    private ReportDataBackupMapper mapper;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     *需要归档的表
     */
    private static final String[] DATA_BACKUP_TABLENAME = new String[] {"CHB_INFO_REPORT_", "CHB_SUBMIT_STATUS_","CHB_INVESTHOLD_INFO_","CHB_INVESTHOLD_INFO_SUB_"};
    /**
     * 需要归档的表的temp
     */
    private static final String[] DATA_BACKUP_TABLENAME_TEMP = new String[] {"CHB_INFO_REPORT_TEMP3", "CHB_SUBMIT_STATUS_TEMP3","CHB_INVESTHOLD_INFO_TEMP3",
    		"CHB_INVESTHOLD_INFO_SUB_TEMP3"};

    /**
     * 自动检查报送
     * @param executeDate 
     *
     * @see
     * @since 1.0
     */
    public void generateRegistData(String executeDateStr) {
    	
    	setBatchBeanName(BatchBeanName.投资者报送数据归档);
    	
    	try{
            logInfoBatchStart();
            /**
        	 * 1.从缓存中获取归档天数（即原表中保留多少天数据）
        	 * 2.获取当前月份，查询当前月份是否有备份表
        	 *  2.1 若有备份表，则使用insert into select
        	 *  2.2若无备份表，则根据当前日期create table_date as select
        	 * 3.完成之后，清楚归档天数前的原表中数据
        	 * 
        	 */
            //缓存中获取原表中保留多少天数据
    		String dataBackupDays = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.DATA_BACKUP_DAYS);
        	String currentDate = DateUtil.getCurrentDate();
        	//归档日期
        	String dataBackupDate = DateUtil.addDay(currentDate, -1*Integer.parseInt(dataBackupDays));
        	if(mapper.isExistBackupData(dataBackupDate)>0){
        		logger.info("归档数据中存在未报送数据，等待下一日归档！");
        	}
        	else{
        		int month = DateUtil.getMonth(currentDate);
            	int year = DateUtil.getYear(currentDate);
            	logger.info("查询需要归档的数据日期开始！");
            	List<Date> backupDate = mapper.getDateByBackupData(dataBackupDate);
            	logger.info("查询需要归档的数据日期结束！");
//            	boolean isDB2DataBase = CommonUtil.isDB2DataBase();
            	if(CollectionUtils.isNotEmpty(backupDate)){
            		//chb_info_report
                	doDataBackupDB2(dataBackupDate, year, month, 0 ,backupDate);
                	//chb_submit_status
                	doDataBackupDB2(dataBackupDate, year, month, 1 ,backupDate);
                	//chb_investhold_info
                	doDataBackupDB2(dataBackupDate, year, month, 2 ,backupDate);
                	//chb_investhold_info_sub
                	doDataBackupDB2(dataBackupDate, year, month, 3 ,backupDate);
//            		doDataBackupAllDB2(dataBackupDate, year, month,backupDate);
//            		if(isDB2DataBase){
//            			doDataBackupAllDB2(dataBackupDate, year, month,backupDate);
//            		}
//            		else{
//            			//备份全量表
//                		doBackupAllData();
//                		//分事物归档
//                		doDataBackupAll(dataBackupDate, year, month,backupDate);
//            		}
            	}
            	else {
            		logger.info("当天无归档数据！");
            	}
        	}
        	logInfoBatchEnd();
    	}
    	 catch (Exception e) {
             logInfo(e.getMessage());
			 logger.error("投资者持有信息登记数据自动生成批处理Error",e);
             insertResultLogException(DateUtil.now());
             throw e;
         }
    }
    
    public void doBackupAllData(){
    	//1.首先清除全量备份表
    	logger.info("清空全量备份表开始！");
    	mapper.truncateTable("CHB_INFO_REPORT_ALL");
    	mapper.truncateTable("CHB_INVESTHOLD_INFO_ALL");
    	mapper.truncateTable("CHB_INVESTHOLD_INFO_SUB_ALL");
    	mapper.truncateTable("CHB_SUBMIT_STATUS_ALL");
    	logger.info("清空全量备份表结束！");
    	//全量备份
    	logger.info("备份全量CHB_INFO_REPORT表开始！");
		mapper.insertDataToOriTable("CHB_INFO_REPORT_ALL", "CHB_INFO_REPORT");
    	logger.info("备份全量CHB_INFO_REPORT表结束！");
    	logger.info("备份全量CHB_INVESTHOLD_INFO表开始！");
		mapper.insertDataToOriTable("CHB_INVESTHOLD_INFO_ALL", "CHB_INVESTHOLD_INFO");
    	logger.info("备份全量CHB_INVESTHOLD_INFO表结束！");
    	logger.info("备份全量CHB_INVESTHOLD_INFO_SUB表开始！");
		mapper.insertDataToOriTable("CHB_INVESTHOLD_INFO_SUB_ALL", "CHB_INVESTHOLD_INFO_SUB");
    	logger.info("备份全量CHB_INVESTHOLD_INFO_SUB表结束！");
    	logger.info("备份全量CHB_SUBMIT_STATUS表开始！");
		mapper.insertDataToOriTable("CHB_SUBMIT_STATUS_ALL", "CHB_SUBMIT_STATUS");
    	logger.info("备份全量CHB_SUBMIT_STATUS表结束！");
    }
    
    public void doDataBackupAll(String dataBackupDate, int year, int month, List<Date> backupDate){
    	//chb_info_report
    	doDataBackup(dataBackupDate, year, month, 0 ,backupDate);
    	//chb_submit_status
    	doDataBackup(dataBackupDate, year, month, 1 ,backupDate);
    	//chb_investhold_info
    	doDataBackup(dataBackupDate, year, month, 2 ,backupDate);
    	//chb_investhold_info_sub
    	doDataBackup(dataBackupDate, year, month, 3 ,backupDate);
    }
    /**
     * 
     * @param dataBackupDate
     * @param year
     * @param month
     * @param flag
     * @param backupDate
     */
    private void doDataBackup(String dataBackupDate, int year, int month, int flag, List<Date> backupDate){
    	String tableName =DATA_BACKUP_TABLENAME[flag]+year+month;
    	String reportDataBackup = mapper.queryTableIsExist(tableName);
//    	String createTableName = DATA_BACKUP_TABLENAME[flag]+executeDateStr.replace("-", "");
    	if(flag == 0){
    		if(StringUtil.isEmpty(reportDataBackup)){
    			logger.info("创建"+ tableName +"表开始！");
        		mapper.createChbInfoReport(tableName);
        		logger.info("创建"+ tableName +"表结束！");
        	}
    		for (Date date : backupDate) {
            	logger.info("将chb_info_report表"+DateUtil.format(date)+"日数据归档至"+tableName+"表开始！");
                mapper.insertChbInfoReportData(tableName, DateUtil.format(date));
                logger.info("将chb_info_report表"+DateUtil.format(date)+"日数据归档至"+tableName+"表结束！");
            	logger.info("删除chb_info_report表"+DateUtil.format(date)+"日数据开始！");
            	mapper.deleteChbInfoReportData(DateUtil.format(date));
            	logger.info("删除chb_info_report表"+DateUtil.format(date)+"日数据结束！");
			}
    	}
    	else if(flag == 1){
    		if(StringUtil.isEmpty(reportDataBackup)){
    			logger.info("创建"+ tableName +"表开始！");
        		mapper.createChbSubmitStatus(tableName);
        		logger.info("创建"+ tableName +"表结束！");
        	}
    		for (Date date : backupDate) {
    			logger.info("插入chb_submit_status表"+DateUtil.format(date)+"日数据归档至"+tableName+"表开始！");
    			mapper.insertChbSubmitStatusData(tableName, DateUtil.format(date));
    			logger.info("插入chb_submit_status表"+DateUtil.format(date)+"日数据归档至"+tableName+"表结束！");
    		}
    		//清除temp表数据
			logger.info("清空CHB_SUBMIT_STATUS_TEMP3数据开始！");
    		mapper.truncateTable(DATA_BACKUP_TABLENAME_TEMP[flag]);
			logger.info("清空CHB_SUBMIT_STATUS_TEMP3数据结束！");
    		//首先将数据移动至temp表
			logger.info("将CHB_SUBMIT_STATUS未归档数据插入CHB_SUBMIT_STATUS_TEMP3开始！");
    		mapper.insertChbSubmitStatusTemp(DATA_BACKUP_TABLENAME_TEMP[flag], dataBackupDate);
			logger.info("将CHB_SUBMIT_STATUS未归档数据插入CHB_SUBMIT_STATUS_TEMP3结束！");
    		//清楚原表数据
			logger.info("清空CHB_SUBMIT_STATUS数据开始！");
    		mapper.truncateTable("CHB_SUBMIT_STATUS");
			logger.info("清空CHB_SUBMIT_STATUS数据结束！");
    		//将temp数据移至原表
			logger.info("将CHB_SUBMIT_STATUS_TEMP3表数据还原到CHB_SUBMIT_STATUS开始！");
    		mapper.insertDataToOriTable("CHB_SUBMIT_STATUS", DATA_BACKUP_TABLENAME_TEMP[flag]);
			logger.info("将CHB_SUBMIT_STATUS_TEMP3表数据还原到CHB_SUBMIT_STATUS结束！");
    	}
    	else if(flag == 2){
    		if(StringUtil.isEmpty(reportDataBackup)){
        		logger.info("创建"+ tableName +"表开始！");
        		mapper.createChbInvestHoldInfo(tableName);
        		logger.info("创建"+ tableName +"表结束！");
        	}
    		for (Date date : backupDate) {
        		logger.info("插入chb_investhold_info表"+DateUtil.format(date)+"日数据归档至"+tableName+"表开始！");
                mapper.insertChbInvestHoldInfoData(tableName, DateUtil.format(date));
                logger.info("插入chb_investhold_info表"+DateUtil.format(date)+"日数据归档至"+tableName+"表结束！");
    		}
    		//清除temp表数据
			logger.info("清空CHB_INVESTHOLD_INFO_TEMP3数据开始！");
    		mapper.truncateTable(DATA_BACKUP_TABLENAME_TEMP[flag]);
			logger.info("清空CHB_INVESTHOLD_INFO_TEMP3数据结束！");
    		//首先将数据移动至temp表
			logger.info("将CHB_INVESTHOLD_INFO未归档数据插入CHB_INVESTHOLD_INFO_TEMP3开始！");
    		mapper.insertChbInvestHoldInfoTemp(DATA_BACKUP_TABLENAME_TEMP[flag], dataBackupDate);
			logger.info("将CHB_INVESTHOLD_INFO未归档数据插入CHB_INVESTHOLD_INFO_TEMP3结束！");
    		//清楚原表数据
			logger.info("清空CHB_INVESTHOLD_INFO数据开始！");
    		mapper.truncateTable("CHB_INVESTHOLD_INFO");
    		logger.info("清空CHB_INVESTHOLD_INFO数据结束！");
    		//将temp数据移至原表
    		logger.info("将CHB_INVESTHOLD_INFO_TEMP3表数据还原到CHB_INVESTHOLD_INFO开始！");
    		mapper.insertDataToOriTable("CHB_INVESTHOLD_INFO", DATA_BACKUP_TABLENAME_TEMP[flag]);
    		logger.info("将CHB_INVESTHOLD_INFO_TEMP3表数据还原到CHB_INVESTHOLD_INFO结束！");
    	}
    	else if(flag == 3){
    		if(StringUtil.isEmpty(reportDataBackup)){
        		logger.info("创建"+ tableName +"表开始！");
        		mapper.createChbInvestHoldInfoSub(tableName);
        		logger.info("创建"+ tableName +"表结束！");
        	}
    		for (Date date : backupDate) {
    			logger.info("插入chb_investhold_info_sub表"+DateUtil.format(date)+"日数据归档至"+tableName+"表开始！");
            	mapper.insertChbInvestHoldInfoSubData(tableName, DateUtil.format(date));
            	logger.info("插入chb_investhold_info_sub表"+DateUtil.format(date)+"日数据归档至"+tableName+"表结束！");
    		}
    		//清除temp表数据
			logger.info("清空CHB_INVESTHOLD_INFO_SUB_TEMP3数据开始！");
    		mapper.truncateTable(DATA_BACKUP_TABLENAME_TEMP[flag]);
    		logger.info("清空CHB_INVESTHOLD_INFO_SUB_TEMP3数据开始！");
    		//首先将数据移动至temp表
    		logger.info("将CHB_INVESTHOLD_INFO_SUB未归档数据插入CHB_INVESTHOLD_INFO_SUB_TEMP3开始！");
    		mapper.insertChbInvestHoldInfoSubTemp(DATA_BACKUP_TABLENAME_TEMP[flag], dataBackupDate);
    		logger.info("将CHB_INVESTHOLD_INFO_SUB未归档数据插入CHB_INVESTHOLD_INFO_SUB_TEMP3结束！");
    		//清楚原表数据
    		logger.info("清空CHB_INVESTHOLD_INFO_SUB数据开始！");
    		mapper.truncateTable("CHB_INVESTHOLD_INFO_SUB");
    		logger.info("清空CHB_INVESTHOLD_INFO_SUB数据结束！");
    		//将temp数据移至原表
    		logger.info("将CHB_INVESTHOLD_INFO_SUB_TEMP3表数据还原到CHB_INVESTHOLD_INFO_SUB开始！");
    		mapper.insertDataToOriTable("CHB_INVESTHOLD_INFO_SUB", DATA_BACKUP_TABLENAME_TEMP[flag]);
    		logger.info("将CHB_INVESTHOLD_INFO_SUB_TEMP3表数据还原到CHB_INVESTHOLD_INFO_SUB结束！");
    	}
    }
    /**
     * 
     * @param dataBackupDate
     * @param year
     * @param month
     * @param flag
     * @param backupDate
     */
    public void doDataBackupAllDB2(String dataBackupDate, int year, int month, List<Date> backupDate){
    	//chb_info_report
    	doDataBackupDB2(dataBackupDate, year, month, 0 ,backupDate);
    	//chb_submit_status
    	doDataBackupDB2(dataBackupDate, year, month, 1 ,backupDate);
    	//chb_investhold_info
    	doDataBackupDB2(dataBackupDate, year, month, 2 ,backupDate);
    	//chb_investhold_info_sub
    	doDataBackupDB2(dataBackupDate, year, month, 3 ,backupDate);
    }
    /**
     * 
     * @param dataBackupDate
     * @param year
     * @param month
     * @param flag
     * @param backupDate
     */
    private void doDataBackupDB2(String dataBackupDate, int year, int month, int flag, List<Date> backupDate){
    	String tableName =DATA_BACKUP_TABLENAME[flag]+year+month;
    	String reportDataBackup = mapper.queryTableIsExist(tableName);
//    	String createTableName = DATA_BACKUP_TABLENAME[flag]+executeDateStr.replace("-", "");
    	if(flag == 0){
    		if(StringUtil.isEmpty(reportDataBackup)){
    			logger.info("创建"+ tableName +"表开始！");
        		mapper.createChbInfoReport(tableName);
        		logger.info("创建"+ tableName +"表结束！");
        	}
    		for (Date date : backupDate) {
            	logger.info("将chb_info_report表"+DateUtil.format(date)+"日数据归档至"+tableName+"表开始！");
                mapper.insertChbInfoReportData(tableName, DateUtil.format(date));
                logger.info("将chb_info_report表"+DateUtil.format(date)+"日数据归档至"+tableName+"表结束！");
            	logger.info("删除chb_info_report表"+DateUtil.format(date)+"日数据开始！");
            	mapper.deleteChbInfoReportData(DateUtil.format(date));
            	logger.info("删除chb_info_report表"+DateUtil.format(date)+"日数据结束！");
			}
    	}
    	else if(flag == 1){
    		if(StringUtil.isEmpty(reportDataBackup)){
    			logger.info("创建"+ tableName +"表开始！");
        		mapper.createChbSubmitStatus(tableName);
        		logger.info("创建"+ tableName +"表结束！");
        	}
    		for (Date date : backupDate) {
    			logger.info("插入chb_submit_status表"+DateUtil.format(date)+"日数据归档至"+tableName+"表开始！");
    			mapper.insertChbSubmitStatusData(tableName, DateUtil.format(date));
    			logger.info("插入chb_submit_status表"+DateUtil.format(date)+"日数据归档至"+tableName+"表结束！");
            	logger.info("删除chb_submit_status表"+DateUtil.format(date)+"日数据开始！");
            	mapper.deleteChbSubmitStatusData(DateUtil.format(date));
            	logger.info("删除chb_submit_status表"+DateUtil.format(date)+"日数据结束！");
    		}
    	}
    	else if(flag == 2){
    		if(StringUtil.isEmpty(reportDataBackup)){
        		logger.info("创建"+ tableName +"表开始！");
        		mapper.createChbInvestHoldInfo(tableName);
        		logger.info("创建"+ tableName +"表结束！");
        	}
    		for (Date date : backupDate) {
        		logger.info("插入chb_investhold_info表"+DateUtil.format(date)+"日数据归档至"+tableName+"表开始！");
                mapper.insertChbInvestHoldInfoData(tableName, DateUtil.format(date));
                logger.info("插入chb_investhold_info表"+DateUtil.format(date)+"日数据归档至"+tableName+"表结束！");
            	logger.info("删除chb_investhold_info表"+DateUtil.format(date)+"日数据开始！");
            	mapper.deleteChbInvestHoldInfoData(DateUtil.format(date));
            	logger.info("删除chb_investhold_info表"+DateUtil.format(date)+"日数据结束！");
    		}
    	}
    	else if(flag == 3){
    		if(StringUtil.isEmpty(reportDataBackup)){
        		logger.info("创建"+ tableName +"表开始！");
        		mapper.createChbInvestHoldInfoSub(tableName);
        		logger.info("创建"+ tableName +"表结束！");
        	}
    		for (Date date : backupDate) {
    			logger.info("插入chb_investhold_info_sub表"+DateUtil.format(date)+"日数据归档至"+tableName+"表开始！");
            	mapper.insertChbInvestHoldInfoSubData(tableName, DateUtil.format(date));
            	logger.info("插入chb_investhold_info_sub表"+DateUtil.format(date)+"日数据归档至"+tableName+"表结束！");
            	logger.info("删除chb_investhold_info_sub表"+DateUtil.format(date)+"日数据开始！");
            	mapper.deleteChbInvestHoldInfoSubData(DateUtil.format(date));
            	logger.info("删除chb_investhold_info_sub表"+DateUtil.format(date)+"日数据结束！");
    		}
    	}
    }
}
