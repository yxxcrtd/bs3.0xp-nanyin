package com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto.ValuationBatchSynDto;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto.ValuationBatchSynResultDto;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.service.DataPushValuatIoninfoProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:数据推送批处理Processor
 * @auther: yuzj
 * @date: 2021/7/13
 * @param:
 * @return:
 */
@Service
public class DataPushValuatIoninfoProcessor extends BaseProcessor<ValuationBatchSynDto, ValuationBatchSynResultDto> {
    /**
     *  数据推送批处理Service
     */
    @Autowired
    private DataPushValuatIoninfoProcessService dataPushValuatIoninfoProcessService;

    /**
     * 处理dto reader -> result
     * @param item 读取到的dto
     * @return 处理转换结果
     * @throws Exception 处理异常
     */
    @Override
    public ValuationBatchSynResultDto process(ValuationBatchSynDto item) throws Exception {
        ValuationBatchSynResultDto result= dataPushValuatIoninfoProcessService.execute(item,getParams());
        return result;
    }
}
