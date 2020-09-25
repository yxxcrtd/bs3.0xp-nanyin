package com.jixu.mvc.control.service;

import java.util.Date;
import java.util.List;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.Pages;
import com.jixu.mvc.control.model.VoucherDetailsView;
import com.jixu.mvc.control.model.VouchersModel;

public interface VoucherService {
	public Pages<VouchersModel> fetchVouchers(Pages<VouchersModel> pages, String portnum, Date startDate, Date endDate, int journalType, String startVoucherNum, String endVoucherNum, String secID, String curCode, String accountnum);

	public List<VoucherDetailsView> fetchVoucherDetails(String voucherNum);
	
	public ResultContainer createManualVoucher(int userID,String portnum, String ledgerDate, List<Object> fields);
	
	public ResultContainer testCaseDailyVoucher(String[] portnum,Date ledgerDate);
}
