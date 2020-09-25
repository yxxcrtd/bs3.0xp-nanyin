package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.CoaahdrDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.Coaahdr;

/**
 * @ClassName 		CoaahdrDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("coaahdrDao")
public class CoaahdrDaoImpl extends CommFuncDAOImpl implements CoaahdrDao{
	
	@Override
	public List<Coaahdr> loadCoaahdr() {
		String hql="from Coaahdr order by id asc";
		List<Coaahdr> coaahdrList=this.executeHQL(hql);
		return coaahdrList;
	}

}
