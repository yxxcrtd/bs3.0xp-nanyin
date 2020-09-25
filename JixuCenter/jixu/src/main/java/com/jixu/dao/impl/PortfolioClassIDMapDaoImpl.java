package com.jixu.dao.impl;


import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.PortfolioClassIDMapDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;

/**
 * @ClassName 		PortfolioClassIDMapDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("portfolioClassIDMapDao")
public class PortfolioClassIDMapDaoImpl extends CommFuncDAOImpl implements PortfolioClassIDMapDao{

	@Override
	public void deletePortfolioClassIDMap(String classID) {
		String hql="delete from PortfolioClassIDMap where classID=:classID";
		String[] params={"classID"};
		Object[] values={classID};
		this.deleteHql(hql, params, values);
	}
}
