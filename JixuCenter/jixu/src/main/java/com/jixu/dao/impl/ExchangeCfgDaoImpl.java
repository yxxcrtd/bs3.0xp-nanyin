package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.ExchangeCfgDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.ExchangeCfg;

/**
 * @ClassName 		ExchangeCfgDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("exchangeCfgDao")
public class ExchangeCfgDaoImpl extends CommFuncDAOImpl implements ExchangeCfgDao{

	@Override
	public ExchangeCfg loadExchange(String exchangeCode) {
		return (ExchangeCfg)this.loadEntity(ExchangeCfg.class,exchangeCode);
	}
	
	@Override
	public List<ExchangeCfg> loadExchangeList() {
		String hql="from ExchangeCfg order by name asc";
		List<ExchangeCfg> exchangeList=this.executeHQL(hql);
		return exchangeList;
	}
}
