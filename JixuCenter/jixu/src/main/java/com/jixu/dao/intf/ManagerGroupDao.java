package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.ManagerGroup;

/**
 * @ClassName 		ManagerGroupDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface ManagerGroupDao extends CommFuncDAO{
	
	public ManagerGroup loadGroupNameByPortName(@Param("portID")String portID);
	
	public void saveManagerGroup(@Param("managerGroup")ManagerGroup managerGroup);
	
	public List<ManagerGroup> loadManagerGroupList();
}
