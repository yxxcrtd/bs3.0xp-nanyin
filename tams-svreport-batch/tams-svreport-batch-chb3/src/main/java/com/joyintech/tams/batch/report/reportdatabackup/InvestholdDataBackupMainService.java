package com.joyintech.tams.batch.report.reportdatabackup;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.report.reportdatabackup.dao.ReportDataBackupMapper;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.interf.common.constant.DateUtil;

/**
 * 投资者持有信息登记数据归档批处理
 * 青银理财专用
 * @author DENGYU
 * @version 1.0 2021年06月22日
 * @see InvestholdDataBackupMainService
 * @since 1.0
 */
@Service
public class InvestholdDataBackupMainService extends AutoSendCBBaseService {
	
    	/*** 数据库配置*/
        @Value("${base.datasource.username}")
        public String tableOwner;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ReportDataBackupMapper mapper;

	/**
	 * 需要归档的表
	 */
	private static final String[] DATA_BACKUP_TABLENAME = new String[]{
			"CHB_INVESTHOLD_CHECK_", 
			"CHB_INVESTHOLD_INFO_",
			"CHB_SUBMIT_STATUS_",
			"CHB_INVESTHOLD_INFO_SUB_"
			};

	public void generateRegistData(String exeDateStr) {

		setBatchBeanName(BatchBeanName.投资者报送数据归档);

		try {
			logger.info("投资者持有信息数据归档开始---begin. ");
			
			// 缓存中获取原表中保留多少天数据
			String dataBackupDays = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,
					ReportGlobalConstant.DATA_BACKUP_DAYS);

			// 归档日期
			String dataBackupDate = DateUtil.addDay(exeDateStr, -1 * Integer.parseInt(dataBackupDays));

			int month = DateUtil.getMonth(exeDateStr);
			int year = DateUtil.getYear(exeDateStr);

			// 查询归档日期（不管是否未报送）
			Map<String,Date> map = mapper.getDateByBackupDataEx(dataBackupDate);
			
			if (map!=null && !map.isEmpty()) {
				// CHB_INVESTHOLD_INFO_CHECK
				doDataBackupMethod(dataBackupDate, year, month, 0, map);
				// CHB_INVESTHOLD_INFO
				doDataBackupMethod(dataBackupDate, year, month, 1, map);
				// CHB_SUBMIT_STATUS
				doDataBackupMethod(dataBackupDate, year, month, 2, map);
				// CHB_INVESTHOLD_INFO_SUB
				doDataBackupMethod(dataBackupDate, year, month, 3, map);
			} else {
				logger.info("投资者持有信息无数据，跳过归档. ");
			}

			logger.info("投资者持有信息数据归档结束---end. ");
		} catch (Exception e) {
			logInfo(e.getMessage());
			logger.error("投资者持有信息数据归档批处理Error", e);
			throw e;
		}
	}

	/**
	 * 
	 * @param dataBackupDate
	 *            归档日期
	 * @param year
	 *            归档所属年
	 * @param month
	 *            归档所属月
	 * @param index
	 *            归档表
	 * @param map
	 */
	public void doDataBackupMethod(String dataBackupDate, int year, int month, int index, Map<String, Date> map) {
		
		String tableName = DATA_BACKUP_TABLENAME[index] + year + month;
		 
		String minDate =DateUtil.format(map.get("MINDATE"),"yyyy-MM-dd");
		
		String maxDate =DateUtil.format(map.get("MAXDATE"),"yyyy-MM-dd");
		
		if (index == 0) {// CHB_INVESTHOLD_INFO_CHECK_（FIXME 标识符过长改造了）
				logger.info("CHB_INVESTHOLD_INFO_CHECK 归档开始.");
				// FIXME ADD BY DENGYU 此写法不会创建备份表的主键，索引都会丢失，如果需要查询归档数据，此处需要改造
				Map<String,Object> callMap = new HashMap<>();
				callMap.put("p_tableName", tableName);
				callMap.put("p_minDate", minDate);
				callMap.put("p_maxDate", maxDate);
				callMap.put("p_tableOwner", tableOwner.toUpperCase());
				callMap.put("p_oriTableName", "CHB_INVESTHOLD_INFO_CHECK");
				callMap.put("p_index", index);
				callBackUp(callMap);
				logger.info("CHB_INVESTHOLD_INFO_CHECK 归档结束.");
				//增加索引分析
				logger.info("CHB_INVESTHOLD_INFO_CHECK 表索引分析开始.");
				mapper.gathertableStatistics(tableOwner.toUpperCase(), "CHB_INVESTHOLD_INFO_CHECK");
				mapper.gatherindexStatistics(tableOwner.toUpperCase(), "CHB_INVESTHOLD_INFO_CHECK_PK");
				mapper.gatherindexStatistics(tableOwner.toUpperCase(), "CHB_INVESTHOLD_INFO_CHECK_2");
				logger.info("CHB_INVESTHOLD_INFO_CHECK 表索引分析结束.");
			}else if (index == 1) {
				logger.info("CHB_INVESTHOLD_INFO 归档开始.");
				Map<String,Object> callMap = new HashMap<>();
				callMap.put("p_tableName", tableName);
				callMap.put("p_minDate", minDate);
				callMap.put("p_maxDate", maxDate);
				callMap.put("p_oriTableName", "CHB_INVESTHOLD_INFO");
				//callMap.put("p_tableOwner", tableOwner.toUpperCase());
				callMap.put("p_index", index);
				callBackUp(callMap);
				logger.info("CHB_INVESTHOLD_INFO 归档结束.");
				//增加分析
				logger.info("CHB_INVESTHOLD_INFO 表索引分析开始.");
				mapper.gathertableStatistics(tableOwner.toUpperCase(), "CHB_INVESTHOLD_INFO");
				
				mapper.gatherindexStatistics(tableOwner.toUpperCase(), "PK_CHB_INVESTHOLD_INFO");
				mapper.gatherindexStatistics(tableOwner.toUpperCase(), "I_CHB_INVESTHOLD_INFO_1");
				mapper.gatherindexStatistics(tableOwner.toUpperCase(), "I_CHB_INVESTHOLD_INFO_2");
				mapper.gatherindexStatistics(tableOwner.toUpperCase(), "I_CHB_INVESTHOLD_INFO_SUB_1");
				logger.info("CHB_INVESTHOLD_INFO 表索引分析结束.");
			} else if (index == 2) {
				logger.info("CHB_SUBMIT_STATUS 归档开始.");
				Map<String,Object> callMap = new HashMap<>();
				callMap.put("p_tableName", tableName);
				callMap.put("p_minDate", minDate);
				callMap.put("p_maxDate", maxDate);
				callMap.put("p_oriTableName", "CHB_SUBMIT_STATUS");
				//callMap.put("p_tableOwner", tableOwner.toUpperCase());
				callMap.put("p_index", index);
				callBackUp(callMap);
				logger.info("CHB_SUBMIT_STATUS 归档结束.");
				
				//增加索引分析
				logger.info("CHB_SUBMIT_STATUS 表索引分析开始.");
				mapper.gathertableStatistics(tableOwner.toUpperCase(), "CHB_SUBMIT_STATUS");
				
				mapper.gatherindexStatistics(tableOwner.toUpperCase(), "PK_CHB_SUBMIT_STATUS");
				logger.info("CHB_SUBMIT_STATUS 表索引分析结束.");
		 
		} else if (index == 3) {
			 
			logger.info("CHB_INVESTHOLD_INFO_SUB 归档开始.");
			Map<String,Object> callMap = new HashMap<>();
			callMap.put("p_tableName", tableName);
			callMap.put("p_minDate", minDate);
			callMap.put("p_maxDate", maxDate);
			callMap.put("p_oriTableName", "CHB_INVESTHOLD_INFO_SUB");
			//callMap.put("p_tableOwner", tableOwner.toUpperCase());
			callMap.put("p_index", index);
			callBackUp(callMap);
			logger.info("CHB_INVESTHOLD_INFO_SUB 归档结束.");
			
			//增加索引分析
			logger.info("CHB_INVESTHOLD_INFO_SUB 表索引分析开始.");
			mapper.gathertableStatistics(tableOwner.toUpperCase(), "CHB_INVESTHOLD_INFO_SUB");
			
			mapper.gatherindexStatistics(tableOwner.toUpperCase(), "I_INVEST_BATCH");
			mapper.gatherindexStatistics(tableOwner.toUpperCase(), "I_CHB_INVESTHOLD_INFO_SUB");
			mapper.gatherindexStatistics(tableOwner.toUpperCase(), "I_CHB_INVESTHOLD_INFO_SUB_2");
			mapper.gatherindexStatistics(tableOwner.toUpperCase(), "PK_CHB_INVESTHOLD_INFO_SUB");
			mapper.gatherindexStatistics(tableOwner.toUpperCase(), "I_CHB_INVESTHOLD_INFO_SUB_3");
			logger.info("CHB_INVESTHOLD_INFO_SUB 表索引分析结束.");
		}
	}
	
//	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
//	public  void callBackUp(Map<String,Object> callMap){
//	     mapper.callChbInvesthold(callMap);
//	}
}
