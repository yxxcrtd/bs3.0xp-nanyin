package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.SmfDivDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.SmfDiv;

/**
 * @ClassName 		SmfDivDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("smfDivDao")
public class SmfDivDaoImpl extends CommFuncDAOImpl implements SmfDivDao{

	@Override
	public List<SmfDiv> loadSmfDivListByDate(Date ledgerDate) {
		String hql="from SmfDiv where exDate=:ledgerDate";
		String[] params={"ledgerDate"};
		Object[] values={ledgerDate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<SmfDiv> loadSmfDivList(String cusip, Date startDate, Date endDate, int caType) {
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		if(caType==0) {
			//公司行为传入0，默认查询所有行为
			String hql="from SmfDiv where recordDate>=:startDate and recordDate<=:endDate";
			if(cusip!=null && !cusip.equals("")){
				hql=hql+" and cusip=:cusip";
				paramsList.add("cusip");
				valuesList.add(cusip);
			}
			hql=hql+" order by recordDate desc";
			paramsList.add("startDate");
			paramsList.add("endDate");
			valuesList.add(startDate);
			valuesList.add(endDate);
			
			String[] params=paramsList.toArray(new String[paramsList.size()]);
			Object[] values=valuesList.toArray();
			return this.executeHQL(hql, params, values);
		}else {
			String hql="from SmfDiv where caType=:caType and recordDate>=:startDate and recordDate<=:endDate";
			if(cusip!=null && !cusip.equals("")){
				hql=hql+" and cusip="+cusip;
			}
			hql=hql+" order by recordDate desc";
			String[] params={"startDate","endDate","caType"};
			Object[] values={startDate,endDate,caType};
			return this.executeHQL(hql, params, values);
		}
		
	}

	@Override
	public SmfDiv loadSmfDiv(String cusip, Date ledgerDate,int caType) {
		String hql="from SmfDiv where exDate=:ledgerDate and cusip=:cusip and caType=:caType";
		String[] params={"ledgerDate","cusip","caType"};
		Object[] values={ledgerDate,cusip,caType};
		List<SmfDiv> smfDivList=this.executeHQL(hql, params, values);
		if(smfDivList.size()>0){
			return smfDivList.get(0);
		}else{
			return null;
		}
	}
}
