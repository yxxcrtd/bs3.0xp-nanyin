package com.joyintech.tams.model.impairment.batch.datapusheclresult.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.dto.AssetLossResultProcessDto;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.dto.AssetLossResultReaderDto;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.service.DataPushEclResultProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:数据推送批处理Processor
 * @auther: yuzj
 * @date: 2021/7/20
 * @param:
 * @return:
 */
@Service
public class DataPushEclResultProcessor extends BaseProcessor<AssetLossResultReaderDto, AssetLossResultProcessDto> {
    /**
     *  数据推送批处理Service
     */
    @Autowired
    private DataPushEclResultProcessService dataPushEclResultProcessService;

    /**
     * 处理dto reader -> result
     * @param item 读取到的dto
     * @return 处理转换结果
     * @throws Exception 处理异常
     */
    @Override
    public AssetLossResultProcessDto process(AssetLossResultReaderDto item) throws Exception {
        AssetLossResultProcessDto result= dataPushEclResultProcessService.execute(item,getParams());
        return result;
    }
}
