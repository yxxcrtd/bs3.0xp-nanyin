package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.CashFlowCfgDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.CashFlowCfg;

/**
 * @ClassName 		CashFlowCfgDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("cashFlowCfgDao")
public class CashFlowCfgDaoImpl extends CommFuncDAOImpl implements CashFlowCfgDao{
	
	@Override
	public CashFlowCfg loadCashFlowCfgBySecid(String secid) {
		String hql="from CashFlowCfg where cashFlowID=(select cashFlowCfgID from IssueInfo where securityid=:secid)";
		String[] params={"secid"};
		Object[] values={secid};
		List<CashFlowCfg> cfcList=this.executeHQL(hql, params, values);
		if(cfcList.size()>0){
			return cfcList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public List<CashFlowCfg> loadCashFlowCfgList(String cfgname) {
		String hql="from CashFlowCfg where cashFlowName like :cfgname";
		List<CashFlowCfg> cfgList=this.executeHQL(hql, "cfgname", "%"+cfgname+"%");
		return cfgList;
	}
	
	@Override
	public CashFlowCfg loadCashFlowCfgByName(String name) {
		String hql="from CashFlowCfg where cashFlowName=:name";
		return (CashFlowCfg) this.executeHQLNo1Record(hql, "name", name);
	}
	
	@Override
	public CashFlowCfg loadCashFlowCfg(String cfgID) {
		CashFlowCfg cfc=this.loadEntity(CashFlowCfg.class, cfgID);
		return cfc;
	}

}
