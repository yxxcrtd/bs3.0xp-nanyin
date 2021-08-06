package com.joyintech.tams.model.impairment.batch.impairmentdataload.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ReaderDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImpairmentDataLoadDelReader extends BaseReader<ImpairmentDataLoadStep1ReaderDto> {
    @Override
    public List<ImpairmentDataLoadStep1ReaderDto> createList() {
        List<ImpairmentDataLoadStep1ReaderDto> result = new ArrayList<>();
        result.add(new ImpairmentDataLoadStep1ReaderDto());
        return result;
    }
}
