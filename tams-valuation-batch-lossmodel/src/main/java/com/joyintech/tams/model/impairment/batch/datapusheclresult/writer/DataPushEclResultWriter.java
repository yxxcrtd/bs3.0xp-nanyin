package com.joyintech.tams.model.impairment.batch.datapusheclresult.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.dto.AssetLossResultProcessDto;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.service.DataPushEclResultProcessService;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchLogResult;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:减值模型-数据推送批处理Writer
 * @auther: yuzj
 * @date: 2021/7/20
 * @param:
 * @return:
 */
@Service
@Scope("prototype")
public class DataPushEclResultWriter extends BaseWriter<AssetLossResultProcessDto> implements StepExecutionListener {
    private final String BATCH_LOG_RESULTS = "logResults";

    private StepExecution stepExecution;

    /**
     *  数据推送批处理Service
     */
    @Autowired
    private DataPushEclResultProcessService dataPushEclResultProcessService;


    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution=stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void write(List<? extends AssetLossResultProcessDto> items)
        throws Exception {
//        @SuppressWarnings("unchecked")
        Map<String,Object> mapParam = this.getParams();
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)mapParam.get("BATCH_INFO");
        List<ModValBatchLogResult> bBatchLogResultList = new ArrayList<>();
        List<AssetLossResultProcessDto> resultList = (List<AssetLossResultProcessDto>)items;
        String taskId =(String)mapParam.get("TASK_ID");
        try {
            dataPushEclResultProcessService.sendEclResultResult(resultList);
            //=====记录成功日志=====start=====
            for(AssetLossResultProcessDto resultDto :resultList){
                ModValBatchLogResult batchParam = new ModValBatchLogResult();
                batchParam.setFinprodId(resultDto.getProdId());
                batchParam.setTaskId(taskId);
                batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                batchParam.setType(ModelDictCodeList.批处理执行结果_正常结束.getValue());
                batchParam.setMsg("【减值模型数据推送批处理】：执行批处理【" + resultDto.getProdId()+ "】成功结束！");
                bBatchLogResultList.add(batchParam);
            }
            dataPushEclResultProcessService.sendlogResults(bBatchLogResultList,stepExecution);
            //=====记录成功日志=====end=====
            System.out.println("====================test========writer========");
        } catch (Exception e) {
            //=====记录计算错误日志=====start=====
            for(AssetLossResultProcessDto processDto:resultList){
                ModValBatchLogResult batchParam = new ModValBatchLogResult();
                batchParam.setFinprodId(processDto.getProdId());
                batchParam.setTaskId(taskId);
                batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                batchParam.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());//EXP 失败
                batchParam.setMsg("【减值模型数据推送批处理】：计算错误");
                bBatchLogResultList.add(batchParam);
            }
            dataPushEclResultProcessService.sendlogResults(bBatchLogResultList,stepExecution);
            //=====记录计算错误日志=====end=====
        }


    }

}