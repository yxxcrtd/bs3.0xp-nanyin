package com.jixu.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.type.PortTranMapType;
import com.jixu.dao.intf.SecAcctDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.jixu.mvc.control.model.SecAccountModel;
import com.orm.obj.SecAcct;

/**
 * @ClassName SecAcctDaoImpl
 * @author Pan Wei
 * @Date 2018年10月10日 下午23:29:03
 * @Description []
 */

@Repository("secAcctDao")
public class SecAcctDaoImpl extends CommFuncDAOImpl implements SecAcctDao {
	// 根据证券账户机构代码获取证券账户
	@Override
	public SecAcct loadSecAcctBySeatNum(String seatNumber) {
		String hql = "from SecAcct where seatNumber=:seatNumber";
		String[] params = { "seatNumber" };
		Object[] values = { seatNumber.trim() };
		return (SecAcct) this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public SecAcct loadSecAcct(String secacctNo) {
		return loadEntity(SecAcct.class, secacctNo);
	}

	public SecAcct loadSecAcctByAcctCode(String secacct) {
		String hql="from SecAcct where acctCode=:secacct";
		List<SecAcct> secacctList=this.executeHQL(hql, "secacct", secacct);
		if(secacctList.size()>0){
			return secacctList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public List<PortTranMapType> getPortTranMapTypeList(String portnum) {
		String sql="select " + 
				" sec.portfolio as portNum, " +
				" sec.brokerCode as brokerID, " +
				" sec.acctID as secacctID, " +
				" cash.currencyCode as curID, " +
				" cash.bk_acct_no as cashacctID " +
				" from JXB_secacct sec, JXB_BK_CASH_ACCT cash " +
				" where sec.portfolio=:portnum and sec.defaultCashAcct=cash.bk_acct_no";
		String[] params={"portnum"};
		Object[] values={portnum};
		return this.executeSQLReturnBean(sql, params, values, PortTranMapType.class);
	}
	
	@Override
	public List<SecAccountModel> loadSecAcctList(String brokerCode) {
		String hql="select new com.jixu.mvc.control.model.SecAccountModel(sc.acctID,sc.acctCode,br.brokerName,sc.acctDesc,"
				+ "ph.shortName,sc.glGrop.groupName,0) "
				+ "from SecAcct sc, PortfolioHdr ph, Broker br where sc.portfolio=ph.id and sc.brokerCode=:brokerCode and sc.brokerCode=br.brokerID";
		String[] params={"brokerCode"};
		Object[] values={brokerCode};
		return this.executeHQL(hql,params,values);
	}
	
	@Override
	public void updateSecacct(SecAcct secacct) {
		updateEntity(secacct);
	}
	
	@Override
	public List<SecAccountModel> loadSecAcctByPortnum(String portNum) {
		//证券账户和现金账户暂时解除关系，返回数量临时设置为0   panwei 20190808
		String hql="select new com.jixu.mvc.control.model.SecAccountModel(sa.acctID,sa.acctCode,br.brokerName,sa.acctDesc,po.shortName,'',0) "
				+ " from SecAcct sa, PortfolioHdr po, Broker br "
				+ " where sa.portfolio=po.id and sa.portfolio=:portNum and sa.brokerCode=br.brokerID";
		String[] params={"portNum"};
		Object[] values={portNum};
		return this.executeHQL(hql,params, values);
	}
	
	@Override
	public boolean checkSecAcctNameExist(String secAcctName, String portnum) {
		String hql="from SecAcct where acctCode=:secAcctName and portfolio=:portnum";
		String[] params={"secAcctName","portnum"};
		Object[] values={secAcctName,portnum};
		List<SecAcct> secAcctList=this.executeHQL(hql, params, values);
		if(secAcctList.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public HashMap<String, String> loadSecAcctByPortnumFromJoyin(String portnum) {
		String sql="select " + 
				"  sa.SEC_ACCT_ID as acctcode," + 
				"  SEC_ACCT_NAME as acctname " + 
				"  from PTL_SEC_ACCT ps, STL_SEC_ACCT_INFO sa " + 
				"  where ps.ACCT_NO=sa.SEC_ACCT_ID and ps.PORTFOLIO_ID=:portnum";
		String[] params={"portnum"};
		Object[] values={portnum};
		List<Object[]> result=this.executeSQL(sql, params, values, null);
		HashMap<String, String> secaccs=new HashMap<String, String>();
		for(Object[] objs:result) {
			secaccs.put(objs[0].toString(), objs[1].toString());
		}
		return secaccs;
	}
	
	@Override
	public List<SecAcct> loadSecAcctsByPortnum(String portnum) {
		String hql="from SecAcct where portfolio=:portnum";
		return this.executeHQL(hql, "portnum", portnum);
	}
	
	@Override
	public int loadDefaultCashAcctNameBySecAcctFromJoyin(String portnum, String secacct) {
		String sql="select jbca.BK_ACCT_NO "
				+ " from STL_SEC_ACCT_INFO stai, JXB_BK_CASH_ACCT jbca "
				+ " where stai.TRD_ACCT_ID=jbca.EXTERNALACCTNUMBER and "
				+ " jbca.PORTFOLIO=:portnum and stai.SEC_ACCT_ID=:secacct";
		String[] params = {"portnum","secacct"};
		Object[] values = {portnum, secacct };
		Object obj=this.executeSQLNo1Record(sql, params, values, null);
		if(obj!=null)
			return Integer.parseInt(obj.toString());
		else
			return 0;
	}
}
