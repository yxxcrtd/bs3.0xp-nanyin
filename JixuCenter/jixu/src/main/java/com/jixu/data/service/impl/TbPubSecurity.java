package com.jixu.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.data.pojo.SecurityMaster;
import com.orm.obj.IssueInfo;

/**
 * @ClassName TbSecurity
 * @author Pan Wei
 * @Date 2017年12月18日 上午10:40:11
 * @Description []
 */

@Component("JixuTbPubSecurity")
public class TbPubSecurity extends OutPublicDataOperation {
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public Class<?> getReturnClass() {
		return IssueInfo.class;
	}
	
	@Override
	public List<?> genResults(Object obj) {
		IssueInfo issue=(IssueInfo)obj;
		SecurityMaster sm=new SecurityMaster();
		sm.setId(issue.getId());
		sm.setSecurityID(issue.getSecurityid());
		sm.setSecurityName(issue.getSecurityname());
		sm.setContractSize(issue.getContractSize());
		sm.setExchangeID(issue.getExchangeCode());
		sm.setIncomeCurID(issue.getIncomeCur());
		sm.setIssueType(issue.getIssueType());
		sm.setIssuerID(issue.getIssuer());
		sm.setPrincipalCurID(issue.getPriCur());
		sm.setCountryID(issue.getCountry());
		
		List<SecurityMaster> returnList=new ArrayList<SecurityMaster>();
		returnList.add(sm);
		return returnList;
	}
	
	@Override
	protected List<?> genResults() {
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		sb.append(" cusip as id, ");
		sb.append(" securityID, ");
		sb.append(" securityname as securityName, ");
		sb.append(" contractSize, ");
		sb.append(" exchangeCode as exchangeID, ");
		sb.append(" incomeCur as incomeCurID, ");
		sb.append(" issueType, ");
		sb.append(" issuer as issuerID, ");
		sb.append(" priCur as principalCurID, ");
		sb.append(" countryCode as countryID ");
		sb.append(" from JXS_Issue_info ");

		List<SecurityMaster> spList = issueInfoDao.executeSQLReturnBean(sb.toString(), SecurityMaster.class);
		return spList;
	}
}
