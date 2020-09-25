package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.Managers;

/**
 * @ClassName 		ManagersDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface ManagersDao extends CommFuncDAO{

	public Managers loadInvestManager(@Param("managerGroupID")int managerGroupID, @Param("managerCode") int managerCode);
	
	public List<Managers> loadManagersByPortnum(@Param("portnum")String portnum);
	
	/**
	 * 测试的时候查找一个默认的基金经理
	 * @param portnum
	 * @return
	 */
	public int getDefaultManagerID(@Param("portnum")String portnum);
	
	public List<Managers> loadManagersByGroupID(@Param("groupID")int groupID);
	
	public void saveManagers(@Param("manager")Managers manager);
	
	public void updateManagers(@Param("manager")Managers manager);
	
	public Managers loadManagerByID(@Param("id")int id);
	
}
