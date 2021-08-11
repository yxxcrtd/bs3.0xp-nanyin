package com.joyintech.tams.model.impairment.batch.eadcalculation.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.model.impairment.batch.eadcalculation.dto.EadCalculationReaderDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EadCalCulationDelReader extends BaseReader<EadCalculationReaderDto> {
    @Override
    public List<EadCalculationReaderDto> createList() {
        List<EadCalculationReaderDto> result = new ArrayList<EadCalculationReaderDto>();
        result.add(new EadCalculationReaderDto());
        return result;
    }
}
