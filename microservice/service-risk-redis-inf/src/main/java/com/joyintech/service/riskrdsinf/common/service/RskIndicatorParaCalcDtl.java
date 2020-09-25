package com.joyintech.service.riskrdsinf.common.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.riskrdsinf.queue.TaskFactory;
import com.joyintech.redis.annotation.Listener;
import com.joyintech.redis.msg.consumer.IConsumer;
import com.joyintech.redis.msg.entity.MessageBody.Operation;
import com.joyintech.tams.common.base.entity.RskIndicatorParaCalcdetail;
import com.joyintech.tams.invest.common.batchList.dao.RskIndicatorParaCalcdetailListMapper;
import com.joyintech.tams.invest.common.batchList.service.BatchListService;

@Service
@Listener
public class RskIndicatorParaCalcDtl implements IConsumer<RskIndicatorParaCalcdetail> {

    @Autowired
    private BatchListService<RskIndicatorParaCalcdetail> service;

    @Autowired
    private RskIndicatorParaCalcdetailListMapper mapper;
    
    private static TaskFactory<RskIndicatorParaCalcdetail> factory;

	@Override
	public void consume(Operation opr, Collection<RskIndicatorParaCalcdetail> vList) {
		service.setBatchListMapper(mapper);
		service.consume(opr, vList);
//		init(service, mapper);
//		factory.addAll(vList);
	}

	protected static final void init(BatchListService<RskIndicatorParaCalcdetail> service, 
			RskIndicatorParaCalcdetailListMapper mapper){
		if (factory == null) {
			factory = new TaskFactory<>(service, mapper);
		}
	}
}
