package com.jixu.mvc.control.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.pojo.Users;
import com.jixu.common.tool.SystemInitialization;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.UserClientMapDao;
import com.jixu.dao.intf.UserPortfolioMapDao;
import com.jixu.mvc.control.service.UserMapControlService;
import com.orm.obj.PortfolioClient;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.UserClientMap;
import com.orm.obj.UserPortfolioMap;

/** 
 * @ClassName 		UserControlImpl
 * @author  		Pan Wei 
 * @Date 			2016年11月27日 上午12:01:31 	
 * @Description		[]  
 */

@Service("jixuUserMapService")
public class UserMapControlServiceImpl implements UserMapControlService{
	
	@Autowired
	private PortfolioHdrDao PortfolioHdrDao;
	
	@Resource(name="systemInit")
	private SystemInitialization systemInit;
	
	@Autowired
	private UserClientMapDao userClientMapDao;
	
	@Autowired
	private UserPortfolioMapDao userPortfolioMapDao;
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createUserClientMap(int userID, List<Integer> clientList) {
		userClientMapDao.deleteUserClientMap(userID);
		for(int clientid:clientList){
			UserClientMap ucmap=new UserClientMap();
			ucmap.setUsers(new Users(userID));
			ucmap.setClient(new PortfolioClient(clientid));
			userClientMapDao.saveEntity(ucmap);
		}
	}
	
	@Override
	public List<PortfolioHdr> loadPortfoliosByUserID(int userID) {
		/*List<UserClientMap> ucMapList=userDAO.loadUserClientMapListByUserID(userID);
		List<PortfolioHdr> portList=new ArrayList<PortfolioHdr>();
		for(UserClientMap ucmap:ucMapList){
			PortfolioClient pc=ucmap.getClient();
			if(pc!=null){
				Set<PortfolioHdr> portSet=pc.getPortfolio();
				if(portSet!=null){
					Iterator<PortfolioHdr> portIter=portSet.iterator();
					while(portIter.hasNext()){
						portList.add(portIter.next());
					}
				}
			}
		}*/
		
		List<PortfolioHdr> portList=new ArrayList<PortfolioHdr>();
		List<UserPortfolioMap> portMapList=userPortfolioMapDao.loadUserPortMapListByUserID(userID);
		for(UserPortfolioMap userPortMap:portMapList){
			PortfolioHdr portfolio=PortfolioHdrDao.loadPortfolio(userPortMap.getPortfolio());
			portList.add(portfolio);
		}
		
		return portList;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createUserPortMap(int userID, List<String> portList) {
		userPortfolioMapDao.deleteUserPortMap(userID);
		for(String port:portList){
			UserPortfolioMap ucMap=new UserPortfolioMap();
			ucMap.setUsers(new Users(userID));;
			ucMap.setPortfolio(port);
			userPortfolioMapDao.createUserPortMap(ucMap);
		}
	}
	
	
	@Override
	public List<PortfolioClient> loadClientListByUserID(int userID) {
		List<PortfolioClient> clientList=new ArrayList<PortfolioClient>();
		List<UserClientMap> ucmapList=userClientMapDao.loadUserClientMapListByUserID(userID);
		for(UserClientMap clientMap:ucmapList){
			clientList.add(clientMap.getClient());
		}
		
		return clientList;
	}
	
}
