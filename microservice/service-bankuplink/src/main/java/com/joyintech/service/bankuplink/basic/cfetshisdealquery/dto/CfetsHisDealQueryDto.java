package com.joyintech.service.bankuplink.basic.cfetshisdealquery.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.service.bankuplink.ibu.dto.BondQuoteInfoDto;

public class CfetsHisDealQueryDto extends BondQuoteInfoDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 操作类型
	 */
	private String operateType;

	/**
	 * 标志（区分现券、质押）
	 */
	private String flag;
	
	/**
	 * 取得 操作类型
	 * @return
	 */
	public String getOperateType() {
		return operateType;
	}

	/**
	 * 设置 操作类型
	 * @param operateType
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	/**
	 * 取得 标志
	 * @return
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * 设置 标志
	 * @param flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	//新增 质押式回购字段 start
	
	/**
	 * 回购利率
	 */
	private BigDecimal rate;

	/**
	 * 首期结算日
	 */
	private Date vSettleDate;
	
	/**
	 * 到期结算日
	 */
	private Date mSettleDate;
	
	/**
	 * 期限(天)
	 */
	private long term;

	/**
	 * 取得 回购利率
	 * @return
	 */
	public BigDecimal getRate() {
		return rate;
	}

	/**
	 * 设置 回购利率
	 * @param rate
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	/**
	 * 取得 首期结算日
	 * @return
	 */
	public Date getvSettleDate() {
		return vSettleDate;
	}

	/**
	 * 设置 首期结算日
	 * @param vSettleDate
	 */
	public void setvSettleDate(Date vSettleDate) {
		this.vSettleDate = vSettleDate;
	}

	/**
	 * 取得 到期结算日
	 * @return
	 */
	public Date getmSettleDate() {
		return mSettleDate;
	}

	/**
	 * 设置 到期结算日
	 * @param mSettleDate
	 */
	public void setmSettleDate(Date mSettleDate) {
		this.mSettleDate = mSettleDate;
	}

	/**
	 * 取得 期限(天)
	 * @return
	 */
	public long getTerm() {
		return term;
	}

	/**
	 * 设置 期限(天)
	 * @param term
	 */
	public void setTerm(long term) {
		this.term = term;
	}
	
	//新增 质押式回购字段  end

}
