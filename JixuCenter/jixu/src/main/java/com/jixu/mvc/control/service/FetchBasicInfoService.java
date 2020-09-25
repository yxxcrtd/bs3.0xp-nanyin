package com.jixu.mvc.control.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.orm.obj.PortfolioClient;
import com.orm.obj.PortfolioHdr;

/**
 * 
 * @author Pan Wei
 *
 */
public interface FetchBasicInfoService{
	/**
	 * 获取客户信息
	 * @return
	 */
	public List<PortfolioClient> getClientList();
	
	public List<PortfolioClient> getClientListByUserID(int userid);
	
	/**
	 * 根据客户编号获取客户下的投资组合
	 * @param clientnum	客户编号
	 * @return	投资组合集
	 */
	public List<PortfolioHdr> getPortfoliosByClientNum(int clientnum);
	
	public List<PortfolioHdr> getPortfoliosByClientNum(int clientnum, int userid);
	
	public void createClient(PortfolioClient client);
	
	public PortfolioClient loadClientDetail(int clientID);
	
	public void updateClient(PortfolioClient client);
}
