package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.types.JixuDictCode;
import com.jixu.dao.intf.PortfolioClientDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.PortfolioClient;

/**
 * @ClassName 		PortfolioClientDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("portfolioClientDao")
public class PortfolioClientDaoImpl extends CommFuncDAOImpl implements PortfolioClientDao{

	@Override
	public List<PortfolioClient> fetchClients() {
		if(JixuUserUtil.isJoyinTablesFlag()) {
			return defaultClients();
		}else {
			String hql="from PortfolioClient";
			List<PortfolioClient> clients=this.executeHQL(hql);
			return clients;
		}
	}
	
	@Override
	public List<PortfolioClient> getClientsByUserID(int userID) {
		if(JixuUserUtil.isJoyinTablesFlag()) {
			return defaultClients();
		}else {
			String hql="select cl from PortfolioClient cl, UserClientMap uc where cl.clientid=uc.client.clientid and uc.users.userid=:userID";
			String[] params={"userID"};
			Object[] values={userID};
			return this.executeHQL(hql, params, values);
		}
	}
	
	private List<PortfolioClient> defaultClients(){
		List<PortfolioClient> clients=new ArrayList<PortfolioClient>();
		PortfolioClient client=new PortfolioClient();
		client.setClientid(JixuDictCode.DEFAULT_CLIENT_ID);
		client.setShortName("默认客户");
		client.setClientName("默认客户");
		clients.add(client);
		return clients;
	}
}
