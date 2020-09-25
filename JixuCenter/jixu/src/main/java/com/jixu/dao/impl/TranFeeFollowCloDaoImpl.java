package com.jixu.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.types.ExpenseBaseType;
import com.jixu.dao.intf.TranFeeFollowCloDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.jixu.job.obj.FollowFeeBean;
import com.orm.obj.TranFeeFollowClo;

/**
 * @ClassName 		TranFeeFollowCloDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("tranFeeFollowCloDao")
public class TranFeeFollowCloDaoImpl extends CommFuncDAOImpl implements TranFeeFollowCloDao{

	@Override
	public void deleteTranFeeFollowClo(String portnum, Date closeDate) {
		String hql="delete from TranFeeFollowClo where portnum=:portnum and closeDate=:closeDate";
		String[] params={"portnum","closeDate"};
		Object[] values={portnum,closeDate};
		this.deleteHql(hql, params, values);
	}
	
	@Override
	public List<TranFeeFollowClo> loadTranFeeFollowClo(String portID, Date closeDate){
		String hql="select tfc from TranFeeFollowClo tfc where tfc.portnum=:portID and closeDate=:closeDate ";
		String[] params={"portID","closeDate"};
		Object[] values={portID, closeDate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public TranFeeFollowClo getTranFeeFollowClo(String portnum, String cusip, int lotnum, int feeType, Date asOfDate) {
		String hql="from TranFeeFollowClo where portnum=:portnum and cusip=:cusip and lotnum=:lotnum and tranFeeDefID=:feeType and closeDate=:asOfDate";
		String[] params={"portnum","cusip","lotnum","feeType","asOfDate"};
		Object[] values={portnum,cusip,lotnum,feeType,asOfDate};
		return (TranFeeFollowClo)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public List<FollowFeeBean> getFollowFeeListWithCusip(String portnum,String cusip, int lotnum) {
		String sql="select "+
				" feedef.feename as feename, "+
				" feedef.fixedFlag as fixFlag, "+
				" fee.totalFeeamt as feeAmount, "+
				" feedef.cashFlowID as cfcID, "+
				" feedef.feeGlCol as crCol, "+
			    " feedef.rcpyGlCol as drCol, "+
			    " feedef.FEETYPEID as feeCfgID, "+
			    " fee.RecOrPay as recOrPay "+
				" from JXT_tran tr, JXT_TRAN_BASIC_FEE fee, JXT_Tran_Fee_Def feedef "+
				" where tr.portfolio=:portnum and tr.cusip=:cusip and tr.lotnum=:lotnum and tr.trannum=1 "+
				" and tr.tanum=fee.tanum and fee.feeTypeID=feedef.feeTypeID and feedef.oneTimeFeeFlag=:oneTimeFeeFlag";
		//去除TRAN_FEE表，更新过sql语句
		String[] params={"portnum", "cusip", "lotnum", "oneTimeFeeFlag"};
		Object[] values={portnum, cusip, lotnum, ExpenseBaseType.followFee};
		return this.executeSQLReturnBean(sql, params, values, FollowFeeBean.class);
	}
	
	@Override
	public List<TranFeeFollowClo> getFollowFeeList(String portnum, Date closeDate) {
		String hql="from TranFeeFollowClo where portnum=:portnum and closeDate=:closeDate";
		String[] params={"portnum", "closeDate"};
		Object[] values={portnum, closeDate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<TranFeeFollowClo> getTranFeeFollowClos(String portnum, Date closeDate, String cusip, int lotnum) {
		String hql="from TranFeeFollowClo where portnum=:portnum and closeDate=:closeDate and cusip=:cusip and lotnum=:lotnum";
		String[] params={"portnum", "closeDate", "cusip", "lotnum"};
		Object[] values={portnum, closeDate, cusip, lotnum};
		return this.executeHQL(hql, params, values);
	}
}
