package com.joyintech.tams.model.impairment.batch.datapusheclresult.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.dto.AssetLossResultReaderDto;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.service.DataPushEclResultReaderService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Description:减值模型-数据推送批处理Reader
 * @auther: yuzj
 * @date: 2021-7-20
 * @param:
 * @return:
 */
@Service
public class DataPushEclResultReader extends BaseReader <AssetLossResultReaderDto>  implements StepExecutionListener {
    /**
     *  数据推送批处理Service
     */
    @Autowired
    private DataPushEclResultReaderService dataPushEclResultReaderService;

    private StepExecution stepExecution;


    @Override
    public List<AssetLossResultReaderDto> createList() {
        List<AssetLossResultReaderDto> result = dataPushEclResultReaderService.execute(getParams(),stepExecution);
        return result;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution=stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
