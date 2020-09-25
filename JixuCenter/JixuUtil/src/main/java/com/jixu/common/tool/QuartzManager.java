package com.jixu.common.tool;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.calendar.HolidayCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jixu.common.type.JobModel;
import com.jixu.common.util.DateUtil;

@Service("quartzManager")
public class QuartzManager {

	@Resource(name="schedulerFactoryBean")
    private Scheduler scheduler;  
	
	private static final Logger logger = LoggerFactory.getLogger(QuartzManager.class);

    /**
     * 添加一个定时任务,目前用在接口的导入/导出数据
     * @param jobmodel	自定义的一个job类，里面存放了job配置信息
     */
    public void addJob(JobModel jobmodel) throws SchedulerException{  
        // 任务名，任务组，任务执行类
        JobDetail jobDetail= JobBuilder.newJob(jobmodel.getJobClass()).withIdentity(jobmodel.getJobName(), jobmodel.getJobGroup()).build();
        jobDetail.getJobDataMap().put("scheduleJob", jobmodel);

        // 触发器  
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
        // 触发器名,触发器组  
        triggerBuilder.withIdentity(jobmodel.getJobName(), jobmodel.getJobGroup());
        if(jobmodel.getJobStartTime()!=null){
        	logger.info("自动任务开始时间: " + DateUtil.convertD2YYYY_MM_DDHHMMSS(jobmodel.getJobStartTime()));
        	triggerBuilder.startAt(jobmodel.getJobStartTime());
        }else{
        	triggerBuilder.startNow();
        }
        // 触发器时间设定  
        triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(jobmodel.getCronExp()));
        // 创建Trigger对象
        CronTrigger trigger = (CronTrigger) triggerBuilder.build();
        //添加排除日期设置
        if(jobmodel.getHolidayList()!=null&&jobmodel.getHolidayList().size()>0){
        	List<Date> hlist=jobmodel.getHolidayList();
        	HolidayCalendar hc=new HolidayCalendar();
        	for (Date hls : hlist) {
            	hc.addExcludedDate(hls);
			}
        	scheduler.addCalendar("holidays", hc, false, false);
        }
        // 调度容器设置JobDetail和Trigger
        scheduler.scheduleJob(jobDetail, trigger);  

        // 启动  
        if (!scheduler.isShutdown()) {  
            scheduler.start();  
        }  

    }  

    /**
     * 修改一个定时任务
     * @param jobmodel	自定义的一个job类，里面存放了job配置信息
     */  
    public void modifyJobTime(JobModel jobmodel) throws SchedulerException{  
        TriggerKey triggerKey = TriggerKey.triggerKey(jobmodel.getJobName(), jobmodel.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);  
        if (trigger == null) {  
            addJob(jobmodel); 
            return;
        }  

        String oldTime = trigger.getCronExpression();
        Date jobStartTime = jobmodel.getJobStartTime();
        if (!oldTime.equalsIgnoreCase(jobmodel.getCronExp()) || (jobStartTime!=null && jobmodel.getJobStartTime().compareTo(trigger.getStartTime())!=0)) { 
            /** 方式一 ：调用 rescheduleJob 开始 */
            // 触发器  
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组  
            triggerBuilder.withIdentity(jobmodel.getJobName(), jobmodel.getJobGroup());
            if(jobmodel.getJobStartTime()!=null){
            	logger.info("自动任务开始时间: " + DateUtil.convertD2YYYY_MM_DDHHMMSS(jobmodel.getJobStartTime()));
            	triggerBuilder.startAt(jobmodel.getJobStartTime());
            }else{
            	triggerBuilder.startNow();
            }
            // 触发器时间设定  
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(jobmodel.getCronExp()));
            // 创建Trigger对象
            trigger = (CronTrigger) triggerBuilder.build();
            // 方式一 ：修改一个任务的触发时间
            scheduler.rescheduleJob(triggerKey, trigger);
            /** 方式一 ：调用 rescheduleJob 结束 */

            /** 方式二：先删除，然后在创建一个新的Job  */
            //JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));  
            //Class<? extends Job> jobClass = jobDetail.getJobClass();  
            //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);  
            //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron); 
            /** 方式二 ：先删除，然后在创建一个新的Job */
        }  
    }  

    /**
     * 删除一个定时任务
     * @param jobmodel	自定义的一个job类，里面存放了job配置信息
     */ 
    public void removeJob(JobModel jobmodel) throws SchedulerException{  
    	TriggerKey triggerKey = TriggerKey.triggerKey(jobmodel.getJobName(), jobmodel.getJobGroup());

        scheduler.pauseTrigger(triggerKey);// 停止触发器  
        scheduler.unscheduleJob(triggerKey);// 移除触发器  
        scheduler.deleteJob(JobKey.jobKey(jobmodel.getJobName(), jobmodel.getJobGroup()));// 删除任务  
    }  

    /** 
     * @Description:启动所有定时任务 
     */  
    public void startJobs() {  
        try {  
            scheduler.start();  
        } catch (Exception e) {  
        	logger.error("Error", e);
            throw new RuntimeException(e);  
        }  
    }  

    /**
     * 关闭所有定时任务  
     */
    public void shutdownJobs() {  
        try {  
            if (!scheduler.isShutdown()) {  
                scheduler.shutdown();  
            }  
        } catch (Exception e) {  
        	logger.error("Error", e);
            throw new RuntimeException(e);  
        }  
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }  
}
