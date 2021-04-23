package com.joyintech.tams.invest.batch.cfetsmanager.cfetscustomer.dao;


import org.springframework.stereotype.Repository;

@Repository
public interface CfetsCustomerMapper {

	public void deleteCustomerInfo();
	
	public void deleteMstCustomerInfo();
	
	public void copyCustomerInfo();
	
	public void saveOrUpdate();
}
