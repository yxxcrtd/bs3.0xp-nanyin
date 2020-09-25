package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.FxCurrencyDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.FxCurrency;

/**
 * @ClassName 		FxCurrencyDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("fxCurrencyDao")
public class FxCurrencyDaoImpl extends CommFuncDAOImpl implements FxCurrencyDao{
	@Override
	public List<FxCurrency> loadCurrency() {
		String hql="from FxCurrency";
		return this.executeHQL(hql);
	}
	
	@Override
	public FxCurrency loadFxCurrencyIDByCurCode(String currcode) {
		String hql = "from FxCurrency where currCode =:currcode";
		return (FxCurrency)this.executeHQLNo1Record(hql, "currcode", currcode);
	}
	
	@Override
	public FxCurrency loadFxCurrency(String currencyCode) {
		FxCurrency cur=this.loadEntity(FxCurrency.class,currencyCode);
		cur.getCurrCode();
		return cur;
	}
		
	public List<FxCurrency> loadCurrencyList() {
		String hql="from FxCurrency order by currName asc";
		return this.executeHQL(hql);
	}
	
	@Override
	public void updateCurrency(FxCurrency currency) {
		updateEntity(currency);
	}
	
	@Override
	public void saveCurrency(FxCurrency currency) {
		saveEntity(currency);
	}
	
	@Override
	public List<FxCurrency> getCurrencyList() {
		String hql="from FxCurrency order by id";
		List<FxCurrency> currencyList=this.executeHQL(hql);
		return currencyList;
	}
}
