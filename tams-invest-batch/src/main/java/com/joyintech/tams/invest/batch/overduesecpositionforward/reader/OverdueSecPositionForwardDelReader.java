package com.joyintech.tams.invest.batch.overduesecpositionforward.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.invest.batch.overduesecpositionforward.dto.OverdueSecPositionForwardDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OverdueSecPositionForwardDelReader extends BaseReader<OverdueSecPositionForwardDto> {
    @Override
    public List<OverdueSecPositionForwardDto> createList() {
        List<OverdueSecPositionForwardDto> result = new ArrayList<>();
        result.add(new OverdueSecPositionForwardDto());
        return result;
    }
}
