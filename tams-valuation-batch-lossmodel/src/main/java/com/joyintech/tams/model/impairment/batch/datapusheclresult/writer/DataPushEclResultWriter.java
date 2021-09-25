package com.joyintech.tams.model.impairment.batch.datapusheclresult.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.dto.AssetLossResultProcessDto;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.service.DataPushEclResultProcessService;
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
        List<AssetLossResultProcessDto> resultList = (List<AssetLossResultProcessDto>)items;
        String taskId =(String)mapParam.get("TASK_ID");
        try {
            dataPushEclResultProcessService.sendEclResultResult(resultList);
        } catch (Exception e) {

        }
    }

}