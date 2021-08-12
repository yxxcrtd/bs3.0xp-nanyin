package com.joyintech.tams.invest.risk.creditrisklimitmanagement.repdealcounterwhitelist.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepAccessList;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.repdealcounterwhitelist.validator.RepDealCounterWhiteListValidator;

/**
 * 
 *  
 * 〈功能详细描述〉
 * @author wangjie1
 * @version 1.0 2020年2月26日
 * @see RepDealCounterWhiteListDto
 * @since 1.0
 */
@PageId("RskAuthorityManage002")
@ValidLogic(targetClass = RepDealCounterWhiteListValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class RepDealCounterWhiteListDto extends RepAccessList {
	
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
		return listTypeText;
	}

	public void setListTypeText(String listTypeText) {
		this.listTypeText = listTypeText;
	}

	public String getStatusText() {
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
