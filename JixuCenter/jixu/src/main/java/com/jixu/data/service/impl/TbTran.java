package com.jixu.data.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jixu.common.types.PostStatusType;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.data.pojo.Tran;
import com.jixu.secTran.SecurityCoreFactory;

/**
 * @author Pan Wei
 * @Date 2017年10月30日 下午2:08:04
 * @Description []
 */

@Component("JixuTbTran")
public class TbTran extends OutDataOperation {
	
	@Autowired
	private TranBasicDao tranBasicDao;
	
	@Override
	public List<?> genResults(String portNum, Date closeDate) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select ");
		sb.append(" t1.*, ");
		sb.append(" tr.PROCEEDS as proceeds, ");
		sb.append(" tr.G_COST as cost, ");
		sb.append(" tr.G_COSTFUNC as costFunc, ");
		sb.append(" (case when ii.securityid is null then '' else ii.securityid end) as securityID, ");
		sb.append(" (case when ii.securityname is null then '' else ii.securityname end) as securityName, ");
		sb.append(" ii.issueType as assetType ");
		sb.append(" from ");
		sb.append(" (select ");
		sb.append(" tc.tanum, ");
		sb.append(" tc.AMOUNT as amount, ");
		sb.append(" tc.AMOUNT2 as amount2, ");
		sb.append(" tc.CASH as cash, ");
		sb.append(" tc.CASH2 as cash2, ");
		sb.append(" tc.FXRATE as fxRate, ");
		sb.append(" tc.FXRATE2 as fxRate2, ");
		sb.append(" tc.LEDGERDATE as ledgerDate, ");
		sb.append(" tc.POST_STATUS as postStatus, ");
		sb.append(" tc.PRICE as price, ");
		sb.append(" tc.PRINCIPAL as principal, ");
		sb.append(" tc.PRINCIPAL2 as principal2, ");
		sb.append(" tc.SETTLE_STATUS as settleStatus, ");
		sb.append(" STR_TO_DATE(tc.settleDate,'%Y%m%d') as settleDate, ");
		sb.append(" (case when altdate is null then STR_TO_DATE('20000101','%Y%m%d') else settleDate2 end) as settleDate2, ");
		sb.append(" STR_TO_DATE(tc.tradeDate,'%Y%m%d') as tradeDate, ");
		sb.append(" tc.TRANTYPE as tranType, ");
		sb.append(" tc.BK_CASHACCT_NO as bk_CashAcct_NO, ");
		sb.append(" tc.BK_CASHACCT_NO2 as bk_CashAcct_NO2, ");
		sb.append(" tc.CUSIP as cusip, ");
		sb.append(" tc.CUSIP2 as cusip2, ");
		sb.append(" tc.CURRENCYCODE as currencyCode, ");
		sb.append(" tc.CURRENCYCODE2 as currencyCode2, ");
		sb.append(" tc.PORTFOLIO as portfolio, ");
		sb.append(" tc.PORTFOLIO2 as portfolio2, ");
		sb.append(" tc.ALTRATE as altRate, ");
		sb.append(" (case when altdate is null then STR_TO_DATE('20000101','%Y%m%d') else altDate end) as altDate, ");
		sb.append(" tc.INTATMAT as intAtMat, ");
		sb.append(" tc.GLGROUP as glGroup, ");
		sb.append(" tc.BROKER as broker, ");
		sb.append(" tc.SETTLECASH as settleCash, ");
		sb.append(" tc.SETTLEFXRATE as settleFxRate, ");
		sb.append(" tc.SETTLECURRENCY as settleCurrency, ");
		sb.append(" tc.INTENT as intent, ");
		sb.append(" tc.TRADEMARKET as tradeMarket, ");
		sb.append(" tc.MANAGERCODE as managerCode, ");
		sb.append(" tc.LOCATION as location, ");
		sb.append(" tc.SECACCTCODE as secAcctCode, ");
		sb.append(" tc.SECACCTCODE2 as secAcctCode2, ");
		sb.append(" tc.GLACCT as glAcct, ");
		sb.append(" tc.TRANDESC as tranDesc, ");
		sb.append(" tc.DIVBASICDATE as divBasicDate, ");
		sb.append(" tc.PRICE2 as price2, ");
		sb.append(" bt.EXTERNALACCTNUMBER as bankAcct, ");
		sb.append(" bf.BANKDESC as bankAcctName, ");
		sb.append(" fy.CURR_ID as currID, ");
		sb.append(" fy.CURR_CODE as currCode, ");
		sb.append(" st.acctCode as acctCode ");
		sb.append(" from tran_basic tc, JXB_BK_CASH_ACCT bt,secacct st,fx_currency fy,JXB_BK_INFO bf ");
		sb.append(" where tc.BK_CASHACCT_NO=bt.BK_ACCT_NO and bt.BANKID=bf.BANKID and tc.CURRENCYCODE=fy.CURR_ID and tc.SECACCTCODE=st.acctID ");
		sb.append(" and tc.PORTFOLIO=:portNum and tc.LEDGERDATE=:closeDate and tc.POST_STATUS=:postStatus) t1  ");
		sb.append(" left join tran tr on t1.tanum=tr.tanum ");
		sb.append(" left join issue_info ii on t1.cusip=ii.cusip");
		String[] params = { "portNum", "closeDate", "postStatus" };
		Object[] values = { portNum, closeDate, PostStatusType.post };

		List<Tran> spList = tranBasicDao.executeSQLReturnBean(sb.toString(), params, values, Tran.class);
		for (Tran tran : spList) {
			String tranTypeName = SecurityCoreFactory.getAllTranTypeHM().get(
					tran.getTranType());
			tran.setTranTypeName(tranTypeName);
		}
		return spList;
	}
}
