package com.joyintech.tams.invest.batch.tradorSeq.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.invest.batch.overduesecpositionforward.dto.OverdueSecPositionForwardDto;
import com.joyintech.tams.invest.batch.overduesecpositionforward.dto.OverdueSecPositionForwardResultDto;
import com.joyintech.tams.invest.batch.overduesecpositionforward.service.OverdueSecPositionForwardService;
import com.joyintech.tams.invest.batch.tradorSeq.dto.TradorSeqForwardDto;
import com.joyintech.tams.invest.batch.tradorSeq.dto.TradorSeqForwardResultDto;
import com.joyintech.tams.invest.batch.tradorSeq.service.TradorSeqForwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradorSeqForwardProcessor extends BaseProcessor<TradorSeqForwardDto, TradorSeqForwardResultDto> {
    /**
     *  逾期资产结转批处理Service
     */
    @Autowired
    private TradorSeqForwardService tradorSeqForwardService;

    /**
     *  处理dto reade -> result
     * @param item 读取到的dto
     * @return 处理转换结果
     * @throws Exception 处理异常
     */
    @Override
    public TradorSeqForwardResultDto process(TradorSeqForwardDto item) throws Exception {
        return tradorSeqForwardService.convertResultFromRead(item,getParams());
    }
}
