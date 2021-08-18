package com.joyintech.tams.model.impairment.batch.eclcalculation.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.EclCalculationReaderDto;
import com.joyintech.tams.model.impairment.batch.eclcalculation.service.EclCalculationReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EclCalculationReader extends BaseReader <EclCalculationReaderDto>{
    /**
     *  ECL计算Service
     */
    @Autowired
    private EclCalculationReaderService eclCalculationReaderService;
    @Override
    public List<EclCalculationReaderDto> createList() {
        List<EclCalculationReaderDto> result = eclCalculationReaderService.createReaderList(getParams());
        return result;
    }
}
