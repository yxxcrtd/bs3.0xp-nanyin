package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.types.JixuDictCode;
import com.jixu.dao.intf.ManagersDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.Managers;

/**
 * @ClassName 		ManagersDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("managersDao")
public class ManagersDaoImpl extends CommFuncDAOImpl implements ManagersDao{

	@Override
	public Managers loadInvestManager(int managerGroupID, int managerCode) {
		if(JixuUserUtil.isJoyinTablesFlag()) {
			return defualManagers();
		}else {
			String hql="from Managers where managerGroup=:managerGroupID and id=:managerCode";
			String[] params={"managerGroupID","managerCode"};
			Object[] values={managerGroupID, managerCode};
			List<Managers> returnList = this.executeHQL(hql, params, values);
			if (returnList.size() > 0) {
				return returnList.get(0);
			} else {
				return null;
			}
		}
	}
	
	@Override
	public List<Managers> loadManagersByPortnum(String portnum) {
		if(JixuUserUtil.isJoyinTablesFlag()) {
			List<Managers> managersList=new ArrayList<Managers>();
			managersList.add(defualManagers());
			return managersList;
		}else{
			String hql="from Managers where managerGroup=(select managerGroup from PortfolioHdr where id=:portnum)";
			String[] params={"portnum"};
			Object[] values={portnum};
			return this.executeHQL(hql, params, values);
		}
	}
	
	@Override
	public int getDefaultManagerID(String portnum) {
		if(JixuUserUtil.isJoyinTablesFlag()) {
			return defualManagers().getId();
		}else {
			String sql="select mgrs.ID " +  
						" from JXS_Managers mgrs,JXS_PORTFOLIO_HDR ph " + 
						" where ph.managerGroup=mgrs.managerGroupCode and ph.portfolio=:portnum";
			String[] params={"portnum"};
			Object[] values={portnum};
			Object obj= this.executeSQL(sql, params, values, null);
			if(obj!=null){
				return (int)obj;
			}else
				return 0;
		}
	}
	
	@Override
	public List<Managers> loadManagersByGroupID(int groupID) {
		if(JixuUserUtil.isJoyinTablesFlag()) {
			List<Managers> managersList=new ArrayList<Managers>();
			managersList.add(defualManagers());
			return managersList;
		}else {
			String hql="from Managers where managerGroup=:groupID";
			String[] params={"groupID"};
			Object[] values={groupID};
			return this.executeHQL(hql, params, values);
		}
	}
	
	@Override
	public void saveManagers(Managers manager) {
		saveEntity(manager);
	}
	
	@Override
	public void updateManagers(Managers manager) {
		updateEntity(manager);
	}
	
	@Override
	public Managers loadManagerByID(int id){
		return loadEntity(Managers.class, id);
	}
	
	private Managers defualManagers() {
		Managers manager=new Managers();
		manager.setId(JixuDictCode.DEFAULT_MANAGER);
		manager.setManagerGroup(JixuDictCode.DEFAULT_MANAGERGROUP);
		manager.setManagerName("默认经理");
		manager.setManagerName("默认经理组");
		return manager;
	}
}
