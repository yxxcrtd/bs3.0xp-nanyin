package com.joyintech.tams.invest.batch.tradorSeq.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.overduesecpositionforward.dto.OverdueSecPositionForwardResultDto;
import com.joyintech.tams.invest.batch.overduesecpositionforward.service.OverdueSecPositionForwardDelService;
import com.joyintech.tams.invest.batch.tradorSeq.dto.TradorSeqForwardResultDto;
import com.joyintech.tams.invest.batch.tradorSeq.service.TradorSeqForwardDelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TradorSeqForwardDelWriter extends BaseWriter<TradorSeqForwardResultDto> {
    /**
     *删除序列service
     */
    @Autowired
    private TradorSeqForwardDelService tradorSeqForwardDelService;
    @Override
    public void write(List<? extends TradorSeqForwardResultDto> items) throws Exception {
        tradorSeqForwardDelService.deleteTradorSeq();
    }
}
