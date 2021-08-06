package com.joyintech.tams.model.impairment.batch.eadcalculation.listener;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.model.impairment.batch.batchtask.dto.ModBatchLogs;
import com.joyintech.tams.model.impairment.batch.batchtask.dto.ModValBatchParams;
import com.joyintech.tams.model.impairment.batch.batchtask.service.ModLogsService;
import com.joyintech.tams.model.impairment.batch.batchtask.service.ModParamsService;
import com.joyintech.tams.model.impairment.batch.eadcalculation.dto.EadErrorParams;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * EAD计算批处理监听
 * @author zhangfei
 * @date 2021年7月24日10:18:05
 */
@Component
public class EadCalCulationBatchListener implements JobExecutionListener {

    private final String TASKID_KEY = "taskId";

    private final String BATCH_PARAMS_KEY = "params";

    private final String BATCH_ERROR_KEY = "ERRORS";

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
        jobExecution.getExecutionContext().putString(TASKID_KEY, taskId);
        // 查询参数
        List<ModValBatchParams> params = modParamsService.selectAllParamsByDate(date);
        if (CollectionUtils.isNotEmpty(params)) {
            jobExecution.getExecutionContext().put(BATCH_PARAMS_KEY, params);
            List<ModBatchLogs> list = new ArrayList<>(params.size());
            for (ModValBatchParams param : params) {
                ModBatchLogs log = new ModBatchLogs();
                log.setSeqno(IDGenerator.generate());
                log.setFinprodId(param.getFinprodId());
                log.setBatchExecSeqno(param.getBatchExecSeqno());
                log.setTaskId(taskId);
                list.add(log);
            }
            modLogsService.saveBatch(list);
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String taskId = jobExecution.getExecutionContext().getString(TASKID_KEY);
        List<ModValBatchParams> params = (List<ModValBatchParams>)jobExecution.getExecutionContext().get(BATCH_PARAMS_KEY);
        List<EadErrorParams> errors  = (List<EadErrorParams>)jobExecution.getExecutionContext().get(BATCH_ERROR_KEY);
        // 错误日志插入
        if (CollectionUtils.isNotEmpty(errors)) {
            errors = errors.stream().collect(
                    Collectors.toMap(EadErrorParams::getFinprodId, Function.identity(), (e1, e2) -> e2)
            ).values().stream().collect(Collectors.toList());
            for (EadErrorParams param : errors) {
                modLogsService.error(param.getFinprodId(), param.getBatchSeqNo(), taskId, param.getErrorMsg());
            }
        }
        // 成功日志插入
        if (CollectionUtils.isNotEmpty(params)) {
            for (ModValBatchParams param : params) {
                modLogsService.success(param.getFinprodId(), param.getBatchExecSeqno(), taskId
                        , "执行批处理【" + param.getFinprodId()+ "】成功结束！");
            }
        }
    }
}
