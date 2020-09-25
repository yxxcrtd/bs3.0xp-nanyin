package com.jixu.dao.impl;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.SecContractDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.SecContract;

/**
 * @ClassName 		SecContractDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("secContractDao")
public class SecContractDaoImpl extends CommFuncDAOImpl implements SecContractDao{

	@Override
	public SecContract loadSecContract(String cusipID) {
		String hql="from SecContract where cusip=:cusipID";
		String[] params={"cusipID"};
		Object[] values={cusipID};
		return (SecContract)this.executeHQLNo1Record(hql, params, values);
	}
}
