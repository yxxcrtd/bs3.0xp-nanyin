package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.AcctLocalFunc;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.VoucherDetailsDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.jixu.job.obj.AcctDCBalance;
import com.jixu.job.obj.NavAssetModel;
import com.jixu.job.obj.PeriodSetDownType;
import com.jixu.mvc.control.model.VoucherDetailsView;

/**
 * @ClassName 		VoucherDetailsDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("voucherDetailsDao")
public class VoucherDetailsDaoImpl extends CommFuncDAOImpl implements VoucherDetailsDao{
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	/**
	 * 获取某天
	 * @param portnum
	 * @param calDate
	 * @return
	 */
	public List<AcctLocalFunc> getCurDateGlResult(String portnum, Date calDate){
		String hql="select new com.jixu.common.types.AcctLocalFunc(accountNum, sum(cr_func), sum(dr_func), sum(cr_local), sum(dr_local)) " +
				" from VoucherDetails where voucher.ledgerDate=:calDate and voucher.portfolio=:portnum group by accountNum";
		String[] params={"portnum","calDate"};
		Object[] values={portnum,calDate};
		List<AcctLocalFunc> resultList=this.executeHQL(hql, params, values);
		return resultList;
	}
	
	public List<NavAssetModel> getNetAsset(List<String> portList, Date calDate){
		
		List<NavAssetModel> netAssetList=new ArrayList<NavAssetModel>();		
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		StringBuilder sql=new StringBuilder();
		sql.append("select ca.accntcategory, round(sum(vd.dr_func-vd.cr_func),2) as totalFunc from JXV_voucher v, JXV_voucherdetails vd, JXC_coaa ca where (");
		for(int i=0;i<portList.size();i++){
			sql.append("v.portfolionum=:portNum"+i);
			paramsList.add("portNum"+i);
			valuesList.add(portList.get(i));
			if(i<portList.size()-1){
				sql.append(" or ");
			}
		}
		sql.append(" )");		
		sql.append( "and v.ledgerdate<=:calDate and v.vouchernum=vd.VOUCH_NUM and vd.chartNum=ca.chartnumber and vd.SUBJECT_NO=ca.accntnum"
				+ " and ca.acctisleaf=1 group by ca.accntcategory");
		
		paramsList.add("calDate");
		valuesList.add(calDate);
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		List<Object[]> resultList=this.executeSQL(sql.toString(), params, values, null);
		for(Object[] obj:resultList){
			NavAssetModel nm=new NavAssetModel();
			nm.setCategoryType(Integer.valueOf(obj[0].toString()));
			nm.setCategoryAmount(RoundUtil.roundMoney2Dec(Double.valueOf(obj[1].toString())));
			netAssetList.add(nm);
		}
		
		return netAssetList;
	}
	

	@Override
	public List<AcctDCBalance> loadAcctDCBalance(String portnum, Date asOfDate) {
		String sql="select " + 
					" vd.SUBJECT_NO as acctnum, " + 
					" vd.detail_subject_no as secid,  " + 
					" round(sum(vd.DR_LOCAL),2) as drLocalAmt, " +  
					" round(sum(vd.CR_LOCAL),2) as crLocalAmt, " +  
					" round(sum(vd.DR_FUNC),2) as drFuncAmt, " + 
					" round(sum(vd.CR_FUNC),2) as crFuncAmt, " + 
					" sum((case when v.JOURNALSOURCECODE<>:setNum then vd.DR_LOCAL else 0 end)) as drLocalAmtUnSet, " +  
					" sum((case when v.JOURNALSOURCECODE<>:setNum then vd.CR_LOCAL else 0 end)) as crLocalAmtUnSet, " +
					" sum((case when v.JOURNALSOURCECODE<>:setNum then vd.DR_FUNC else 0 end)) as drFuncAmtUnSet,  " +
					" sum((case when v.JOURNALSOURCECODE<>:setNum then vd.CR_FUNC else 0 end)) as crFuncAmtUnSe, " +
					" sum((case when v.ledgerdate=:asOfDate then vd.DR_LOCAL else 0 end)) as drLocalAmtToday, " + 
					" sum((case when v.ledgerdate=:asOfDate then vd.CR_LOCAL else 0 end)) as crLocalAmtToday, " + 
					" sum((case when v.ledgerdate=:asOfDate then vd.DR_FUNC else 0 end)) as drFuncAmtToday, " + 
					" sum((case when v.ledgerdate=:asOfDate then vd.CR_FUNC else 0 end)) as crFuncAmtToday, " +
					" sum((case when v.ledgerdate=:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.DR_LOCAL else 0 end)) as drLocalAmtUnSetToday, " + 
					" sum((case when v.ledgerdate=:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.CR_LOCAL else 0 end)) as crLocalAmtUnSetToday, " + 
					" sum((case when v.ledgerdate=:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.DR_FUNC else 0 end)) as drFuncAmtUnSetToday, " + 
					" sum((case when v.ledgerdate=:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.CR_FUNC else 0 end)) as crFuncAmtUnSetToday, " +
					" sum((case when v.ledgerdate<:asOfDate then vd.DR_LOCAL else 0 end)) as drLocalAmtLastDay, " +
					" sum((case when v.ledgerdate<:asOfDate then vd.CR_LOCAL else 0 end)) as crLocalAmtLastDay, " + 
					" sum((case when v.ledgerdate<:asOfDate then vd.DR_FUNC else 0 end)) as drFuncAmtLastDay, " +
					" sum((case when v.ledgerdate<:asOfDate then vd.CR_FUNC else 0 end)) as crFuncAmtLastDay, " +
					" sum((case when v.ledgerdate<:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.DR_LOCAL else 0 end)) as drLocalAmtUnSetLastDay, " + 
					" sum((case when v.ledgerdate<:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.CR_LOCAL else 0 end)) as crLocalAmtUnSetLastDay, " + 
					" sum((case when v.ledgerdate<:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.DR_FUNC else 0 end)) as drFuncAmtUnSetLastDay, " +
					" sum((case when v.ledgerdate<:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.CR_FUNC else 0 end)) as crFuncAmtUnSetLastDay " +
					" from JXV_Voucher v, JXV_voucherdetails vd " +
					" where v.portfolionum=:portnum and v.ledgerdate<=:asOfDate and v.vouchernum=vd.VOUCH_NUM " + 
					" group by vd.SUBJECT_NO,vd.detail_subject_no";
		String[] params={"portnum","asOfDate","setNum"};
		Object[] values = { portnum, asOfDate, JournalTypes.SETDOWN};
		List<AcctDCBalance> balList=this.executeSQLReturnBean(sql, params, values, AcctDCBalance.class);
		return balList;
	}
	
	
	@Override
	public List<AcctDCBalance> loadAcctDCBalanceTranUnInternalTran(String portnum, Date asOfDate) {
		String sql="select " + 
				" vd.SUBJECT_NO as acctnum, " + 
				" vd.detail_subject_no as secid,  " + 
				" round(sum(vd.DR_LOCAL),2) as drLocalAmt, " +  
				" round(sum(vd.CR_LOCAL),2) as crLocalAmt, " +  
				" round(sum(vd.DR_FUNC),2) as drFuncAmt, " + 
				" round(sum(vd.CR_FUNC),2) as crFuncAmt, " + 
				" sum((case when v.JOURNALSOURCECODE<>:setNum then vd.DR_LOCAL else 0 end)) as drLocalAmtUnSet, " +  
				" sum((case when v.JOURNALSOURCECODE<>:setNum then vd.CR_LOCAL else 0 end)) as crLocalAmtUnSet, " +
				" sum((case when v.JOURNALSOURCECODE<>:setNum then vd.DR_FUNC else 0 end)) as drFuncAmtUnSet,  " +
				" sum((case when v.JOURNALSOURCECODE<>:setNum then vd.CR_FUNC else 0 end)) as crFuncAmtUnSe, " +
				" sum((case when v.ledgerdate=:asOfDate then vd.DR_LOCAL else 0 end)) as drLocalAmtToday, " + 
				" sum((case when v.ledgerdate=:asOfDate then vd.CR_LOCAL else 0 end)) as crLocalAmtToday, " + 
				" sum((case when v.ledgerdate=:asOfDate then vd.DR_FUNC else 0 end)) as drFuncAmtToday, " + 
				" sum((case when v.ledgerdate=:asOfDate then vd.CR_FUNC else 0 end)) as crFuncAmtToday, " +
				" sum((case when v.ledgerdate=:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.DR_LOCAL else 0 end)) as drLocalAmtUnSetToday, " + 
				" sum((case when v.ledgerdate=:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.CR_LOCAL else 0 end)) as crLocalAmtUnSetToday, " + 
				" sum((case when v.ledgerdate=:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.DR_FUNC else 0 end)) as drFuncAmtUnSetToday, " + 
				" sum((case when v.ledgerdate=:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.CR_FUNC else 0 end)) as crFuncAmtUnSetToday, " +
				" sum((case when v.ledgerdate<:asOfDate then vd.DR_LOCAL else 0 end)) as drLocalAmtLastDay, " +
				" sum((case when v.ledgerdate<:asOfDate then vd.CR_LOCAL else 0 end)) as crLocalAmtLastDay, " + 
				" sum((case when v.ledgerdate<:asOfDate then vd.DR_FUNC else 0 end)) as drFuncAmtLastDay, " +
				" sum((case when v.ledgerdate<:asOfDate then vd.CR_FUNC else 0 end)) as crFuncAmtLastDay, " +
				" sum((case when v.ledgerdate<:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.DR_LOCAL else 0 end)) as drLocalAmtUnSetLastDay, " + 
				" sum((case when v.ledgerdate<:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.CR_LOCAL else 0 end)) as crLocalAmtUnSetLastDay, " + 
				" sum((case when v.ledgerdate<:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.DR_FUNC else 0 end)) as drFuncAmtUnSetLastDay, " +
				" sum((case when v.ledgerdate<:asOfDate and v.JOURNALSOURCECODE <> :setNum then vd.CR_FUNC else 0 end)) as crFuncAmtUnSetLastDay " +
				" from JXV_Voucher v, JXV_voucherdetails vd, JXT_TranBasic tb " +
				" where v.portfolionum=:portnum and v.ledgerdate<=:asOfDate and v.vouchernum=vd.VOUCH_NUM " + 
				" and v.journalSourceCode=:tranLedger and v.taNum=tb.id and tb.trantype!=:internalTran " +
				" group by vd.accountnum,vd.detail_subject_no";
		String[] params={"portnum","asOfDate","setNum","tranLedger", "internalTran"};
		Object[] values = { portnum, asOfDate, JournalTypes.SETDOWN, JournalTypes.PURSALEJNL, TransTypes.Portfolio_InternalTran};
		List<AcctDCBalance> balList=this.executeSQLReturnBean(sql, params, values, AcctDCBalance.class);
		return balList;
	}
	
	@Override
	public List<PeriodSetDownType> loadPreSetDownList(String portnum, Date ledgerDate) {
		String sql="select coa.closetoaccntnum,vd.SUBJECT_NO, sum(vd.dr_func-vd.cr_func) as funcv,sum(vd.dr_local-vd.cr_local) as localv " +
					" from JXV_voucher v, JXV_voucherdetails vd, JXC_coaa coa " +
					" where v.vouchernum=vd.VOUCH_NUM and vd.chartnum=coa.chartnumber and v.ledgerdate<=:ledgerDate  " +
					" and v.portfolionum=:portnum and coa.CLOSETOACCNTNUM is not null and coa.CLOSETOACCNTNUM != '' " +
					" and vd.SUBJECT_NO=coa.accntnum " +
					" group by coa.closetoaccntnum, vd.SUBJECT_NO";	
		String[] params={"portnum","ledgerDate"};
		Object[] values={portnum,ledgerDate};
		List<Object[]> resultList=this.executeSQL(sql, params, values, null);
		List<PeriodSetDownType> setdownList=new ArrayList<PeriodSetDownType>();
		if(resultList.size()>0){
			for(Object[] objs:resultList){
				PeriodSetDownType setdown=new PeriodSetDownType();
				setdown.setCloseToacctnum(objs[0].toString());
				setdown.setAcctnum(objs[1].toString());
				setdown.setDr_func(Double.valueOf(objs[2].toString()));
				setdown.setDr_local(Double.valueOf(objs[3].toString()));
				setdownList.add(setdown);
			}
		}
		return setdownList;
	}
	
	@Override
	public void deleteVoucherDetailsByVoucherNum(String vouchernum) {
		String hql="delete from VoucherDetails where voucher.vouchernum=:vouchernum";
		String[] params={"vouchernum"};
		Object[] values={vouchernum};
		this.deleteHql(hql, params, values);
	}
	
	@Override
	public List<VoucherDetailsView> searchVoucherDetails(String voucherNum) {
		String hql="select new com.jixu.mvc.control.model.VoucherDetailsView" +
				"(vd.id,vd.voucher,vd.itemNum,vd.accountNum,vd.chartNum,vd.isAdjusted,"+
				" vd.localCurrency,vd.functionCurrency,vd.fxRate,vd.securityID,vd.securityTypeCode,vd.cr_local," + 
				" vd.dr_local,vd.cr_func,vd.dr_func,vd.price,vd.amount,vd.curFxRate, ca.accntname) "+
				" from VoucherDetails vd, Coaa ca where vd.chartNum=ca.chartnum and vd.accountNum=ca.accntnum and vd.voucher.vouchernum=:voucherNum";
		String[] params={"voucherNum"};
		Object[] values={voucherNum};
		List<VoucherDetailsView> vdList=this.executeHQL(hql, params, values);
		return vdList;
	}
	
	@Override
	public List<VoucherDetailsView> searchVoucherDetailsBokSubject(String voucherNum) {
		String hql="select new com.jixu.mvc.control.model.VoucherDetailsView" +
				"(vd.id,vd.voucher,vd.itemNum,vd.accountNum,vd.chartNum,vd.isAdjusted,"+
				" vd.localCurrency,vd.functionCurrency,vd.fxRate,vd.securityID,vd.securityTypeCode,vd.cr_local," + 
				" vd.dr_local,vd.cr_func,vd.dr_func,vd.price,vd.amount,vd.curFxRate, ca.subject_name) "+
				" from VoucherDetails vd, BokSubject ca where vd.chartNum=ca.bokSubjectPK.bookset_id and vd.accountNum=ca.bokSubjectPK.subject_no and vd.voucher.vouchernum=:voucherNum";
		String[] params={"voucherNum"};
		Object[] values={voucherNum};
		List<VoucherDetailsView> vdList=this.executeHQL(hql, params, values);
		return vdList;
	}
}
