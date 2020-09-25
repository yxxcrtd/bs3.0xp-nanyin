package com.jixu.framework.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.pojo.VerifyConfig;
import com.jixu.framework.dao.intf.VerifyConfigDao;

/**
 * @ClassName 		VerifyConfigDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("verifyConfigDao")
public class VerifyConfigDaoImpl extends CommFuncDAOImpl implements VerifyConfigDao{
	
	@Override
	public void deleteVerifyConfig(int groupID) {
		String hql="delete from VerifyConfig where roleGroup.groupID=:groupID";
		String[] params={"groupID"};
		Object[] values={groupID};
		this.deleteHql(hql, params, values);
	}
	
	@Override
	public VerifyConfig loadVerifyConfigByHeader(String msgheader) {
		String hql="from VerifyConfig where msgHeader=:msgheader";
		String[] params={"msgheader"};
		Object[] values={msgheader};
		List<VerifyConfig> list=this.executeHQL(hql, params, values);
		if(list.size()>0){
			VerifyConfig vc = list.get(0);
			return vc;
		}else{
			return null;
		}
	}

	@Override
	public VerifyConfig loadVerifyConfigDetail(int id) {
		String hql="from VerifyConfig where id=:id";
		String[] params={"id"};
		Object[] values={id};
		List<VerifyConfig> list=this.executeHQL(hql, params, values);
		if(list.size()>0){
			VerifyConfig vc=list.get(0);
			return vc;
		}else{
			return null;
		}
	}
	
	@Override
	public List<VerifyConfig> loadVerifyConfig(int groupid) {
		String hql="from VerifyConfig where roleGroup.groupID=:groupid";
		String[] params={"groupid"};
		Object[] values={groupid};
		List<VerifyConfig> list=this.executeHQL(hql, params, values);
		return list;
	}
	
}
