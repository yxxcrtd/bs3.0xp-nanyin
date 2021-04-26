package com.joyintech.tams.settle.batch.datadeal.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.settle.batch.datadeal.dto.StlDownDataDealOperatorDto;
import com.joyintech.tams.settle.batch.datadeal.dto.StlDownDataDealWriteDto;
import com.joyintech.tams.settle.batch.datadeal.service.StlDataDealProcessService;

/**
 * 结算数据批处理step2 processor
 * 
 * @author 陈卓
 * @date 2020/7/7
 * @see StlDownDataDealProcessor
 * @since 1.0
 */
@Service
@Lazy
public class StlDownDataDealProcessor extends BaseProcessor<NeedOperateEntity, StlDownDataDealWriteDto> {
	
	@Autowired
    private StlDataDealProcessService stlDataDealProcessService;

	@Override
    public StlDownDataDealWriteDto process(NeedOperateEntity item) throws Exception {
		StlDownDataDealOperatorDto stlDownDataDealOperatorDto = (StlDownDataDealOperatorDto)item;
		return stlDataDealProcessService.generatorStlDownDataDealWriteDto(stlDownDataDealOperatorDto);
	}
}
