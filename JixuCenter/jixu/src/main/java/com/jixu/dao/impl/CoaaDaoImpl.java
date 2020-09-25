package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.CoaaDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.Coaa;

/**
 * @ClassName 		CoaaDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("coaaDao")
public class CoaaDaoImpl extends CommFuncDAOImpl implements CoaaDao{
	@Override
	public List<Coaa> getLevel1Coaa(String portNum){
		String coaahql="select coa from Coaa coa, PortfolioHdr po "
				+ "where coa.acctLevel=1 and coa.chartnum=po.coaahdr and po.id=:portNum";
		List<Coaa> coaList=this.executeHQL(coaahql, "portNum", portNum);
		return coaList;
	}
	
	@Override
	public void saveCOA(Coaa coa) {
		this.saveEntity(coa);
	}
	
	@Override
	public void updateCOA(Coaa coa) {
		this.updateEntity(coa);
	}
	
	@Override
	public List<Coaa> loadCoaParent(String coaahdr, String acctNum) {
		String hql="from Coaa where chartnum=:coaahdr and accntnum != :acctNum and instr(:acctNum,accntnum)=1";
		String[] params={"coaahdr", "acctNum"};
		Object[] values={coaahdr, acctNum};
		return  this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<Coaa> loadCoaaDetailsByChartNum(String chartnum) {
		String hql="from Coaa where chartnum=:chartnum and acctisleaf=1 order by accntnum asc";
		String[] params={"chartnum"};
		Object[] values={chartnum};
		List<Coaa> coaaList=this.executeHQL(hql, params, values);
		return coaaList;
	}
	
	@Override
	public List<Coaa> loadCoaaDetailsAllByChartNum(String chartnum) {
		String hql="from Coaa where chartnum=:chartnum order by accntnum asc";
		String[] params={"chartnum"};
		Object[] values={chartnum};
		List<Coaa> coaaList=this.executeHQL(hql, params, values);
		return coaaList;
	}
	
	@Override
	public List<Coaa> loadCoaaDetailsByPortnum(String portnum) {
		String hql="select ca from Coaa ca, PortfolioHdr po where ca.chartnum=po.coaahdr and po.id=:portnum and ca.acctisleaf=1 order by ca.id asc";
		String[] params={"portnum"};
		Object[] values={portnum};
		List<Coaa> coaaList=this.executeHQL(hql, params, values);
		return coaaList;
	}
	
	@Override
	public Coaa loadCoa(String chartnum, String accntnum) {
		String hql="from Coaa where chartnum=:chartnum and accntnum=:accntnum";
		String[] params={"chartnum", "accntnum"};
		Object[] values={chartnum, accntnum};
		List<Coaa> coaaList=this.executeHQL(hql, params, values);
		if(coaaList.size()>0){
			return coaaList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public Coaa loadCoaByChartAndAcctname(String chartnum, String accntname) {
		String hql="from Coaa where chartnum=:chartnum and accntname=:accntname and acctisleaf=1";
		String[] params={"chartnum", "accntname"};
		Object[] values={chartnum, accntname};
		List<Coaa> coaaList=this.executeHQL(hql, params, values);
		if(coaaList.size()>0){
			return coaaList.get(0);
		}else{
			return null;
		}
	}
}
