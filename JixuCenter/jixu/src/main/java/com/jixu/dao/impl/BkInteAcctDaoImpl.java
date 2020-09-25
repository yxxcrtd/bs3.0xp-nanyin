package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.BkInteAcctDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.BkInteAcct;

/**
 * @ClassName 		BkInteAcctDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("bkInteAcctDao")
public class BkInteAcctDaoImpl extends CommFuncDAOImpl implements BkInteAcctDao{

	@Override
	public boolean checkInteAcctNameExist(String bankID, String inteAcct) {
		String hql="from BkInteAcct where masterrecname=:inteAcct and bkinfo.bankID=:bankID";
		String[] params={"inteAcct", "bankID"};
		Object[] values={inteAcct, bankID};
		List<BkInteAcct> inteacctList=this.executeHQL(hql, params, values);
		if(inteacctList.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public List<BkInteAcct> loadBankInteAcctByBank(String bankID) {
		String hql="from BkInteAcct where bkinfo.bankID=:bankID";
		String[] params={"bankID"};
		Object[] values={bankID};
		List<BkInteAcct> acctList=this.executeHQL(hql, params, values);
		return acctList;	
	}
	
	@Override
	public BkInteAcct loadBkInteAcct(int bkInteAcctID) {
		return loadEntity(BkInteAcct.class,bkInteAcctID);
	}
	
}
