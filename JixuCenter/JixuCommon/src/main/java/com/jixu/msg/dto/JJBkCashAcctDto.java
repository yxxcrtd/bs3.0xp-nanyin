package com.jixu.msg.dto;

public class JJBkCashAcctDto {
	//银行账户代码
	private String acct_no;
	
	//银行账户名
	private String acct_name;
	
	//账套名称
	private String bookset_id;
	
	//银行信息
	private String bank_dep;
	
	//货币
	private String ccy;
	
	//银行账户类型
	private String acc_type;

	public String getAcct_no() {
		return acct_no;
	}

	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}

	public String getAcct_name() {
		return acct_name;
	}

	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}

	public String getBookset_id() {
		return bookset_id;
	}

	public void setBookset_id(String bookset_id) {
		this.bookset_id = bookset_id;
	}

	public String getBank_dep() {
		return bank_dep;
	}

	public void setBank_dep(String bank_dep) {
		this.bank_dep = bank_dep;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getAcc_type() {
		return acc_type;
	}

	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}
}
