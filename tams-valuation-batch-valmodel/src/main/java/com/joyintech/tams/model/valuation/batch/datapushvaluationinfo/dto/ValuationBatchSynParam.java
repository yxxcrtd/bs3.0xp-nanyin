package com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto;

import java.util.List;

/**
 * 估值批处理同步参数
 * @author zhangfei
 * @date 2021年7月9日14:04:29
 *
 */
public class ValuationBatchSynParam {
	
	private List<ValuationBatchSynDto> synList;

	public List<ValuationBatchSynDto> getSynList() {
		return synList;
	}

	public void setSynList(List<ValuationBatchSynDto> synList) {
		this.synList = synList;
	}
	
	
	
}
