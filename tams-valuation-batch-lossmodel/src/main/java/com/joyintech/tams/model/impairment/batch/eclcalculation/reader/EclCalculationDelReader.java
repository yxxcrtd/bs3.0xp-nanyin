package com.joyintech.tams.model.impairment.batch.eclcalculation.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.EclCalculationReaderDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EclCalculationDelReader extends BaseReader<EclCalculationReaderDto> {
    @Override
    public List<EclCalculationReaderDto> createList() {
        List<EclCalculationReaderDto> result = new ArrayList<>();
        result.add(new EclCalculationReaderDto());
        return result;
    }
}
