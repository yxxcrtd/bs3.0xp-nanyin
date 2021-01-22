package com.joyintech.tams.batch.svreport.chb.inInvestTradeSub.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.svreport.chbhz.inInvestTradeSub.service.InInvestTradeInfoBatchService;

/**
 * 功能:产品交易登记生成批处理入口
 * @since 
 * @version 
 * @作者：zhaoyongsheng
 * @创建时间：
 * @修改记录：
 */
@Service(value="inInvestTradeInfoMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class InInvestTradeInfoMainService {

    @Autowired
	private InInvestTradeInfoBatchService inInvestTradeInfoBatchService;
	
    /**
     * @param bookDate 
     * @see
     * @since 1.0
     */
    public void generateDurationRegData(Date batchDate,List<String> prodIdList) throws BatchException {
    	inInvestTradeInfoBatchService.generateInvestTradeInfoData(batchDate, prodIdList);
    }
}
