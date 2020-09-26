/*
 * 文件名：InfoDisclosureReadAndGenDetailService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年12月03日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.valuation.report.common.infodisclosure.infodisclosurereadandgen.service;


import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.report.common.base.dao.ChbDeadLineMapper;
import com.joyintech.tams.valuation.report.common.constant.ReportDictCodeList;
import com.joyintech.tams.valuation.report.common.infodisclosure.infodisclosurereadandgen.dao.InfoDisclosureReadAndGenMapper;
import com.joyintech.tams.valuation.report.common.infodisclosure.infodisclosurereadandgen.entity.ProdInfoListEntity;
import com.joyintech.tams.valuation.report.common.infodisclosure.util.RevealDeadLineService;
import com.joyintech.tams.valuation.report.common.base.entity.ChbDeadLine001Dto;
import com.joyintech.tams.common.base.dao.RepInstallLogMapper;
import com.joyintech.tams.common.base.entity.RepExpectedLog;
import com.joyintech.tams.common.base.entity.RepInstallFile;
import com.joyintech.tams.common.base.entity.RepInstallLog;
import com.joyintech.tams.common.base.entity.RepJob;
import com.joyintech.tams.common.base.entity.RepJobRange;
import com.joyintech.tams.common.base.entity.RepRule;
import com.joyintech.tams.valuation.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.valuation.report.common.constant.DateUtil;


/**
 * 读取作业信息并生成待处理信息相关方法service
 * @author wangmin
 * @version 1.0 2018年12月03日
 * @see InfoDisclosureReadAndGenDetailService
 * @since 1.0
 */
