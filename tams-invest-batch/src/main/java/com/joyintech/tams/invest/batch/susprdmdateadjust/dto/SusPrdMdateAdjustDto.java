package com.joyintech.tams.invest.batch.susprdmdateadjust.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 2019年9月24日
 * 子产品信息
 */
public class SusPrdMdateAdjustDto extends NeedOperateEntity {

	private String finprodId; // 金融产品代码
	private String branch; // 分支序号
	private String mdate; // 到期日
	private String investmentCycle; // 投资周期
	
	public String getFinprodId() {
		return finprodId;
	}
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getInvestmentCycle() {
		return investmentCycle;
	}
	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}
}
