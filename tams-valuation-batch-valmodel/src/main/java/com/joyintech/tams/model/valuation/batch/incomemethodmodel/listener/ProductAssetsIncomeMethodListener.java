package com.joyintech.tams.model.valuation.batch.incomemethodmodel.listener;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.model.valuation.batch.batchtask.dto.ModBatchLogs;
import com.joyintech.tams.model.valuation.batch.batchtask.dto.ModValBatchLogResult;
import com.joyintech.tams.model.valuation.batch.batchtask.dto.ModValBatchParams;
import com.joyintech.tams.model.valuation.batch.batchtask.service.ModLogsService;
import com.joyintech.tams.model.valuation.batch.batchtask.service.ModParamsService;
import com.joyintech.tams.model.valuation.batch.common.constants.ModelDictCodeList;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 王波
 * @date 2021-07-23
 * @see ProductAssetsIncomeMethodListener
 * @since 1.0
 */
@Component
public class ProductAssetsIncomeMethodListener implements JobExecutionListener {

    private final String TASKID_KEY = "taskId";
    private final String BATCH_LOG_RESULTS = "logResults";
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
        List<ModValBatchLogResult> batchLogResultList = (List<ModValBatchLogResult>)jobExecution.getExecutionContext().get(BATCH_LOG_RESULTS);
        for(ModValBatchLogResult batchLogResult:batchLogResultList){
            if(ModelDictCodeList.批处理执行结果_异常结束.getValue().equals(batchLogResult.getType())){
                modLogsService.error(batchLogResult.getFinprodId(), batchLogResult.getBatchExecSeqno(), batchLogResult.getTaskId(), batchLogResult.getMsg());
            }else if(ModelDictCodeList.批处理执行结果_警告.getValue().equals(batchLogResult.getType())){
                modLogsService.warn(batchLogResult.getFinprodId(), batchLogResult.getBatchExecSeqno(), batchLogResult.getTaskId(), batchLogResult.getMsg());
            }else if(ModelDictCodeList.批处理执行结果_正常结束.getValue().equals(batchLogResult.getType())){
                modLogsService.success(batchLogResult.getFinprodId(), batchLogResult.getBatchExecSeqno(), batchLogResult.getTaskId(), batchLogResult.getMsg());
            }
        }
    }
}
