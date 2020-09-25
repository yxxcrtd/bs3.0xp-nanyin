package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.BokBookSetDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.BokBookSet;

/**
 * @ClassName 		BkInteRatioDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("bokBookSetDao")
public class BokBookSetDaoImpl extends CommFuncDAOImpl implements BokBookSetDao{
	@Override
	public List<BokBookSet> loadBokBookSetTemplateList() {
		String hql="from BokBookSet where bookset_type='01'";
		return this.executeHQL(hql);
	}
}
