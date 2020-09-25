package com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscgendetailfile.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.disclosure.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.base.dao.RepInstallLogMapper;
import com.joyintech.tams.common.base.entity.RepInstallLog;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscgenfiledetail.dao.InfoDiscGenFileDetailMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscgenfiledetail.entity.InfoDisclosureReadAndGenEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscgenfiledetail.service.InfoDiscGenFileDetailService;
import com.joyintech.tams.disclosure.common.common.constant.BatchBeanName;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;

@Service
public class InfoDiscGenDetailFileMainService  extends AutoSendCBBaseService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private InfoDiscGenFileDetailMapper infoDiscGenFileDetailMapper;
    @Autowired
    private InfoDiscGenFileDetailService infoDiscGenFileDetailService;
	@Autowired
	private RepInstallLogMapper repInstallLogMapper;
	public void generateData(String executeDateStr) {
    	setBatchBeanName(BatchBeanName.生成待处理详情);
    	logInfoBatchStart();
    	//infoDiscGenFileDetailService.isReady(executeDateStr);
    		//首先查询出所有符合条件的日志表中的信息
    		List<InfoDisclosureReadAndGenEntity> list = infoDiscGenFileDetailMapper.queryAllPreInstallLog(DictCodeListExt2.执行状态_待执行.getValue()
    				, DictCodeListExt2.执行标志_执行错误.getValue());
    		for (InfoDisclosureReadAndGenEntity infoDisclosureReadAndGenEntity : list) {
    	    	try{
    	    	  //后期待完善后添加具体适配器可执行逻辑
//    	            boolean runFlag = infoDiscGenFileDetailService.checkDateisReady(DateUtil.format(infoDisclosureReadAndGenEntity.getExecDate()));
    	            boolean runFlag = infoDiscGenFileDetailService.checkDateisReadyNew(infoDisclosureReadAndGenEntity,DateUtil.format(infoDisclosureReadAndGenEntity.getExecDate()));
    	            Map<String,Date> dateMap = new HashMap<String,Date>();
    	            if(runFlag){
    	            	
    	            	//执行完成后将主表的状态修改为执行中
    	        		RepInstallLog repInstallLog = new RepInstallLog();
    	        		repInstallLog.setId(infoDisclosureReadAndGenEntity.getId());
    	        		repInstallLog.setExecStatus(DictCodeListExt2.执行状态_执行中.getValue());
    	        		repInstallLog.setStatus(DictCodeListExt2.数据状态_有效.getValue());
    	        		repInstallLogMapper.updateByPrimaryKeySelective(repInstallLog);
    	        		
    	                boolean flag = infoDiscGenFileDetailService.genInstallFileDetail(infoDisclosureReadAndGenEntity, dateMap, infoDisclosureReadAndGenEntity.getExecDate());    		
                        
    	                if(!flag){
    	                    logger.info("无满足条件的规则");
    	                    repInstallLog.setExecStatus(DictCodeListExt2.执行状态_已执行.getValue());
    	                    repInstallLog.setExecResult(DictCodeListExt2.执行结果_无符合要求执行数据.getValue());
    	                    if(StringUtil.equals(infoDisclosureReadAndGenEntity.getExecFlag(), DictCodeListExt2.执行标志_首次执行.getValue())){
    	                        repInstallLog.setStartDate(DateUtil.now());
    	                        repInstallLog.setEndDate(DateUtil.now());
    	                    }
    	                    repInstallLog.setExecDetail("无符合条件的规则");
    	                    repInstallLog.setStatus(DictCodeListExt2.数据状态_有效.getValue());
                            repInstallLogMapper.updateByPrimaryKeySelective(repInstallLog);
    	                }
    	            }else{
        	            logger.info("适配器未就绪！");
        	        }
    	        }
    	    	catch(Exception e){
//    	    		e.printStackTrace();
    	    		logger.error("error:", e);
    	    		logger.info("jobid:"+infoDisclosureReadAndGenEntity.getJobId()+"jobName:"+infoDisclosureReadAndGenEntity.getJobName()+"的任务执行失败");
    	    	}
			}
    	logInfoBatchEnd();

	}
}
