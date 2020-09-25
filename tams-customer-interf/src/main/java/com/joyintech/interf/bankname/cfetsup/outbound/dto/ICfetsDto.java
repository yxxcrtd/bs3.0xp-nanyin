package com.joyintech.interf.bankname.cfetsup.outbound.dto;

public interface ICfetsDto {
	/**
	 * 根据业务类型判断调用的接口类型
	 */
	// public String businessType = null;

	public String getBusinessType();

	// public void setBusinessType(String businessType);
	/**
	 * 根据防重设计，报价表需要增加字段发送标记
	 */
	// public Integer sendmark;
	
	public Integer getSendmark();
	
	/**
	 * 获取主键
	 * @return 主键
	 */
	public String getSeqno();

	/**
	 * 是否再次发送
	 *
	 */
	public String getIsResend();

}
