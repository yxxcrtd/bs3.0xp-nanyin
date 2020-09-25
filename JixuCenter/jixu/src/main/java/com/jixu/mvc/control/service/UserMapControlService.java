package com.jixu.mvc.control.service;

import java.util.List;

import com.orm.obj.PortfolioClient;
import com.orm.obj.PortfolioHdr;
/** 
 * @ClassName 		UserControlService
 * @author  		Pan Wei 
 * @Date 			2016年11月26日 下午11:58:43 	
 * @Description		[]  
 */

public interface UserMapControlService {
	
	public void createUserClientMap(int userID, List<Integer> clientList);
	
	public List<PortfolioHdr> loadPortfoliosByUserID(int userID);
	
	public void createUserPortMap(int userID, List<String> portList);
	
	public List<PortfolioClient> loadClientListByUserID(int userID);
	
}
