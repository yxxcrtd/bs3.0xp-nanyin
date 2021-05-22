package com.joyintech.tams.report.interf.common.service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.interf.common.dto.JobContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service("adapterService")
public class AdapterService {

    public static String  exeAdapterJob="ExecuteJixuAdapter/exeAdapterJobAsync/";

    public static String  fetchJobStatus="ExecuteJixuAdapter/fetchJobStatus/";

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(AdapterService.class);

    public static JobContainer exeDisJob(String jobId) {
        JobContainer jobContainer = new JobContainer();
        RestTemplate jixuRestTemplate=new RestTemplate() ;
        String  Adapterurl= CacheUtil.getSysParam(ReportGlobalConstant.ADAPTER_URL);
        try {
            String jobApi = Adapterurl+"/"+exeAdapterJob+ jobId;
            //封装批处理调用信息
            //调用任务，获取任务状态
            logger.info("调用批处理接口: " + jobApi);
            Map<String,Object> params = new HashMap<>();
//            简单调用，不需传参数
            jobContainer=jixuRestTemplate.postForObject(jobApi, params, JobContainer.class);
            //判断jobContainer是否为null
            if(jobContainer == null){
                jobContainer = new JobContainer();
                jobContainer.setJobMsg("执行任务异常，未获得实例编号");
            }
            //如果没有返回instanceid  运行状态是EXP
            if(StringUtil.isEmpty(jobContainer.getInstanceid())){
                jobContainer.setJobStatus("EXP");
            }
            //如果是异常  输出异常信息
            if("EXP".equals(jobContainer.getJobStatus())){
                logger.error(jobContainer.getJobMsg());
            }
        } catch (Exception e) {
            logger.error("调用批处理异常", e);
            if(jobContainer == null){
                jobContainer = new JobContainer();
            }
            jobContainer.setJobStatus("EXP");
            jobContainer.setJobMsg(e.getMessage());
        }
        return jobContainer;
    }

    /**
     *
     * @param jobId
     * @param instanceId
     * @return RUN/EXP/END等任务状态
     */
    public static JobContainer fetchDisJobStatus(String instanceId) {

        String  Adapterurl= CacheUtil.getSysParam(ReportGlobalConstant.ADAPTER_URL);
        String jobStatusApi = Adapterurl+"/"+fetchJobStatus+instanceId;
        RestTemplate jixuRestTemplate=new RestTemplate() ;
        JobContainer jobContainer = new JobContainer();

        //捕获微服务调用异常
        try {
            jobContainer = jixuRestTemplate.postForObject(jobStatusApi, instanceId, JobContainer.class);
            //判断jobContainer是否为null
            if(jobContainer == null || StringUtils.isEmpty(jobContainer.getJobStatus())){
                jobContainer = new JobContainer();
                jobContainer.setJobStatus("EXP");
                jobContainer.setJobMsg("无法获取任务状态");
            }
        } catch (Exception e) {
            logger.error("获取任务状态异常", e);
            if(jobContainer == null){
                jobContainer = new JobContainer();
            }
            jobContainer.setJobStatus("EXP");
            jobContainer.setJobMsg(e.getMessage());
        }
        return jobContainer;
    }

}
