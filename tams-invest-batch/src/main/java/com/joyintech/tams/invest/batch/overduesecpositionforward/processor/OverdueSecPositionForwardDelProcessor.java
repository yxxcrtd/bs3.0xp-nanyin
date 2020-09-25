package com.joyintech.tams.invest.batch.overduesecpositionforward.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.invest.batch.autodurdealgenerate.bond.dto.BondAutoDurDealGenerateResult;
import com.joyintech.tams.invest.batch.overduesecpositionforward.dto.OverdueSecPositionForwardDto;
import com.joyintech.tams.invest.batch.overduesecpositionforward.dto.OverdueSecPositionForwardResultDto;
import org.springframework.stereotype.Service;

@Service
public class OverdueSecPositionForwardDelProcessor extends BaseProcessor<OverdueSecPositionForwardDto, OverdueSecPositionForwardResultDto> {
    @Override
    public OverdueSecPositionForwardResultDto process(OverdueSecPositionForwardDto item) throws Exception {
        OverdueSecPositionForwardResultDto resultDto = new OverdueSecPositionForwardResultDto();
        resultDto.setProdId("逾期结转批处理删除！");
        return resultDto;
    }
}
