package com.joyintech.tams.model.impairment.batch.impairmentdataload.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ReaderDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ResultDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep2ReaderDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.service.ImpairmentDataLoadProcessorService;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.service.ImpairmentDataLoadProcessorStep2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpairmentDataLoadStep2Processor extends BaseProcessor<ImpairmentDataLoadStep2ReaderDto, ImpairmentDataLoadStep1ResultDto> {
    /**
     *  逾期资产结转批处理Service
     */
    @Autowired
    private ImpairmentDataLoadProcessorStep2Service impairmentDataLoadProcessorService;

    /**
     *  处理dto reade -> result
     * @param item 读取到的dto
     * @return 处理转换结果
     * @throws Exception 处理异常
     */
    @Override
    public ImpairmentDataLoadStep1ResultDto process(ImpairmentDataLoadStep2ReaderDto item) throws Exception {
        return impairmentDataLoadProcessorService.excute(item,getParams());
    }
}
