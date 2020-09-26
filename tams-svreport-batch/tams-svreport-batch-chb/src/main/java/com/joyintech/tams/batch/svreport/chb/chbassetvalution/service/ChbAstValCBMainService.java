package com.joyintech.tams.batch.svreport.chb.chbassetvalution.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.chb.astvalueregist.service.ChbAstValRegistBatchService;

/**
 * 功能:资产估值登记批处理调度入口
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChbAstValCBMainService extends AutoSendCBBaseService{

	@Autowired
	private ChbAstValRegistBatchService chbAstValRegistBatchService;

	public void createRegist(Date batchTime) throws BatchException {
	    chbAstValRegistBatchService.createRegist(batchTime);
	}

}