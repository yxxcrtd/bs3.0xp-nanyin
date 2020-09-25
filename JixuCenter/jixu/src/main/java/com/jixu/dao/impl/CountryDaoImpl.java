package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.types.JixuDictCode;
import com.jixu.dao.intf.CountryDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.Country;

/**
 * @ClassName 		CountryDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("countryDao")
public class CountryDaoImpl extends CommFuncDAOImpl implements CountryDao{

	@Override
	public Country loadCountry(String countryID) {
		return (Country)this.loadEntity(Country.class, countryID);
	}
	
	@Override
	public List<Country> getCountryist() {
		if(JixuUserUtil.isJoyinTablesFlag()) {
			return defaultCountrys();
		}else {
			String hql="from Country order by countryID asc";
			List<Country> countryList=this.executeHQL(hql);
			return countryList;
		}
	}
	
	@Override
	public void updateCountry(Country country) {
		updateEntity(country);
	}
	
	@Override
	public void saveCountry(Country country) {
		saveEntity(country);
	}
	
	private List<Country> defaultCountrys(){
		List<Country> countrys=new ArrayList<Country>();
		Country country=new Country();
		country.setCountryID("CN");
		country.setCountryCode("CN");
		country.setCountryName("中国");
		countrys.add(country);
		return countrys;
	}
}
