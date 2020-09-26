/*
 * 文件名：BatchDispatchProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年4月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.batchdispatch.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDispatchProcessorDto;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDispatchReaderDto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhiang
 * @version 1.0 2018年4月27日
 * @see BatchDispatchProcessorStep3
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BatchDispatchProcessorStep3 extends BaseProcessor<BatchDispatchReaderDto, NeedToWriteEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public NeedToWriteEntity process(BatchDispatchReaderDto readerDto)
        throws Exception {
        // 任务组执行ID
        BatchDispatchProcessorDto dto = new BatchDispatchProcessorDto();
        dto.setSysBatchgrpExecInfo(readerDto.getSysBatchgrpExecInfo());
        dto.setProdId(readerDto.getDetailSeqNo());
        return dto;
    }
}
