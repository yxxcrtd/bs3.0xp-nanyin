package com.joyintech.tams.model.impairment.batch.impairmentdataload.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep2ReaderDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.service.ImpairmentDataLoadReaderStep2Service;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpairmentDataLoadStep2Reader extends BaseReader <ImpairmentDataLoadStep2ReaderDto>   implements StepExecutionListener {
    /**
     *  逾期资产结转批处理Service
     */
    @Autowired
    private ImpairmentDataLoadReaderStep2Service impairmentDataLoadReaderService;

    private StepExecution stepExecution;

    @Override
    public List<ImpairmentDataLoadStep2ReaderDto> createList() {
        List<ImpairmentDataLoadStep2ReaderDto> result = impairmentDataLoadReaderService.createReaderList(getParams(), stepExecution);
        return result;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

}
