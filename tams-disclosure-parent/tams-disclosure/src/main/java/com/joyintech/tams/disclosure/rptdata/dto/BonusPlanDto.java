/**
 * 
 */
package com.joyintech.tams.disclosure.rptdata.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class BonusPlanDto {
	
	private String bonusPlanId;
	private String prodId;
	private String bonusMode;
	private Date settDate;
	private Date paymentDate;
	private String achievement;     //业绩报酬
	private String excessAchievementProp;  //超额比例
	private String achievementBasis;//业绩基准
	private BigDecimal achievementBasisProp;//业绩比较基准收取比例
	
	public String getBonusPlanId() {
		return bonusPlanId;
	}
	public void setBonusPlanId(String bonusPlanId) {
		this.bonusPlanId = bonusPlanId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getBonusMode() {
		return bonusMode;
	}
	public void setBonusMode(String bonusMode) {
		this.bonusMode = bonusMode;
	}
	public Date getSettDate() {
		return settDate;
	}
	public void setSettDate(Date settDate) {
		this.settDate = settDate;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	public String getExcessAchievementProp() {
		return excessAchievementProp;
	}
	public void setExcessAchievementProp(String excessAchievementProp) {
		this.excessAchievementProp = excessAchievementProp;
	}
	public String getAchievementBasis() {
		return achievementBasis;
	}
	public void setAchievementBasis(String achievementBasis) {
		this.achievementBasis = achievementBasis;
	}
	public BigDecimal getAchievementBasisProp() {
		return achievementBasisProp;
	}
	public void setAchievementBasisProp(BigDecimal achievementBasisProp) {
		this.achievementBasisProp = achievementBasisProp;
	}

	
}
