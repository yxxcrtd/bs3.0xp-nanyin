package com.joyintech.tams.model.impairment.batch.impairmentdataload.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ReaderDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.service.ImpairmentDataLoadReaderService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service public class ImpairmentDataLoadReader
    extends BaseReader<ImpairmentDataLoadStep1ReaderDto>  implements StepExecutionListener {

    /**
     * 逾期资产结转批处理Service
     */
    @Autowired private ImpairmentDataLoadReaderService impairmentDataLoadReaderService;

    private StepExecution stepExecution;

    @Override public List<ImpairmentDataLoadStep1ReaderDto> createList() {
        return  impairmentDataLoadReaderService.createReaderList(getParams(), stepExecution);
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
