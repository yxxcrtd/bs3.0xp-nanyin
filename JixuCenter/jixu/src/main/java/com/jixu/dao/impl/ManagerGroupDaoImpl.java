package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.types.JixuDictCode;
import com.jixu.dao.intf.ManagerGroupDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.ManagerGroup;

/**
 * @ClassName ManagerGroupDaoImpl
 * @author Pan Wei
 * @Date 2018年10月10日 下午23:29:03
 * @Description []
 */

@Repository("managerGroupDao")
public class ManagerGroupDaoImpl extends CommFuncDAOImpl implements ManagerGroupDao {

	@Override
	public ManagerGroup loadGroupNameByPortName(String portID) {
		if(JixuUserUtil.isJoyinTablesFlag()) {
			return defaultManagerGroup();
		}else {
			String hql = "select mg from ManagerGroup mg,PortfolioHdr por where mg.id=por.managerGroup and por.id=:portID";
			String[] params = { "portID" };
			Object[] values = { portID };
			return (ManagerGroup) this.executeHQLNo1Record(hql, params, values);
		}
	}
	
	@Override
	public void saveManagerGroup(ManagerGroup managerGroup) {
		saveEntity(managerGroup);
	}
	

	@Override
	public List<ManagerGroup> loadManagerGroupList() {
		if(JixuUserUtil.isJoyinTablesFlag()) {
			List<ManagerGroup> mgList=new ArrayList<ManagerGroup>();
			mgList.add(defaultManagerGroup());
			return mgList;
		}else {
			String hql="from ManagerGroup";
			return this.executeHQL(hql);
		}
	}
	
	private ManagerGroup defaultManagerGroup() {
		ManagerGroup mg=new ManagerGroup();
		mg.setId(JixuDictCode.DEFAULT_MANAGERGROUP);
		mg.setGroupname("默认经理组");
		return mg;
	}
}
