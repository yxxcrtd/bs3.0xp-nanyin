package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.listener;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModBatchLogs;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchLogResult;
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

/**
 * @author 刘华明
 * @date 2021-07-24
 * @see AssetImpairmentStageJudgeModelListener
 * @since 1.0
 * 参照估值批中写法
 */
@Component
public class AssetImpairmentStageJudgeModelListener implements JobExecutionListener {

    private final String TASKID_KEY = "taskId";

    private final String BATCH_PARAMS_KEY = "params";

    // 日志key
    private final String LOG_KEY = "logkey";
    
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
        //封装日志信息
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
        List<ModValBatchLogResult> params = (List<ModValBatchLogResult>)jobExecution.getExecutionContext().get(LOG_KEY);
        if (CollectionUtils.isNotEmpty(params)) {
            for (ModValBatchLogResult param : params) {
                if(param.getType().equals(ModelDictCodeList.批处理执行结果_异常结束.getValue())){
                    modLogsService.error(param.getFinprodId(), param.getBatchExecSeqno(), taskId, param.getMsg());
                }else if(param.getType().equals(ModelDictCodeList.批处理执行结果_警告.getValue())){
                    modLogsService.warn(param.getFinprodId(), param.getBatchExecSeqno(), taskId, param.getMsg());
                }else{
                    modLogsService.success(param.getFinprodId(), param.getBatchExecSeqno(), taskId
                            , "执行批处理【" + param.getFinprodId()+ "】成功结束！");
                }
            }
        }
    }
}
