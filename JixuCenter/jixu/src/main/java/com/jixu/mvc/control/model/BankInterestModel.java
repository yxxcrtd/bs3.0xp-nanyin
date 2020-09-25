package com.jixu.mvc.control.model;

public class BankInterestModel {
	private int id;
	private String code;
	private String name;
	private String bankid;
	//结算推迟天数
	private int pmtDelay;
	//利息结算方法
	private int cpnDateConv;
	//天数计算方法
	private int intCode;
	//期间方法
	private int intperiodconv;
	//付息频率
	private int cpnFreq;
	//付息月份
	private int cpnMo;
	//付息日
	private int cpnDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public int getPmtDelay() {
		return pmtDelay;
	}
	public void setPmtDelay(int pmtDelay) {
		this.pmtDelay = pmtDelay;
	}
	public int getCpnDateConv() {
		return cpnDateConv;
	}
	public void setCpnDateConv(int cpnDateConv) {
		this.cpnDateConv = cpnDateConv;
	}
	public int getIntCode() {
		return intCode;
	}
	public void setIntCode(int intCode) {
		this.intCode = intCode;
	}
	public int getIntperiodconv() {
		return intperiodconv;
	}
	public void setIntperiodconv(int intperiodconv) {
		this.intperiodconv = intperiodconv;
	}
	public int getCpnFreq() {
		return cpnFreq;
	}
	public void setCpnFreq(int cpnFreq) {
		this.cpnFreq = cpnFreq;
	}
	public int getCpnMo() {
		return cpnMo;
	}
	public void setCpnMo(int cpnMo) {
		this.cpnMo = cpnMo;
	}
	public int getCpnDate() {
		return cpnDate;
	}
	public void setCpnDate(int cpnDate) {
		this.cpnDate = cpnDate;
	}
}
