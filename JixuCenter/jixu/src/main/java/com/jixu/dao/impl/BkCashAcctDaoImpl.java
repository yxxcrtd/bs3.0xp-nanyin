package com.jixu.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jixu.common.util.DateUtil;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.SecAcctDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.BkCashAcct;
import com.orm.obj.SecAcct;

/**
 * @ClassName BkCashAcctDaoImpl
 * @author Pan Wei
 * @Date 2018年10月10日 下午23:29:03
 * @Description []
 */

@Repository("bkCashAcctDao")
public class BkCashAcctDaoImpl extends CommFuncDAOImpl implements BkCashAcctDao {

	@Autowired
	private SecAcctDao secAcctDao;
	
	@Override
	public BkCashAcct loadBankAcct(int acctID) {
		return loadEntity(BkCashAcct.class, acctID);
	}
	
	@Override
	public List<BkCashAcct> loadBankAcctList(String bankID) {
		String hql="from BkCashAcct where bkinfo.bankID=:bankID";
		String[] params={"bankID"};
		Object[] values={bankID};
		List<BkCashAcct> acctList=this.executeHQL(hql, params, values);
		return acctList;
	}
	
	@Override
	public List<BkCashAcct> unMapBankAccountList(String bankID) {
		String hql="from BkCashAcct where bkinfo.bankID=:bankID and secAcctCode is null";
		String[] params={"bankID"};
		Object[] values={bankID};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public BkCashAcct loadBankCashAcct(int acctID) {
		return (BkCashAcct)this.loadEntity(BkCashAcct.class,acctID);
	}
	
	@Override
	public BkCashAcct loadDefaultCashAcctByCur(String curCode) {
		String hql = "from BkCashAcct where fxcurrency.currCode=:curCode";
		List<BkCashAcct> secacctList = this.executeHQL(hql, "curCode", curCode);
		if (secacctList.size() > 0) {
			return secacctList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public BkCashAcct loadBankCashByBkCode(String bankcashCode) {
		String hql = "from BkCashAcct where externalacctnumber=:bankcashCode";
		String[] params = { "bankcashCode" };
		Object[] values = { bankcashCode };
		List<BkCashAcct> bkList = this.executeHQL(hql, params, values);
		if (bkList.size() > 0) {
			return bkList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public BkCashAcct loadBankAcctByBankSeatNumber(String seatNumber) {
		String hql = "from BkCashAcct where seatNumber=:seatNumber";
		String[] params = { "seatNumber" };
		Object[] values = { seatNumber.trim() };
		return (BkCashAcct) this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public List<BkCashAcct> loadBankCashAccts(String portnum) {
		String hql="from BkCashAcct where lastclose>:initDate and portfolio.id=:portnum";
		String[] params={"portnum", "initDate"};
		Object[] values={portnum, DateUtil.getInitDate()};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<BkCashAcct> getBankCashAcctByPortNum(String portnum){
		String hql="from BkCashAcct where portfolio.id=:portnum order by id asc";
		String[] params={"portnum"};
		Object[] values={portnum};
		List<BkCashAcct> accts=this.executeHQL(hql, params, values);
		return accts;
	}
	
	@Override
	public boolean checkBkCashAcctNameExist(String portnum, String bkCashAcct) {
		String hql="from BkCashAcct where (externalacctnumber=:bkCashAcct or bankdesc=:bkCashAcct) and portfolio.id=:portnum";
		String[] params={"bkCashAcct", "portnum"};
		Object[] values={bkCashAcct, portnum};
		List<BkCashAcct> cashacctList=this.executeHQL(hql, params, values);
		if(cashacctList.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String loadCurIDByCashAcctID(int cashid) {
		String hql="select fxcurrency from BkCashAcct where id=:cashid";
		String[] params={"cashid"};
		Object[] values={cashid};
		
		List<Object> cashIDList= this.executeHQL(hql, params, values);
		if(cashIDList.size()>0){
			return cashIDList.get(0).toString();
		}else{
			return "";
		}
	}
	
	@Override
	public BkCashAcct loadBankCash(int bankcashNo) {
		return loadEntity(BkCashAcct.class, bankcashNo);
	}
	
	@Override
	public BkCashAcct loadCashAcctBySecAcct(String secacctid){
		BkCashAcct cashacct=null;
		SecAcct secacct=secAcctDao.loadSecAcct(secacctid);
		if(secacct!=null){
			int cashacctid=secacct.getDefaultCashAcct();
			if(cashacctid!=0){
				cashacct=this.loadBankCash(cashacctid);
			}
		}
		return cashacct;
	}
	
	@Override
	public List<BkCashAcct> loadInteBankCashAcctInfo(String portnum) {
		String hql="from BkCashAcct where portfolio.id=:portnum and intCshflwMasterRec>0";
		String[] params={"portnum"};
		Object[] values = {portnum};
		return this.executeHQL(hql, params, values);
	}
	
	public List<BkCashAcct> loadCashAcctByPortnumCur(String portnum, String cur) {
		String hql="from BkCashAcct where portfolio.id=:portnum and fxcurrency.currCode=:cur";
		String[] params={"portnum","cur"};
		Object[] values={portnum, cur};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<BkCashAcct> loadBkCashAcctByPortnum(String portnum) {
		String hql="from BkCashAcct where secAcctCode.portfolio=:portnum";
		return this.executeHQL(hql, "portnum", portnum);
	}
	
	@Override
	public List<BkCashAcct> loadBkCashAcctBySecAcct(String secAcctID) {
		String hql="from BkCashAcct where secAcctCode=:secAcctID";
		String[] params={"secAcctID"};
		Object[] values={secAcctID};
		return this.executeHQL(hql,params,values);
	}
	
	@Override
	public void unMapCashSecAcct(int cashAcct) {
		String hql="update BkCashAcct set secAcctCode=null where id=?";
		Object[] values={cashAcct};
		executeHQLupdate(hql, values);
	}
	
	@Override
	public BkCashAcct loadCashAcctWithNoSecAcct(int cashacct) {
		String hql="from BkCashAcct where id=:cashacct and (secAcctCode is null or secAcctCode='0')";
		String[] params={"cashacct"};
		Object[] values={cashacct};
		List<BkCashAcct> acctList=this.executeHQL(hql, params, values);
		if(acctList.size()>0)
			return acctList.get(0);
		else
			return null;
	}
	
	@Override
	public BkCashAcct loadBankCashAcctByExAcct(String acctID, String portnum) {
		String hql="from BkCashAcct where (to_char(id)=:acctID or externalacctnumber=:acctID) and portfolio.id=:portnum";
		String[] params={"acctID","portnum"};
		Object[] values={acctID,portnum};
		return (BkCashAcct)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public int countCashAcctsBySecAcct(String secacctid) {
		String hql="select count(*) from BkCashAcct where secAcctCode=:secacctid";
		return this.exeFetchCount(hql, "secacctid", secacctid);
	}
}
