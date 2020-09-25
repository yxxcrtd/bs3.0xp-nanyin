package com.jixu.job.service;

import java.util.Date;

import com.jixu.common.type.ResultContainer;

public interface AutoJobService {

	/**
	 * 当天执行自动job
	 * @param userID
	 * @param portnum
	 * @param ledgerDate
	 * @return
	 */
	public ResultContainer executeAutoJob(int userID, String portnum, Date ledgerDate);
}
