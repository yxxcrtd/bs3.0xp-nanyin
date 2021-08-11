package com.joyintech.tams.model.impairment.batch.impairmentdataload.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ReaderDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.service.ImpairmentDataLoadReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpairmentDataLoadReader extends BaseReader <ImpairmentDataLoadStep1ReaderDto>{
    /**
     *  逾期资产结转批处理Service
     */
    @Autowired
    private ImpairmentDataLoadReaderService impairmentDataLoadReaderService;
    @Override
    public List<ImpairmentDataLoadStep1ReaderDto> createList() {
        List<ImpairmentDataLoadStep1ReaderDto> result = impairmentDataLoadReaderService.createReaderList(getParams());
        return result;
    }
}
