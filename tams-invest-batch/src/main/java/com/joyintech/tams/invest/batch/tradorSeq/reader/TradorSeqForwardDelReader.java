package com.joyintech.tams.invest.batch.tradorSeq.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.invest.batch.tradorSeq.dto.TradorSeqForwardDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradorSeqForwardDelReader extends BaseReader<TradorSeqForwardDto> {
    @Override
    public List<TradorSeqForwardDto> createList() {
        List<TradorSeqForwardDto> result = new ArrayList<>();
        result.add(new TradorSeqForwardDto());
        return result;
    }
}
