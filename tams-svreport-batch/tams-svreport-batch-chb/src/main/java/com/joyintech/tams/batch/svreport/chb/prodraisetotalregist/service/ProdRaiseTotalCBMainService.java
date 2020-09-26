package com.joyintech.tams.batch.svreport.chb.prodraisetotalregist.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.chb.prodraisetotalregist.service.ProdRaiseTotalRegistBatchService;

/**
 * 功能:募集总量登记批处理入口
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdRaiseTotalCBMainService extends AutoSendCBBaseService {
	@Autowired
	private ProdRaiseTotalRegistBatchService prodRaiseTotalRegistBatchService;
	
	public void generateRegistData(Date executeDate,List<String> prodIdList) throws BatchException {
	    prodRaiseTotalRegistBatchService.generateRegistData(executeDate, prodIdList);
	}
	

}
