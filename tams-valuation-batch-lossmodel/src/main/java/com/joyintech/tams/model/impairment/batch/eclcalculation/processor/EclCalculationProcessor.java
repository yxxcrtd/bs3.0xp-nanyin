package com.joyintech.tams.model.impairment.batch.eclcalculation.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.EclCalculationReaderDto;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.EclCalculationResultDto;
import com.joyintech.tams.model.impairment.batch.eclcalculation.service.EclCalculationProcessorService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EclCalculationProcessor extends BaseProcessor<EclCalculationReaderDto, EclCalculationResultDto>  implements StepExecutionListener {
    /**
     *  逾期资产结转批处理Service
     */
    @Autowired
    private EclCalculationProcessorService eclCalculationProcessorService;

    private StepExecution stepExecution;

    /**
     *  处理dto reade -> result
     * @param item 读取到的dto
     * @return 处理转换结果
     * @throws Exception 处理异常
     */
    @Override
    public EclCalculationResultDto process(EclCalculationReaderDto item) throws Exception {
        return eclCalculationProcessorService.excute(item,getParams(), stepExecution);
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
