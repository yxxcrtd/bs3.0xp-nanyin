package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.BkInteSetHisDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.BkInteSetHis;

/**
 * @ClassName 		BkInteSetHisDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("bkInteSetHisDao")
public class BkInteSetHisDaoImpl extends CommFuncDAOImpl implements BkInteSetHisDao{
	
	@Override
	public BkInteSetHis loadLatestInterestSet(String portnum, int cashacctid){
		String hql="from BkInteSetHis where portfolio.id=:portnum and bankacct.id=:cashacctid order by id desc";
		String[] params={"portnum","cashacctid"};
		Object[] values={portnum, cashacctid};
		List<BkInteSetHis> setHistList=this.executeHQL(hql, params, values);
		if(setHistList.size()>0){
			return setHistList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public void deleteBkInteSetHisByTanum(String tanum) {
		String hql="delete from BkInteSetHis where tanum.id=:tanum";
		this.deleteHql(hql, "tanum", tanum);
	}
}
