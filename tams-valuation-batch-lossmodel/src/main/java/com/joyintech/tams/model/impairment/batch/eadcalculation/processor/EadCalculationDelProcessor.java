package com.joyintech.tams.model.impairment.batch.eadcalculation.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.model.impairment.batch.eadcalculation.dto.EadCalCulationResultDto;
import com.joyintech.tams.model.impairment.batch.eadcalculation.dto.EadCalculationReaderDto;
import org.springframework.stereotype.Service;

@Service
public class EadCalculationDelProcessor extends BaseProcessor<EadCalculationReaderDto, EadCalCulationResultDto> {
    @Override
    public EadCalCulationResultDto process(EadCalculationReaderDto item) throws Exception {
        EadCalCulationResultDto resultDto = new EadCalCulationResultDto();
        resultDto.setProdId("减值模型EAD计算批处理删除！");
        return resultDto;
    }
}
