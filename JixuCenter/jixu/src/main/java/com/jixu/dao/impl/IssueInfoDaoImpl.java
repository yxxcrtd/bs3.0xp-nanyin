package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.util.Pages;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.jixu.joyin2jixu.dto.SecurityInfoDto;
import com.jixu.mvc.control.model.MarketStockModel;
import com.orm.obj.FinProductType;
import com.orm.obj.IssueInfo;
import com.orm.obj.Tran;

/**
 * @ClassName 		IssueInfoDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("issueInfoDao")
public class IssueInfoDaoImpl extends CommFuncDAOImpl implements IssueInfoDao{
	
	//多个key清除对应的缓存
	@Override
	public void saveSecurity(IssueInfo sec) {
		this.saveEntity(sec);
	}
	
	@Override
	public HashMap<String, IssueInfo> loadIssueInfo(int countryCode) {
		HashMap<String, IssueInfo> map=new HashMap<String, IssueInfo>();
		String hql="from IssueInfo where country=:countryCode";
		String[] params = { "countryCode" };
		Object[] values = { countryCode };
		List<IssueInfo> list=this.executeHQL(hql, params, values);
		for (IssueInfo issueInfo : list) {
			map.put(issueInfo.getSecurityid(), issueInfo);
		}
		return map;
	}
	
	@Override
	public void deleteIssue(IssueInfo issue) {
		deleteEntity(issue);
	}

	@Override
	public void updateIssue(IssueInfo issue) {
		updateEntity(issue);
	}
	
	@Override
	public IssueInfo loadSwapSecurity(String secid) {
		String hql="from IssueInfo where securityid=:secid and issueType=:swapType";
		String[] params={"secid","swapType"};
		Object[] values={secid,AssetTypes.SWAP_INTEREST};
		return (IssueInfo)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public List<String> loadSecIDNameList(int sourceid, String secType) {
		
		StringBuilder strBuilder=new StringBuilder();
		List<String> params=new ArrayList<String>();
		List<Object> values=new ArrayList<Object>();
		String hql="select id || '|' || securityid || '|' || securityname from IssueInfo where 1=1";
		strBuilder.append(hql);
		
		if(sourceid>0){
			strBuilder.append(" and sourceID.sourceID=:sourceid");
			params.add("sourceid");
			values.add(sourceid);
		}
		if(!secType.equals("0")){
			strBuilder.append(" and issueType=:secType");
			params.add("secType");
			values.add(secType);
		}
		
		strBuilder.append(" order by securityid asc");
		
		String[] paramsArr=params.toArray(new String[params.size()]);
		Object[] valuesArr=values.toArray();
		
		List<String> secidList=this.executeHQL(strBuilder.toString(), paramsArr, valuesArr);
		
		return secidList;
	}
	
	@Override
	public Pages<MarketStockModel> loadStockList(int[] sourceids, String secType, String secID, Pages<MarketStockModel> pages) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> params=new ArrayList<String>();
		List<Object> values=new ArrayList<Object>();
		//String cusip,String sourcename,String secType,String secCode,String secName,String industryName,String coaName,String curName,String country
		String hql="";
		if(secType.equals(AssetTypes.SWAP_INTEREST)||secType.equals(AssetTypes.FXOption)||secType.equals(AssetTypes.FORWARD_CURRENCY)) {
			//临时解决，需要优化语句，不需要为特殊的swap作为特例
			hql="select new com.jixu.mvc.control.model.MarketStockModel(ii.id,ii.sourceID.sourceDesc,ii.rpSecType.typename,ii.securityid,'-','-',"
					+ " cg.groupName,ii.priCur,'-') from IssueInfo ii, CoaaGroup cg where ii.coaaGroup=cg.groupNum and ii.issueType=:secType ";
		}else if(JixuUserUtil.isJoyinTablesFlag()){
			hql="select new com.jixu.mvc.control.model.MarketStockModel(ii.id,ii.sourceID.sourceDesc,ii.rpSecType.typename,ii.securityid,ii.securityname,'-',"
					+ " cg.groupName,ii.priCur,ii.country) from IssueInfo ii, CoaaGroup cg where ii.coaaGroup=cg.groupNum and ii.issueType=:secType ";
		}else {
			hql="select new com.jixu.mvc.control.model.MarketStockModel(ii.id,ii.sourceID.sourceDesc,ii.rpSecType.typename,ii.securityid,ii.securityname,isr.rpSecType.typename,"
				+ " cg.groupName,ii.priCur,ii.country) from IssueInfo ii, Issuer isr, CoaaGroup cg where ii.issuer=isr.issuerID and ii.coaaGroup=cg.groupNum and ii.issueType=:secType ";
		}
		
		strBuilder.append(hql);
		params.add("secType");
		values.add(secType);
		
		List<Integer> sourceidList=new ArrayList<Integer>();
		for(int sourceid:sourceids) {
			sourceidList.add(sourceid);
		}
		if(!sourceidList.contains(0)) {
			if(sourceidList.size()>0){
				strBuilder.append(" and (");
				for(int i=0;i<sourceidList.size();i++){
					int source=sourceidList.get(i);
					strBuilder.append(" ii.sourceID.sourceID=:sourceid"+i);
					params.add("sourceid"+i);
					values.add(source);
					if(i<sourceidList.size()-1){
						strBuilder.append(" or ");
					}
				}
				strBuilder.append(")");
			}
		}
		
		if(!secID.equals("")){
			strBuilder.append(" and (ii.securityid=:secID or ii.securityname=:secID)");
			params.add("secID");
			values.add(secID);
		}
		strBuilder.append(" order by securityid asc,ii.priCur asc");
		
		
		String[] paramsArr=params.toArray(new String[params.size()]);
		Object[] valuesArr=values.toArray();
		
		Pages<MarketStockModel> marketDateList=this.getPageHqlData(strBuilder.toString(), paramsArr, valuesArr, false, pages);
		return marketDateList;
	}
	
	@Override
	public Page<MarketStockModel> loadPageHelperStockList(int[] sourceids, String secType, String secID, Page<MarketStockModel> page) {
		return page;
	}
	
	@Override
	public IssueInfo loadSecurity(String secid) {
		String hql="from IssueInfo where securityid=:secid";
		String[] params={"secid"};
		Object[] values={secid};
		return (IssueInfo)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public IssueInfo loadIssueByCusip(String cusip) {
		return (IssueInfo)this.loadEntity(IssueInfo.class, cusip);
	}
		
	public List<IssueInfo> loadBasePotinSec() {
		String hql="from IssueInfo where issueType=:basePointType";
		return this.executeHQL(hql, "basePointType", AssetTypes.BasePoint);
	}

	@Override
	public IssueInfo loadIssueInfoBuyCur(String assetType, String longCur, String shortCur) {
		String hql="from IssueInfo where issueType=:assetType and priCur2=:longCur and priCur=:shortCur";
		String[] params={"assetType","longCur","shortCur"};
		Object[] values={assetType,longCur,shortCur};
		return (IssueInfo)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public FinProductType loadFinProductType(String secid) {
		String hql="from FinProductType where finprod_id=:secid";
		String[] params={"secid"};
		Object[] values= {secid};
		List<FinProductType> flist=this.executeHQL(hql, params, values);
		if(flist.size()>0){
			return flist.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public IssueInfo loadIssueBySecurityname(String secname) {
		String hql="from IssueInfo where securityname=:secname";
		return (IssueInfo)this.executeHQLNo1Record(hql, "secname", secname);
	}
	
	@Override
	public List<SecurityInfoDto> loadSecurityByAssetType(String assetType) {
		String hql="select new com.jixu.joyin2jixu.dto.SecurityInfoDto(securityid, securityname) from IssueInfo where issueType=:assetType order by securityid asc";
		return this.executeHQL(hql, "assetType", assetType);
	}
	
	@Override
	public List<SecurityInfoDto> loadSecurityByAssetType(String[] assetTypes) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> params=new ArrayList<String>();
		List<Object> values=new ArrayList<Object>();
		strBuilder.append("select new com.jixu.joyin2jixu.dto.SecurityInfoDto(securityid, securityname) from IssueInfo where 1=1");
		if(assetTypes.length>0){
			strBuilder.append(" and (");
			for(int i=0;i<assetTypes.length;i++){
				String assetType=assetTypes[i];
				strBuilder.append(" issueType=:assetType"+i);
				params.add("assetType"+i);
				values.add(assetType);
				if(i<assetTypes.length-1){
					strBuilder.append(" or ");
				}
			}
			strBuilder.append(")");
		}
		strBuilder.append(" order by issueType, securityid asc");
		String[] paramsArr=params.toArray(new String[params.size()]);
		Object[] valuesArr=values.toArray();
		return this.executeHQL(strBuilder.toString(), paramsArr, valuesArr);
	}
	
	@Override
	public String getCusipBySecid(String secid) {
		String hql="select id from IssueInfo where securityid=:secid";
		return (String)this.executeHQLNo1Record(hql, "secid", secid);
	}
}
