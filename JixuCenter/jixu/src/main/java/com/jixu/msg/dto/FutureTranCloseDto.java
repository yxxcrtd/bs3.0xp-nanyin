package com.jixu.msg.dto;

import com.jixu.common.valid.NotEmptyJixu;

public class FutureTranCloseDto extends FutureTranOpenDto{

	private static final long serialVersionUID = 1L;

	/**
	 * 合同编号
	 */
	@NotEmptyJixu(keymsg="合同编号")
	private String contract;

	

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}
	
}
