/*
 * 文件名：InfoDisclosureGenAndSendMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年11月28日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.batch.disclosure.informationdisclourse.informationdisclosuregenandsend.service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import com.joyintech.tams.common.base.dao.RepInstallFileMapper;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.executor.ExecutorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.disclosure.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.base.entity.RepInstallLog;
import com.joyintech.tams.common.base.entity.RepScreen;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosuregenandsend.dao.InfoDisclosureGenAndSendMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosuregenandsend.entity.InfoDisclosureGenAndSendEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosuregenandsend.service.InfoDisclosureGenService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.entity.CheckConfirmMessage;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.service.RepCheckInfoService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.RevealDeadLineService;
import com.joyintech.tams.disclosure.common.common.constant.BatchBeanName;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 信息披露生成报表并发送service
 * @author wangmin
 * @version 1.0 2018年11月28日
 * @see InfoDisclosureGenAndSendMainService
 * @since 1.0
 */
@Service
public class InfoDisclosureGenAndSendMainService extends AutoSendCBBaseService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
//    private 
    /**
     * 信息披露生成报表并发送批处理service
     */
    @Autowired
    private InfoDisclosureGenAndSendMapper infoDisclosureGenAndSendMapper;
    /**
     * 生成文件及相关service
     */
    @Autowired
    private InfoDisclosureGenService infoDisclosureGenService;
	@Autowired
	private RevealDeadLineService revealDeadLineService;
	/**
	 * 信批内容校验
	 */
	@Autowired
	private RepCheckInfoService repCheckInfoService;

	@Autowired
	private RepInstallFileMapper repInstallFileMapper;

	@Value("${application.interfMaxCorePoolSize}")
	private int interfMaxCorePoolSize;
    /**
	/**
     * 信息披露生成报表并发送批处理主方法
     * @param executeDate 
     * @throws IntrospectionException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     *
     * @see
     * @since 1.0
     */
    public void generateData(String executeDateStr) {
    	setBatchBeanName(BatchBeanName.信息披露生成报表并发送批处理);

    	logInfoBatchStart();
    	//获取需要自动披露的披露列表
    	boolean isWork = true;
		String holidayIsWork = CacheUtil.getSysParam(ReportGlobalConstant.HOLIDAY_IS_WORK);
    	if(isWork){
    		//1.首先从组装批处理信息表中查询出所有需要执行的数据
    		List<InfoDisclosureGenAndSendEntity> repFileList = infoDisclosureGenAndSendMapper.getAllRepInstallFileList(); // sql内新增了POI报表信息查询  zhangyf 20200319


			if(CollectionUtils.isNotEmpty(repFileList)){
				List<String>tmpList=repFileList.stream().distinct().map(InfoDisclosureGenAndSendEntity::getJobSeq).collect(Collectors.toList());

				List<String>joblist=tmpList.stream().distinct().collect(Collectors.toList());


				//系统中配置的文件服务器ip
        	    //后台批处理报表地址单独啦出来
        		String serverUrl = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_BATCH_SERVER_URL);
        		//用户名
        		String serverUser = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_USERNAME);
        		//密码 
        		String serverPwd = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_PASSWORD);
        		//导出层级
        		//String exportPath = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_BATCH_EXPORT_PATH);
        		//将所有报表查询条件首先查询出，并放入map中
        		List<RepScreen> screenList = infoDisclosureGenAndSendMapper.getAllRepScreenList();// sql内新增了POI条件查询  zhangyf 20200319
                Map<String, List<RepScreen>> screenMap = screenList.stream().collect(
                        JoyinCollectors.groupingBy(
                            x -> x.getRepId()));
        		//循环遍历处理
            	String jobSeq = "";
            	Date dateStr = null;
				boolean success = true;

				Iterator itr = repFileList.iterator();
				while ( itr.hasNext() ) {
					InfoDisclosureGenAndSendEntity info=(InfoDisclosureGenAndSendEntity)itr.next();

				}

				if (repFileList.size() <= 200) {
					batch(repFileList,holidayIsWork,executeDateStr,jobSeq,screenMap);
				}
				else{
					if (interfMaxCorePoolSize < 1) {
						interfMaxCorePoolSize = 20;
					}
					ExecutorService service = new JoyinThreadPoolExecutor(interfMaxCorePoolSize);
					List<Callable<Boolean>> tasks = new ArrayList<>();
					if (null != repFileList) {
						int start = 0;
						int end = 0;
						int size = repFileList.size();
						int max = (int)Math.ceil((double)size / 200);
						for (int i = 0; i < max; i++ ) {
							start = end;
							end = Math.min(size, 200 * (i + 1));
							List<InfoDisclosureGenAndSendEntity> subList = repFileList.subList(start, end);
							tasks.add(() -> {
								 batch(subList,holidayIsWork,executeDateStr,jobSeq,screenMap);
								 return true;
							});
						}
					}
					// 为线程池添加任务
					List<Future<Boolean>> taskResult = null;
					try {
						taskResult = service.invokeAll(tasks);
					} catch (InterruptedException e1) {
						logger.error(e1.getMessage(),e1);
						Thread.currentThread().interrupt();
						throw new ExecutorException(e1);
					}
					for (Future<Boolean> res : taskResult) {
						try {
							success = success && res.get();
						} catch (InterruptedException | ExecutionException e) {
							logger.error(e.getMessage(),e);
							Thread.currentThread().interrupt();
							throw new ExecutorException(e);
						}
					}
					// 任务结束，停止线程池的所有线程
					service.shutdown();
				}

        		//日志主表
				for(String seq:joblist){
					this.insertLog(seq, dateStr);
				}

        	}
        	else{
        		logger.info("无信息披露信息需要生成");
        	}
    	}
    	else{
    		logger.info("非工作日不生成");
    	}
    	logInfoBatchEnd();
    }


	private  void batch(List<InfoDisclosureGenAndSendEntity> repFileList,String holidayIsWork,String executeDateStr,String jobSeq,Map<String, List<RepScreen>> screenMap){
		for (InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity : repFileList) {
			//在这里加上具体每个作业的工作日判断 add by zhengxiaolian
			boolean isWork = false;
			if(StringUtil.equals(holidayIsWork, DictCodeListExt1.是或否_是.getValue())){
				isWork = true;
			}
			else{
				Date execDate = DateUtil.parse(executeDateStr);
				if(revealDeadLineService.isWorkDay(execDate, infoDisclosureGenAndSendEntity.getMarketCode())){
					isWork = true;
				}
			}
			if (!isWork) {
				continue;
			}
//			if(!StringUtil.equals(jobSeq,infoDisclosureGenAndSendEntity.getJobSeq())){
//				if(StringUtil.isNotEmpty(jobSeq)){
//					//更新日志主表
//					this.insertLog(jobSeq, DateUtil.parse(executeDateStr));
//				}
//				jobSeq = infoDisclosureGenAndSendEntity.getJobSeq();
//			}
			infoDisclosureGenAndSendEntity.setCheckFor(DictCodeListExt2.校验位置_必要数据.getValue());

			infoDisclosureGenAndSendEntity.setExecDate(DateUtil.parse(executeDateStr));
			CheckConfirmMessage ccm = repCheckInfoService.check(infoDisclosureGenAndSendEntity,infoDisclosureGenAndSendEntity.getClass());
			if (ccm != null && ccm.isFlag()) {
				infoDisclosureGenService.genDetail(infoDisclosureGenAndSendEntity,screenMap,DateUtil.parse(executeDateStr));
			}
			else if(ccm!=null){
				logger.info(ccm.getMsg());
				if(StringUtil.equals(ccm.getMsg(),"校验不满足")){
					repInstallFileMapper.deleteByPrimaryKey(infoDisclosureGenAndSendEntity.getId());
				}
			}
		}
	}



    /**
     * 插入日志主表
     * @param jobSeq 作业流水号
     * @param dateStr 开始日
     */
    private void insertLog(String jobSeq, Date dateStr){
    	RepInstallLog repInstallLog = new RepInstallLog();
		repInstallLog.setJobSeq(jobSeq);
		repInstallLog.setStartDate(dateStr);
		repInstallLog.setEndDate(DateUtil.now());
		repInstallLog.setStatus(DictCodeListExt2.数据状态_有效.getValue());
		repInstallLog.setExecStatus(DictCodeListExt2.执行状态_已执行.getValue());
		List<InfoDisclosureGenAndSendEntity> list = infoDisclosureGenAndSendMapper.getExecStatusList(jobSeq);
		int sussces = 0;
		int fail = 0;
		int unGen = infoDisclosureGenAndSendMapper.getTotalByJobSeq(jobSeq);
		for (InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity2 : list) {
			if(StringUtil.equals(DictCodeListExt2.执行结果_成功.getValue(), infoDisclosureGenAndSendEntity2.getExecStatus())){
				sussces = infoDisclosureGenAndSendEntity2.getExecTotal();
			}
			else{
				fail = fail + infoDisclosureGenAndSendEntity2.getExecTotal();
			}
		}
		//当未执行数据为0条
		if(unGen==0){
			if(sussces>0&&fail==0){
				repInstallLog.setExecResult(DictCodeListExt2.执行结果_成功.getValue());
			}
			else if(sussces==0&&fail>0){
				repInstallLog.setExecResult(DictCodeListExt2.执行结果_失败.getValue());
			}
			else if(sussces>0&&fail>0){
				repInstallLog.setExecResult(DictCodeListExt2.执行结果_部分成功.getValue());
			}
			repInstallLog.setExecDetail("执行成功"+sussces+"条,执行失败"+fail+"条,未执行"+unGen+"条");
			infoDisclosureGenAndSendMapper.updateByJobSeqSelective(repInstallLog);
		}
		else if(unGen>0){
			if(sussces>0||fail>0){
				repInstallLog.setExecResult(DictCodeListExt2.执行结果_部分成功.getValue());
				repInstallLog.setExecDetail("执行成功"+sussces+"条,执行失败"+fail+"条,未执行"+unGen+"条");
				infoDisclosureGenAndSendMapper.updateByJobSeqSelective(repInstallLog);
			}
		}
    }
}
