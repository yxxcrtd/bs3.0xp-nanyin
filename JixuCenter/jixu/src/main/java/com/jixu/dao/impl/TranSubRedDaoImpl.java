package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.TranSubRedDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.TranSubRed;

/**
 * @ClassName 		TranSubRedDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("tranSubRedDao")
public class TranSubRedDaoImpl extends CommFuncDAOImpl implements TranSubRedDao{
	
	@Override
	public List<TranSubRed> loadTranSubRedInClass(List<String> portList, Date tradeDate) {
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		StringBuilder hql=new StringBuilder();
		hql.append("from TranSubRed where (");
		//子投资组合
		for(int i=0;i<portList.size();i++){
			hql.append("portfolio.id=:portNum"+i);
			paramsList.add("portNum"+i);
			valuesList.add(portList.get(i));
			if(i<portList.size()-1){
				hql.append(" or ");
			}
		}
		hql.append(" )");		
		hql.append( " and tradeDate=:tradeDate");
		paramsList.add("tradeDate");
		valuesList.add(tradeDate);
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(hql.toString(), params, values);
	}
	
	@Override
	public List<TranSubRed> loadTranSubRed(String portfolio, Date tradedate){
		String hql = "from TranSubRed where portfolio.id=:portfolio and ledgerin=:tradedate order by id asc";
		String[] params={"portfolio","tradedate"};
		Object[] values = {portfolio,tradedate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public void deleteTranSubRedAfterLedgerDate(String portnum, Date ledgerDate) {
		String hql="delete from TranSubRed where portfolio.id=:portnum and ledgerin>=:ledgerDate";
		String[] params={"portnum","ledgerDate"};
		Object[] values={portnum,ledgerDate};
		this.deleteHql(hql, params, values);
	}
}
