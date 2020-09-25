package com.jixu.dao.impl;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.TranFeeTypeMapDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;

/**
 * @ClassName 		TranFeeTypeMapDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("tranFeeTypeMapDao")
public class TranFeeTypeMapDaoImpl extends CommFuncDAOImpl implements TranFeeTypeMapDao{
	
	@Override
	public void deleteTranFeeDefMapByFeeID(int feeDefID) {
		String hql="delete from TranFeeTypeMap where tranFeeTypeCode.id=:feeDefID";
		String[] params={"feeDefID"};
		Object[] values={feeDefID};
		this.deleteHql(hql, params, values);
	}

}
