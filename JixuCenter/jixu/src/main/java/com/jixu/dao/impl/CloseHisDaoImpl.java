package com.jixu.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.CloseHisDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.CloseHis;

/**
 * @ClassName 		CloseHisDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("closeHisDao")
public class CloseHisDaoImpl extends CommFuncDAOImpl implements CloseHisDao{
	
	@Override
	public CloseHis loadCloseHisByCloseDate(String portnum, Date closeDate){
		String hql="from CloseHis where portfolio.id=:portnum and closeDate>=:closeDate order by closeDate desc";
		String[] params={"portnum","closeDate"};
		Object[] values={portnum, closeDate};
		List<CloseHis> closeList=this.executeHQL(hql, params, values);
		if(closeList.size()>0){
			return closeList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public void deleteCloseHisRecords(String portnum, Date closeDate) {
		String hql="delete from CloseHis where portfolio.id=:portnum and closeDate>=:closeDate";
		String[] params={"portnum","closeDate"};
		Object[] values={portnum, closeDate};
		this.deleteHql(hql, params, values);
	}
}
