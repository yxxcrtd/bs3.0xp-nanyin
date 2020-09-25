package com.jixu.mvc.control.model;

import java.util.List;

import com.orm.obj.Voucher;
import com.orm.obj.VoucherDetails;

public class VouchersModel {
	public Voucher voucher;
	
	public List<VoucherDetails> voucherDetailsList;

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public List<VoucherDetails> getVoucherDetailsList() {
		return voucherDetailsList;
	}

	public void setVoucherDetailsList(List<VoucherDetails> voucherDetailsList) {
		this.voucherDetailsList = voucherDetailsList;
	}
}
