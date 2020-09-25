package com.joyintech.tams.invest.batch.overduesecpositionforward.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.invest.batch.overduesecpositionforward.dto.OverdueSecPositionForwardDto;
import com.joyintech.tams.invest.batch.overduesecpositionforward.dto.OverdueSecPositionForwardResultDto;
import com.joyintech.tams.invest.batch.overduesecpositionforward.service.OverdueSecPositionForwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OverdueSecPositionForwardProcessor extends BaseProcessor<OverdueSecPositionForwardDto, OverdueSecPositionForwardResultDto> {
    /**
     *  逾期资产结转批处理Service
     */
    @Autowired
    private OverdueSecPositionForwardService overdueSecPositionForwardService;

    /**
     *  处理dto reade -> result
     * @param item 读取到的dto
     * @return 处理转换结果
     * @throws Exception 处理异常
     */
    @Override
    public OverdueSecPositionForwardResultDto process(OverdueSecPositionForwardDto item) throws Exception {
        return overdueSecPositionForwardService.convertResultFromRead(item,getParams());
    }
}
