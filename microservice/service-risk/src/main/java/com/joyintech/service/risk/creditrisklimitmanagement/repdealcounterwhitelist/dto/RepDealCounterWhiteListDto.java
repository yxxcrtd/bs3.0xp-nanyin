package com.joyintech.service.risk.creditrisklimitmanagement.repdealcounterwhitelist.dto;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RepAccessList;
import com.joyintech.tams.constant.DictCode;

public class RepDealCounterWhiteListDto  extends RepAccessList {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customerName;
	
	private String listTypeText;
	
	private String statusText;
	
	private String insitituteCode;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getListTypeText() {
		if (StringUtil.isNotEmpty(this.getListType())) {
			this.listTypeText = SysDataUtil.getNameFromDataDict(DictCode.回购对手类型,this.getListType());
  		     }
  		return listTypeText;
	}

	public void setListTypeText(String listTypeText) {
		this.listTypeText = listTypeText;
	}

	public String getStatusText() {
		if (StringUtil.isNotEmpty(this.getListType())) {
			this.statusText = SysDataUtil.getNameFromDataDict(DictCode.白名单状态,this.getStatus());
  		     }
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getInsitituteCode() {
		return insitituteCode;
	}

	public void setInsitituteCode(String insitituteCode) {
		this.insitituteCode = insitituteCode;
	}

}
