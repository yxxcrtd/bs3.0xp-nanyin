package com.jixu.journal.obj;

import java.util.List;

import com.orm.obj.TranBasicFee;
import com.orm.obj.TranSubRed;

public class SubRedWrapObj {

	/**
	 * 申购或者赎回交易
	 */
	private TranSubRed tranSubRed;

	/**
	 * 申赎的各项费用
	 */
	private List<TranBasicFee> tranFeeList;
	
	public TranSubRed getTranSubRed() {
		return tranSubRed;
	}

	public void setTranSubRed(TranSubRed tranSubRed) {
		this.tranSubRed = tranSubRed;
	}

	public List<TranBasicFee> getTranFeeList() {
		return tranFeeList;
	}

	public void setTranFeeList(List<TranBasicFee> tranFeeList) {
		this.tranFeeList = tranFeeList;
	}
}
