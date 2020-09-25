package com.joyintech.tams.invest.batch.tradorSeq.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.tams.invest.batch.overduesecpositionforward.dto.OverdueSecPositionForwardResultDto;
import com.joyintech.tams.invest.batch.overduesecpositionforward.service.OverdueSecPositionForwardService;
import com.joyintech.tams.invest.batch.tradorSeq.dto.TradorSeqForwardResultDto;
import com.joyintech.tams.invest.batch.tradorSeq.service.TradorSeqForwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradorSeqForwardWriter extends BaseWriter<TradorSeqForwardResultDto> {
    /**
     * 新增交易员序列
     */
    @Autowired
    private TradorSeqForwardService tradorSeqForwardService;
    @Override
    public void write(List<? extends TradorSeqForwardResultDto> items) throws Exception {
        tradorSeqForwardService.holdResultDto(items);
    }
}
