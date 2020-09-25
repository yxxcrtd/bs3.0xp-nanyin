package com.joyintech.tams.invest.batch.tradorSeq.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.invest.batch.overduesecpositionforward.dto.OverdueSecPositionForwardDto;
import com.joyintech.tams.invest.batch.overduesecpositionforward.service.OverdueSecPositionForwardService;
import com.joyintech.tams.invest.batch.tradorSeq.dto.TradorSeqForwardDto;
import com.joyintech.tams.invest.batch.tradorSeq.service.TradorSeqForwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradorSeqForwardReader extends BaseReader <TradorSeqForwardDto>{
    /**
     *  逾期资产结转批处理Service
     */
    @Autowired
    private TradorSeqForwardService tradorSeqForwardService;
    @Override
    public List<TradorSeqForwardDto> createList() {
        List<TradorSeqForwardDto> result = tradorSeqForwardService.selectTradorSeqByRole();
        return result;
    }
}
