package com.joyintech.tams.invest.bankuplink.basic.errorcodeinfocompare.dto;

import com.joyintech.framework.base.BaseEntity;

public class ErrorCodeInfoCompare001Dto extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 错误码
	 */
	private String errCode;
	
	/**
	 * 错误信息
	 */
	private String errMsg;

	/**
	 * 取得 错误码
	 * @return
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * 设置 错误码
	 * @param errCode
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * 取得 错误信息
	 * @return
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * 设置 错误信息
	 * @param errMsg
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	

}
