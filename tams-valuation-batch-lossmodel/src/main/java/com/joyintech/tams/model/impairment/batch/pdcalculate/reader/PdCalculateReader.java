package com.joyintech.tams.model.impairment.batch.pdcalculate.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.model.impairment.batch.pdcalculate.dto.AssetGroupPdCalculateReaderDto;
import com.joyintech.tams.model.impairment.batch.pdcalculate.service.PdCalculateReaderService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:减值-PD计算批处理Reader
 * @auther: yuzj
 * @date: 2021/7/14
 * @param:
 * @return:
 */
@Service
public class PdCalculateReader extends BaseReader<AssetGroupPdCalculateReaderDto> implements StepExecutionListener {

    private StepExecution stepExecution;


    /**
     *  减值-PD计算批处理Service
     */
    @Autowired
    private PdCalculateReaderService pdCalculateReaderService;
    @Override
    public List<AssetGroupPdCalculateReaderDto> createList() {
        List<AssetGroupPdCalculateReaderDto> result = pdCalculateReaderService.execute(getParams(), stepExecution);
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
