package com.joyintech.tams.invest.common.trade.dto;

import com.joyintech.tams.common.base.entity.CreditLinesDeal;
import com.joyintech.tams.invest.common.risktrial.dto.CreditLinesDetail;

import java.util.List;

public class CreditLinesDealCommon extends CreditLinesDeal{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//错误提示消息
	String errMessage;
	//提示消息
	String message;
	//平安银行授信个性化添加 start
	List<CreditLinesDetail> creditLinesdetail;

	//授信额度校验结果
	String creditresult;
	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<CreditLinesDetail> getCreditLinesdetail() {
		return creditLinesdetail;
	}

	public void setCreditLinesdetail(List<CreditLinesDetail> creditLinesdetail) {
		this.creditLinesdetail = creditLinesdetail;
	}

	public String getCreditresult() {
		return creditresult;
	}

	public void setCreditresult(String creditresult) {
		this.creditresult = creditresult;
	}
}
