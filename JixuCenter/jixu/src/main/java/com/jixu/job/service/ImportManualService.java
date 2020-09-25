package com.jixu.job.service;

import java.util.Date;
import java.util.List;

import com.jixu.common.type.ResultContainer;
import com.jixu.journal.obj.GLCOOABase;
import com.orm.obj.IssueInfo;
import com.orm.obj.VoucherDetails;

public interface ImportManualService {
	/**
	 * 导入手工分录
	 * @param portnum
	 * @param voucherDate
	 * @param vdList
	 * @return
	 */
	public int importManualVoucher(String portnum, Date voucherDate, List<VoucherDetails> vdList);

	/**
	 * 
	 * @param portnum	资产代码
	 * @param ledgerDate	凭证日期
	 * @param sec	证券
	 * @param tranType	交易类型
	 * @param vdList	凭证
	 * @return
	 */
	public ResultContainer importManualVoucher(String portnum, Date ledgerDate, IssueInfo sec, int tranType, GLCOOABase cooa);
}
