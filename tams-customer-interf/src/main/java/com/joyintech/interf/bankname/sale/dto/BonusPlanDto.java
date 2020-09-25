package com.joyintech.interf.bankname.sale.dto;


import java.math.BigDecimal;
import java.util.Date;

public class BonusPlanDto {
	
	
	private String finprodid;
	private String   prodstyle;
	private String   ccy;
	private String bonuscalcflag;
	private Date regdate;
	private Date deldate;
	private Date bonusdate;
	private BigDecimal unitbonusvlu;
	private BigDecimal totalamt;
    private String isnetmormalize;
	public String getFinprodid() {
		return finprodid;
	}
	public void setFinprodid(String finprodid) {
		this.finprodid = finprodid;
	}
	public String getProdstyle() {
		return prodstyle;
	}
	public void setProdstyle(String prodstyle) {
		this.prodstyle = prodstyle;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public String getBonuscalcflag() {
		return bonuscalcflag;
	}
	public void setBonuscalcflag(String bonuscalcflag) {
		this.bonuscalcflag = bonuscalcflag;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getDeldate() {
		return deldate;
	}
	public void setDeldate(Date deldate) {
		this.deldate = deldate;
	}
	public Date getBonusdate() {
		return bonusdate;
	}
	public void setBonusdate(Date bonusdate) {
		this.bonusdate = bonusdate;
	}
	public BigDecimal getUnitbonusvlu() {
		return unitbonusvlu;
	}
	public void setUnitbonusvlu(BigDecimal unitbonusvlu) {
		this.unitbonusvlu = unitbonusvlu;
	}
	public BigDecimal getTotalamt() {
		return totalamt;
	}
	public void setTotalamt(BigDecimal totalamt) {
		this.totalamt = totalamt;
	}
	public String getIsnetmormalize() {
		return isnetmormalize;
	}
	public void setIsnetmormalize(String isnetmormalize) {
		this.isnetmormalize = isnetmormalize;
	}
	
    
}
