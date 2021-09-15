package com.jixu.journal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.DateUtil;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.TranSumDao;
import com.jixu.journal.obj.CashBalanceWrapObj;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecCashWrapObj;
import com.jixu.journal.obj.TransactionObj;
import com.jixu.journal.service.GeneralJNLService;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranSum;

@Service("genjnlServiceML")
public class GeneralJNLServiceMLImpl implements GeneralJNLService {

	private static final Logger logger = LoggerFactory.getLogger(GeneralJNLServiceMLImpl.class);
	
	private CommFuncService commfuncService;
	
	@Autowired
	private TranSumDao tranSumDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	

	public CommFuncService getCommfuncService() {
		return commfuncService;
	}

	public void setCommfuncService(CommFuncService commfuncService) {
		this.commfuncService = commfuncService;
	}

	public List<CashBalanceWrapObj> loadCashBalanceGenJNLRecord(
			PortfolioHdr portoflio, String date) throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<CashBalanceWrapObj> loadCashBalanceGenJNLRecord(Date date)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}

	public double loadCusipPrice(String cusip) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<SecCashWrapObj> loadSecCashJNLRecord(PortfolioHdr portfolio, Date date)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<CashBalanceWrapObj> loadCashBalanceGenJNLRecord(
			PortfolioHdr portoflio, Date ledgerDate, Date lastDate)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<STCostWrapObj> loadSecGenerelJNLRecord(PortfolioHdr portfolio,
			Date date, String assetType) throws JNLException {
		// TODO Auto-generated method stub
		List<STCostWrapObj> genwrapList = new ArrayList<STCostWrapObj>();
		STCostWrapObj stockCostWrap = null;

		CommFuncService commService = getCommfuncService();
		
		Date priorDay = DateUtil.getLastDay(date);
		Date asofDay = date;
		String cusip = "";
		String currId = "";
		int lotnum = 0;
		
		double temp_g_cost = 0.0, temp_amount= 0.0;
		double temp_g_cost_p = 0.0, temp_amount_p = 0.0;
		double fxrate = 0.0;

		List<TransactionObj> tranList = null;
		try {
			List<TranSum> secPositionList=null;
			if(assetType.equals("0")){
				secPositionList = tranSumDao.loadSecPosition(portfolio.getId());
			}else{
				secPositionList = tranSumDao.loadSecPositionWithAssetType(portfolio.getId(), assetType);
			}
			for (TranSum transum : secPositionList) {
				IssueInfo issue=issueInfoDao.loadIssueByCusip(transum.getCusip());
				cusip = issue.getId();
				currId = issue.getPriCur();

				temp_g_cost = 0.0;
				temp_amount= 0.0;
				temp_g_cost_p = 0.0;
				temp_amount_p = 0.0;
				
				lotnum = 0;
				
				//stockCostWrap = new STCostWrapObj();
				stockCostWrap = new STCostWrapObj();
				stockCostWrap.setCusip(cusip);
				stockCostWrap.setGlgroupnum(issue.getCoaaGroup());
				//获取CUSIP的交易记录，ORDER BY LOTNUM, TRANNUM
				tranList = null;//
				//public List<TransactionObj> loadCusipTransMulti(String portfolio, int cusip, Date ledgerDate) {
				//	StringBuffer sql = new StringBuffer();
				//	sql.append("select new com.jixu.journal.general.obj.TransactionObj(gCost,gCostFunc,Amount,Trantype,LedgerDate,Lotnum,Trannum,IncFxrate)" +
				//			" from Transactions where Portfolio=:portfolio and Cusip.Id=:cusip and LedgerDate<=:ledgerDate order by Lotnum,Trannum, Id");
				//	String[] params={"portfolio","cusip","ledgerDate"};
				//	Object[] values={portfolio,cusip, ledgerDate};		
				//	return this.executeHQL(sql.toString(), params, values);
				//}
				
				if (tranList != null){
					for (TransactionObj trandata : tranList) {

						if (lotnum == 0) {
							lotnum = trandata.getLotnum();
						} else if (lotnum == trandata.getLotnum()) {
							//Nothing need to do
						} else {
							lotnum = trandata.getLotnum();
							//wrapLotCostObj(new TranSum(), stockCostWrap.getPriordayCost(),
							//		temp_amount_p, temp_g_cost_p, fxrate);

							//wrapLotCostObj(new TranSum(), stockCostWrap.getAsofdayCost(),
							//		temp_amount, temp_g_cost, fxrate);

							temp_g_cost = 0.0;
							temp_amount= 0.0;
							temp_g_cost_p = 0.0;
							temp_amount_p = 0.0;
						}

						if (trandata.getTrantype() == TransTypes.PURCHASE) {
							if (trandata.getLedgerdate().compareTo(date) < 0) {
								temp_g_cost_p += trandata.getGcost();
								temp_amount_p += trandata.getAmount();
							}
							temp_g_cost += trandata.getGcost();
							temp_amount += trandata.getAmount();
							fxrate = trandata.getFxrate();
						} else if (trandata.getTrantype() == TransTypes.SALE) {
							if (trandata.getLedgerdate().compareTo(date) < 0) {
								temp_g_cost_p -= trandata.getGcost();
								temp_amount_p -= trandata.getAmount();
							}
							temp_g_cost -= trandata.getGcost();
							temp_amount -= trandata.getAmount();
						}
					}
				}
				//加载最后一个lot的信息
				if (temp_g_cost > DBCR.ZERO) {
					//wrapLotCostObj(new TranSum(), stockCostWrap.getPriordayCost(), 
					//		temp_amount_p, temp_g_cost_p, fxrate);
					//
					//wrapLotCostObj(new TranSum(), stockCostWrap.getAsofdayCost(), 
					//		temp_amount, temp_g_cost, fxrate);
				}
				
				//获取当天的价格和汇率
				/*stockCostWrap.setAsofdayPrice(commService.loadCusipPrice(portfolio.getId(), cusip,asofDay));
				stockCostWrap.setAsofdayRate(commService.loadFxrate(portfolio.getId(),portfolio.getCurID(),currId,asofDay)); // 

				
				//获取前一天的价格，汇率
				stockCostWrap.setPriordayRate(commService.loadFxrate(portfolio.getId(),portfolio.getCurID(),currId,priorDay));
				stockCostWrap.setPriordayPrice(commService.loadCusipPrice(portfolio.getId(), cusip,priorDay));*/

				genwrapList.add(stockCostWrap);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error", e);
			throw new JNLException(e.toString());
		}

		return genwrapList;
	}
	
	private void wrapLotCostObj(TranSum lotCostobj, List<TranSum> stcostObjList, 
			double amount, double gcost, double fxrate) {
		//改进凭证框架,临时注释 pan wei 2017/01/22
		/*lotCostobj.setAmount(amount);
		lotCostobj.setGcost(gcost);
		lotCostobj.setFxrate(fxrate);
		stcostObjList.add(lotCostobj);*/
	}

}
