package com.joyintech.tams.settle.batch.datadeal.writer;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * @author 何阳阳
 * @date 2020/7/7
 * @see StlDownDataDealDeleteWriter
 * @since 1.0
 */
@Service
@Lazy
public class StlDownDataDealDeleteWriter extends BaseWriter<NeedToWriteEntity> {
	
	@Override
    public void write(List<? extends NeedToWriteEntity> items) throws Exception {
		return;
	}
}
