package com.joyintech.tams.valuation.batch.batchdispatch.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDispatchProcessorDto;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDispatchReaderDto;

@Service
@Scope("prototype")
public class BatchDispatchProcessorStep0 extends BaseProcessor<BatchDispatchReaderDto, NeedToWriteEntity>{
    
    public NeedToWriteEntity process(BatchDispatchReaderDto readerDto)
            throws Exception {
            // 任务组执行ID
            BatchDispatchProcessorDto dto = new BatchDispatchProcessorDto();
            dto.setOldDate(readerDto.getOldDate());
            return dto;
        }
}

