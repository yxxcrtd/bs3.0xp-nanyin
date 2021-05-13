/*
 * 文件名：InfoDisclosureGenAndSendMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年11月28日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.batch.disclosure.informationdisclourse.quartz.service;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.List;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.BusinessMode;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.disclosure.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.disclosure.informationdisclourse.informationdisclosuregenandsend.service.InfoDisclosureGenAndSendMainService;
import com.joyintech.tams.batch.disclosure.informationdisclourse.quartz.dao.InfoDisclosureQuartzMapper;
import com.joyintech.tams.batch.disclosure.informationdisclourse.quartz.dto.RepJobDetail;
import com.joyintech.tams.batch.disclosure.informationdisclourse.quartz.dto.ReportJob;
import com.joyintech.tams.common.base.dao.RepJobMapper;
import com.joyintech.tams.common.base.entity.RepJob;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.taskDetail.dao.RepTaskDeatilMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.taskDetail.dto.RepTaskDeatil;
import com.joyintech.tams.disclosure.common.common.constant.BatchBeanName;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tools.mq.constant.OperationType;
//import com.joyintech.tools.mq.entity.PushMessageDto;
//import com.joyintech.tools.mq.listener.MessagePushListener;

/**
 * 信息披露生成报表并发送service
 * @author leipeng
 * @version 1.0 2018年11月28日
 * @see InfoDisclosureGenAndSendMainService
 * @since 1.0
 */
@Service
public class InfoDisclosureQuartzService extends AutoSendCBBaseService /*implements  MessagePushListener*/{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    
    private Scheduler scheduler;
    
    @Autowired
    private InfoDisclosureQuartzMapper quartzMapper;
    
    
    @Autowired
    private RepJobMapper Mapper;
    
    @Autowired
    private RepTaskDeatilMapper repTaskDeatilMapper;
    
    
    private Date date;
    
    
    private String min;
    
    /**
	/**
     * 信息披露生成报表并发送批处理主方法
     * @param executeDate 
     *
     * @see
     * @since 1.0
     */
    public void generateData(String executeDateStr) {
    	setBatchBeanName(BatchBeanName.信息披露扫描作业情况批处理);

    	logInfoBatchStart();
    	date = new Date();
    	 min = CacheUtil.getSysParam(ReportGlobalConstant.REP_REFRESH_JOBRULE);
    	
    	int time = StringUtil.isNotEmpty(min)?Integer.parseInt(min):1;
    	//
    	while (repTaskDeatilMapper.RepTaskDeatilin()>0) {
    	    //通知上一次停止
            logger.info("发送停止请求,请求线程停止");
            repTaskDeatilMapper.updateRepTaskDeatil(DisclosureDictCodeList.数据状态_有效.getValue(),DisclosureDictCodeList.数据状态_待处理.getValue());
    	    try {
    	        //如果当前有现成正在运行.则暂停10分钟
    	        Thread.sleep(1000*60*time);
                logger.info("等待时间结束.查看线程是否可使用");

            } catch (InterruptedException e) {
                
            }
        }
    	
    	RepTaskDeatil taskDetail = new RepTaskDeatil();
    	taskDetail.setFlag(DisclosureDictCodeList.数据状态_有效.getValue());
    	taskDetail.setTaskdate(DateUtil.getCurrentDate());
    	taskDetail.setTaskflag("0");
    	repTaskDeatilMapper.insert(taskDetail);
    	
    	try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            List<RepJobDetail> configList = quartzMapper.selectJobByDate(DateUtil.parse(executeDateStr));
            configList.forEach(item -> {
                JobDataMap jobDataMap = new JobDataMap();
                jobDataMap.put("JobId", item.getJobId());
                JobDetail job = newJob(ReportJob.class).withIdentity(
                        "job:" + item.getJobId()).setJobData(jobDataMap).build();
                Trigger trigger = newTrigger().withIdentity(
                        "trigger:" + item.getJobId()).withSchedule(
                        cronSchedule(item.getLoopDateValue())).build();
                try {
                    scheduler.scheduleJob(job, trigger);
                } catch (SchedulerException e) {
                    logger.warn("注册定时任务失败，请检查设置时间是否比当前时间小，永远不会触发。id:"
                            + item.getJobId() + "config:" + item.getLoopDateValue(), e);
                }
            });
            while (!(scheduler.isShutdown()
                    || (scheduler.isStarted() && scheduler.isInStandbyMode()))) {
                try {
                    Thread.sleep(1000);
                    if(DateUtil.addMinutes(date, time).compareTo(new Date())<0){
                        logger.info("检查日期了");
                        if(repTaskDeatilMapper.RepTaskDeatil(DisclosureDictCodeList.数据状态_待处理.getValue())>0){
                            repTaskDeatilMapper.updateRepTaskDeatil(DisclosureDictCodeList.数据状态_待处理.getValue(),DisclosureDictCodeList.数据状态_删除.getValue());

                            logger.info("收到停止请求,已经停止");
                            break;
                        }
                        this.setDate(new Date());
                    }
                } catch (InterruptedException e) {
                    
                }
            }
        } catch (SchedulerException e) {
//            e.printStackTrace();
        	logger.error("error:", e);
        }finally {
            try {
                scheduler.shutdown();
            } catch (SchedulerException e1) {
                logger.error("", e1);
            }
        }
        logger.info("结束了!!!!!!!!!!!!!!!!!");

    }
    
    




    
    public Date getDate() {
        return date;
    }






    
    public void setDate(Date date) {
        this.date = date;
    }






    /**
     * 
     * 根据作业编号获取作业信息
     * <功能详细描述>
     * @param jobId
     * @return
     * @see
     * @since 1.0
     */
    public RepJob getRepJob(String jobId){
        return Mapper.selectByPrimaryKey(jobId);
        
    }


    //@Override
    //public boolean messageFilter(PushMessageDto msg) {
    //    if (StringUtil.equals(msg.getBusinessMode(), BusinessMode.定时批处理参数更新.getModeCode())) {
    //        return true;
    //    }
    //    return false;
    //}

    //@Override
    //public PushMessageDto onMessage(PushMessageDto msg) {
    //    OperationType type = OperationType.from(msg.getOperationType());
    //    RepJob item = (RepJob) msg.getBusinessEntity();
    //    RepJobDetail dto = new RepJobDetail();
    //    BeanUtils.copyProperties(dto, item);
    //    reQuartzSchedule(type, dto);
    //    return null;
    //}

    public void reQuartzSchedule(OperationType type, RepJobDetail item) {
        Trigger trigger = newTrigger().withIdentity("trigger:" + item.getJobId()).withSchedule(
                cronSchedule(item.getLoopDateValue())).build();
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("JobId", item.getJobId());
        JobDetail job = newJob(ReportJob.class).withIdentity("job:" + item.getJobId()).setJobData(
                jobDataMap).build();
        try {
            switch (type) {
                case 插入:
                    scheduler.scheduleJob(job, trigger);
                    break;
                case 更新:
                    scheduler.rescheduleJob(trigger.getKey(), trigger);
                    break;
                case 删除:
                    scheduler.deleteJob(job.getKey());
                    break;
                default:
                    break;
            }
        } catch (SchedulerException e) {
            logger.error("", e);
        }
    }
}
