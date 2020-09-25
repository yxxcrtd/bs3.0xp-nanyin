package com.jixu.mvc.control.model;

import com.orm.obj.Voucher;
import com.orm.obj.VoucherDetails;

public class VoucherDetailsView extends VoucherDetails{

	private static final long serialVersionUID = 3997153136869984075L;
	
	private String acctname;

	public VoucherDetailsView() {
	}
	
	public VoucherDetailsView(String id, Voucher voucher, String itemNum,
			String accountNum, String chartNum, 
			int isAdjusted, String localCurrency, String functionCurrency,
			double fxRate, String securityID, String securityTypeCode,
			double cr_local, double dr_local, double cr_func, double dr_func,
			double price, double amount, double curFxRate,String acctname) {
		setAcctname(acctname);
		setId(id);
		setVoucher(voucher);
		setItemNum(itemNum);
		setAccountNum(accountNum);
		setChartNum(chartNum);
		setIsAdjusted(isAdjusted);
		setLocalCurrency(localCurrency);
		setFunctionCurrency(functionCurrency);
		setFxRate(fxRate);
		setSecurityID(securityID);
		setSecurityTypeCode(securityTypeCode);
		setCr_local(cr_local); 
		setDr_local(dr_local);
		setCr_func(cr_func);
		setDr_func(dr_func);
		setPrice(price);
		setAmount(amount);
		setCurFxRate(curFxRate);
	}

	public String getAcctname() {
		return acctname;
	}

	public void setAcctname(String acctname) {
		this.acctname = acctname;
	}
}
