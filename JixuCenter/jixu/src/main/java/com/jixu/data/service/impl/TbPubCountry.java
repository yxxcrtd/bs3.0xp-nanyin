package com.jixu.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.dao.intf.CountryDao;
import com.jixu.data.pojo.Country;

/**
 * @author Pan Wei
 * @Date 2017年10月30日 下午2:08:04
 * @Description []
 */

@Component("JixuTbPubCountry")
public class TbPubCountry extends OutPublicDataOperation {
	
	@Autowired
	private CountryDao countryDao;
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public Class<?> getReturnClass() {
		return com.orm.obj.Country.class;
	}
	
	@Override
	public List<?> genResults(Object obj){
		com.orm.obj.Country c=(com.orm.obj.Country) obj;
		List<Country> spList=new ArrayList<Country>();
		Country country=new Country();
		country.setCountryCode(c.getCountryCode());
		country.setCountryID(c.getCountryID());
		country.setCountryName(c.getCountryName());
		spList.add(country);
		return spList;
	}
	
	@Override
	protected List<?> genResults() {
		String sql="select countryID as countryID, countryCode as countryCode, countryname as countryName from JXM_country";
		List<Country> countryList = countryDao.executeSQLReturnBean(sql, Country.class);
		return countryList;
	}
}
