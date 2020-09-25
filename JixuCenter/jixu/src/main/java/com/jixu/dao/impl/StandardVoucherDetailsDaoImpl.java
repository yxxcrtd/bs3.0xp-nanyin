package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.StandardVoucherDetailsDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.StandardVoucherDetails;

/**
 * @ClassName 		StandardVoucherDetailsDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("standardVoucherDetailsDao")
public class StandardVoucherDetailsDaoImpl extends CommFuncDAOImpl implements StandardVoucherDetailsDao{
	
    ////查询标准凭证  并将查询结果存入到HashMap中
	@Override
	public List<StandardVoucherDetails> testCaseDailyVoucherDetails(String[] portList,Date ledgerDate) {
		StringBuilder hql=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		hql.append("select svd from StandardVoucherDetails svd,StandardVoucher sv where svd.voucher.vouchernum=sv.vouchernum "
				+ "and sv.ledgerDate=:ledgerDate");
		hql.append(" and (");
		for(int i=0;i<portList.length;i++){
			hql.append("sv.portfolio=:port"+i);
			paramsList.add("port"+i);
			valuesList.add(portList[i]);
			if(i<portList.length-1){
				hql.append(" or ");
			}
		}
		hql.append(")");
		paramsList.add("ledgerDate");
		valuesList.add(ledgerDate);
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(hql.toString(), params, values);
	}
}
