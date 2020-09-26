package com.joyintech.tams.batch.report.equitybusiinfodatabackup.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.report.equitybusiinfodatabackup.dao.EquityBusiInfoDataBackupMapper;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;


/**
 * 投资者明细信息数据归档批处理service
 * 
 * @author zhengxiaolian
 * @version 1.0 2019年12月5日
 * @see EquityBusiInfoDataBackupMainService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EquityBusiInfoDataBackupMainService extends AutoSendCBBaseService {
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 投资者明细信息数据归档批处理业务mapper
	 */
	@Autowired
	private EquityBusiInfoDataBackupMapper mapper;

	/**
	 * 需要归档的表名
	 */
	private static final String[] DATA_BACKUP_TABLENAME = new String[] { "CHB_EQUDET", "CHB_EQUDET_SUB",
			"CHB_EQUDET_SUBMIT_STATUS" };

	/**
	 * 主键索引名
	 */
	private static final String[] TABLENAME_INDEXNAME_ONE = new String[] { "PK_CHB_EQUDET", "PK_CHB_EQUDET_SUB",
			"PK_CHB_EQUDET_SUBMIT_STATUS" };

	/**
	 * 日期索引名
	 */
	private static final String[] TABLENAME_INDEXNAME_TWO = new String[] { "INDEX_CHB_EQUDET_DATE",
			"INDEX_CHB_EQUDET_SUB_DATE", "INDEX_CHB_EQUDET_SUBMIT_DATE" };

	/**
	 * 数据归档处理方法
	 * 
	 * @param executeDate
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	public void doDataBackup(Date executeDate)  {
		setBatchBeanName(BatchBeanName.投资者明细信息数据归档批处理);
			logInfoBatchStart();

			String executeDateStr = DateUtil.format(executeDate);
			// 1.先从缓存中要保留的月份数
			String dataBackupMonths = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,
					ReportGlobalConstant.DATA_BACKUP_MONTHS);
			// 2.计算归档截止月（含）例如：12月进行归档批处理操作，保留3个月数据，则截止到9月（含）的数据都被归档
			Date dataBackupMonth;
            try {
                dataBackupMonth = DateUtil.addMonthDateStr(executeDateStr, -1 * Integer.parseInt(dataBackupMonths));
                doDataBackupDB(dataBackupMonth, 0);
                doDataBackupDB(dataBackupMonth, 1);
                doDataBackupDB(dataBackupMonth, 2);
            }
            catch (NumberFormatException | ParseException e) {
                e.printStackTrace();
            }


			logInfoBatchEnd();
	}

	/**
	 * 
	 * @param dataBackupMonth 归档截止月（含）
	 * @param flag
	 */
	private void doDataBackupDB(Date dataBackupMonth, int flag) {
		// CHB_EQUDET flag=0
		// CHB_EQUDET_SUB flag=1
		// CHB_EQUDET_SUBMIT_STATUS flag=2
		String tableName = DATA_BACKUP_TABLENAME[flag];
		// 找出所有表分区
		
		
		//List<Map<String, String>> partitionDataList = mapper.queryAllPartition(tableName);
		
		List<Map<String, String>> partitionDataList=new ArrayList<Map<String, String>>();
		List<String>partitionList=mapper.queryAllPartition(tableName);
		
		for(String partitionName:partitionList){
			Map<String,String>map=new HashMap<String,String>();
			//根据分区找出数据的日期
			String partitionTime=mapper.getPartitionTimeByName(tableName,partitionName);
			if(partitionTime!=null){
				map.put("PARTITIONNAME", partitionName);
				map.put("PARTITIONTIME", partitionTime);
				partitionDataList.add(map);
			}
		}
		
		if (CollectionUtils.isNotEmpty(partitionDataList)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 是否需要重建索引
			Boolean needRebuileIndex = false;
			for (Map<String, String> map : partitionDataList) {
				//String partitionTimeStr = sdf.format(map.get("PARTITIONTIME"));
				String partitionTimeStr = map.get("PARTITIONTIME");
				// 归档月份的第一天
				Date firstDate = DateUtil.getFirstDay(dataBackupMonth);
				Date partitionDate = null;
				partitionDate = DateUtil.parse(partitionTimeStr);
				Date partitionFirstDate=DateUtil.getFirstDay(partitionDate);
				if (DateUtil.compareDate(partitionFirstDate, firstDate) <= 0) {
					// 需要归档的表分区,按月创建归档表
					int year = DateUtil.getYear(DateUtil.format(partitionDate));
					int month = DateUtil.getMonth(DateUtil.format(partitionDate));
					String NewTableName = tableName  + year + month;
					//按月创建归档表
					logger.info("创建" + NewTableName + "表开始！");
					mapper.createBackupTable(NewTableName, tableName);
					logger.info("创建" + NewTableName + "表结束！");
					// 将表分区数据插入归档表
					String cesPartitionName = map.get("PARTITIONNAME");
					logger.info("插入" + tableName + "表" + month + "月数据归档至" + NewTableName + "表开始！");
					mapper.insertBackupData(NewTableName, tableName, cesPartitionName);
					logger.info("插入" + tableName + "表" + month + "月数据归档至" + NewTableName + "表结束！");
					// 删除表分区，清除原表数据
					logger.info("删除" + tableName + "表" + year + month + "月份分区数据开始！");
					mapper.deleteTablePartition(tableName, cesPartitionName);
					logger.info("删除" + tableName + "表" + year + month + "月份分区数据结束！");
					// 删除分区之后需要重建分区
					needRebuileIndex = true;
				}
			}
			if (needRebuileIndex) {
				String indexNameOne = TABLENAME_INDEXNAME_ONE[flag];
				String indexNameTwo = TABLENAME_INDEXNAME_TWO[flag];
				logger.info("重建" + tableName + "表索引开始！");
				mapper.rebuildIndex(indexNameOne);
				mapper.rebuildIndex(indexNameTwo);
				logger.info("重建" + tableName + "表索引结束！");
			} else {
				// 没有做过归档
				logger.info("没有需要归档的表分区！");
			}
		}
	}

}
