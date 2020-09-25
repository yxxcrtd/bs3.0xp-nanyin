package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.types.JixuDictCode;
import com.jixu.dao.intf.InterfaceSourceDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;

/**
 * @ClassName 		InterfaceSourceDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("interfaceSourceDao")
public class InterfaceSourceDaoImpl extends CommFuncDAOImpl implements InterfaceSourceDao{

	@Override
	public InterfaceSource loadSource(int sourceid) {
		return this.loadEntity(InterfaceSource.class,sourceid);
	}
	
	@Override
	public List<InterfaceSource> loadInterfaceSourceList(String[] portfolios, int sourceType) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		String hql="from InterfaceSource where (sourceType=:sourceType ";
		strBuilder.append(hql);
		paramsList.add("sourceType");
		valuesList.add(sourceType);
		
		
		if(portfolios.length>0){
			strBuilder.append("  and sourceID in (select ineterfaceSource.sourceID from PortfolioInterfaceMap where ");
			for(int i=0;i<portfolios.length;i++){
				strBuilder.append("portfolio.id=:portnum"+i);
				paramsList.add("portnum"+i);
				valuesList.add(portfolios[i]);
				if(i<portfolios.length-1){
					strBuilder.append(" or ");
				}
			}
			strBuilder.append(")");
		}
		strBuilder.append(")");
		
		strBuilder.append(" or sourceType=:mannualSource");
		paramsList.add("mannualSource");
		valuesList.add(JixuDictCode.INTERFACE_SOURCE_MANNUAL);
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(strBuilder.toString(), params, values);
	}
	
	@Override
	public List<InterfaceSource> loadInterfaceSourceByPortnum(String portnum) {
		String hql="select ints from InterfaceSource ints, PortfolioInterfaceMap pm where ints.sourceID=pm.ineterfaceSource.sourceID "
				+ " and ints.sourceType=0 and pm.portfolio.id=:portnum";
		String[] params={"portnum"};
		Object[] values={portnum};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<InterfaceSource> loadInterfaceSource(String[] ports, int sourceType) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> params=new ArrayList<String>();
		List<Object> values=new ArrayList<Object>();
		
		String hql="select iss from InterfaceSource iss, PortfolioHdr po, PortfolioInterfaceMap pi where (iss.sourceID=pi.ineterfaceSource.sourceID "
				+ " and po.id=pi.portfolio.id and iss.sourceType=:sourceType";
		strBuilder.append(hql);
		params.add("sourceType");
		values.add(sourceType);
		
		if(ports.length>0){
			strBuilder.append(" and (");
			for(int i=0;i<ports.length;i++){
				String port=ports[i];
				strBuilder.append(" po.id=:portfolio"+i);
				params.add("portfolio"+i);
				values.add(port);
				if(i<ports.length-1){
					strBuilder.append(" or ");
				}
			}
			strBuilder.append(")");
		}
		strBuilder.append(") or iss.sourceType=:mannualSource");
		params.add("mannualSource");
		values.add(JixuDictCode.INTERFACE_SOURCE_MANNUAL);
		
		String[] paramsArr=params.toArray(new String[params.size()]);
		Object[] valuesArr=values.toArray();
		return this.executeHQL(strBuilder.toString(), paramsArr, valuesArr);
	}
	
	@Override
	public List<InterfaceSource> loadAutoExecuteJobs() {
		String hql="from InterfaceSource where autoFlag is true";
		return this.executeHQL(hql);
	}
		
	public List<InterfaceSource> loadInterfaceByType(int sourceType) {
		if(sourceType==0){
			String hql="from InterfaceSource";
			return this.executeHQL(hql);
		}else{
			String hql="from InterfaceSource where sourceType=:sourceType";
			String[] params={"sourceType"};
			Object[] values={sourceType};
			return this.executeHQL(hql,params, values);
		}
	}
	
	@Override
	public int getInterfaceSourceID(String portnum, int sourceType, int rpSecType) {
		String sql="select iis.sourceid " +
					" from JXS_portfoliointerfacemap pim, adpt_interfacesource iis  " +
					" where pim.interfacesourceid=iis.sourceid and pim.rpSecType=:rpSecType and iis.sourceType=:sourceType  " +
					" and pim.portfolio=:portnum";
		String[] params={"portnum","sourceType","rpSecType"};
		Object[] values={portnum,sourceType,rpSecType};
		Object obj=this.executeSQLNo1Record(sql, params, values, null);
		if(obj!=null)
			return Integer.parseInt(obj.toString());
		else
			return 0;
	}
}
