package com.joyintech.tams.model.impairment.batch.eclcalculation.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.EclCalculationReaderDto;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.EclCalculationResultDto;
import org.springframework.stereotype.Service;

@Service
public class EclCalculationDelProcessor extends BaseProcessor<EclCalculationReaderDto, EclCalculationResultDto> {
    @Override
    public EclCalculationResultDto process(EclCalculationReaderDto item) throws Exception {
        EclCalculationResultDto resultDto = new EclCalculationResultDto();
        resultDto.setProdId("减值模型ECL计算批处理删除！");
        return resultDto;
    }
}
