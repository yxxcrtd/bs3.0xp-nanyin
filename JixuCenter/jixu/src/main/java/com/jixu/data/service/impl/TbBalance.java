package com.jixu.data.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jixu.common.func.SystemSelect;
import com.jixu.dao.intf.VoucherDao;
import com.jixu.data.pojo.Balance;

/**
 * @ClassName TbBalance
 * @author Pan Wei
 * @Date 2018年1月1日 下午10:15:28
 * @Description []
 */
@Component("JixuTbBalance")
public class TbBalance extends OutDataOperation {
	
	@Autowired
	private VoucherDao voucherDao;
	
	@Override
	public List<?> genResults(String portNum, Date closeDate) {
		List<String> portList=portCfgService.loadPortfolioClassIDRecursion(portNum);
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		List<Balance> balList =new ArrayList<Balance>();
		if(SystemSelect.getSystemDBFrameWork()==1){
			balList=voucherDao.genResults(portNum, portList, closeDate);
		}else{
			StringBuffer sb = new StringBuffer();
			sb.append("select ");
			sb.append(" t2.accntNum, ");
			sb.append(" coa1.accntName, ");
			sb.append(" coa1.acctlevel as accntLevel, ");
			sb.append(" coa1.ACCNTCATEGORY as accntCategory, ");
			sb.append(" coa1.acctisleaf as accntIsLeaf, ");
			sb.append(" po1.PORTFOLIO as portnum, ");
			sb.append(" po1.shortCode as portCode, ");
			sb.append(" po1.PORT_NAME as portName, ");
			sb.append(" t2.drFunc, ");
			sb.append(" t2.crFunc, ");
			sb.append(" t2.drLocal, ");
			sb.append(" t2.crLocal, ");
			sb.append(" t2.localCur ");
			sb.append(" from ");
			sb.append(" (select ");
			sb.append(" coa.accntNum, ");
			sb.append(" round(sum(t1.drFunc),2) as drFunc, ");
			sb.append(" round(sum(t1.crFunc),2) as crFunc, ");
			sb.append(" round(sum(t1.drLocal),2) as drLocal, ");
			sb.append(" round(sum(t1.crLocal),2) as crLocal, ");
			sb.append(" max(t1.localCur) as localCur ");
			sb.append(" from ");
			sb.append(" (select  ");
			sb.append(" vd.SUBJECT_NO as accntNum, ");
			sb.append(" round(sum(vd.dr_func),2) as drFunc, ");
			sb.append(" round(sum(vd.cr_func),2) as crFunc, ");
			sb.append(" round(sum(vd.DR_LOCAL),2) as drLocal, ");
			sb.append(" round(sum(vd.CR_LOCAL),2) as crLocal, ");
			sb.append(" max(vd.localcurrency) as localCur ");
			sb.append(" from JXV_voucher v, JXV_voucherdetails vd ");
			sb.append(" where v.vouchernum=vd.VOUCH_NUM and ( ");
			//子投资组合
			for(int i=0;i<portList.size();i++){
				sb.append("v.portfolionum =:port"+i);
				paramsList.add("port"+i);
				valuesList.add(portList.get(i));
				if(i<portList.size()-1){
					sb.append(" or ");
				}
			}
			sb.append( " )and v.ledgerdate<=:closeDate ");
			sb.append(" group by vd.SUBJECT_NO) t1,JXS_portfolio_hdr po, JXC_Coaa coa ");
			sb.append(" where po.chartnumber=coa.chartnumber and po.portfolio=:portNum and instr(t1.accntNum, coa.accntnum)=1 ");
			sb.append(" group by coa.accntnum) t2, JXS_portfolio_hdr po1, JXC_Coaa coa1 ");
			sb.append(" where po1.portfolio=:portNum and po1.chartnumber=coa1.chartnumber and t2.accntNum=coa1.accntnum order by t2.accntNum asc");

			paramsList.add("closeDate");
			valuesList.add(closeDate);
			paramsList.add("portNum");
			valuesList.add(portNum);
			String[] params=paramsList.toArray(new String[paramsList.size()]);
			Object[] values=valuesList.toArray();

			balList = voucherDao.executeSQLReturnBean(sb.toString(), params, values, Balance.class);
		}
		
		for(Balance bal:balList){
			bal.setAsOfDate(closeDate);
			if(bal.getAccntIsLeaf()==0){
				bal.setLocalCur("");
			}
		}
		return balList;
	}
}
