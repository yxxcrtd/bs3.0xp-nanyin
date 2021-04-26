package com.joyintech.tams.settle.batch.datadeal.processor;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.settle.batch.datadeal.dto.StlDownDataDealDeleteWriteDto;

/**
 * @author 何阳阳
 * @date 2020/7/7
 * @see StlDownDataDealDeleteProcessor
 * @since 1.0
 */
@Service
@Lazy
public class StlDownDataDealDeleteProcessor extends BaseProcessor<NeedOperateEntity, StlDownDataDealDeleteWriteDto> {
	
	@Override
    public StlDownDataDealDeleteWriteDto process(NeedOperateEntity item) throws Exception {
		return new StlDownDataDealDeleteWriteDto();
	}
}
