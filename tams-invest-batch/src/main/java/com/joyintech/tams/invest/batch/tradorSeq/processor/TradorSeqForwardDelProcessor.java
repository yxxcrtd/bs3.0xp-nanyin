package com.joyintech.tams.invest.batch.tradorSeq.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.invest.batch.overduesecpositionforward.dto.OverdueSecPositionForwardDto;
import com.joyintech.tams.invest.batch.overduesecpositionforward.dto.OverdueSecPositionForwardResultDto;
import com.joyintech.tams.invest.batch.tradorSeq.dto.TradorSeqForwardDto;
import com.joyintech.tams.invest.batch.tradorSeq.dto.TradorSeqForwardResultDto;
import org.springframework.stereotype.Service;

@Service
public class TradorSeqForwardDelProcessor extends BaseProcessor<TradorSeqForwardDto, TradorSeqForwardResultDto> {
    @Override
    public TradorSeqForwardResultDto process(TradorSeqForwardDto item) throws Exception {
        TradorSeqForwardResultDto resultDto = new TradorSeqForwardResultDto();
        resultDto.setProdId("交易员序列批处理删除！");
        return resultDto;
    }
}
