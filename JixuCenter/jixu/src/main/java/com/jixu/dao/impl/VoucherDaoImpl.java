package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jixu.common.util.Pages;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.VoucherDao;
import com.jixu.data.pojo.Balance;
import com.jixu.dataInterface.model.VouchersBean;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.mvc.control.model.VouchersModel;
import com.orm.obj.Voucher;
import com.orm.obj.VoucherDetails;

/**
 * @ClassName 		VoucherDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("voucherDao")
public class VoucherDaoImpl extends CommFuncDAOImpl implements VoucherDao{
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Override
	public int generateKey() {
		//用于拼接vouchernum的主键
		return 0;
	}
	
	@Override
	public List<VouchersBean> getVouchersBeans(String portnum, Date ledgerDate) {
		String sql="select " + 
				" v.vouchernum as vouchernum," +
			    " v.LEDGERDATE as ledgerDate," +
				" vd.SUBJECT_NO as acctnum," +
				" coa.ACCNTNAME as acctname," +
				" vd.DESCRIPTION as desciption," +
				" vd.DR_LOCAL as drLocal," +
				" vd.CR_LOCAL as crLocal," +
				" vd.DR_FUNC as drFunc," +
				" vd.CR_FUNC as crFunc," +
				" round(vd.AMOUNT,2) as amount," +
				" v.JOURNALSOURCECODE as journalCode," +
				" po.port_name as portname," +
				" vd.detail_subject_no as secid" +
				" from JXV_voucher v,JXV_voucherdetails vd, JXC_coaa coa, JXS_PORTFOLIO_HDR po" +
				" where v.vouchernum=vd.VOUCH_NUM and v.portfolionum=po.portfolio and v.portfolionum=:portnum and v.ledgerDate=:ledgerDate" + 
				" and vd.chartnum=coa.chartnumber and vd.SUBJECT_NO=coa.accntnum order by v.vouchernum asc";
		String[] params={"portnum", "ledgerDate"};
		Object[] values={portnum, ledgerDate};
		List<VouchersBean> beans=this.executeSQLReturnBean(sql, params, values, VouchersBean.class);
		return beans;
	}
	
	@Override
	public void deleteVouchers(String portnum, Date ledgerDate, int journalType){
		String sql="delete from JXV_voucherDetails where VOUCH_NUM in (select vouchernum from JXV_voucher where ledgerDate>=:ledgerDate and portfolionum=:portnum and journalSourceCode=:journalType)";
		String hql="delete from Voucher where ledgerDate>=:ledgerDate and portfolio=:portnum and journalSourceCode=:journalType";
		String[] params={"portnum","ledgerDate","journalType"};
		Object[] values={portnum, ledgerDate, journalType};
		this.executeSQLdelete(sql, params, values);
		this.deleteHql(hql, params, values);
	}
	
	public void deleteAllVouchers(String[] portnums, Date ledgerDate) {
		StringBuilder strBuilder=new StringBuilder();
		strBuilder.append("delete from JXV_voucherDetails where VOUCH_NUM in (select vouchernum from JXV_voucher where ledgerDate>=:ledgerDate and (");
		
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		for(int i=0;i<portnums.length;i++){
			strBuilder.append("portfolionum=:portnum"+i);
			paramsList.add("portnum"+i);
			valuesList.add(portnums[i]);
			if(i<portnums.length-1){
				strBuilder.append(" or ");
			}
		}
		strBuilder.append("))");	
		paramsList.add("ledgerDate");
		valuesList.add(ledgerDate);
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		this.executeSQLdelete(strBuilder.toString(), params, values);
		
		StringBuilder strBuilder2=new StringBuilder();
		strBuilder2.append("delete from Voucher where ledgerDate>=:ledgerDate and (");
		for(int i=0;i<portnums.length;i++){
			strBuilder2.append("portfolionum=:portnum"+i);
			if(i<portnums.length-1){
				strBuilder2.append(" or ");
			}
		}
		strBuilder2.append(")");
		
		this.deleteHql(strBuilder2.toString(), params, values);
		
	}
	
	@Override
	public boolean checkJournalRunable(String portNum, Date journalDate, int journalType){
		String[] params = { "portNum", "journalDate", "journalType"};
		Object[] values = { portNum, journalDate, journalType};
		String query = "select count(v.id) from Voucher v where v.portfolio=:portNum and v.ledgerDate=:journalDate and v.journalSourceCode=:journalType";
		List secList = (List) this.executeHQL(query, params, values);
		int countNum=Integer.valueOf(secList.get(0).toString());
		if(countNum<=0)
			return false;
		else 
			return true;
	}
	
	@Override
	public void saveVouchers(List<GLCOOABase> pursaleJnlList) {
		// TODO Auto-generated method stub
		for(GLCOOABase coaBase:pursaleJnlList){
			Voucher voucher=coaBase.getVoucher();
			this.saveEntity(voucher);
			
			List<VoucherDetails> voucherDetailsList=coaBase.getVoucherDetails();
			
			for(VoucherDetails vd:voucherDetailsList){
				vd.setVoucher(voucher);
				this.saveEntity(vd);
			}
		}
	}
	
	@Override
	public String getVoucherNumByTaNumAndType(String tanum, int journalType) {
		String hql="select vouchernum from Voucher where taNum=:tanum and journalSourceCode=:journalType";
		String[] params={"tanum", "journalType"};
		Object[] values={tanum, journalType};
		List<String> resultList=this.executeHQL(hql, params, values);
		if(resultList.size()>0){
			return String.valueOf(resultList.get(0));
		}else{
			return "-";
		}
	}
	
	@Override
	public Pages<Voucher> searchVouchers(Pages<VouchersModel> pages,List<String> portList, Date startDate,
			Date endDate, int journalType, String startVoucherNum, String endVoucherNum,
			String secID, String curCode, String acctnum) {
		//配合mybatis将endDate放到了外面
		//endDate=DateUtil.getNextDay(endDate);
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
	
		StringBuilder hql=new StringBuilder();
		
		if(!secID.equals("") || !acctnum.equals("")){
			hql.append("select distinct v from Voucher v, VoucherDetails vd where v.vouchernum=vd.voucher.vouchernum");
			hql.append(" and (");
			//子投资组合
			for(int i=0;i<portList.size();i++){
				hql.append("v.portfolio=:portNum"+i);
				paramsList.add("portNum"+i);
				valuesList.add(portList.get(i));
				if(i<portList.size()-1){
					hql.append(" or ");
				}
			}
			hql.append(" )");		
			hql.append( "and v.ledgerDate>=:startDate and v.ledgerDate<:endDate");
		}else{
			hql.append("from Voucher v where ");
			hql.append("(");
			for(int i=0;i<portList.size();i++){
				hql.append("portfolio=:portNum"+i);
				paramsList.add("portNum"+i);
				valuesList.add(portList.get(i));
				if(i<portList.size()-1){
					hql.append(" or ");
				}
			}
			hql.append(" )");
			hql.append(" and v.ledgerDate>=:startDate and v.ledgerDate<:endDate");
		}
		
		if(!startVoucherNum.equals("") && !endVoucherNum.equals("")){
			hql.append(" and v.vouchernum>=");
			hql.append(startVoucherNum);
			hql.append(" and v.vouchernum<=");
			hql.append(endVoucherNum);
		}else if(!startVoucherNum.equals("") && endVoucherNum.equals("")){
			hql.append(" and v.vouchernum>=");
			hql.append(startVoucherNum);
		}else if(startVoucherNum.equals("") && !endVoucherNum.equals("")){
			hql.append(" and v.vouchernum<=");
			hql.append(endVoucherNum);
		}
		
		if(journalType>0){
			hql.append(" and v.journalSourceCode=");
			hql.append(journalType);
		}
		
		if(!secID.equals("")){
			hql.append(" and vd.securityID='");
			hql.append(secID);
			hql.append("'");
		}
		
		if(!acctnum.equals("")){
			hql.append(" and vd.accountNum='");
			hql.append(acctnum);
			hql.append("'");
		}

		hql.append(" order by v.vouchernum asc");
		paramsList.add("startDate");
		valuesList.add(startDate);
		paramsList.add("endDate");
		valuesList.add(endDate);
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		//List<Voucher> vouchers=this.executeHQL(hql.toString(), params, values);
		return this.getPageHqlData(hql.toString(), params, values, false, pages);
	}
	
	@Override
	public Page<Voucher> searchPageHelperVouchers(Page<Voucher> page,List<String> portList, Date startDate,
			Date endDate, int journalType, String startVoucherNum, String endVoucherNum,
			String secID, String curCode, String acctnum) {
		return page;
	}
	
	
	//根据投资组合和记账日期  查询Voucher和VoucherDetails中的当天凭证
	@Override
	public List<VouchersModel> testCaseDailyVoucher(String[] portnum,Date ledgerDate) {
		// 通过投资组合和日期查询voucher表中当天的凭证
		StringBuilder hql = new StringBuilder();
		List<String> paramsList = new ArrayList<String>();
		List<Object> valuesList = new ArrayList<Object>();
		hql.append("from Voucher where ledgerDate=:ledgerDate");
		paramsList.add("ledgerDate");
		valuesList.add(ledgerDate);
		hql.append(" and (");
		for (int i = 0; i < portnum.length; i++) {
			hql.append("portfolio=:port" + i);
			paramsList.add("port" + i);
			valuesList.add(portnum[i]);
			if (i < portnum.length - 1) {
				hql.append(" or ");
			}
		}
		hql.append(")");
		String[] params = paramsList.toArray(new String[paramsList.size()]);
		Object[] values = valuesList.toArray();
		List<Voucher> list = this.executeHQL(hql.toString(), params, values);

		// 一次性查询和voucher有关的所有VoucherDetails
		StringBuilder hql2 = new StringBuilder();
		hql2.append("select vd from Voucher v, VoucherDetails vd where v.ledgerDate=:ledgerDate ");
		hql2.append(" and (");
		for (int i = 0; i < portnum.length; i++) {
			hql2.append("v.portfolio=:port" + i);
			if (i < portnum.length - 1) {
				hql2.append(" or ");
			}
		}
		hql2.append(")");
		hql2.append(" and v.vouchernum=vd.voucher.vouchernum");
		List<VoucherDetails> vdlist = this.executeHQL(hql2.toString(), params,values);
		HashMap<String, List<VoucherDetails>> vmap = new HashMap<String, List<VoucherDetails>>();
		String t = "";
		List<VoucherDetails> vlist = null;
		// 将VoucherDetails结果存入到HashMap中
		for (VoucherDetails vd : vdlist) {
			if (!t.equals(vd.getVoucher().getVouchernum())) {
				if (vlist != null && vlist.size() > 0) {
					vmap.put(t, vlist);
				}
				vlist = new ArrayList<VoucherDetails>();
				vlist.add(vd);
			} else {
				if (vlist != null){
					vlist.add(vd);
				}
			}
			t = vd.getVoucher().getVouchernum();// 将凭证编号赋给t
		}
		if (vlist != null && vlist.size() > 0)
			vmap.put(t, vlist);

		// 将Voucher和VoucherDetails对应存入到VouchersModel中
		List<VouchersModel> vmlist = new ArrayList<VouchersModel>();
		for (Voucher voucher : list) {
			VouchersModel vm = new VouchersModel();
			vm.setVoucher(voucher);
			vm.setVoucherDetailsList(vmap.get(voucher.getVouchernum()));
			vmlist.add(vm);
		}
		return vmlist;
	}
	
	@Override
	public int getVoucherCountByPortnum(String portnum) {
		String hql="select count(vouchernum) from Voucher where portfolio=:portnum";
		List<Object> resultList=this.executeHQL(hql, "portnum", portnum);
		int count=Integer.valueOf(resultList.get(0).toString());
		return count;
	}
	
	@Override
	public String getVouchernumByTradeNum(String tanum) {
		String hql="from Voucher where taNum=:tanum";
		Voucher voucher=(Voucher)this.executeHQLNo1Record(hql, "tanum", tanum);
		if(voucher!=null){
			return voucher.getVouchernum();
		}else{
			return "";
		}
	}

	@Override
	public List<Balance> genResults(String portnum,List<String> portList, Date closeDate) {
		return null;
	}

	@Override
	public String genBokDetailID(String portnum, String busiID) {
		String sql="select BOK_DETAIL_ID from BOK_DETAIL_ID"
				+ "  where BOOKSET_ID='"+portnum+ "' and BUSI_ID='"+busiID+"'";
		List<String> list=this.executeSQL(sql, null);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
