package com.jixu.job.service;

import java.util.Date;

import com.jixu.common.exception.JNLException;
import com.jixu.common.type.ResultContainer;

/** 
 * @ClassName 		CalNavService
 * @author  		Pan Wei 
 * @Date 			2016年10月9日 下午10:26:10 	
 * @Description		[计算净值，同时生成估值表]  
 */

public interface CalNavService {

	public ResultContainer createNav(int userID, String[] portnums, Date calDate);
	
	public ResultContainer createNav(int userID, String portnum, Date calDate);
	
	public ResultContainer deleteNav(int userID, String[] portnums, Date navDate);
	
	public ResultContainer deleteNav(int userID, String portnum, Date navDate);
	
	public void genValuationReport(String[] portnums, Date navDate);
	
	public void genValuationReport(String portnum, Date navDate);
	
	public void delValuationReport(String[] portnums, Date navDate);
	
	public void calBankZiGuanNav(String portnum, Date asOfDate)throws JNLException;
}
