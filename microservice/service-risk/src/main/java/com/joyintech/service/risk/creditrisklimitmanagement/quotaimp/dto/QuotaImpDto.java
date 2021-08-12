package com.joyintech.service.risk.creditrisklimitmanagement.quotaimp.dto;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.constant.DictCode;

public class QuotaImpDto extends CreditLines{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String termStr;
	
	private String amountName;
	
	private String customerName;
	
	private String amountType;
	
	private String insitituteCode;

	public String getTermStr() {
		return termStr;
	}

	public void setTermStr(String termStr) {
		this.termStr = termStr;
	}

	public String getAmountName() {
		if (StringUtil.isNotEmpty(this.getAmountType())) {
			this.amountName = SysDataUtil.getNameFromDataDict(DictCode.额度分类,this.getAmountType());
  		     }
  		return amountName;
	}

	public void setAmountName(String amountName) {
		this.amountName = amountName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	public String getInsitituteCode() {
		return insitituteCode;
	}

	public void setInsitituteCode(String insitituteCode) {
		this.insitituteCode = insitituteCode;
	}
	
	
}
