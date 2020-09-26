package com.joyintech.tams.report.monthreport.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.report.common.base.TableRow;

public class MonthReport001Dto extends TableRow{
	
	//产品id
	private String prodid;
	//产品名称
	private String prodname;
	//报表日期
	private Date reportdate;
	//A列
	private BigDecimal amta;
	//B列
	private BigDecimal amtb;
	//C列
	private BigDecimal amtc;
	//D列
	private BigDecimal amtd;
	//E
	private BigDecimal amte;
	//F
	private BigDecimal amtf;
	//G
	private BigDecimal amtg;
	//H
	private BigDecimal amth;
	//I
	private BigDecimal amti;
	//J
	private BigDecimal amtj;
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public Date getReportdate() {
		return reportdate;
	}
	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
	}
	public BigDecimal getAmta() {
		return amta;
	}
	public void setAmta(BigDecimal amta) {
		this.amta = amta;
	}
	public BigDecimal getAmtb() {
		return amtb;
	}
	public void setAmtb(BigDecimal amtb) {
		this.amtb = amtb;
	}
	public BigDecimal getAmtc() {
		return amtc;
	}
	public void setAmtc(BigDecimal amtc) {
		this.amtc = amtc;
	}
	public BigDecimal getAmtd() {
		return amtd;
	}
	public void setAmtd(BigDecimal amtd) {
		this.amtd = amtd;
	}
	public BigDecimal getAmte() {
		return amte;
	}
	public void setAmte(BigDecimal amte) {
		this.amte = amte;
	}
	public BigDecimal getAmtf() {
		return amtf;
	}
	public void setAmtf(BigDecimal amtf) {
		this.amtf = amtf;
	}
	public BigDecimal getAmtg() {
		return amtg;
	}
	public void setAmtg(BigDecimal amtg) {
		this.amtg = amtg;
	}
	public BigDecimal getAmth() {
		return amth;
	}
	public void setAmth(BigDecimal amth) {
		this.amth = amth;
	}
	public BigDecimal getAmti() {
		return amti;
	}
	public void setAmti(BigDecimal amti) {
		this.amti = amti;
	}
	public BigDecimal getAmtj() {
		return amtj;
	}
	public void setAmtj(BigDecimal amtj) {
		this.amtj = amtj;
	}
	
	
}
