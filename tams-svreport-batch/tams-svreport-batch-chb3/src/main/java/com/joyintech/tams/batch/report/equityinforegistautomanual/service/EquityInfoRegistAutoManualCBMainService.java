package com.joyintech.tams.batch.report.equityinforegistautomanual.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.submitmanagement.equityinforegist.service.EquityInfoRegist001Service;

/**
 * 投资者身份信息登记手工处理 业务service
 *
 * @author zhengxiaolian
 * @version 1.0 2020年01月07日
 * @see EquityInfoRegistAutoManualCBMainService
 * @since 1.0
 */
@Service
public class EquityInfoRegistAutoManualCBMainService extends AutoSendCBBaseService{

	@Autowired
	private EquityInfoRegist001Service equityInfoRegist001Service;
	
    /**
     * 手工处理
     *
     * @see
     * @since 1.0
     */
    public void autoManualCB() {
        setBatchBeanName(BatchBeanName.投资者身份信息登记手工处理);
        logInfoBatchStart();
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("sendStatus", "01");
        equityInfoRegist001Service.updateSumStausAll(map);
        logInfoBatchEnd();
    }
}
