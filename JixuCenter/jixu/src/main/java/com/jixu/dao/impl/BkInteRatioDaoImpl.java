package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.BkInteRatioDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.BkInteRatio;

/**
 * @ClassName 		BkInteRatioDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("bkInteRatioDao")
public class BkInteRatioDaoImpl extends CommFuncDAOImpl implements BkInteRatioDao{

	@Override
	public List<BkInteRatio> loadBkInteRatio(int bkInteAcctID){
		String hql="from BkInteRatio where bkInteAcct.id=:bkInteAcctID";
		String[] params={"bkInteAcctID"};
		Object[] values={ bkInteAcctID };
		List<BkInteRatio> ratioList=this.executeHQL(hql, params, values);
		return ratioList;
	}
}
