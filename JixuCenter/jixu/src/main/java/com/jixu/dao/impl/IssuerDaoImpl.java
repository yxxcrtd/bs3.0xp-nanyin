package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jixu.common.util.Pages;
import com.jixu.dao.intf.IssuerDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.jixu.mvc.control.model.IssuerModel;
import com.jixu.mvc.control.model.VouchersModel;
import com.orm.obj.Issuer;

/**
 * @ClassName 		IssuerDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("issuerDao")
public class IssuerDaoImpl extends CommFuncDAOImpl implements IssuerDao{

	@Override
	public Issuer loadIssuer(String issuerID) {
		return loadEntity(Issuer.class,issuerID);
	}
	
	@Override
	public Issuer loadIssuerByCode(String issuerCode) {
		String hql="from Issuer where issuerCode=:issuerCode";
		String[] params={"issuerCode"};
		Object[] values={issuerCode};
		List<Issuer> issuerList=this.executeHQL(hql, params, values);
		if(issuerList.size()>0){
			return issuerList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public List<Issuer> loadAllIssuer() {
		String hql="from Issuer order by issuerName asc";
		List<Issuer> issuerList=this.executeHQL(hql);
		return issuerList;
	}
	
	@Override
	public List<Issuer> getIssuersByName(String name){
		String hql="from Issuer where issuerName=:name";
		String[] params={"name"};
		Object[] values= {name};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public Issuer loadIssuerByID(String issuerID) {
		return loadEntity(Issuer.class, issuerID);
	}
	
	@Override
	public Pages<IssuerModel> loadIssuerModel(Pages<VouchersModel> pages, String issuername) {
		String hql="select new com.jixu.mvc.control.model.IssuerModel(issuerID,issuerCode,issuerName,country,rpSecType.id) from "
				+ "Issuer where issuerName like :issuername or issuerShortName like :issuername order by issuerCode asc";
		String[] params={"issuername"};
		Object[] values={"%"+issuername+"%"};
		return this.getPageHqlData(hql, params, values, false, pages);
	}
	
	@Override
	public Page<IssuerModel> loadPageHelperIssuerModel(Page<IssuerModel> page, String issuername) {
		return page;
	}
}
