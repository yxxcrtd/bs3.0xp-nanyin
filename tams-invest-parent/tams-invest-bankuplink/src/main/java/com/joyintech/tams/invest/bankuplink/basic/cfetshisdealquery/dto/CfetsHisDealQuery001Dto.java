package com.joyintech.tams.invest.bankuplink.basic.cfetshisdealquery.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.invest.bankuplink.bond.dto.BondQuoteInfoDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

public class CfetsHisDealQuery001Dto extends BondQuoteInfoDto implements ProcessEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String operateType;
	
	private String flag;

	private transient List<CfetsHisDealQuery001Dto> selectedItem;

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<CfetsHisDealQuery001Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<CfetsHisDealQuery001Dto> selectedItem) {
		this.selectedItem = selectedItem;
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
	
	//新增 质押式回购字段 end


	@Override
	@PrimaryKey(PrimaryKey.Field.PK_1)
	public String getQuoteId() {
		return super.getQuoteId();
	}
}
