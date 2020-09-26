package com.joyintech.tams.batch.svreport.chb.investtradecb.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.chb.investtradereg.service.InvestTradeRegistBatchService;

/**
 * 功能:交易登记批处理入口
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InvestTradeCBMainService extends AutoSendCBBaseService {
    
	@Autowired
	private InvestTradeRegistBatchService investTradeRegistBatchService;

	/**
	 * 功能:资产交易登记
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年2月21日
     * @param executeDate 跑批日
     * @param prodList 产品代码
     * @param tradenoList 交易编号
	 * void
	 * @throws BatchException 
	 */
    public void autoRegistInvestTrade(Date executeDate,List<String> prodList,List<String> tradenoList) throws BatchException {
	    investTradeRegistBatchService.autoRegistInvestTrade(executeDate, prodList, tradenoList);
	}

}
