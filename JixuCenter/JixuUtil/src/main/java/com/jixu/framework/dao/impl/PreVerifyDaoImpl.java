package com.jixu.framework.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.pojo.PreVerify;
import com.jixu.framework.dao.intf.PreVerifyDao;

/**
 * @ClassName 		PreVerifyDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("preVerifyDao")
public class PreVerifyDaoImpl extends CommFuncDAOImpl implements PreVerifyDao{

	@Override
	public List<PreVerify> loadPreVerifyBySubmitterID(String[] portfolios,int submitter,Date start,Date end) {
		//查询需要该用户审核的用户所做的操作（根据页面传过来的提交人id单个查询，包括自己）
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		strBuilder.append("from PreVerify where ");
		
		strBuilder.append(" submitterID=:submitter ");
		paramsList.add("submitter");
		valuesList.add(submitter);
		
	    strBuilder.append(" and ");
		strBuilder.append(" operationDate>=:start ");
		paramsList.add("start");
		valuesList.add(start);
		
		strBuilder.append(" and ");
		
		strBuilder.append(" operationDate<=:end ");
		paramsList.add("end");
		valuesList.add(end);
		
		strBuilder.append(" and (");
		
		for(int i=0;i<portfolios.length;i++){
			strBuilder.append("portfolio=:portnum"+i);
			paramsList.add("portnum"+i);
			valuesList.add(portfolios[i]);
			if(i<portfolios.length-1){
				strBuilder.append(" or ");
			}
		}
		strBuilder.append(")");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(strBuilder.toString(), params, values);
	}
	
	@Override
	public List<PreVerify> loadPreVerifyByAll(String[] portfolios,int userid,Date start,Date end){
		//查询需要该用户审核的其他所有用户所做的操作
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		strBuilder.append("select distinct py ");
		strBuilder.append(" from PreVerify py,PortfolioHdr ph,VerifyConfig vc,UserRoleGroupMap urgm,Users u where ");
		strBuilder.append(" py.portfolio=ph.id and py.verifyconfigID=vc.id and vc.roleGroup.groupID=urgm.userRoleGroup.groupID and urgm.userRole.roleID=u.userRole.roleID ");
		strBuilder.append(" and ");
		strBuilder.append(" u.userid=:userid ");
		paramsList.add("userid");
		valuesList.add(userid);
		
	    strBuilder.append(" and ");
		strBuilder.append(" operationDate>=:start ");
		paramsList.add("start");
		valuesList.add(start);
		
		strBuilder.append(" and ");
		
		strBuilder.append(" operationDate<=:end ");
		paramsList.add("end");
		valuesList.add(end);
		
		strBuilder.append(" and (");
		
		for(int i=0;i<portfolios.length;i++){
			strBuilder.append("ph.id=:portnum"+i);
			paramsList.add("portnum"+i);
			valuesList.add(portfolios[i]);
			if(i<portfolios.length-1){
				strBuilder.append(" or ");
			}
		}
		strBuilder.append(")");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(strBuilder.toString(), params, values);
	}
	
	@Override
	public PreVerify loadPreVerify(int id) {
		String hql="from PreVerify where id=:id";
		String[] params={"id"};
		Object[] values={id};
		List<PreVerify> PVerify=this.executeHQL(hql, params, values);
		if(PVerify.size()>0){
			PreVerify pv = PVerify.get(0);
			return pv;
		}else{
			return null;
		}
	}
	
	@Override
	public List<PreVerify> loadPreVerifyByVConfigID(int id) {
		String hql="from PreVerify where verifyconfigID=:id";
		String[] params={"id"};
		Object[] values={id};
		List<PreVerify> list=this.executeHQL(hql, params, values);
		return list;
	}
}
