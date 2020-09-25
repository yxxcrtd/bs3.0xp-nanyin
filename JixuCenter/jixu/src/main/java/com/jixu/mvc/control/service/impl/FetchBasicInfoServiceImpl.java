package com.jixu.mvc.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.dao.intf.PortfolioClientDao;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.mvc.control.service.FetchBasicInfoService;
import com.orm.obj.PortfolioClient;
import com.orm.obj.PortfolioHdr;

@Service("basicinfoService")
public class FetchBasicInfoServiceImpl implements FetchBasicInfoService{
	
	@Autowired
	private PortfolioClientDao portfolioClientDao;
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	public List<PortfolioClient> getClientList() {
		return portfolioClientDao.fetchClients();
	}
	
	public List<PortfolioHdr> getPortfoliosByClientNum(int clientnum) {
		return portfolioHdrDao.getPortfoliosByClientNum(clientnum); 
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createClient(PortfolioClient client) {
		portfolioClientDao.saveEntity(client);
	}
	
	@Override
	public PortfolioClient loadClientDetail(int clientID) {
		PortfolioClient client=(PortfolioClient)portfolioClientDao.loadEntity(PortfolioClient.class, clientID);
		return client;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateClient(PortfolioClient client) {
		portfolioClientDao.updateEntity(client);
	}
	
	public List<PortfolioHdr> getPortfoliosByClientNum(int clientnum, int userid){
		return portfolioHdrDao.getPortfoliosByClientAndUser(clientnum, userid);
	}
	
	@Override
	public List<PortfolioClient> getClientListByUserID(int userid) {
		return portfolioClientDao.getClientsByUserID(userid);
	}
}
