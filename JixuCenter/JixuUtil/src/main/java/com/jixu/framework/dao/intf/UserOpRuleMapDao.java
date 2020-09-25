package com.jixu.framework.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.common.pojo.UserOpRuleMap;

/**
 * @ClassName 		UserOpRuleMapDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface UserOpRuleMapDao extends CommFuncDAO{
	
	public List<UserOpRuleMap> loadUserRoles(@Param("userid")int userid);
	
	/**
	 *删除该用户的流程操作权限
	 * @param userID
	 */
	public void deleteUserOpRole(@Param("userID")int userID);

}
