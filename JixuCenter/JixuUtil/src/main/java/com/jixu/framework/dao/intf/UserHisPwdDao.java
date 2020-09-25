package com.jixu.framework.dao.intf;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.jixu.common.pojo.UserHisPwd;
import com.jixu.common.util.Pages;

/**
 * @ClassName 		UserHisPwdDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface UserHisPwdDao extends CommFuncDAO{
	
	//获取修改的历史密码并分页
	public Pages<UserHisPwd> getUpdatePwdList(@Param("pages")Pages<UserHisPwd> pages, @Param("userid") int userid);
	
	public Page<UserHisPwd> getPageHelperUpdatePwdList(@Param("pages")Page<UserHisPwd> page,@Param("userid") int userid);

}
