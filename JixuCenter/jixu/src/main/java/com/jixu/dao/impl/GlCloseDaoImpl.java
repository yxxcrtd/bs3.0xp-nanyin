package com.jixu.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.GlCloseDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.GlClose;

/**
 * @ClassName 		GlCloseDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("glCloseDao")
public class GlCloseDaoImpl extends CommFuncDAOImpl implements GlCloseDao{

	public List<GlClose> getClosedGL(String portnum, Date glDate){
		String hql="from GlClose where portfolio.id=:portnum and closeDate=:glDate";
		String[] params={"portnum","glDate"};
		Object[] values={portnum,glDate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public double getCloseBalance(String portnum, Date glDate, String acctnum) {
		String hql="from GlClose where portfolio.id=:portnum and closeDate=:glDate and acctnum=:acctnum";
		String[] params={"portnum","glDate", "acctnum"};
		Object[] values={portnum,glDate,acctnum};
		GlClose close=(GlClose)this.executeHQLNo1Record(hql, params, values);
		if(close==null)
			return 0;
		else
			return RoundUtil.roundMoney2Dec(close.getDr_local()-close.getCr_local());
	}
	
	@Override
	public void deleteGlGlose(String portnum, Date closeDate){
		String hql="delete from GlClose where portfolio.id=:portnum and closeDate=:closeDate";
		String[] params={"portnum","closeDate"};
		Object[] values={portnum,closeDate};
		this.deleteHql(hql, params, values);
	}
}
