package com.joyintech.tams.invest.batch.prebatchcheck.assetmarketdata.dto;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
/**
 * 资产行情实体类，继承自SysPrebatchResultDetail，添加了校验所需要的几个属性
 * @author yantianyuan
 * 创建日期：2021-05-13
 */
public class AssetMarketDataResultDto extends SysPrebatchResultDetail{
	
	/**
	 * 获取账套代码
	 * @return
	 */
	public String getBooksetId() {
		return booksetId;
	}
	
	/**
	 * 设置账套代码
	 * @param booksetId
	 */
	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}
	/**
	 * 获取投资目的
	 * @return
	 */
	public String getInvAim() {
		return invAim;
	}
	/**
	 * 设置投资目的
	 * @param invAim
	 */
	public void setInvAim(String invAim) {
		this.invAim = invAim;
	}
	/**
	 * 获取证券类型
	 * @return
	 */
	public String getSecType() {
		return secType;
	}
	/**
	 * 设置证券类型
	 * @param secType
	 */
	public void setSecType(String secType) {
		this.secType = secType;
	}
	/**
	 * 获取交易市场
	 * @return
	 */
	public String getTradeMarket() {
		return tradeMarket;
	}
	/**
	 * 设置交易市场
	 * @param tradeMarket
	 */
	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}
	/**
	 * 获取交易日历
	 * @return
	 */
	public String getCalenderId() {
		return calenderId;
	}
	/**
	 * 设置交易日历
	 * @param calenderId
	 */
	public void setCalenderId(String calenderId) {
		this.calenderId = calenderId;
	}
	/**
	 * 账套代码
	 */
	private String booksetId;
	/**
	 * 投资目的
	 */
	private String invAim;
	/**
	 * 证券类型
	 */
	private String secType;
	
	/**
	 * 交易场所
	 */
	private String tradeMarket;
	/**
	 * 交易日历
	 */
	private String calenderId;

}
