package com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto.ValuationBatchSynDto;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.service.DataPushValuatIoninfoReaderService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Description:数据推送批处理Reader
 * @auther: yuzj
 * @date: 2021-7-13
 * @param:
 * @return:
 */
@Service
public class DataPushValuatIoninfoReader extends BaseReader <ValuationBatchSynDto> implements StepExecutionListener {

    private StepExecution stepExecution;
    /**
     *  数据推送批处理Service
     */
    @Autowired
    private DataPushValuatIoninfoReaderService dataPushValuatIoninfoReaderService;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution=stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public List<ValuationBatchSynDto> createList() {
        List<ValuationBatchSynDto> result = dataPushValuatIoninfoReaderService.execute(getParams(),stepExecution);
        return result;
    }





}
