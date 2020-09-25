package com.jixu.data.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jixu.common.func.SystemSelect;
import com.jixu.dao.intf.TranCloDao;
import com.jixu.data.pojo.SecPosition;


/**
 * @author Pan Wei
 * @Date 2017年10月30日 下午2:08:04
 * @Description []
 */

@Component("JixuTbSecPosition")
public class TbSecPosition extends OutDataOperation {
	
	@Autowired
	private TranCloDao tranCloDao;
	
	@Override
	public List<?> genResults(String portNum, Date closeDate) {
		List<String> portList=portCfgService.loadPortfolioClassIDRecursion(portNum);
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		List<SecPosition> spList=new ArrayList<SecPosition>();
		
		if(SystemSelect.getSystemDBFrameWork()==1){
			spList=tranCloDao.genResults(portList, closeDate);
		}else{
			StringBuffer sb=new StringBuffer();
			sb.append("select ");
			sb.append(" clo.portfolio as portnum,");
			sb.append(" ph.PORT_NAME as portname,");
			sb.append(" clo.closeDate,");
			sb.append(" clo.CUSIP as secMasterID,");
			sb.append(" ii.SECURITYID as securityid,");
			sb.append(" ii.issueType as issuerType,");
			sb.append(" ii.securityname,");
			sb.append(" isr.issuerName,");
			sb.append(" clo.managerID,");
			sb.append(" clo.secacct as secAcctID,");
			sb.append(" sa.defaultCashAcct as defaultCashAcctID,");
			sb.append(" sa.brokerCode as brokerID,");
			sb.append(" ph.CURRENCYCODE as funcCurID,");
			sb.append(" ii.priCur as localCurID,");
			sb.append(" ii.incomeCur as incomeCurID,");
			sb.append(" clo.amount,");
			sb.append(" clo.gCost as cost,");
			sb.append(" clo.gCostFunc as funcCost,");
			sb.append(" (case when clo.altRate>0 and ii.issueType=108 then clo.amrValue else clo.GMKVALUE end) as mkValue,");
			sb.append(" clo.accrued as accrued,");
			sb.append(" clo.lsindicator as lsindicator,");
			sb.append(" clo.amrValue as amortValue,");
			sb.append(" round((case when clo.altRate>0 and ii.issueType=108 then clo.amrValue else clo.GMKVALUE end)*clo.PRINFXRATE,2) as funMkValue");
			sb.append(" from JXT_tran_clo clo, JXS_portfolio_hdr ph, JXS_issue_info ii, JXS_issuer isr, JXB_secacct sa ");
			sb.append(" where clo.portfolio=ph.portfolio and clo.cusip=ii.cusip and ii.issuer=isr.issuerID  ");
			sb.append(" and clo.secacct=sa.acctID ");
			sb.append(" and ( ");
			//子投资组合
			for(int i=0;i<portList.size();i++){
				sb.append("clo.portfolio =:port"+i);
				paramsList.add("port"+i);
				valuesList.add(portList.get(i));
				if(i<portList.size()-1){
					sb.append(" or ");
				}
				sb.append(") and clo.closedate=:closeDate");
				
				paramsList.add("closeDate");
				valuesList.add(closeDate);
				String[] params=paramsList.toArray(new String[paramsList.size()]);
				Object[] values=valuesList.toArray();
				
				spList=tranCloDao.executeSQLReturnBean(sb.toString(), params, values, SecPosition.class);
			}
		}
		return spList;
		
	}
}
