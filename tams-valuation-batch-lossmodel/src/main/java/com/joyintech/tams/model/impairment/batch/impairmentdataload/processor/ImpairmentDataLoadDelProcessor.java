package com.joyintech.tams.model.impairment.batch.impairmentdataload.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ReaderDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ResultDto;
import org.springframework.stereotype.Service;

@Service
public class ImpairmentDataLoadDelProcessor extends BaseProcessor<ImpairmentDataLoadStep1ReaderDto, ImpairmentDataLoadStep1ResultDto> {
    @Override
    public ImpairmentDataLoadStep1ResultDto process(ImpairmentDataLoadStep1ReaderDto item) throws Exception {
        ImpairmentDataLoadStep1ResultDto resultDto = new ImpairmentDataLoadStep1ResultDto();
        resultDto.setProdId("减值模型数据加载批处理删除！");
        return resultDto;
    }
}
