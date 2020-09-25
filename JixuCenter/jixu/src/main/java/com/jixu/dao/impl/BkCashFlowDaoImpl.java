package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jixu.common.type.CashPositionModel;
import com.jixu.common.util.Pages;
import com.jixu.dao.intf.BkCashFlowDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.BkCashFlow;

/**
 * @ClassName 		BkCashFlowDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("bkCashFlowDao")
public class BkCashFlowDaoImpl extends CommFuncDAOImpl implements BkCashFlowDao{

	@Override
	public List<BkCashFlow> loadCashFlowList(String portnum, Date ledgerDate){
		String hql="from BkCashFlow where portfolio.id=:portnum and ledger>=:ledgerDate";
		String[] params={"portnum","ledgerDate"};
		Object[] values={portnum, ledgerDate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<BkCashFlow> loadCashFlowRecords(Date bankcashdate, String portfolio) {
		String hql="from BkCashFlow where Bankcashdate=:bankcashdate and Portfolio=:portfolio and Zero = 1 order by id asc";
		String[] params={"bankcashdate","portfolio"};
		Object[] values = {bankcashdate,portfolio};
		return this.executeHQL(hql,params,values);
	}
	
	@Override
	public List<BkCashFlow> loadBankCashFlow(int cashacctNO, String portnum, Date bankcashdate) {
		String hql="from BkCashFlow where bkCashAcct.id=:cashacctNO and bkCashAcct.portfolio.id=:portnum and zero=1 and bankcashdate<=:bankcashdate order by Id asc";
		String[] params={"cashacctNO","portnum","bankcashdate"};
		Object[] values={cashacctNO, portnum, bankcashdate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<BkCashFlow> loadCashFlowByCashAcct(Date ledgerDate, int bkcashAcctNo, String portnum) {
		String hql="from BkCashFlow where ledger<=:ledgerDate and bkCashAcct.id=:bkcashAcctNo and portfolio.id=:portnum order by id asc";
		String[] params={"ledgerDate","bkcashAcctNo","portnum"};
		Object[] values={ledgerDate, bkcashAcctNo,portnum};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public Pages<BkCashFlow> getCashFlow(Pages<BkCashFlow> pages, String portNum, Date startDate, Date endDate) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		strBuilder.append("from BkCashFlow where portfolio.id=:portNum ");
		paramsList.add("portNum");
		valuesList.add(portNum);
		
		if(startDate!=null){
			strBuilder.append(" and bankcashdate>=:startDate");
			paramsList.add("startDate");
			valuesList.add(startDate);
		}
		
		if(endDate!=null){
			strBuilder.append(" and bankcashdate<=:endDate");
			paramsList.add("endDate");
			valuesList.add(endDate);
		}
		
		strBuilder.append(" order by id desc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		return this.getPageHqlData(strBuilder.toString(), params, values, false, pages);
	}
	
	@Override
	public Page<BkCashFlow> getPageHelperCashFlow(Page<BkCashFlow> page, String portNum, Date startDate, Date endDate) {
		return page;
	}
	
	@Override
	public List<BkCashFlow> loadCashFlowListByCashAcct(String portnum, int cashacctID) {
		String hql="from BkCashFlow where portfolio.id=:portnum and bkCashAcct.id=:cashacctID order by id desc";
		String[] params={"portnum", "cashacctID"};
		Object[] values={portnum, cashacctID};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<CashPositionModel> getCashPosition(String portNum, Date endDate) {
		String sql="select t2.BK_ACCT_NO, t3.BANKCODE, t2.EXTERNALACCTNUMBER, t3.BANKDESC, t4.CURR_CODE, t1.income, t4.CURR_ID " +
			 " from " +
			 " (select cf.BK_ACCT_NO, "+
			 " round(sum((case when cf.DBCR='C' then -1*(cf.FXORIGBAL-cf.FXREMAINBAL) else (cf.FXORIGBAL-cf.FXREMAINBAL) end)),2) as income "+
			 " from JXB_BK_CASH_FLOW cf where cf.PORTFOLIO=:portNum and cf.LEDGER<=:endDate group by cf.BK_ACCT_NO) t1, JXB_BK_CASH_ACCT t2, JXB_BK_INFO t3, JXM_FX_CURRENCY t4 "+
			 " where t1.BK_ACCT_NO=t2.BK_ACCT_NO and t2.bankid=t3.bankid and t2.CURRENCYCODE=t4.CURR_ID";
			String[] params = { "portNum", "endDate"};
			Object[] values = { portNum, endDate};
			List<Object[]> list= this.executeSQL(sql, params, values, null);
			List<CashPositionModel> cashModelList=new ArrayList<CashPositionModel>();
			for (Object[] obj : list) {
				CashPositionModel cp=new CashPositionModel();
				cp.setCashacctID(Integer.valueOf(obj[0].toString()));
				cp.setBank(obj[1].toString());
				cp.setBankAcct(obj[2].toString());
				cp.setBankAcctName(obj[3].toString());
				cp.setLocalCurCode(obj[4].toString());
				cp.setLocalBalance(Double.valueOf(obj[5].toString()));
				cp.setCurCode(obj[6].toString());
				cashModelList.add(cp);
			}
			return cashModelList;
	}
	
	@Override
	public void deleteCashFlowAfterLedgerDate(String portnum, Date ledgerDate) {
		String hql="delete from BkCashFlow where portfolio.id=:portnum and ledger>=:ledgerDate";
		String[] params={"portnum","ledgerDate"};
		Object[] values = {portnum,ledgerDate};
		this.deleteHql(hql, params, values);
	}
}
