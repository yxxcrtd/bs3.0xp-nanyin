package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.TranCollateralDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.TranCollateral;

/**
 * @ClassName 		TranCollateralDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("tranCollateralDao")
public class TranCollateralDaoImpl extends CommFuncDAOImpl implements TranCollateralDao{
	
	@Override
	public List<TranCollateral> loadTranCollateralList(String tanum) {
		String hql="from TranCollateral where tanum.id=:tanum";
		String[] params={"tanum"};
		Object[] values={tanum};
		return this.executeHQL(hql, params, values);
	}

}
