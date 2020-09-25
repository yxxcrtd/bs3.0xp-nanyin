package com.jixu.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.dao.intf.IssuerDao;
import com.jixu.data.pojo.Issuer;

/**
 * @ClassName TbSecurity
 * @author Pan Wei
 * @Date 2017年12月18日 上午10:40:11
 * @Description []
 */

@Component("JixuTbPubIssuer")
public class TbPubIssuer extends OutPublicDataOperation {
	
	@Autowired
	private IssuerDao issuerDao;
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public Class<?> getReturnClass() {
		return com.orm.obj.Issuer.class;
	}
	
	@Override
	public List<?> genResults(Object obj) {
		com.orm.obj.Issuer jixuIssuer=(com.orm.obj.Issuer)obj;
		
		Issuer issuer=new Issuer();
		issuer.setIssuerID(jixuIssuer.getIssuerID());
		issuer.setIssuerName(jixuIssuer.getIssuerShortName());
		issuer.setIssuerLongName(jixuIssuer.getIssuerName());
		issuer.setCountryID(jixuIssuer.getCountry());
		
		List<Issuer> returnList=new ArrayList<Issuer>();
		returnList.add(issuer);
		return returnList;
	}
	
	@Override
	protected List<?> genResults() {
		StringBuffer sb = new StringBuffer();
		sb.append("select  ");
		sb.append(" issuerID as issuerID, ");
		sb.append(" country as countryID, ");
		sb.append(" issuerName as issuerLongName, ");
		sb.append(" issuerShortName as issuerName ");
		sb.append(" from JXS_issuer");
		List<Issuer> spList = issuerDao.executeSQLReturnBean(sb.toString(), Issuer.class);
		return spList;
	}
}
