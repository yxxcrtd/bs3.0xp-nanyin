package com.joyintech.tams.batch.svreport.chb.prodterminationregist.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.svreport.chb.prodterminationregist.service.ProdTerminationRegistBatchService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProdTerminationCBMainService extends AutoSendCBBaseService {
	@Autowired
	private ProdTerminationRegistBatchService prodTerminationRegistBatchService;
	@Value("${application.reportInsertCount}")
	public  int reportInsertCount;
	
	public void generateRegistData(Date executeDate,List<String> prodIdList) throws BatchException {
		// 设置批处理bean
		setBatchBeanName(BatchBeanName.产品终止登记报送);
		// 批处理开始
		logInfoBatchStart();
		prodTerminationRegistBatchService.generateRegistData(executeDate, prodIdList);
		logInfoBatchEnd();

	}
	

}
