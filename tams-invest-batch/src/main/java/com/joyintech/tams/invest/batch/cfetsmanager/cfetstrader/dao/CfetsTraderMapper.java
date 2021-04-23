package com.joyintech.tams.invest.batch.cfetsmanager.cfetstrader.dao;


import org.springframework.stereotype.Repository;

@Repository
public interface CfetsTraderMapper {

	public void deleteCustomerTrader();
	
	public void deleteMstCustomerTrader();
	
	public void copyCustomerInfo();
}
