package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.NewFundDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.NewFund;

/**
 * @ClassName 		NewFundDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("newFundDao")
public class NewFundDaoImpl extends CommFuncDAOImpl implements NewFundDao{
	
	@Override
	public List<NewFund> loadNewFundList(int clientID, int newFundType) {
		String hql="from NewFund where client=:clientID and newFundType=:newFundType";
		String[] params={"clientID","newFundType"};
		Object[] values={clientID, newFundType};
		return this.executeHQL(hql, params, values);
	}

}
