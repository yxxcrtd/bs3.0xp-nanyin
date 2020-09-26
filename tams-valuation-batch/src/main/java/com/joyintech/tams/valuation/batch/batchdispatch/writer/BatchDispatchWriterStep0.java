package com.joyintech.tams.valuation.batch.batchdispatch.writer;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.batchdispatch.dao.BatchDispatchMapper;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDispatchProcessorDto;

@Service
@Scope("prototype")
public class BatchDispatchWriterStep0 extends BaseWriter<NeedToWriteEntity>{
	
    /**
     * batchExecuteDetailMapper
     */
    @Autowired
    BatchDispatchMapper batchDispatchMapper;
	
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> list)
        throws Exception {
        if (list == null || list.isEmpty()) {
            return;
        }
        List<BatchDispatchProcessorDto> dtoList = (List<BatchDispatchProcessorDto>)list;
        for (BatchDispatchProcessorDto dto : dtoList) {
            Date oldDate = dto.getOldDate();
            batchDispatchMapper.deleteOldData(oldDate);
        }
    }
}
