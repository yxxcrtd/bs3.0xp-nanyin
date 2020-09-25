package com.jixu.job.service;

import java.util.Date;

import com.jixu.common.type.ResultContainer;

/** 
 * @ClassName 		AutoMaturityService
 * @author  		Pan Wei 
 * @Date 			2016年11月7日 下午10:18:34 	
 * @Description		[]  
 */

public interface AutoMaturityService {
	
	public ResultContainer autoMaturity(int userID, String[] portnums, Date ledgerDate, ResultContainer container);
	
	public ResultContainer autoMaturity(int userID, String portnum, Date ledgerDate);
}
