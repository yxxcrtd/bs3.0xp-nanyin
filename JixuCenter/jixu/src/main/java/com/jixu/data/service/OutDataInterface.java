package com.jixu.data.service;

import java.sql.Date;
import java.util.List;

/** 
 * @ClassName 		DataServiceInterface
 * @author  		Pan Wei 
 * @Date 			2017年10月30日 下午2:11:42 	
 * @Description		[]  
 */

public interface OutDataInterface{

	public void sendResults(String portNum, Date closeDate);
	
	public List<?> genResults(String portNum, Date closeDate);
}
