package com.joyintech.tams.model.impairment.batch.eclcalculation.listener;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.valuation.common.batch.batchTasks.CommonErrorParams;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModBatchLogs;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.component.batch.service.ModLogsService;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import org.apache.commons.collections.CollectionUtils;
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
 * @author 王波
 * @date 2021-07-23
 * @see EclCalculationListener
 * @since 1.0
 */
@Component
public class EclCalculationListener implements JobExecutionListener {

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
                list.add(modLogsService.initLog(taskId,param.getBatchExecSeqno(),param.getFinprodId()));

            }
            modLogsService.saveBatch(list);
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String taskId = jobExecution.getExecutionContext().getString(TASKID_KEY);
        List<ModValBatchParams> params = (List<ModValBatchParams>)jobExecution.getExecutionContext().get(BATCH_PARAMS_KEY);
        List<CommonErrorParams> errors  = (List<CommonErrorParams>)jobExecution.getExecutionContext().get(BATCH_ERROR_KEY);
        // 错误日志插入
        if (CollectionUtils.isNotEmpty(errors)) {
            errors = errors.stream().collect(
                    Collectors.toMap(CommonErrorParams::getFinprodId, Function.identity(), (e1, e2) -> e2)
            ).values().stream().collect(Collectors.toList());
            for (CommonErrorParams param : errors) {
                if (ModelDictCodeList.批处理执行结果_异常结束.getValue().equals(param.getErrorType())) {
                    modLogsService.error(param.getFinprodId(), param.getBatchSeqNo(), taskId, param.getErrorMsg());
                } else {
                    modLogsService.warn(param.getFinprodId(), param.getBatchSeqNo(), taskId, param.getErrorMsg());
                }
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
