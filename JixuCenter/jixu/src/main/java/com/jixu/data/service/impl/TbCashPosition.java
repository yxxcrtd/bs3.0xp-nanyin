package com.jixu.data.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.data.pojo.CashPosition;
import com.orm.obj.PortfolioHdr;


/**
 * @author Pan Wei
 * @Date 2017年10月30日 下午2:08:04
 * @Description []
 */

@Component("JixuTbCashPosition")
public class TbCashPosition extends OutDataOperation {
	
	@Autowired
	private BkCashAcctDao bkCashAcctDao;
	
	@Override
	public List<?> genResults(String portNum, Date closeDate) {
		StringBuffer sb=new StringBuffer();
		sb.append("select ");
		sb.append(" bt.portfolio as portnum,");
		sb.append(" :closedate as ledgerDate,");
		sb.append(" bt.BK_ACCT_NO as bkID,");
		sb.append(" bt.EXTERNALACCTNUMBER as bankAcct,");
		sb.append(" bf.BANKDESC as bankAcctName,");
		sb.append(" t1.income as cash,");
		sb.append(" fy.CURR_ID as currID,");
		sb.append(" fy.CURR_CODE as currCode ");
		sb.append(" from JXB_BK_CASH_ACCT bt,JXB_BK_INFO bf,fx_currency fy,");
		sb.append(" (select cf.BK_ACCT_NO,round(sum((case when cf.DBCR='C' then -1*(cf.FXORIGBAL-cf.FXREMAINBAL) else (cf.FXORIGBAL-cf.FXREMAINBAL) end)),2) as income from JXB_BK_CASH_FLOW cf where cf.PORTFOLIO=:portNum and cf.LEDGER<=:closedate group by cf.BK_ACCT_NO) t1");
		sb.append(" where bt.BANKID=bf.BANKID and bt.BK_ACCT_NO=t1.BK_ACCT_NO and bt.CURRENCYCODE=fy.CURR_ID");
		sb.append(" and bt.portfolio=:portNum");
		String[] params={"portNum","closedate"};
		Object[] values={portNum, closeDate};
		List<CashPosition> spList=bkCashAcctDao.executeSQLReturnBean(sb.toString(), params, values, CashPosition.class);
		
		PortfolioHdr portfolio=comService.loadPortfolio(portNum);
		String baseCur=portfolio.getCurID();
		
		for(CashPosition pos:spList){
			String curID=pos.getCurrID();
			double fxrate=comService.loadFxrate(portNum, baseCur, curID, closeDate);
			double marketvalue=RoundUtil.roundMoney2Dec(pos.getCash()*fxrate);
			pos.setFxRate(fxrate);
			pos.setFuncMarketValue(marketvalue);
		}
		return spList;
	}
}
