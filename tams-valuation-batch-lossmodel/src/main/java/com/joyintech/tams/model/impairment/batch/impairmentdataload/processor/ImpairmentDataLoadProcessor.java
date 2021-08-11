package com.joyintech.tams.model.impairment.batch.impairmentdataload.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ReaderDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ResultDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.service.ImpairmentDataLoadProcessorService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpairmentDataLoadProcessor extends BaseProcessor<ImpairmentDataLoadStep1ReaderDto, ImpairmentDataLoadStep1ResultDto> implements StepExecutionListener {
    /**
     *  逾期资产结转批处理Service
     */
    @Autowired
    private ImpairmentDataLoadProcessorService impairmentDataLoadProcessorService;

    private StepExecution stepExecution;

    /**
     *  处理dto reade -> result
     * @param item 读取到的dto
     * @return 处理转换结果
     * @throws Exception 处理异常
     */
    @Override
    public ImpairmentDataLoadStep1ResultDto process(ImpairmentDataLoadStep1ReaderDto item) throws Exception {
        return impairmentDataLoadProcessorService.excute(item,getParams(), stepExecution);
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
