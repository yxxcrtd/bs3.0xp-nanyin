package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.RPSecTypeDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.jixu.mvc.control.model.RPSecTypeLevelModel;
import com.orm.obj.RPSecType;

/**
 * @ClassName 		RPSecTypeDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("rpSecTypeDao")
public class RPSecTypeDaoImpl extends CommFuncDAOImpl implements RPSecTypeDao{

	public RPSecType loadIssuerType(int issuerTypeCode) {
		return loadRPSecType(issuerTypeCode, 2);
	}
	
	public RPSecType loadStockType(int stockTypeCode) {
		return loadRPSecType(stockTypeCode, 1);
	}
	public RPSecType loadStockType2(int stockTypeCode) {
		return loadRPSecType(stockTypeCode, 2);
	}
	
	private RPSecType loadRPSecType(int code, int tableCode){
		String hql="from RPSecType where id=:code and tablecode=:tableCode";
		String[] params={"code","tableCode"};
 		Object[] values={code, tableCode};
		List<RPSecType> RPSecTypeList=this.executeHQL(hql, params, values);
		if(RPSecTypeList.size()>0){
			return RPSecTypeList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public List<RPSecType> loadRPSecTypeS(int tableCode, int groupLevel) {
		String hql="from RPSecType where tablecode=:tableCode and grouplevel=:groupLevel";
		String[] params={"tableCode", "groupLevel"};
		Object[] values={tableCode,groupLevel};
		List<RPSecType> typeList=this.executeHQL(hql, params, values);
		return typeList;
	}
	
	/*@Override
	public RPSecTypeLevelModel loadAssetTypeModel(int level3id) {
		List<RPSecTypeLevelModel> typeList=loadRPSecTypeLevelModel(level3id,1);
		if(typeList.size()>0){
			return typeList.get(0);
		}else{
			return null;
		}
	}*/
	
	@Override
	public List<RPSecTypeLevelModel> loadRPSecTypeLevelModel(int level3id, int tablecode) {
		String sql1="select t1.id as level1id,t1.typename as level1name,t23.level2id,t23.level2name,t23.level3id,t23.level3name from "
				+ " JXS_rpsectype t1,"
				+ " (select t2.id as level2id,t2.fathernode, t2.typename as level2name,t3.id as level3id,t3.typename as level3name "
				+ " from JXS_rpsectype t2, (select id,typename,fathernode from JXS_rpsectype where tablecode="+tablecode+" and grouplevel=3";
		String sql2=" and id=" + level3id;
		String sql3= ") t3 where t3.fathernode=t2.id) t23 where t1.id=t23.fathernode";
		if(level3id==0){
			sql1=sql1+sql3;
		}else{
			sql1=sql1+sql2+sql3;
		}
		List<RPSecTypeLevelModel> rpTypesList=new ArrayList<RPSecTypeLevelModel>();
		List<Object[]> typesList=this.executeSQL(sql1, null);
		for(Object[] objs:typesList){
			RPSecTypeLevelModel rpType=new RPSecTypeLevelModel();
			rpType.setLevel1id(Integer.valueOf(objs[0].toString()));
			rpType.setLevel1name(objs[1].toString());
			rpType.setLevel2id(Integer.valueOf(objs[2].toString()));
			rpType.setLevel2name(objs[3].toString());
			rpType.setLevel3id(Integer.valueOf(objs[4].toString()));
			rpType.setLevel3name(objs[5].toString());
			
			rpTypesList.add(rpType);
		}
		return rpTypesList;
	}
	
	/*@Override
	public RPSecTypeLevelModel loadIndustryTypeModel(int level3id) {
		List<RPSecTypeLevelModel> typeList=loadRPSecTypeLevelModel(level3id,2);
		if(typeList.size()>0){
			return typeList.get(0);
		}else{
			return null;
		}
	}*/
	
	/*@Override
	public List<RPSecTypeLevelModel> loadIndustryTypeModel2() {
		return loadRPSecTypeLevelModel(0,2);
	}*/
	
	@Override
	public List<RPSecType> getRPSecTypename(String name,int tablecode) {
		String hql="from RPSecType where typename=:name and tablecode=:tablecode and grouplevel=3";
		String[] params={"name","tablecode"};
		Object[] values= {name,tablecode};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<RPSecType> loadAssetFatherType() {
		return loadRPSecTypeFatherList(1);
	}
	
	@Override
	public java.util.List<RPSecType> loadIndustryFatherType() {
		return loadRPSecTypeFatherList(2);
	}
	
	/*@Override
	public List<RPSecType> loadAssetTypeList(int typeCode) {
		return loadRPSecTypeList(1,typeCode);
	}
	
	@Override
	public List<RPSecType> loadIndustryTypeList(int typeCode) {
		return loadRPSecTypeList(2,typeCode);
	}*/
	
	
	public List<RPSecType> loadRPSecTypeFatherList(int tableCode){
		String hql="from RPSecType where tablecode=:tableCode and grouplevel=1";
		String[] params={"tableCode"};
		Object[] values={tableCode};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<RPSecType> loadRPSecTypeList(int tableCode, int typeCode){
		String sql="Select * From JXS_Rpsectype Where tablecode=:tablecode and Id=:typecode "
				+ " Union "
				+ " Select * From JXS_Rpsectype Where tablecode=:tablecode and Fathernode=:typecode "
				+ " Union "
				+ " select * from JXS_Rpsectype where tablecode=:tablecode and fathernode in (Select id From JXS_Rpsectype Where Fathernode =:typecode)";
		String[] params={"tablecode","typecode"};
		Object[] values={tableCode,typeCode};
		return this.executeSQL(sql, params, values, RPSecType.class);
	}
	
	@Override
	public List<RPSecType> loadIndustryAssetTypeList(int typeCode) {
		String hql="from RPSecType where tablecode=:typeCode";
		String[] params={"typeCode"};
		Object[] values={typeCode};
		return this.executeHQL(hql, params, values);
	}
}
