package com.jixu.msg.dto;

import com.jixu.common.valid.IsDateYYYYMMDDJixu;

/**
 * 执行估值任务的参数
 * @author 桃汰猪
 *
 */
public class JobExeDto {
	
	//投资组合
	private String portnum;
	
	//执行日期
	@IsDateYYYYMMDDJixu(keymsg="跑批日期")
	private String ledgerDate;
	
	private int jobID;
	
	//估增日记账: 151
	//费用日记账: 152
	//申赎日记账: 153
	//现金日记账: 154
	//交易日记账: 155
	//收入日记账: 156
	//结转日记账: 157
	//末尾(增值税): 158

	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	public String getLedgerDate() {
		return ledgerDate;
	}

	public void setLedgerDate(String ledgerDate) {
		this.ledgerDate = ledgerDate;
	}

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
}
