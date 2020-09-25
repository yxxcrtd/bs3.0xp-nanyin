package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.CoaaGroupDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.CoaaGroup;

/**
 * @ClassName 		CoaaGroupDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("coaaGroupDao")
public class CoaaGroupDaoImpl extends CommFuncDAOImpl implements CoaaGroupDao{
	
	@Override
	public CoaaGroup loadCoaaGroup(int groupCode) {
		return (CoaaGroup)this.loadEntity(CoaaGroup.class,groupCode);
	}
	
	@Override
	public CoaaGroup loadFxTranCoaaGroup(String fxcur) {
		String hql = "from CoaaGroup where instr(groupName,:fxcur)>0";
		List<CoaaGroup> coaaGroupList = this.executeHQL(hql, "fxcur", fxcur);
		if (coaaGroupList.size() > 0) {
			return coaaGroupList.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public List<CoaaGroup> loadAllCoaaGroup() {
		String hql="from CoaaGroup order by groupNum asc";
		List<CoaaGroup> coaaGroupList=this.executeHQL(hql);
		return coaaGroupList;
	}
	
	@Override
	public CoaaGroup loadFxOpenGlGroup(String shortCur, String longCur) {
		String hql="from CoaaGroup where groupName=:groupName";
		String groupName="外汇即期-"+shortCur+"/"+longCur;
		return (CoaaGroup)this.executeHQLNo1Record(hql, "groupName", groupName);
	}
	
	@Override
	public CoaaGroup loadSwapFxOpenGlGroup(String shortCur, String longCur) {
		String hql="from CoaaGroup where groupName=:groupName";
		String groupName="外汇掉期-"+shortCur+"/"+longCur;
		return (CoaaGroup)this.executeHQLNo1Record(hql, "groupName", groupName);
	}
	
	@Override
	public CoaaGroup loadForwardFxOpenGlGroup(String shortCur, String longCur) {
		String hql="from CoaaGroup where groupName=:groupName";
		String groupName="外汇远期-"+shortCur+"/"+longCur;
		return (CoaaGroup)this.executeHQLNo1Record(hql, "groupName", groupName);
	}
}
