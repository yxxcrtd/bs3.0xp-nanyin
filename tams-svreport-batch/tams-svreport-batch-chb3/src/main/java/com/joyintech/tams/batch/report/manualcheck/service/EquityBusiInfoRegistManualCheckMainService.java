package com.joyintech.tams.batch.report.manualcheck.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.submitmanagement.equitybusiinforegist.service.EquityBusiInfoRegist001Service;

/**
 * 投资者明细信息数据检查批处理 业务service
 *
 * @author zhengxiaolian
 * @version 1.0 2019年11月21日
 * @see EquityBusiInfoRegistManualCheckMainService
 * @since 1.0
 */
@Service
public class EquityBusiInfoRegistManualCheckMainService extends AutoSendCBBaseService {

	/**
	 * EquityInfoRegist001Service
	 */
	@Autowired
	private EquityBusiInfoRegist001Service equityBusiInfoRegist001Service;

	/**
	 * 数据检查
	 *
	 * @see
	 * @since 1.0
	 */
	public void manualCheck(SysBatchExecInfo batchExecuteInfo) {
		setBatchBeanName(BatchBeanName.投资者明细信息数据检查批处理);
		logInfoBatchStart();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sendStatus", "01");
		equityBusiInfoRegist001Service.sendBeforeCheck(map);
		logInfoBatchEnd();
	}

}
