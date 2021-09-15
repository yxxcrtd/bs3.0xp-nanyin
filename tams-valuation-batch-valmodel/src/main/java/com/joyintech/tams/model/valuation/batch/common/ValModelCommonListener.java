package com.joyintech.tams.model.valuation.batch.common;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.batch.batchTasks.CommonErrorParams;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModBatchLogs;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.component.batch.service.ModLogsService;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ValModelCommonListener implements JobExecutionListener {

    @Autowired
    private ModParamsService modParamsService;

    @Autowired
    private ModLogsService modLogsService;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // 执行日
        Date date = jobExecution.getJobParameters().getDate("date");
        // 任务名称
        String jobName = jobExecution.getJobInstance().getJobName();
        String[] jobNames = StringUtil.split(jobName, GlobalConstant.UNDER_LINE);
        if (jobNames == null || jobNames.length != 2) {
            return;
        }
        String taskId = jobNames[1];
        // 查询参数
        List<ModValBatchParams> params = modParamsService.selectAllParamsByDate(date);
        if (CollectionUtils.isNotEmpty(params)) {
            Map<String,ModBatchLogs> logsMap = new HashedMap<>();
            params.stream().forEach(x->{
                ModBatchLogs log = modLogsService.initLog(taskId,x.getBatchExecSeqno(),x.getFinprodId());
                logsMap.put(x.getFinprodId(),log);
            });
            jobExecution.getExecutionContext().put(ValComponentConstant.MODEL_LOGS,logsMap);
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        Map<String,CommonErrorParams> logs = (Map<String,CommonErrorParams>)jobExecution.getExecutionContext().get(ValComponentConstant.MODEL_LOGS);
        if(logs != null && logs.size() > 0){
            modLogsService.saveBatch(new ArrayList(logs.values()));
        }
    }
}