@Service("valuationInfoDisclosureReadAndGenDetailService")
@Transactional(rollbackFor = Exception.class)
public class InfoDisclosureReadAndGenDetailService{
	/**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	private InfoDisclosureReadAndGenMapper infoDisclosureReadAndGenMapper;
	/**
	 * 生成截止日公共类
	 */
	@Autowired
	private RevealDeadLineService revealDeadLineService;
	/**
	 * 节假日处理mapper
	 */
	@Autowired
	private ChbDeadLineMapper chbDeadLineMapper;
	/**
	 * 日志mapper
	 */
	@Autowired
	private RepInstallLogMapper repInstallLogMapper;
    /**
     * 长sql最大一次插入或者更新条数
     */
//    @Value("${application.reportInsertLongCount}")
    public int reportInsertLongCount;
	/**
	 * 读取作业信息并生成待处理信息具体方法
	 * @param executeDateStr 跑批日期
	 * @throws Exception 异常
	 */
	public boolean readAndGenDetail(RepJob repJob, Map<String,Date> dateMap, Date executeDateStr) throws Exception{
		boolean flag = false;
		//规则表信息
		RepRule repRule = infoDisclosureReadAndGenMapper.getRepRuleInfo(repJob.getRuleId());
		if(repRule == null){
			logger.info("无满足条件的规则");
			return true;
		}
		//基准日类型为平台日时的披露截止日
		Date ptDate = null;
		//获取需要披露的产品列表
		List<ProdInfoListEntity> list = this.getRuleProdList(repJob, repRule, executeDateStr, ptDate, dateMap);
		
		ptDate = dateMap.get("ptDate");
		if(CollectionUtils.isEmpty(list)){
			return true;
		}
		//执行插操作
		flag = this.doInsert(list, ptDate, repJob, repRule, executeDateStr, false);
		return flag;
	}
	/**
	 * 获取需要披露的产品列表
	 * @param repJob 作业任务
	 * @param repRule 规则
	 * @param executeDateStr 传入日期
	 * @param ptDate  披露截止日
	 * @param dateMap 存储日期
	 * @return
	 * @throws Exception
	 */
	public List<ProdInfoListEntity> getRuleProdList(RepJob repJob, RepRule repRule, Date executeDateStr, 
			Date ptDate, Map<String,Date> dateMap) throws Exception{
		//获取作业范围list
		List<RepJobRange> repJobRangeList = infoDisclosureReadAndGenMapper.getRepJobRangeList(repJob.getJobId());
		//通过组装的条件，查询产品相关内容
		List<ProdInfoListEntity> prodList = this.getRepJobRangeByJobid(repJobRangeList, repJob.getJobId());
		//当无满足条件的产品时，返回
		if(CollectionUtils.isEmpty(prodList)){
			logger.info("无满足条件的产品");
			return new ArrayList<ProdInfoListEntity>();
		}
		List<ProdInfoListEntity> list = new ArrayList<ProdInfoListEntity>();
		if(StringUtil.equals(ReportDictCodeList.基准日_平台日.getValue(), repRule.getBasisDateType())){
			//获取披露截止日
			ptDate = revealDeadLineService.getRevDeadlineByMark(executeDateStr, repRule, dateMap);
			dateMap.put("ptDate", ptDate);
			list.addAll(prodList);
		}
		else{
			ProdInfoListEntity prodInfo = null;
			for (ProdInfoListEntity prodInfoListEntity : prodList) {
				prodInfo = new ProdInfoListEntity();
				Date revDeadDate = this.getBasisDate(prodInfoListEntity, repRule);
				int days = Integer.parseInt(repRule.getMakeValue());
				if(StringUtil.equals(ReportDictCodeList.生成时点类型_前.getValue(), repRule.getRevealType())){
					days = -1*days;
				}
				boolean isGen = false;
				if(StringUtil.equals(ReportDictCodeList.日期类型_自然日.getValue(), repRule.getMakeDateType())){
					if(DateUtil.getBetweenDayNumber(executeDateStr, revDeadDate )==days){
						isGen = true;
					}
				}
				else if(revealDeadLineService.isWorkDay(revDeadDate, ReportDictCodeList.交易市场_中国法定节假日.getValue())){
					isGen = revealDeadLineService.isCanGen(revDeadDate, repRule, dateMap, executeDateStr);
				}
				prodInfoListEntity.setBasisDate(revDeadDate);
				if(isGen){
					prodInfoListEntity.setRevDeadDate(revealDeadLineService.getRevDeadlineByMark(revDeadDate, repRule, dateMap));
					BeanUtil.copyPropertiesIgnoreNull(prodInfo, prodInfoListEntity);
					list.add(prodInfo);
				}
			}
		}		
		return list;
	}
	/**
	 * 执行插入操作
	 * @param list 产品列表
	 * @param ptDate 披露截止日
	 * @param repJob 工作
	 * @param repRule 规则
	 * @param executeDateStr 传入日期
	 * @param runAgainFlag  是否重跑标志
	 * @return
	 * @throws Exception
	 */
	public boolean doInsert(List<ProdInfoListEntity> list, Date ptDate, RepJob repJob,RepRule repRule,
			Date executeDateStr, boolean runAgainFlag) throws Exception{
		List<RepInstallFile> revealList = new ArrayList<RepInstallFile>();
		RepInstallFile repInstallFile = null;
		String seq = IDGenerator.generate();
		for (ProdInfoListEntity prodInfoListEntity : list) {
			repInstallFile = new RepInstallFile();
			//id
			repInstallFile.setId(IDGenerator.generate());
			//产品编号
			repInstallFile.setProdid(prodInfoListEntity.getProdId());
			//产品名称
			repInstallFile.setProdName(prodInfoListEntity.getProdName());
			//披露截止日
			if(StringUtil.equals(ReportDictCodeList.基准日_平台日.getValue(), repRule.getBasisDateType())){
				repInstallFile.setRevealEndDate(ptDate);
			}
			else{
				repInstallFile.setRevealEndDate(prodInfoListEntity.getRevDeadDate());
			}
			//作业编号
			repInstallFile.setJobId(repJob.getJobId());
			//作业流水号
			repInstallFile.setJobSeq(seq);
			//报表类型
			repInstallFile.setRepType(ReportDictCodeList.信息披露报表类型_报表.getValue());
			//报表编号
			repInstallFile.setRepId(repRule.getRepId());
			//报表名称
			repInstallFile.setRepName(repRule.getRepName());
			//生产文件类型
			repInstallFile.setFileType(repRule.getFileOutType());
			//披露渠道
			repInstallFile.setRevealChannel(repRule.getRevealChannel());
			//是否自动确认
			repInstallFile.setIsConfirm(repRule.getIsConfirm());
			//是否自动披露
			repInstallFile.setIsReveal(repRule.getIsReveal());
			//基准日
			Date basicDate;
			if(StringUtil.equals(ReportDictCodeList.基准日_平台日.getValue(), repRule.getBasisDateType())){
				basicDate = executeDateStr;
			}
			else{
				basicDate = prodInfoListEntity.getBasisDate();
			}
			//开始日与结束日赋值
			Map<String,Date> map = this.getDataBeginAndEndDate(repRule, basicDate);
			if(map.get("startDate")!=null){
				repInstallFile.setStartDate(map.get("startDate"));
			}
			if(map.get("endDate")!=null){
				repInstallFile.setEndDate(map.get("endDate"));
			}
			revealList.add(repInstallFile);
		}
		//若list不为空，则记录日志表
		if(CollectionUtils.isNotEmpty(revealList)){
			RepInstallLog repInstallLog = new RepInstallLog();
			repInstallLog.setId(IDGenerator.generate());
			//作业编号
			repInstallLog.setJobId(repJob.getJobId());
			//作业名称 
			repInstallLog.setJobName(repJob.getJobName());
			//作业流水号
			repInstallLog.setJobSeq(seq);
			//状态
			repInstallLog.setExecStatus(ReportDictCodeList.批处理信息披露状态_未执行.getValue());
			//status
			repInstallLog.setStatus(ReportDictCodeList.数据状态_有效.getValue());
			//重跑需要将原日志update为D状态,并且将开始结束日更新为今天
			if(runAgainFlag){
				repInstallLog.setStartDate(DateUtil.now());
				repInstallLog.setEndDate(DateUtil.now());
				repInstallLog.setLastexecDate(DateUtil.now());
				repInstallLog.setExecDate(executeDateStr);
			}
			else{
				//执行日期 
				repInstallLog.setExecDate(DateUtil.now());
			}
			repInstallLogMapper.insert(repInstallLog);
		}
		//批量插入组装信息,分批次插入
//		infoDisclosureReadAndGenMapper.insertRepInstallFileBatch(revealList);
		this.insertRepInstallFile(revealList);
		return true;
	}
	/**
	 * 预处理作业
	 * @param executeDateStr 跑批日期
	 * @throws Exception 异常
	 */
	public boolean preReadAndGenDetail(RepJob repJob, Map<String,Date> dateMap, Date executeDateStr) throws Exception{
		boolean flag = false;
		//规则表信息
		RepRule repRule = infoDisclosureReadAndGenMapper.getRepRuleInfo(repJob.getRuleId());
		if(repRule == null){
			logger.info("无满足条件的规则");
			return true;
		}
		//基准日类型为平台日时的披露截止日
		Date ptDate = null;
		
		//获取需要披露的产品列表
		List<ProdInfoListEntity> list = this.getRuleProdList(repJob, repRule, executeDateStr, ptDate, dateMap);
		
		ptDate = dateMap.get("ptDate");
		if(CollectionUtils.isEmpty(list)){
			return true;
		}
		//执行插操作
		List<RepExpectedLog> repExpectedLogList = new ArrayList<RepExpectedLog>();
		RepExpectedLog repExpectedLog = null;
		for (ProdInfoListEntity prodInfoListEntity : list) {
			repExpectedLog = new RepExpectedLog();
			//id
			repExpectedLog.setId(IDGenerator.generate());
			//作业编号
			repExpectedLog.setJobId(repJob.getJobId());
			//产品编号
			repExpectedLog.setProdid(prodInfoListEntity.getProdId());
			//产品名称
			repExpectedLog.setProdName(prodInfoListEntity.getProdName());
			//披露截止日
			if(StringUtil.equals(ReportDictCodeList.基准日_平台日.getValue(), repRule.getBasisDateType())){
				repExpectedLog.setRevealEndDate(ptDate);
			}
			else{
				repExpectedLog.setRevealEndDate(prodInfoListEntity.getRevDeadDate());
			}
			//报表类型
			repExpectedLog.setRepType(ReportDictCodeList.信息披露报表类型_报表.getValue());
			//报表编号
			repExpectedLog.setRepId(repRule.getRepId());
			//报表名称
			repExpectedLog.setRepName(repRule.getRepName());
			//生产文件类型
			repExpectedLog.setFileType(repRule.getFileOutType());
			//执行状态
			repExpectedLog.setExecStatus(ReportDictCodeList.批处理信息披露状态_未执行.getValue());
			//基准日
			Date basicDate;
			if(StringUtil.equals(ReportDictCodeList.基准日_平台日.getValue(), repRule.getBasisDateType())){
				basicDate = executeDateStr;
			}
			else{
				basicDate = prodInfoListEntity.getBasisDate();
			}
			//开始日与结束日赋值
			//开始日与结束日赋值
			Map<String,Date> map = this.getDataBeginAndEndDate(repRule, basicDate);
			if(map.get("startDate")!=null){
				repExpectedLog.setStartDate(map.get("startDate"));
			}
			if(map.get("endDate")!=null){
				repExpectedLog.setEndDate(map.get("endDate"));
			}
			repExpectedLogList.add(repExpectedLog);
		}
		//flag = this.doInsert(list, ptDate, repJob, repRule, executeDateStr);
		//分批次插入
		this.insertRepExpectedLogBatch(repExpectedLogList);
		return flag;
	}
	/**
	 * 分批次插入
	 * @param repExpectedLogList 需要插入的内容
	 */
	private void insertRepExpectedLogBatch(List<RepExpectedLog> repExpectedLogList){
		if(CollectionUtils.isEmpty(repExpectedLogList)){
			return;
		}
		//分批次插入数据库
		int maxDataCountOneTime = reportInsertLongCount;
		//插入list大小
		int dataCount = repExpectedLogList.size();
		//若需要插入的数据比最大插入的数据少，则直接全部插入
		if (dataCount <= maxDataCountOneTime) {
			infoDisclosureReadAndGenMapper.insertRepExceptedLogBatch(repExpectedLogList);
            return;
        }
		
		List<RepExpectedLog> newDataList = new ArrayList<RepExpectedLog>();
		newDataList.addAll(repExpectedLogList);
		
		// 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++ ) {
        	infoDisclosureReadAndGenMapper.insertRepExceptedLogBatch(newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
        	infoDisclosureReadAndGenMapper.insertRepExceptedLogBatch(newDataList);
        }		
	}
	/**
	 * 分批次插入组装信息表
	 * @param revealList
	 */
	private void insertRepInstallFile(List<RepInstallFile> revealList){
		if(CollectionUtils.isEmpty(revealList)){
			return;
		}
		//一次性最大插入条数
		int maxDataCountOneTime = reportInsertLongCount;
		//插入list大小
		int dataCount = revealList.size();
		//若需要插入的数据比最大插入的数据少，则直接全部插入
		if (dataCount <= maxDataCountOneTime) {
			infoDisclosureReadAndGenMapper.insertRepInstallFileBatch(revealList);
            return;
        }
		
		List<RepInstallFile> newDataList = new ArrayList<RepInstallFile>();
		newDataList.addAll(revealList);
		
		// 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++ ) {
        	infoDisclosureReadAndGenMapper.insertRepInstallFileBatch(newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
        	infoDisclosureReadAndGenMapper.insertRepInstallFileBatch(newDataList);
        }
	}
	/**
	 * 获取基准日
	 * @param prodInfoListEntity 产品相关
	 * @param repRule 规则表
	 * @return 返回基准日 
	 * @throws IntrospectionException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	private Date getBasisDate(ProdInfoListEntity prodInfoListEntity, RepRule repRule) throws NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException{
		if(StringUtil.equals(ReportDictCodeList.基准日_募集起始日.getValue(), repRule.getBasisDateType())
				||StringUtil.equals(ReportDictCodeList.基准日_实际到期日.getValue(), repRule.getBasisDateType())){
			return (Date) CommonUtil.getValueByClassNameObject(prodInfoListEntity, CommonUtil.dealString(repRule.getBasisDateType(),true), ProdInfoListEntity.class);
		}
		return null;
	}
	/**
	 * 通过规则获取开始日与结束日
	 * @param repInstallFile 结果集
	 * @param repRule 规则
	 * @param basicDate 基准日
	 * @throws ParseException  日期转换异常
	 */
	public Map<String,Date> getDataBeginAndEndDate(RepRule repRule, Date basicDate) throws ParseException{
		int dayOfWeek  = DateUtil.getWeekDay(basicDate);
		Map<String,Date> map = new HashMap<String,Date>();
		if(StringUtil.equals(ReportDictCodeList.报表数据类型_区间报表.getValue(), repRule.getDataType())){
			if(StringUtil.equals(ReportDictCodeList.取数范围_上周.getValue(), repRule.getDataRange())){
				int begin = 0;
				int end  = 0 ;
				if(StringUtil.isEmpty(repRule.getDataBegin())&&StringUtil.isEmpty(repRule.getDataEnd())){
					begin = 1;
					end = 7;
				}
				else{
					begin = Integer.parseInt(repRule.getDataBegin());
					end = Integer.parseInt(repRule.getDataEnd());
				}
				map.put("startDate", DateUtil.addDay(basicDate, begin-dayOfWeek-7));
				map.put("endDate", DateUtil.addDay(basicDate, end-dayOfWeek-7));
			}
			else if(StringUtil.equals(ReportDictCodeList.取数范围_上月.getValue(), repRule.getDataRange())){
				//上月初
				map.put("startDate", DateUtil.addMonthDate(DateUtil.getFirstDay(basicDate), -1));
//				repInstallFile.setStartDate(DateUtil.addMonthDate(DateUtil.getFirstDay(basicDate), -1));
				//上月末
				map.put("endDate", DateUtil.addDay(DateUtil.getFirstDay(basicDate), -1));
//				repInstallFile.setEndDate(DateUtil.addDay(DateUtil.getFirstDay(basicDate), -1));
			}
			else if(StringUtil.equals(ReportDictCodeList.取数范围_上季度.getValue(), repRule.getDataRange())){
				//上季度初
				map.put("startDate", DateUtil.addMonthDate(DateUtil.getFirstDayOfQuarter(basicDate), -3));
//				repInstallFile.setStartDate(DateUtil.addMonthDate(DateUtil.getFirstDayOfQuarter(basicDate), -3));
				//上季度末
				map.put("endDate", DateUtil.addDay(DateUtil.getFirstDayOfQuarter(basicDate), -1));
//				repInstallFile.setEndDate(DateUtil.addDay(DateUtil.getFirstDayOfQuarter(basicDate), -1));
			}
			else if(StringUtil.equals(ReportDictCodeList.取数范围_上半年.getValue(), repRule.getDataRange())){
				//上半年初
				map.put("startDate", DateUtil.addMonthDate(DateUtil.getFirstDayOfHalfYear(basicDate), -6));
//				repInstallFile.setStartDate(DateUtil.addMonthDate(DateUtil.getFirstDayOfHalfYear(basicDate), -6));
				//上半年末
				map.put("endDate", DateUtil.addDay(DateUtil.getFirstDayOfHalfYear(basicDate), -1));
//				repInstallFile.setEndDate(DateUtil.addDay(DateUtil.getFirstDayOfHalfYear(basicDate), -1));
			}
			else if(StringUtil.equals(ReportDictCodeList.取数范围_上年.getValue(), repRule.getDataRange())){
				//上年初
				map.put("startDate", DateUtil.addYear(DateUtil.getFirstDayOfYear(basicDate), -1));
//				repInstallFile.setStartDate(DateUtil.addYear(DateUtil.getFirstDayOfYear(basicDate), -1));
				//上年末
				map.put("endDate", DateUtil.addDay(DateUtil.getFirstDayOfYear(basicDate), -1));
//				repInstallFile.setEndDate(DateUtil.addDay(DateUtil.getFirstDayOfYear(basicDate), -1));
			}
		}
		else if(StringUtil.equals(ReportDictCodeList.报表数据类型_时点报表.getValue(), repRule.getDataType())){
			if(StringUtil.equals(ReportDictCodeList.取数范围_上一自然日.getValue(), repRule.getDataRange())){
//				repInstallFile.setEndDate(DateUtil.addDay(basicDate, -1));
				map.put("endDate", DateUtil.addDay(basicDate, -1));
			}
			else if(StringUtil.equals(ReportDictCodeList.取数范围_上一工作日.getValue(), repRule.getDataRange())){
				ChbDeadLine001Dto  chbDeadLine001Dto = chbDeadLineMapper.getChbDeadline(ReportDictCodeList.交易市场_中国法定节假日.getValue(),
						DateUtil.format(basicDate), ReportDictCodeList.生成时点类型_前.getValue(), 1);
				if(chbDeadLine001Dto != null){
//					repInstallFile.setEndDate(chbDeadLine001Dto.getBusiDate());
					map.put("endDate", chbDeadLine001Dto.getBusiDate());
				}
				else{
//					repInstallFile.setEndDate(DateUtil.addDay(basicDate, -1));
					map.put("endDate", DateUtil.addDay(basicDate, -1));
				}
			}
			else if(StringUtil.equals(ReportDictCodeList.取数范围_上月末最后一自然日.getValue(), repRule.getDataRange())){
//				repInstallFile.setEndDate(DateUtil.addDay(DateUtil.getFirstDay(basicDate), -1));
				map.put("endDate", DateUtil.addDay(DateUtil.getFirstDay(basicDate), -1));
			}
			else if(StringUtil.equals(ReportDictCodeList.取数范围_上月末最后一工作日.getValue(), repRule.getDataRange())){
				//首先获取本月初时间
				Date firstDayOfMonth = DateUtil.getFirstDay(basicDate);
				ChbDeadLine001Dto  chbDeadLine001Dto = chbDeadLineMapper.getChbDeadline(ReportDictCodeList.交易市场_中国法定节假日.getValue(),
						DateUtil.format(firstDayOfMonth), ReportDictCodeList.生成时点类型_前.getValue(), 1);
				if(chbDeadLine001Dto != null){
//					repInstallFile.setEndDate(chbDeadLine001Dto.getBusiDate());
					map.put("endDate", chbDeadLine001Dto.getBusiDate());
				}
				else{
//					repInstallFile.setEndDate(DateUtil.addDay(basicDate, -1));
					map.put("endDate", DateUtil.addDay(basicDate, -1));
				}
			}
			else if(StringUtil.equals(ReportDictCodeList.取数范围_本周.getValue(), repRule.getDataRange())){
//				repInstallFile.setEndDate(DateUtil.addDay(basicDate, Integer.parseInt(repRule.getDataValue())-dayOfWeek));
				map.put("endDate", DateUtil.addDay(basicDate, Integer.parseInt(repRule.getDataValue())-dayOfWeek));
			}
			else if(StringUtil.equals(ReportDictCodeList.取数范围_上周.getValue(), repRule.getDataRange())){
//				repInstallFile.setEndDate(DateUtil.addDay(basicDate, Integer.parseInt(repRule.getDataValue())-dayOfWeek-7));
				map.put("endDate", DateUtil.addDay(basicDate, Integer.parseInt(repRule.getDataValue())-dayOfWeek-7));
			}
		}
		return map;
	}
	/**
	 * 通过作业范围获取产品list
	 * @param repJobRangeList  作业范围list
	 * @param jobId 作业id
	 * @return
	 */
	public List<ProdInfoListEntity> getRepJobRangeByJobid(List<RepJobRange> repJobRangeList, String jobId){
		if(CollectionUtils.isEmpty(repJobRangeList)){
			return null;
		}
		StringBuffer sqlVal = new StringBuffer();
		
		//通过规则id组装并查询产品信息方法
		for (RepJobRange repJobRange : repJobRangeList) {
			String val = repJobRange.getRuleType4();
			//当为like时增加%
			if(StringUtil.equals("like", repJobRange.getRuleType2().toLowerCase())){
				val = ReportGlobalConstant.SIGN_MARK +ReportGlobalConstant.PERCENT_SIGN
						+ val + ReportGlobalConstant.PERCENT_SIGN + ReportGlobalConstant.SIGN_MARK ;
			}
			//日期增加to_date
			else if(StringUtil.equals(ReportDictCodeList.作业规则1_起息日.getValue(), repJobRange.getRuleType1())||
					StringUtil.equals(ReportDictCodeList.作业规则1_到期日.getValue(), repJobRange.getRuleType1())){
				val = "to_date" + GlobalConstant.OPEN_PARENTHESIS +  ReportGlobalConstant.SIGN_MARK 
						+ val  + ReportGlobalConstant.SIGN_MARK  + GlobalConstant.COMMA + "'yyyy-MM-dd'" + GlobalConstant.CLOSE_PARENTHESIS;
			}
			else{
				val =  ReportGlobalConstant.SIGN_MARK + val  + ReportGlobalConstant.SIGN_MARK ;
			}
			sqlVal.append("and ").append(repJobRange.getRuleType1()).append(StringUtil.SPACE)
					.append(repJobRange.getRuleType2()).append(StringUtil.SPACE).append(val);
		}
		//通过组装的条件，查询产品相关内容
		List<ProdInfoListEntity> prodList = infoDisclosureReadAndGenMapper.getProdList(sqlVal.toString(), jobId);
		return prodList;
	} 
	/**
	 * 重新读取作业信息并生成待处理信息具体方法
	 * @param repJob 工作
	 * @param dateMap date集合
	 * @param executeDateStr 操作日期
	 * @param jobSeq 工作seq
	 * @return
	 * @throws Exception
	 */
	public boolean readAndGenDetailAgain(RepJob repJob, Map<String,Date> dateMap, Date executeDateStr,String jobSeq) throws Exception{
		boolean flag = false;
		//规则表信息
		RepRule repRule = infoDisclosureReadAndGenMapper.getRepRuleInfo(repJob.getRuleId());
		if(repRule == null){
			logger.info("无满足条件的规则");
			return true;
		}
		//基准日类型为平台日时的披露截止日
		Date ptDate = null;
		//获取需要披露的产品列表
		List<ProdInfoListEntity> list = this.getRuleProdList(repJob, repRule, executeDateStr, ptDate, dateMap);
		
		ptDate = dateMap.get("ptDate");
		
		if(CollectionUtils.isEmpty(list)){
			return true;
		}
		//执行插操作前先更新现有日志主表
		infoDisclosureReadAndGenMapper.updateInstallLogD(jobSeq);

		flag = this.doInsert(list, ptDate, repJob, repRule, executeDateStr, true);
		return flag;
	}
	/**
	 * 重新读取错误作业信息并生成待处理信息具体方法
	 * @param executeDateStr 跑批日期
	 * @throws Exception 异常
	 */
	public boolean readAndGenErrorAgain(RepJob repJob, Map<String,Date> dateMap, 
			Date executeDateStr,String jobSeq) throws Exception{
		boolean flag = false;
		//规则表信息
		RepRule repRule = infoDisclosureReadAndGenMapper.getRepRuleInfo(repJob.getRuleId());
		if(repRule == null){
			logger.info("无满足条件的规则");
			return true;
		}
		//基准日类型为平台日时的披露截止日
		Date ptDate = null;
		//获取需要披露的产品列表
		//获取作业范围list
//		List<RepJobRange> repJobRangeList = infoDisclosureReadAndGenMapper.getRepJobRangeList(repJob.getJobId());
		//通过组装的条件，查询产品相关内容
//		List<ProdInfoListEntity> prodList = this.getRepJobRangeByJobid(repJobRangeList, repJob.getJobId());
		List<ProdInfoListEntity> prodList = infoDisclosureReadAndGenMapper.getErrorProdList(ReportDictCodeList.执行结果_失败.getValue(), jobSeq);
		//当无满足条件的产品时，返回
		if(CollectionUtils.isEmpty(prodList)){
			logger.info("无满足条件的产品");
			return true;
		}
		List<ProdInfoListEntity> list = new ArrayList<ProdInfoListEntity>();
		if(StringUtil.equals(ReportDictCodeList.基准日_平台日.getValue(), repRule.getBasisDateType())){
			//获取披露截止日
			ptDate = revealDeadLineService.getRevDeadlineByMark(executeDateStr, repRule, dateMap);
			list.addAll(prodList);
		}
		else{
			ProdInfoListEntity prodInfo = null;
			for (ProdInfoListEntity prodInfoListEntity : prodList) {
				prodInfo = new ProdInfoListEntity();
				Date revDeadDate = this.getBasisDate(prodInfoListEntity, repRule);
				int days = Integer.parseInt(repRule.getMakeValue());
				if(StringUtil.equals(ReportDictCodeList.生成时点类型_前.getValue(), repRule.getRevealType())){
					days = -1*days;
				}
				boolean isGen = false;
				if(StringUtil.equals(ReportDictCodeList.日期类型_自然日.getValue(), repRule.getMakeDateType())){
					if(DateUtil.getBetweenDayNumber(executeDateStr, revDeadDate )==days){
						isGen = true;
					}
				}
				else if(revealDeadLineService.isWorkDay(revDeadDate, ReportDictCodeList.交易市场_中国法定节假日.getValue())){
					isGen = revealDeadLineService.isCanGen(revDeadDate, repRule, dateMap, executeDateStr);
				}
				prodInfoListEntity.setBasisDate(revDeadDate);
				if(isGen){
					prodInfoListEntity.setRevDeadDate(revealDeadLineService.getRevDeadlineByMark(revDeadDate, repRule, dateMap));
					BeanUtil.copyPropertiesIgnoreNull(prodInfo, prodInfoListEntity);
					list.add(prodInfo);
				}
			}
		}			
//		List<ProdInfoListEntity> list = this.getRuleProdList(repJob, repRule, executeDateStr, ptDate, dateMap);
		
		if(CollectionUtils.isEmpty(list)){
			return true;
		}
		//执行插操作
		//执行插操作前先更新现有日志主表
		infoDisclosureReadAndGenMapper.updateInstallLogD(jobSeq);
		flag = this.doInsert(list, ptDate, repJob, repRule, executeDateStr, true);
		return flag;
	}
}
