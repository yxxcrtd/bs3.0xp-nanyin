package com.joyintech.tams.batch.report.investorsholdautomanual.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.service.InvestorsHoldInfoRegist001Service;

@Service
public class InvestorsHoldInfoRegistAutoManualCBMainService extends AutoSendCBBaseService{
	
	/**
	 * 投资者持有数量信息登记
	 *
	 */
	@Autowired
	private InvestorsHoldInfoRegist001Service investorsHoldInfoRegist001Service;
	
    /**
     * 手工处理
     *
     * @see
     * @since 1.0
     */
    public void autoManualCB() {
        setBatchBeanName(BatchBeanName.投资者持有信息登记手工处理);
        logInfoBatchStart();
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("sendStatus", "01");
        investorsHoldInfoRegist001Service.updateSumStausAll(map);
        logInfoBatchEnd();
    }
}
