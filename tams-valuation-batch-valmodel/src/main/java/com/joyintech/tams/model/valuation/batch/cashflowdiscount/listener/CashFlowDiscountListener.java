package com.joyintech.tams.model.valuation.batch.cashflowdiscount.listener;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.component.batch.service.ModLogsService;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author 王波
 * @date 2021-07-23
 * @see CashFlowDiscountListener
 * @since 1.0
 */
@Component
public class CashFlowDiscountListener implements JobExecutionListener {

    private final String TASKID_KEY = "taskId";

    private final String BATCH_PARAMS_KEY = "params";
    
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
//            List<ModBatchLogs> list = new ArrayList<>(params.size());
//            for (ModValBatchParams param : params) {
//                ModBatchLogs log = new ModBatchLogs();
//                log.setSeqno(IDGenerator.generate());
//                log.setFinprodId(param.getFinprodId());
//                log.setBatchExecSeqno(param.getBatchExecSeqno());
//                log.setTaskId(taskId);
//                list.add(log);
//            }
//            modLogsService.saveBatch(list);
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String taskId = jobExecution.getExecutionContext().getString(TASKID_KEY);
        List<ModValBatchParams> params = (List<ModValBatchParams>)jobExecution.getExecutionContext().get(BATCH_PARAMS_KEY);
        if (jobExecution.getExitStatus().equals(ExitStatus.FAILED)) {
            if (CollectionUtils.isNotEmpty(params)) {
                for (ModValBatchParams param : params) {
                    modLogsService.error(param.getFinprodId(), param.getBatchExecSeqno(), taskId, "执行批处理失败！");
                }
            }
        }else {
            if (CollectionUtils.isNotEmpty(params)) {
                for (ModValBatchParams param : params) {
                    modLogsService.success(param.getFinprodId(), param.getBatchExecSeqno(), taskId
                            , "执行批处理【" + param.getFinprodId()+ "】成功结束！");
                }
            }
        }
    }
}
