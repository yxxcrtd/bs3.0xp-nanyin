package com.joyintech.tams.invest.batch.cfetsmanager.cfetscustomerrela.dao;


import org.springframework.stereotype.Repository;

@Repository
public interface CfetsCustomerRelaMapper {

	public void deleteCustomerRela();
	
	public void deleteMstCustomerRela();
	
	public void insertMstCustomerRela();
	
	public void deleteMoreMstCustomerRela();
}
