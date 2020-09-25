package com.jixu.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.types.ActiveType;
import com.jixu.common.types.ExpenseBaseType;
import com.jixu.dao.intf.TranFeeDefDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.TranFeeDef;

/**
 * @ClassName 		TranFeeDefDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("tranFeeDefDao")
public class TranFeeDefDaoImpl extends CommFuncDAOImpl implements TranFeeDefDao{
	
	@Override
	public List<TranFeeDef> loadTranFeeDefListByTranType(String assettype) {
		String hql="from TranFeeDef de where de.id in (select tranFeeTypeCode.id from TranFeeTypeMap where assetType=:assettype)";
		String[] params={"assettype"};
		Object[] values={assettype};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<TranFeeDef> loadTranFeeTypeList() {
		String hql="from TranFeeDef order by id asc ";
		return this.executeHQL(hql);
	}

	@Override
	public List<TranFeeDef> subExpenseTypes(String portID,String secid, int lotnum){
		String hql="select distinct tfd from TranFeeDef tfd,TranBasicFee tf,Tran tb,IssueInfo ii where tfd.id=tf.feeTypeID.id and tf.tanum.id=tb.tanum.id "
				+ "and tb.cusip.id=ii.id and tb.portfolio=:portID and tb.lotnum=:lotnum and ii.securityid=:secid";
		String[] params={"portID","secid","lotnum"};
		Object[] values={portID,secid,lotnum};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<Integer> getAssetFeeFollowList() {
		String hql="select distinct tm.assetType from TranFeeDef def, TranFeeTypeMap tm where def.id=tm.tranFeeTypeCode.id "
				+ " and def.oneTimeFeeFlag=:feeFollowFlag and def.status=:active";
		String[] params={"feeFollowFlag", "active"};
		Object[] values={ExpenseBaseType.followFee, ActiveType.active};
		List<Integer> assettypeList=this.executeHQL(hql, params, values);
		return assettypeList;
	}
	
	@Override
	public TranFeeDef loadTranFeeDef(int tranFeeTypeID) {
		return loadEntity(TranFeeDef.class,tranFeeTypeID);
	}
}
