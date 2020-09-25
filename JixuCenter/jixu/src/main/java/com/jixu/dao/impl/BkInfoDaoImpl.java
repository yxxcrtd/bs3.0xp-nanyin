package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.BkInfoDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.BkInfo;

/**
 * @ClassName 		BkInfoDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("bkInfoDao")
public class BkInfoDaoImpl extends CommFuncDAOImpl implements BkInfoDao{
	
	public List<BkInfo> loadBankList(){
		String hql="from BkInfo order by bankID asc";
		List<BkInfo> bkList=this.executeHQL(hql);
		return bkList;
	}
	
	public BkInfo loadloadBkInfoIDByName(String name){
		  String hql="from BkInfo where bankDesc=:name";
		  String[] params={"name"};
		  Object[] values={name};
		  return (BkInfo) this.executeHQLNo1Record(hql, params, values);
	}
	
	public boolean checkBkInfoNameExist(String name) {
		  String hql="from BkInfo where bankDesc=:name";
		  String[] params={"name"};
		  Object[] values={name};
		  List<BkInfo> bList=this.executeHQL(hql, params, values);
		  if(bList.size()>0){
			  return false;
		  }else{
			  return true;
		  }
	}
}
