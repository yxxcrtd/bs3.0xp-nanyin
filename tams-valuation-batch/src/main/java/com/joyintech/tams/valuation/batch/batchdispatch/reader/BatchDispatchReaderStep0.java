package com.joyintech.tams.valuation.batch.batchdispatch.reader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDispatchReaderDto;

/**
 * 清除三天前的记录 - reader
 */
@Service
@Scope("prototype")
public  class BatchDispatchReaderStep0 extends BaseReader<BatchDispatchReaderDto>{

	@Override
	public List<BatchDispatchReaderDto> createList() {
        Date oldDate = (Date) this.getParams().get("oldDate");
		List<BatchDispatchReaderDto> result = new ArrayList<>();
        BatchDispatchReaderDto dto = new BatchDispatchReaderDto();
        dto.setOldDate(oldDate);
        result.add(dto);
		return result ;
	}
}
