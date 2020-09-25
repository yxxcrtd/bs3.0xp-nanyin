package com.jixu.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.Pages;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.TranSumDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.jixu.mvc.control.model.SecPositionModel;
import com.orm.obj.TranSum;
import com.orm.obj.TranSumRecord;

/**
 * @ClassName 		TranSumDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("tranSumDao")
public class TranSumDaoImpl extends CommFuncDAOImpl implements TranSumDao{
	
	/*证券头寸*/
	@Override
	public List<TranSum> loadSecPosition(String portfolio) {
		String hql="from TranSum where portfolio=:portfolio and cusip is not null";
		String[] params={"portfolio"};
		Object[] values = {portfolio};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<TranSum> loadSecPositionWithAssetType(String portfolio, String assetType) {
		String hql="select ts from TranSum ts, IssueInfo ii where ts.portfolio=:portfolio and ts.cusip=ii.id and ii.issueType=:assetType";
		String[] params={"portfolio","assetType"};
		Object[] values = {portfolio,assetType};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public TranSum loadTSum(String portfolio, String cusip, String secacct, int longshort) {
		String hql="from TranSum where portfolio=:portfolio and cusip=:cusip and secacct=:secacct and lsindicator=:longshort";
		String[] params={"portfolio","cusip","secacct","longshort"};
		Object[] values={portfolio,cusip,secacct,longshort};
		return (TranSum)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public List<TranSum> loadTSums(String portfolio, String cusip, String secacct, int longshort) {
		String hql="from TranSum where portfolio=:portfolio and cusip=:cusip and secacct=:secacct and lsindicator=:longshort order by lotnum asc";
		String[] params={"portfolio","cusip","secacct","longshort"};
		Object[] values={portfolio,cusip,secacct,longshort};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<TranSum> loadTranSumByPortCusipForSale(String portnum, String cusip, int longshort) {
		String hql = "from TranSum where portfolio=:portnum and cusip=:cusip and lsindicator=:longshort and Posclosed = 'O' order by Lotnum";
		String[] params={"portnum","cusip","longshort"};
		Object[] values={portnum,cusip,longshort};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public TranSum loadTranSumByPortCusip(@Param("portfolio")String portnum, @Param("cusip") String cusip, int longshort){
		String hql = "from TranSum where portfolio=:portnum and cusip=:cusip and lsindicator=:longshort and Posclosed = 'O' order by Lotnum";
		String[] params={"portnum","cusip","longshort"};
		Object[] values={portnum,cusip,longshort};
		return (TranSum)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public int findMaxLotNumByCusip(String portfolio, String cusip) {
		String query = "select max(lotnum) from TranSum where portfolio=:portfolio and cusip=:cusip";
		String[] params={"portfolio","cusip"};
		Object[] values={portfolio, cusip};
		List<Object> lotList = this.executeHQL(query, params, values);
		if (lotList.get(0) == null)
			return 0;
		else
			return Integer.valueOf(lotList.get(0).toString());
	}
	
	@Override
	public int findMaxLotNumByFxTranFlag(String portnum, int fxTranFlag) {
		String hql = "select max(lotnum) from TranSum where portfolio=:portnum and fxTranFlg=:fxTranFlag";
		String[] params={"portnum","fxTranFlag"};
		Object[] values={portnum, fxTranFlag};
		List<Object> lotList = this.executeHQL(hql, params, values);
		if (lotList.get(0) == null)
			return 0;
		else
			return Integer.valueOf(lotList.get(0).toString());
	}
	
	@Override
	public TranSum loadFxTranTSum(String portnum, double amount, double amount2, Date lsttradeDate, Date lstSettledate) {
		String hql = "from TranSum where portfolio=:portnum and amount=:amount and amount2=:amount2 and lsttradeDate=:lsttradeDate and lstSettledate=:lstSettledate";
		String[] params={"portnum", "amount", "amount2", "lsttradeDate", "lstSettledate"};
		Object[] values={portnum, amount, amount2, lsttradeDate, lstSettledate};
		List<TranSum> lotList = this.executeHQL(hql, params, values);
		if (lotList.size()>0)
			return lotList.get(0);
		else
			return null;
	}
	
	@Override
	public double getTotalTsumAmountByCusip(String portfolio, String cusip) {
		String query = "select sum(Amount) from TranSum where Portfolio = " + portfolio + " and Cusip =" + cusip;
		List<Object> amountList = this.executeHQL(query);
		if (amountList.get(0) == null)
			return 0;
		else
			return RoundUtil.roundMoney2Dec(Double.valueOf(amountList.get(0).toString()));
	}
	
	@Override
	public TranSum getMapedRepoTranSum(String portnum, String cusip, int lotnum){
		String hql="from TranSum where portfolio=:portnum and cusip=:cusip and lotnum=:lotnum and posclosed='O'";
		String[] params={"portnum","cusip","lotnum"};
		Object[] values={portnum,cusip,lotnum};
		List<TranSum> tranSumList=this.executeHQL(hql, params, values);
		if(tranSumList.size()>0)
			return tranSumList.get(0);
		else
			return null;
	}
	
	@Override
	public TranSum loadTSumByLot(String portnum, String cusip, int lotnum){
		String hql="from TranSum where portfolio=:portnum and cusip=:cusip and lotnum=:lotnum";
		String[] params={"portnum", "cusip", "lotnum"};
		Object[] values={portnum, cusip, lotnum}; 
		return (TranSum)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public TranSum loadTSumByCusipLot(String portfolio, String cusip, int lotnum) {
		String hql = "from TranSum where portfolio=:portfolio and cusip=:cusip and lotnum=:lotnum";
		String[] params={"portfolio","cusip","lotnum"};
		Object[] values={portfolio,cusip,lotnum};
		List<TranSum> tsumList=this.executeHQL(hql, params, values);
		if (tsumList.isEmpty())
			return null;
		else
			return (TranSum) tsumList.get(0);
	}
	
	@Override
	public TranSum loadTranSumByCusip(String portnum, String cusip) {
		String hql="from TranSum where portfolio=:portnum and cusip=:cusip";
		String[] params={"portnum","cusip"};
		Object[] values={portnum, cusip};
		List<TranSum> tsumList=this.executeHQL(hql, params, values);
		if (tsumList.isEmpty())
			return null;
		else
			return (TranSum) tsumList.get(0);
	}
	
	@Override
	public List<TranSum> loadTSumByCusip(String portnum, String cusip) {
		String hql="from TranSum where portfolio=:portnum and cusip=:cusip";
		String[] params={"portnum","cusip"};
		Object[] values={portnum, cusip};
		return this.executeHQL(hql, params, values);
	}

	public List<TranSum> loadTranSum(String portnum, String secid) {
		//String hql="select ts from TranSum ts, IssueInfo ii where ts.portfolio=:portnum and ts.cusip=ii.id and ii.securityid=:secid and ts.amount>0";
		String hql="select ts from TranSum ts, IssueInfo ii where ts.portfolio=:portnum and ts.cusip=ii.id and ii.securityid=:secid";
		String[] params={"portnum","secid"};
		Object[] values={portnum,secid};
		return this.executeHQL(hql, params, values);
	}
	
	/**
	 * 获取该投资组合投资的股票
	 */
	@Override
	public Pages<String> loadTranedSecCusip(Pages<SecPositionModel> pages,String portfolio) {
		String sql="select distinct cusip from JXT_TRAN_SUM where portfolio=:portfolio";
		String[] params={"portfolio"};
		Object[] values = {portfolio};
		return this.getPageSqlData(sql.toString(), params, values, false, pages, null);
	}
	
	/**
	 * pageHelper分页
	 */
	@Override
	public Page<String> loadPageHelperTranedSecCusip(Page<SecPositionModel> page,String portfolio) {
		return null;
	}
	
	@Override
	public void deleteAllTSumDataByPortnum(String portnum) {
		String hql="delete from TranSum where portfolio=:portnum";
		String[] params={"portnum"};
		Object[] values={portnum};
		this.deleteHql(hql, params, values);
	}
	
	@Override
	public List<TranSumRecord> loadTranSumRecordByPortDate(String portnum, Date ledgerDate) {
		String hql="from TranSumRecord where portfolio=:portnum and ledgerDate=:ledgerDate";
		String[] params={"portnum", "ledgerDate"};
		Object[] values={portnum, ledgerDate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<TranSum> loadOpenedTranSum(String portnum) {
		String hql="from TranSum where portfolio=:portnum and posclosed=:posclosed";
		String[] params={"portnum", "posclosed"};
		Object[] values={portnum, PostStatusType.sumOpen};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public void deleteTranSumRecord(String portnum, Date ledgerDate) {
		String hql="delete from TranSumRecord where portfolio=:portnum and ledgerDate>=:ledgerDate";
		String[] params={"portnum", "ledgerDate"};
		Object[] values={portnum, ledgerDate};
		this.deleteHql(hql, params, values);
	}
}
