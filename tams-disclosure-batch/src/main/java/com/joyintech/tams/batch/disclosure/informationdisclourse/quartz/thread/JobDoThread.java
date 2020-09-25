/*
 * 文件名：JobDoThread.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月6日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.batch.disclosure.informationdisclourse.quartz.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.disclosure.informationdisclourse.quartz.service.InfoDisclosureQuartzService;
import com.joyintech.tams.common.base.entity.RepJob;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.service.InfoDisclosureReadAndGenDetailService;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年12月6日
 * @see JobDoThread
 * @since 1.0
 */
@Component
public class JobDoThread implements Runnable{
    
    private Vector<RepJob> jobList = new Vector();
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private InfoDisclosureQuartzService infoDisclosureQuartzService;
    
    
    private InfoDisclosureReadAndGenDetailService infoDisclosureReadAndGenDetailService;
    
    private void init(){
        if(null==infoDisclosureQuartzService){
            infoDisclosureQuartzService = SpringContextUtil.getBean(InfoDisclosureQuartzService.class);
        }
        
        if(null==infoDisclosureReadAndGenDetailService){
            infoDisclosureReadAndGenDetailService = SpringContextUtil.getBean(InfoDisclosureReadAndGenDetailService.class);
        }
    }

   public void pushJob(String jobId){
       init();
       RepJob job = infoDisclosureQuartzService.getRepJob(jobId);
       if(null!=job){
           jobList.add(job);
           run();
       }
       
   }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        try {
            while (!jobList.isEmpty()) {
                doJob();
            } 
        }catch (Exception e) {
        	logger.error("error:", e);
        }
    }
    
    //作业执行job任务
    public synchronized void doJob() throws Exception{
        
        if(jobList.isEmpty()){
            return;
        }
        
        RepJob detailJob = null;
        try {
            detailJob = jobList.firstElement();
            logger.info("作业:"+detailJob.getJobName() +",编号:"+detailJob.getJobId() +"开始进行生成作业"+"开始时间:"+DateUtil.format(new Date(),"yyyy-MM-dd hh:mm:ss"));
            Map<String,Date> dateMap = new HashMap<String, Date>();
            
            //生成待处理任务
            infoDisclosureReadAndGenDetailService.readAndGenMain(detailJob, DictCodeListExt2.执行标志_首次执行.getValue(), null, new Date());        
            int day = 1;
            
            String  dayStr = CacheUtil.getSysParam(ReportGlobalConstant.PRE_GEN_DATE);
            if(StringUtil.isNotEmpty(dayStr)){
                day = Integer.parseInt(dayStr);
            }else{
                logger.debug("未取到预期生成天数参数.默认生成"+day+"天后数据");
            }

           //生成预生成任务
            infoDisclosureReadAndGenDetailService.preReadAndGenDetail(detailJob, dateMap, DateUtil.addDay(new Date(), day));
            
            

            logger.info("作业:"+detailJob.getJobName() +",编号:"+detailJob.getJobId() +"结束生成作业"+"结束时间:"+DateUtil.format(new Date(),"yyyy-MM-dd hh:mm:ss"));
        }
        catch (Exception e) {
            logger.error("error:", e);
        }finally {
            if(StringUtil.isNotEmpty(detailJob)){
                jobList.remove(detailJob);
            }
        }
    }

}
