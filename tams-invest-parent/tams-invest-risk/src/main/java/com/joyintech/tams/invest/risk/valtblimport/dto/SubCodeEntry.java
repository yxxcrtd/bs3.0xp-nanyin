package com.joyintech.tams.invest.risk.valtblimport.dto;


public class SubCodeEntry {

	private String src_code;
	private String tgt_code;
	private String tgt_name;
	private String allowSum;
	
	
	public String getAllowSum() {
		return allowSum;
	}
	public void setAllowSum(String allowSum) {
		this.allowSum = allowSum;
	}
	public String getTgt_name() {
		return tgt_name;
	}
	public void setTgt_name(String tgt_name) {
		this.tgt_name = tgt_name;
	}
	public String getSrc_code() {
		return src_code;
	}
	public void setSrc_code(String src_code) {
		this.src_code = src_code;
	}
	public String getTgt_code() {
		return tgt_code;
	}
	public void setTgt_code(String tgt_code) {
		this.tgt_code = tgt_code;
	}
	
}