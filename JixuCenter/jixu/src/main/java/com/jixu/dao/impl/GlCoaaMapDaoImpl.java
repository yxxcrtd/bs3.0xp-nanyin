package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.type.AssetTypes;
import com.jixu.dao.intf.GlCoaaMapDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.jixu.mvc.control.model.GlCoaaMapModel;
import com.orm.obj.CoaaGroup;
import com.orm.obj.GlClose;
import com.orm.obj.GlCoaaMap;

/**
 * @ClassName 		GlCoaaMapDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("glCoaaMapDao")
public class GlCoaaMapDaoImpl extends CommFuncDAOImpl implements GlCoaaMapDao{
	
	@Override
	public List<GlCoaaMapModel> loadGlCoaaMap(String chartnum, int groupnum) {
		String hql="select new com.jixu.mvc.control.model.GlCoaaMapModel(gl.id,gl.columnNum,gl.acctNum,ca.accntname) from GlCoaaMap gl, Coaa ca where gl.acctNum=ca.accntnum and "
				+ "ca.chartnum=:chartnum and gl.chartNum=:chartnum and gl.groupNum=:groupnum order by gl.columnNum asc";
		String[] params={"chartnum","groupnum"};
		Object[] values={chartnum,groupnum};
		List<GlCoaaMapModel> glmapList=this.executeHQL(hql, params, values);
		return glmapList;
	}
	
	@Override
	public List<GlCoaaMapModel> loadGlBookSubjectMap(String chartnum, int groupnum) {
		String hql="select new com.jixu.mvc.control.model.GlCoaaMapModel(gl.id,gl.columnNum,gl.acctNum,ca.subject_name) from GlCoaaMap gl, BokSubject ca where gl.acctNum=ca.bokSubjectPK.subject_no and "
				+ "ca.bokSubjectPK.bookset_id=:chartnum and gl.chartNum=:chartnum and gl.groupNum=:groupnum order by gl.columnNum asc";
		String[] params={"chartnum","groupnum"};
		Object[] values={chartnum,groupnum};
		List<GlCoaaMapModel> glmapList=this.executeHQL(hql, params, values);
		return glmapList;
	}
	
	@Override
	public List<GlCoaaMap> loadGlColAcctMap(String chartnum, int groupnum){
		String hql="from GlCoaaMap where chartNum=:chartnum and groupNum=:groupnum";
		String[] params={"chartnum","groupnum"};
		Object[] values={chartnum,groupnum};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public void deleteGlColMap(GlCoaaMap coamap) {
		this.deleteEntity(coamap);
	}
	
	@Override
	public void updateGlColMap(GlCoaaMap coamap) {
		this.updateEntity(coamap);
	}
	
	@Override
	public String loadGLAcctnum(String chartnum, int groupnum, int glnum) {
		String hql="from GlCoaaMap where chartnum=:chartnum and groupnum=:groupnum and columnnum=:glnum";
		String[] params={"chartnum","groupnum","glnum"};
		Object[] values = { chartnum, groupnum, glnum };
		GlCoaaMap glCoaaMap=(GlCoaaMap)this.executeHQLNo1Record(hql, params, values);
		if(glCoaaMap!=null){
			return glCoaaMap.getAcctNum();
		}else{
			return null;
		}
	}
	
	/**
	 * 自动匹配总账栏
	 * @param assetType	资产类型
	 * @param currency1	即期换汇(空方货币), 外汇掉期(远端收入货币)
	 * @param currency2 即期换汇(多方货币), 外汇掉期(远端支出货币)
	 * @return
	 */
	public CoaaGroup getMapedGlGroup(String assetType, String currency1, String currency2) {
		String groupname=currency1+"/"+currency2;
		if(assetType.equals(AssetTypes.SPOT_CURRENCY)) 
			groupname="外汇即期-"+groupname;
		else if(assetType.equals(AssetTypes.SWAP_FX)) 
			groupname="外汇掉期-"+groupname;
		String hql="from CoaaGroup where groupName=:groupname";
		return (CoaaGroup)this.executeHQLNo1Record(hql, "groupname", groupname);
	}
}
