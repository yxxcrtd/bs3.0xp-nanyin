package com.jixu.adapter.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.adapter.common.func.AdapterSysInit;
import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.types.ActiveType;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.InterfaceSourceType;
import com.jixu.common.util.DBTypeSelect;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;

/**
 * @ClassName 		InterfaceSourceDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("interfaceAdapterDao")
public class InterfaceAdapterDaoImpl extends CommFuncDAOImpl implements InterfaceAdapterDao{

	@Override
	public InterfaceSource loadSource(int sourceid) {
		return this.loadEntity(InterfaceSource.class,sourceid);
	}
	
	@Override
	public List<InterfaceSource> loadInterfaceSourceList(int[] portfolios, int sourceType) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		String hql="from InterfaceSource where sourceType=:sourceType";
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
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(strBuilder.toString(), params, values);
	}
	
	@Override
	public List<InterfaceSource> loadInterfaceSourceByPortnum(int portnum) {
		String hql="select ints from InterfaceSource ints, PortfolioInterfaceMap pm where ints.sourceID=pm.ineterfaceSource.id "
				+ " and ints.sourceType=0 and pm.portfolio.id=:portnum";
		String[] params={"portnum"};
		Object[] values={portnum};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<InterfaceSource> loadInterfaceSource(int[] ports, int sourceType) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> params=new ArrayList<String>();
		List<Object> values=new ArrayList<Object>();
		
		String hql="select iss from InterfaceSource iss, PortfolioHdr po, PortfolioInterfaceMap pi where iss.sourceID=pi.ineterfaceSource.sourceID "
				+ " and po.id=pi.portfolio.id and iss.sourceType=:sourceType";
		strBuilder.append(hql);
		params.add("sourceType");
		values.add(sourceType);
		
		if(ports.length>0){
			strBuilder.append(" and (");
			for(int i=0;i<ports.length;i++){
				int port=ports[i];
				strBuilder.append(" po.id=:portfolio"+i);
				params.add("portfolio"+i);
				values.add(port);
				if(i<ports.length-1){
					strBuilder.append(" or ");
				}
			}
			strBuilder.append(")");
		}
		
		String[] paramsArr=params.toArray(new String[params.size()]);
		Object[] valuesArr=values.toArray();
		return this.executeHQL(strBuilder.toString(), paramsArr, valuesArr);
	}
	
	@Override
	public List<InterfaceSource> loadAutoExecuteJobs() {
		String hql="from InterfaceSource where autoFlag is true and activeStatus=1 and projectGroup=:projectGroup";
		return this.executeHQL(hql,"projectGroup",AdapterSysInit.AdapterProjectGroup);
	}
	
	@Override
	public List<InterfaceSource> loadAllInterfaceSourceList() {
		String hql="from InterfaceSource where activeStatus=1 and projectGroup=:projectGroup";
		return this.executeHQL(hql,"projectGroup",AdapterSysInit.AdapterProjectGroup);
	}
		
	public List<InterfaceSource> loadInterfaceByType(int sourceType, String category, String sourcename) {
//		if(sourceType==0){
//			String hql="from InterfaceSource where activeStatus=:activeStatus order by sourceID asc";
//			return this.executeHQL(hql, "activeStatus", ActiveType.active);
//		}else{
		if(category.equals("0")) {
			String hql="from InterfaceSource where sourceType=:sourceType and activeStatus=:activeStatus and projectGroup=:projectGroup order by sourceID asc";
			String[] params={"sourceType", "activeStatus","projectGroup"};
			Object[] values={sourceType, ActiveType.active, AdapterSysInit.AdapterProjectGroup};
			return this.executeHQL(hql,params, values);
		}else {
			String hql="from InterfaceSource where sourceType=:sourceType and sourceSecType=:category and activeStatus=:activeStatus and projectGroup=:projectGroup order by sourceID asc";
			String[] params={"sourceType", "activeStatus","category", "projectGroup"};
			Object[] values={sourceType, ActiveType.active, category, AdapterSysInit.AdapterProjectGroup};
			return this.executeHQL(hql,params, values);
		}
	}
	
	@Override
	public List<InterfaceSource> loadExpInterfaceSourceList(String sourcename, String className) {
		String hql="from InterfaceSource where expSourceClass=:className";
		String[] params={"className"};
		Object[] values={className};
		return this.executeHQL(hql,params, values);
	}
	
	@Override
	public List<InterfaceSource> loadExpInterfaceSourceList() {
		String hql="from InterfaceSource where sourceType=:expType and projectGroup=:projectGroup";
		String[] params={"expType","projectGroup"};
		Object[] values={InterfaceSourceType.exportType,AdapterSysInit.AdapterProjectGroup};
		return this.executeHQL(hql,params, values);
	}
	
	@Override
	public List<InterfaceSource> loadChildInterfaces(int sourceID) {
		String hql="from InterfaceSource where dependency=:sourceID and activeStatus=1";
		return this.executeHQL(hql, "sourceID", sourceID);
	}
	
	@Override
	@Transactional(value="jixuTXManager",readOnly=false)
	public List<Object> getFGuid() {
		// TODO add by wangqidong
		String sql = "select F_GUID() from dual";
		return this.executeSQL(sql, null);
	}
	@Transactional(value="jixuTXManager",readOnly=false)
	public List<String> getFileSeq() {
		// TODO add by wangqidong
		String sql = "SELECT REPLACE(LPAD(FILE_SEQ.NEXTVAL, 4), ' ', '0') FROM DUAL";
		if(DBTypeSelect.curDBType.equals(DBTypeSelect.db2)){
			sql = "SELECT REPLACE(LPAD(next value for FILE_SEQ, 4), ' ', '0') FROM Sysibm.sysdummy1";
		}
		return this.executeSQL(sql, null);
	}

	@Override
	@Transactional(value="jixuTXManager",readOnly=false)
	public List<Date> getCurDate() {
		// TODO add by wangqidong
		String sql = "select cur_date from sys_date";
		return  this.executeSQL(sql, null);
	}
	
	@Override
	@Transactional(value="jixuTXManager",readOnly=false)
	public long genDataKeySeq(String seqname) {
		String sql = "select "+seqname+".nextval from dual";
		if(DBTypeSelect.curDBType.equals(DBTypeSelect.db2))
			sql = "select next value for " + seqname + " from Sysibm.sysdummy1";
			
		List<Object> seqresultList=this.executeSQL(sql, null);
		return Long.parseLong(seqresultList.get(0).toString());
	}

	@Override
	@Transactional(value="jixuTXManager",readOnly=false)
	public List<String> getSeq(String seqName) {
		String sql = "SELECT REPLACE(LPAD("+seqName+".NEXTVAL,4), ' ', '0') FROM DUAL";
		if(DBTypeSelect.curDBType.equals(DBTypeSelect.db2)){
			sql = "SELECT REPLACE(LPAD(next value for "+seqName+",4), ' ', '0') FROM Sysibm.sysdummy1";
		}
		return this.executeSQL(sql, null);
	}
}
